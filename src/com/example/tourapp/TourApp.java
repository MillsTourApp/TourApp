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
	
/*	public Database database = new Database(this, "division.txt", "fullDivision.db");
	public static ArrayList<DivisionObject> listOfDivisions;
	public ArrayList<DivisionObject> listOfPlaces;
	private static String TABLE_NAME1 = "Place";
	private static String TABLE_NAME2 = "Division";
	private static String[] PLACEFROM = {"_id", "name", "description", "directionsFromPrevious", "imageName"};
	private static String[] DIVISIONFROM = {"_id", "name", "description", "directionsFromPrevious", "imageName"};
*/	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//final Cursor placeCursor = getDBContents(TABLE_NAME1, PLACEFROM);
		//final Cursor divisionCursor = getDBContents(TABLE_NAME2, DIVISIONFROM);
		
		//use cursor to add DivisionObject's to ArrayList
/*		while(divisionCursor.moveToNext()){
			int ID = divisionCursor.getInt(0);
			String name = divisionCursor.getString(1);
			String description = divisionCursor.getString(2);
			String dirFromPrev = divisionCursor.getString(3);
			String imageName = divisionCursor.getString(4);
			DivisionObject objectToAdd = new DivisionObject(ID, name, description, dirFromPrev, imageName);
			listOfDivisions.add(objectToAdd);
		}//end while loop for iterating through the cursor
*/
		Button onLocation = (Button) findViewById(R.id.button_on_location);
		onLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OnLocationMenu.class);
				startActivity(intent);			
			}
		});	

		Button offLocation = (Button) findViewById(R.id.button_off_location);
		offLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OffLocationMenu.class);
				startActivity(intent);			
			}
		});	
	}
/*	private Cursor getDBContents(String tablename, String[] from){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(tablename, from, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method
	
	public static ArrayList<DivisionObject> getDivisionArrayList(){
		return listOfDivisions;
	}*/
}