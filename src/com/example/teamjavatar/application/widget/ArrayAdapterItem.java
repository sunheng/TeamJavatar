package com.example.teamjavatar.application.widget;

import com.example.teamjavatar.domain.ListItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ArrayAdapterItem<T extends ListItem> extends ArrayAdapter<T> {
	
	private Context context;
	private int resourceID;
	private T[] data;

	public ArrayAdapterItem(Context context, int resourceID, T[] objects) {
		super(context, resourceID, objects);
		this.resourceID = resourceID;
		this.context = context;
		this.data = objects;
		//no way to access superclass fields???
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resourceID, parent, false);
		}
		return parent;
	}

}
