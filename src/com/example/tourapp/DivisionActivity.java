package com.example.tourapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DivisionActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionactivity);
		Database database = new Database(this);
		SQLiteDatabase db = database.getDatabase();
		
		int index = 2;
		String object = database.place(db, "idPlace", index) + " " + database.place(db, "name", index) + " " + database.place(db, "lon", index) + " " +database.place(db, "lat", index)+ " " + database.place(db, "description", index)+ " " +database.place(db, "directionsFromPrevious", index)+ " " +database.place(db, "directionsToNext", index);
		
		TextView textView = (TextView) findViewById(R.id.text_view);
		textView.setText(object);
		
		
		
		
	}
	

}
