package com.example.tourapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * The start of the virtual tour class, creating a grid view, extending an activity
 * @author nwashington
 * @author sthompson
 *
 */
public class VirtualTour extends Activity{
	/**
	 * recognizing the array list called PlaceObject, naming it as listOfPlaces
	 */
	public ArrayList<PlaceObject> listOfPlaces;
	/**
	 * onCreate Method, creating the virtual tour grid
	 *@param savedInstanceState
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.virtualtourgrid);

		//Obtaining data from intent passed in last activity
		Bundle extras = getIntent().getExtras();
		listOfPlaces = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");

		//the grid is now captured from layout with findViewById
		GridView gridview = (GridView) findViewById(R.id.virtualtourgrid);
		gridview.setAdapter(new ImageAdapter(this));//sets custom adapter as the source for all images provided
		gridview.setOnItemClickListener(new OnItemClickListener() {//to do something when item is clicked.
			/**
			 * When the item is clicked, this takes you to the PlaceObject class which pulls the specified data(name, descrpt, imageName from the database, to get the correct info
			 * @param position references what is clicked
			 * @param index references what is clicked
			 * @param id references what is clicked
			 * @param view references what is clicked
			 * @param parent references what is clicked
			 */
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Object object = findObject(position);
				PlaceObject placeObject = (PlaceObject)object;
				String name = placeObject.getName();
				String description = placeObject.getDescription();
				String imageName = placeObject.getImageName();
				Intent intent = new Intent().setClass(VirtualTour.this, Place.class);
				intent.putExtra("name", name);
				intent.putExtra("description", description);
				intent.putExtra("imageName", imageName);
				startActivity(intent);
			}//onItemClick
		});
	}//onCreate

	/**
	 * implements required methods from BaseAdapter
	 */
	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		/**
		 * referring to the ImageAdapter as mContext
		 * @param c 
		 */
		public ImageAdapter(Context c) {
			mContext = c;
		}
		/**
		 * @return the size of the places
		 */
		public int getCount() {
			return listOfPlaces.size();
		}
		/**
		 * returns the actual object at the specified position in the adapter
		 * @return null
		 */
		public Object getItem(int position) {
			return null;
		}
		/**
		 * returns the row id of the current item
		 * @return not needed at this time.
		 */
		public long getItemId(int position) {
			return 0;
		}

		/**
		 *creates a new ImageView for each item referenced by the Adapter
		 *@param position of the image
		 *@param convertView 
		 *@param parent
		 *@return imageView going to show the image
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}//else
			imageView.setImageResource(findImageName(position));
			return imageView;
		}//getView
	}//Image Adapter
	
	/**
	 * this will find the specific object we that has been clicked
	 * @param position
	 * @return the object
	 */
	public PlaceObject findObject(int position){
		for (PlaceObject obj:listOfPlaces){
			if(obj.getId()-1 == position){
				return obj;
			}
		}
		return null;
	}//findObject

	/**
	 * Finds the image associated with the index
	 * @param position index of the desired picture
	 * @return the image to be shown
	 */
	private int findImageName(int position){
		for (PlaceObject obj:listOfPlaces){
			if(obj.getId()-1 == position){
				return findImage(obj);
			}//if
		}//for
		return 0; //image of that position was not found
	}//public findImageName

	//Loads an image from the image's name
	private int findImage(PlaceObject obj){
		int resId = this.getResources().getIdentifier(obj.getImageName(), "drawable", "com.example.tourapp");
		return resId;
	}//private findImageName
}//VirtualTour