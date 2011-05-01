package com.example.tourapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

public class TourApp extends Activity {
	
	public Database database = new Database(this, "division.txt", "fullDivision.db");
	private static String TABLE_NAME1 = "Place";
	private static String TABLE_NAME2 = "Division";
	private static String[] PLACEFROM = {"_id", "name", "lon", "lat", "description", "directionsFromPrevious", "directionsToNext", "imageName"};
	private static String[] DIVISIONFROM = {"_id", "name","lon", "lat", "description", "imageName"};

	public ArrayList<DivisionObject> listOfDivisions;
	public ArrayList<PlaceObject> listOfPlaces;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Cursor placeCursor = getDBContents(TABLE_NAME1, PLACEFROM);
		final Cursor divisionCursor = getDBContents(TABLE_NAME2, DIVISIONFROM);
		int numOfDivisions = divisionCursor.getCount();
		int numOfPlaces = placeCursor.getCount();
		listOfDivisions = new ArrayList<DivisionObject>(numOfDivisions);
		listOfPlaces = new ArrayList<PlaceObject>(numOfPlaces);
		
		//use cursor to add DivisionObjects to ArrayList
		while(divisionCursor.moveToNext()){
			int ID = divisionCursor.getInt(0);
			String name = divisionCursor.getString(1);
			String lon = divisionCursor.getString(2);
			String lat = divisionCursor.getString(3);
			String description = divisionCursor.getString(4);
			String imageName = divisionCursor.getString(5);
			DivisionObject objectToAdd = new DivisionObject(ID, name, lon, lat, description, imageName);
			listOfDivisions.add(objectToAdd);
		}//end while loop for iterating through the cursor
		
		//use cursor to add PlaceObjects to ArrayList
		while(placeCursor.moveToNext()){
			int ID = placeCursor.getInt(0);
			String name = placeCursor.getString(1);
			String lon = placeCursor.getString(2);
			String lat = placeCursor.getString(3);
			String description = placeCursor.getString(4);
			String dirFromPrev = placeCursor.getString(5);
			String dirToNext = placeCursor.getString(6);
			String imageName = placeCursor.getString(7);
			PlaceObject objectToAdd = new PlaceObject(ID, name, lon, lat, description, dirFromPrev, dirToNext, imageName);
			listOfPlaces.add(objectToAdd);
		}//end while loop for iterating through the cursor

		Button onLocation = (Button) findViewById(R.id.button_on_location);
		onLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OnLocationMenu.class);
				intent.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", listOfDivisions);
				intent.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", listOfPlaces);
				startActivity(intent);			
			}
		});	

		Button offLocation = (Button) findViewById(R.id.button_off_location);
		offLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OffLocationMenu.class
				Intent intent = new Intent(TourApp.this, OffLocationMenu.class);
				intent.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", listOfDivisions);
				intent.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", listOfPlaces);
				startActivity(intent);			
			}
		});	
	}
	
	
	private Cursor getDBContents(String tablename, String[] from){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(tablename, from, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getDBContents method
}