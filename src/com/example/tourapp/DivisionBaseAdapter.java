package com.example.tourapp;
//http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DivisionBaseAdapter extends BaseAdapter{
	public static ArrayList<DivisionObject> listOfDivObjects;
	private LayoutInflater mInflater;
	
	public DivisionBaseAdapter (Context context, ArrayList<DivisionObject> list){
		listOfDivObjects = list;
		mInflater = LayoutInflater.from(context);
	}//end constructor

	@Override
	public int getCount() {
		return listOfDivObjects.size();
	}

	@Override
	public Object getItem(int position) {
		return listOfDivObjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  ViewHolder holder;
		  if (convertView == null) {
		   convertView = mInflater.inflate(R.layout.customdivisionlistrow, null);
		   holder = new ViewHolder();
		   holder.name = (TextView) convertView.findViewById(R.id.divisionlistname);
		   convertView.setTag(holder);
		  } else {
		   holder = (ViewHolder) convertView.getTag();
		  }
		  
		  holder.name.setText(listOfDivObjects.get(position).getName());

		  return convertView;
	}
	
	 static class ViewHolder {
		  TextView name;
		 }
	

}
