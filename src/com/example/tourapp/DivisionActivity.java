package com.example.tourapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DivisionActivity extends Activity {
	Database database;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionactivity);
		database = new Database(this);
		
		Cursor cursor = getPlaces();
		StringBuilder places = stringOfPlaces(cursor);
		
		TextView textview = (TextView) findViewById(R.id.text_view);
		textview.setText(places);
	}
	
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"idPlace", "name"};
	
	
	private Cursor getPlaces(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private StringBuilder stringOfPlaces(Cursor cursor){
		StringBuilder builder = new StringBuilder("");
		
		while (cursor.moveToNext()){
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			builder.append(id).append("\n");
			builder.append(name).append("\n");
		}
		return builder;
		
	}
}
