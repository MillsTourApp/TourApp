package com.example.tourapp;
//Fatima used the website below for assistance in customizing the clickable ListView in DivisionList.java
//http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//custom BaseAdapter for populating DivisionList
/**
 * custom BaseAdapter for populating DivisionList
 * @see DivisionList
 */
public class DivisionBaseAdapter extends BaseAdapter{
	//ArrayList of objects of class DivisionObject with which to populate the ListView
	/**ArrayList of objects of class DivisionObject with which to populate the ListView*/
	private static ArrayList<DivisionObject> mListOfDivObjects;
	//LayoutInflator to build Views from the customdivisionlistrow.xml file
	/**Used to build Views from the customdivisionlistrow.xml file*/
	private LayoutInflater mInflater;

	//constructor
	/**
	 * Constructor
	 * @param context context from which to obtain a LayoutInflater
	 * @param list ArrayList of objects of class DivisionObject, whose content will populate the ListView
	 * */
	public DivisionBaseAdapter (Context context, ArrayList<DivisionObject> list){
		mListOfDivObjects = list;
		mInflater = LayoutInflater.from(context);
	}//end constructor
	//getCount method
	/**
	 * method to override the default getCount method of the class BaseAdapter
	 * @return size of the ArrayList listOfDivObjects
	 * @see listOfDivObjects
	 */
	@Override
	public int getCount() {
		return mListOfDivObjects.size();
	}//end getCount method
	//getItem method
	/**
	 * method to override the default getItem method of the class BaseAdapter
	 * @param position position of the desired object in the ArrayList
	 * @return an object from the ArrayList listOfDivObjects 
	 */
	@Override
	public Object getItem(int position) {
		return mListOfDivObjects.get(position);
	}//end getItem
	//getItemId method
	/**
	 * method to override the default getItemId method of the class BaseAdapter
	 * @param position position of the desired object in the ArrayList
	 * @return the position of the desired object in the ArrayList
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}//end getItemId method
	//getView method
	/**
	 * custom getView method to override the method in the Adapter class which gets the view
	 * that will display the data at the specified position in the ArrayList
	 * @param position position of the desired object in the ArrayList
	 * @param convertView the old view to reuse, if possible
	 * @param parent the view with which the view will eventually be attached
	 * @return View a view corresponding to the data at the specified position
	 */
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
		}//end if-else statement
		holder.name.setText(mListOfDivObjects.get(position).getName());
		return convertView;
	}//end getView method
	//static class used to hold all the views in the custom row view
	//currently only holds a single textview, but could be expanded, maybe to hold an image and a short description
	/**
	 * Holds all the views in the custom row view
	 */
	static class ViewHolder {
		TextView name;
	}//end class ViewHolder


}
