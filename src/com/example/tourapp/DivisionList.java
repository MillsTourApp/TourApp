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
	

	//begone, ye Magic Numbers! (Anna, 04.30.2011, 11:15AM)
	private static final int MAX_NUM_DIVISIONOBJECTS = 30;
	private static final int ID_LIST = 0;
	private static final int NAME_LIST = 1;
	private static final int DESC_LIST = 2;
	private static final int DIR_LIST = 3;
	private static final int IMG_LIST = 4;
	private static final int LON_LIST = 5;
	private static final int LAT_LIST = 6;
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		database = new Database(this, "division.txt", "division.db");
		final Cursor cursor = getDivisions();
		listOfDivisionObjects = new ArrayList<DivisionObject>(MAX_NUM_DIVISIONOBJECTS);

		//use cursor to add DivisionObject's to ArrayList
		while(cursor.moveToNext()){
			int ID = cursor.getInt(ID_LIST);
			String name = cursor.getString(NAME_LIST);
			String description = cursor.getString(DESC_LIST);
			String dirFromPrev = cursor.getString(DIR_LIST);
			String imageName = cursor.getString(IMG_LIST);
			String lon = cursor.getString(LON_LIST);
			String lat = cursor.getString(LAT_LIST);
			DivisionObject objectToAdd = new DivisionObject(ID, name, lon, lat, description, dirFromPrev, imageName);
			listOfDivisionObjects.add(objectToAdd);
		} //end while loop for iterating through the cursor
		
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
