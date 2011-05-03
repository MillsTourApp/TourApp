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

public class VirtualTour extends Activity{
	public ArrayList<PlaceObject> listOfPlaces;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.virtualtourgrid);
		
		Bundle extras = getIntent().getExtras();
		listOfPlaces = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");
	
		GridView gridview = (GridView) findViewById(R.id.virtualtourgrid);
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setOnItemClickListener(new OnItemClickListener() {
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
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return listOfPlaces.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(findImageName(position));
			return imageView;
		}
	}

	public PlaceObject findObject(int position){
		for (PlaceObject obj:listOfPlaces){
			if(obj.getId()-1 == position){
				return obj;
			}
		}
		return null;
	}
	
	public int findImageName(int position){
		for (PlaceObject obj:listOfPlaces){
			if(obj.getId()-1 == position){
				return findImage(obj);
			}
		}
		return 0; //image of that position was not found
	}
	
	public int findImage(PlaceObject obj){
		int resId = this.getResources().getIdentifier(obj.getImageName(), "drawable", "com.example.tourapp");
		return resId;
	}
}