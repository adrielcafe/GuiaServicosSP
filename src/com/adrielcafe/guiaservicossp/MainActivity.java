package com.adrielcafe.guiaservicossp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adrielcafe.guiaservicossp.model.Category;
import com.adrielcafe.guiaservicossp.model.Service;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends ListActivity {
	private List<Category> db;
	private List<String> categoryTitles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
		setContentView(R.layout.activity_main);
		
		setup();
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Util.showDialog(this, getString(R.string.about), Util.ABOUT_MESSAGE);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String title = getListAdapter().getItem(position).toString();
		Intent i = new Intent(this, CategoryActivity.class);
		i.putExtra(Util.EXTRA_CATEGORY, db.get(position));
		startActivity(i);
	}

	private void setup(){
		db = Util.GSON.fromJson(Util.loadJSON(this, "db.json"), new TypeToken<List<Category>>() {}.getType());
		categoryTitles = new ArrayList<String>();
		
		Collections.sort(db, new Comparator<Category>() {
	        @Override
	        public int compare(Category  c1, Category  c2) {
	        	return  Util.SLUG.slugify(c1.title).compareTo(Util.SLUG.slugify(c2.title));
	        }
	    });
		
		for(Category category : db){
			categoryTitles.add(category.title);
			Collections.sort(category.services, new Comparator<Service>() {
		        @Override
		        public int compare(Service  s1, Service  s2) {
		        	return  Util.SLUG.slugify(s1.title).compareTo(Util.SLUG.slugify(s2.title));
		        }
		    });
		}
		
		setListAdapter(new ListAdapter(this, new ArrayList<String>(categoryTitles)));
	}
}