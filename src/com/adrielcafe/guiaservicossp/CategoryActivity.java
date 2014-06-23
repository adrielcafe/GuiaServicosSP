package com.adrielcafe.guiaservicossp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.adrielcafe.guiaservicossp.model.Category;
import com.adrielcafe.guiaservicossp.model.Service;

public class CategoryActivity extends ListActivity {

	private Category category;
	private ArrayList<String> serviceTitles;
	
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
		
		Service service = category.services.get(position);
		Intent i = new Intent(this, ServiceActivity.class);
		i.putExtra(Util.EXTRA_TITLE, category.title);
		i.putExtra(Util.EXTRA_SERVICE, service);
		startActivity(i);
	}
	
	private void setup(Intent intent){
		category = (Category) intent.getSerializableExtra(Util.EXTRA_CATEGORY);
		serviceTitles = new ArrayList<String>();
		
		for(Service service : category.services)
			serviceTitles.add(service.title);
		
		setTitle(category.title);
		setListAdapter(new ListAdapter(this, serviceTitles));
	}
}