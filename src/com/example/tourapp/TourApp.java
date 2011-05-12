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
/**
 * Beginning activity for the tour app.  This activity creates the database from the text file, generates
 * DivisionObject 's and PlaceObject 's using data from the database, and adds those objects to two ArrayLists.
 * These lists are passed along to each activity through intents so the data can be called from anywhere.
 * @author Fatima Padojino
 *
 */
public class TourApp extends Activity {
	//create database from text file
	private Database database = new Database(this, "database.txt", "fullDatabase.db");
	//name of table that holds place info in the database
	private static String TABLE_NAME1 = "Place";
	//name of table that holds division info in the database
	private static String TABLE_NAME2 = "Division";
	//names of fields in the place table and division table- will be used when creating the cursor over the table in the databases
	private static String[] PLACEFROM = {"_id", "name", "lon", "lat", "description", "directionsFromPrevious", "directionsToNext", "imageName"};
	private static String[] DIVISIONFROM = {"_id", "name", "description", "imageName", "phone", "email", "website","building"};
	//ArrayLists to hold Division and Place data objects - empty at first
	private ArrayList<DivisionObject> mListOfDivisions;
	private ArrayList<PlaceObject> mListOfPlaces;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Cursor placeCursor = getDBContents(TABLE_NAME1, PLACEFROM);
		final Cursor divisionCursor = getDBContents(TABLE_NAME2, DIVISIONFROM);
		int numOfDivisions = divisionCursor.getCount();
		int numOfPlaces = placeCursor.getCount();
		mListOfDivisions = new ArrayList<DivisionObject>(numOfDivisions);
		mListOfPlaces = new ArrayList<PlaceObject>(numOfPlaces);
		
		//use cursor to add DivisionObjects to ArrayList
		while(divisionCursor.moveToNext()){
			int ID = divisionCursor.getInt(0);
			String name = divisionCursor.getString(1);
			String description = divisionCursor.getString(2);
			String imageName = divisionCursor.getString(3);
			String phone = divisionCursor.getString(4);
			String email = divisionCursor.getString(5);
			String website = divisionCursor.getString(6);
			String building = divisionCursor.getString(7);
			DivisionObject objectToAdd = new DivisionObject(ID, name, description, imageName, phone, email, website, building);
			mListOfDivisions.add(objectToAdd);
		}//end while loop for iterating through the cursor
		
		//use cursor to add PlaceObjects to ArrayList
		while(placeCursor.moveToNext()){
			int ID = placeCursor.getInt(0);
			String name = placeCursor.getString(1);
			double lon = placeCursor.getDouble(2);
			double lat = placeCursor.getDouble(3);
			String description = placeCursor.getString(4);
			String dirFromPrev = placeCursor.getString(5);
			String dirToNext = placeCursor.getString(6);
			String imageName = placeCursor.getString(7);
			PlaceObject objectToAdd = new PlaceObject(ID, name, lon, lat, description, dirFromPrev, dirToNext, imageName);
			mListOfPlaces.add(objectToAdd);
		}//end while loop for iterating through the cursor

		Button onLocation = (Button) findViewById(R.id.button_on_location);
		onLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OnLocationMenu.class);
				intent.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", mListOfDivisions);
				intent.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", mListOfPlaces);
				startActivity(intent);			
			}
		});	

		Button offLocation = (Button) findViewById(R.id.button_off_location);
		offLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OffLocationMenu.class
				Intent intent = new Intent(TourApp.this, OffLocationMenu.class);
				intent.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", mListOfDivisions);
				intent.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", mListOfPlaces);
				startActivity(intent);			
			}
		});	
	}
	
	//creates a cursor over a specific table in the database
	private Cursor getDBContents(String tablename, String[] from){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(tablename, from, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getDBContents method
}