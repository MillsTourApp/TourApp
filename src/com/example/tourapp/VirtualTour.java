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
import android.widget.Toast;

public class VirtualTour extends Activity{
	private ArrayList<DivisionObject> manualListOfDivisionObjects;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manualListOfDivisionObjects = new ArrayList<DivisionObject>(30);
		DivisionObject obj1 = new DivisionObject(0, "Mills Hall", "-122.18212", "37.779496", "This is a description of Mills Hall.","Start your tour here!", "sample_0");
		manualListOfDivisionObjects.add(obj1);
		DivisionObject obj2 = new DivisionObject(1, "Natural Sciences Building", "-122.180666", "37.780143", "This is a description of Natural Sciences Building","Directions from Mills Hall: These are directions to the building before the Natural Sciences Building", "sample_1");
		manualListOfDivisionObjects.add(obj2);
		DivisionObject obj3 = new DivisionObject(2, "Carnegie Hall", "-122.181645", "37.778995", "This is a description of Carnegie Hall","Directions from NSB: These are directions from the building before Carnegie Hall", "sample_2");
		manualListOfDivisionObjects.add(obj3);
		DivisionObject obj4 = new DivisionObject(3, "CPM", "-122.182791", "37.778726", "This is a description of CPM","From Carnegie Hall: These are directions from the building before CPM", "sample_3");
		manualListOfDivisionObjects.add(obj4);
		setContentView(R.layout.virtualtourgrid);

		GridView gridview = (GridView) findViewById(R.id.virtualtourgrid);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				//Toast.makeText(VirtualTour.this, "" + position, Toast.LENGTH_SHORT).show();
				Object object = findObject(position);
				DivisionObject divisionObject = (DivisionObject)object;
				String name = divisionObject.getName();
				String description = divisionObject.getDescription();
				String imageName = divisionObject.getImageName();
				Intent intent = new Intent().setClass(VirtualTour.this, Division.class);
				intent.putExtra("dName", name);
				intent.putExtra("description", description);
				intent.putExtra("dImageName", imageName);
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
			return manualListOfDivisionObjects.size();
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
		//private Integer[] 
		// references to our images
	}
	public DivisionObject findObject(int position){
		for (DivisionObject obj:manualListOfDivisionObjects){
			if(obj.getId() == position){
				return obj;
			}
		}
		return null;
	}
	
	public int findImageName(int position){
		for (DivisionObject obj:manualListOfDivisionObjects){
			if(obj.getId() == position){
				return findImage(obj);
			}
		}
		return 0; //image of that position was not found
	}
	
	public int findImage(DivisionObject obj){
		int resId = this.getResources().getIdentifier(obj.getImageName(), "drawable", "com.example.tourapp");
		return resId;
	}
}
