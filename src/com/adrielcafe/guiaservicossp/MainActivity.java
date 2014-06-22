package com.adrielcafe.guiaservicossp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

import com.google.gson.reflect.TypeToken;

public class MainActivity extends ListActivity {
	private Map<String, ArrayList<Service>> db;

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
		i.putExtra(Util.EXTRA_TITLE, title);
		i.putExtra(Util.EXTRA_DB, db.get(title));
		startActivity(i);
	}

	private void setup(){
		ArrayList<Service> dbAdministracaoPublica = Util.GSON.fromJson(Util.loadJSON(this, "db/administracao-publica.json"), new TypeToken<ArrayList<Service>>() {}.getType());
		
		db = new TreeMap<String, ArrayList<Service>>();
		db.put("Administração Pública 1", dbAdministracaoPublica);
		db.put("Administração Pública 2", dbAdministracaoPublica);
		db.put("Administração Pública 3", dbAdministracaoPublica);
		db.put("Administração Pública 4", dbAdministracaoPublica);
		db.put("Administração Pública 5", dbAdministracaoPublica);
		
		for(ArrayList<Service> services : db.values())
			Collections.sort(services, new Comparator<Service>() {
		        @Override
		        public int compare(Service  s1, Service  s2) {
		        	return  s1.title.compareTo(s2.title);
		        }
		    });
		
		setListAdapter(new ListAdapter(this, new ArrayList<String>(db.keySet())));
	}
}