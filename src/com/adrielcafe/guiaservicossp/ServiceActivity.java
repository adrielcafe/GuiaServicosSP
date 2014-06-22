package com.adrielcafe.guiaservicossp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ServiceActivity extends Activity {

	private String categoryTitle;
	private Service service;

	private TextView titleView;
	private TextView descriptionView;
	private TextView urlView;
	private TextView emailView;
	private TextView phoneView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_service);
		
		setup(getIntent());
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return false;
	}

	private void setup(Intent intent){
		categoryTitle = intent.getStringExtra(Util.EXTRA_TITLE);
		service = (Service) intent.getSerializableExtra(Util.EXTRA_SERVICE);
		
		titleView = (TextView) findViewById(R.id.title);
		descriptionView = (TextView) findViewById(R.id.description);
		urlView = (TextView) findViewById(R.id.url);
		emailView = (TextView) findViewById(R.id.email);
		phoneView = (TextView) findViewById(R.id.phone);
		
		titleView.setText(service.title);
		descriptionView.setText(service.description);
		
		if(service.url == null || service.url.isEmpty())
			urlView.setVisibility(View.GONE);
		else
			urlView.setText(service.url);
		
		if(service.email == null || service.email.isEmpty())
			emailView.setVisibility(View.GONE);
		else
			emailView.setText(service.email);
		
		if(service.phone == null || service.phone.isEmpty())
			phoneView.setVisibility(View.GONE);
		else
			phoneView.setText(service.phone);
		
		setTitle(categoryTitle); 
	}
}