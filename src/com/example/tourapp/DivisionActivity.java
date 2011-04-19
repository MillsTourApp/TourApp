package com.example.tourapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DivisionActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionactivity);
		Database database = new Database(this);
		SQLiteDatabase db = database.getReadableDatabase();


		int index = 2;
		String object = place(db, "idPlace", index) + " " + place(db, "name", index) + " " + place(db, "lon", index) + " " +place(db, "lat", index)+ " " + place(db, "description", index)+ " " +place(db, "directionsFromPrevious", index)+ " " +place(db, "directionsToNext", index);
		
		TextView textView = (TextView) findViewById(R.id.text_view);
		textView.setText(object);
	}
	
	/**
	 * creates a string with all (string) information associated with 1 location object
	 * @param db the SQLite database that contains the information
	 * @param item the particular item you want to get out, for example address or zip code
	 * @param index the index of your item, for example index of Museum Tour or index of Mills College
	 * @return a string with information associated with index object
	 */
	private String place(SQLiteDatabase db, String item, int index) {
		Cursor myCursor = db.rawQuery("select " + item + " from Place", null);
		String[] myStringArray = makeString(myCursor);
		return findDataAtIndex(index, myStringArray);
	} //loction

	/**
	 * creates an array with all info of 1 type, for example address or zip
	 * @param myCursor the cursor of the entry you go over
	 * @return an array with all enries of the same sort
	 */
	private String[] makeString(Cursor myCursor) {
		String sqlString = "";
		while(myCursor.moveToNext()) {
			sqlString = sqlString + myCursor.getString(0) + "::";
		} //while

		String[] myStringArray = sqlString.split("::", 15);

		return myStringArray;
	} //makeString

	/**
	 * finds an entry of a specific object at a specific index
	 * @param index the index of the item sought
	 * @param myArray the array searched through
	 * @return the entry at the index
	 */
	private String findDataAtIndex(int index, String[] myArray) {
		String myString = "";
		if(myArray[index]==null) {
			myString = myString+ "This index is null";
		}else{
			myString = myString+myArray[index];
		}
		return myString;
	} //findDataAtIndex

	

}
