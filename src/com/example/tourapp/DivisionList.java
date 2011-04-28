package com.example.tourapp;
/*Totally taking code from this guy:
 * http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
 */

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DivisionList<listOfDivisionObjects> extends Activity {
	private Database database;
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"_id", "name", "description", "directionsFromPrevious", "imageName", "lon", "lat"};
	private ArrayList<DivisionObject> listOfDivisionObjects;// = TourApp.getDivisionArrayList();
	
	//04.28.2011, 4.55PM, Anna:
	//Created a new branch branch_to_merge in order to hopefully
	//make a change and then merge with master. I created it from  
	//the remote/master, my previous comment disappeared, and 
	//I added this one instead.


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		database = new Database(this, "division.txt", "division.db");
		final Cursor cursor = getDivisions();
		listOfDivisionObjects = new ArrayList<DivisionObject>(30);

		//use cursor to add DivisionObject's to ArrayList
		while(cursor.moveToNext()){
			int ID = cursor.getInt(0);
			String name = cursor.getString(1);
			String description = cursor.getString(2);
			String dirFromPrev = cursor.getString(3);
			String imageName = cursor.getString(4);
			String lon = cursor.getString(5);
			String lat = cursor.getString(6);
			DivisionObject objectToAdd = new DivisionObject(ID, name, lon, lat, description, dirFromPrev, imageName);
			listOfDivisionObjects.add(objectToAdd);
		}//end while loop for iterating through the cursor
		
		final ListView lv = (ListView) findViewById(R.id.divisionlistview);
		lv.setAdapter(new DivisionBaseAdapter(this, listOfDivisionObjects));

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Object object = lv.getItemAtPosition(position);
				DivisionObject divisionObject = (DivisionObject)object;
				int iden = divisionObject.getId();
				String name = divisionObject.getName();
				String description = divisionObject.getDescription();
				String dirFromPrev = divisionObject.getDirFromPrev();
				String imageName = divisionObject.getImageName();
				String lon = divisionObject.getLon();
				String lat = divisionObject.getLat();
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				intent.putExtra("dName", name);
				intent.putExtra("description", description);
				intent.putExtra("dDirFromPrev", dirFromPrev);
				intent.putExtra("dID", iden);
				intent.putExtra("dImageName", imageName);
				intent.putExtra("lon", lon);
				intent.putExtra("lat", lat);
				startActivity(intent);
			}//end onItemClick
		});
	}//end onCreate method

	private Cursor getDivisions(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method
}//end DivisonList