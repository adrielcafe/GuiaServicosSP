package com.adrielcafe.guiaservicossp;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryActivity extends ListActivity {

	private String title;
	private ArrayList<String> itemTitles;
	private ArrayList<Service> db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_category);

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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Service service = db.get(position);
		Intent i = new Intent(this, ServiceActivity.class);
		i.putExtra(Util.EXTRA_TITLE, title);
		i.putExtra(Util.EXTRA_SERVICE, service);
		startActivity(i);
	}
	
	private void setup(Intent intent){
		db = (ArrayList<Service>) intent.getSerializableExtra(Util.EXTRA_DB);
		title = intent.getStringExtra(Util.EXTRA_TITLE);
		itemTitles = new ArrayList<String>();
		
		for(Service service : db)
			itemTitles.add(service.title);
		
		setTitle(title);
		setListAdapter(new ListAdapter(this, itemTitles));
	}
}