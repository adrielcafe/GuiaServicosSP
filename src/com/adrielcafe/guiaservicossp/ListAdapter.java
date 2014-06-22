package com.adrielcafe.guiaservicossp;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
	
	public ListAdapter(Context context, List<String> objects) {
		super(context, R.layout.list_item, objects);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String item = getItem(position);
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title.setText(item);
		
		return convertView;
	}

	private class ViewHolder {
		TextView title;
	}
	
}