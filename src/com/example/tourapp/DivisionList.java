package com.example.tourapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DivisionList extends ListActivity {
	private DivisionDatabase database;
	//private static String TABLE_NAME = "Place";
	private static String[] FROM = {"name", "description", "directionsFromPrevious", "directionsToNext"};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.divisionlist);
		database = new DivisionDatabase(this);
		Cursor cursor = getDivisions();
		final String[] arrayOfDivisionNames = makeListOfDivisionNames(cursor, 0);
		final String[] arrayOfDivisionDescriptions = makeListOfDivisionNames(cursor,1);
		final String[] arrayOfDivisionDirFromPrev = makeListOfDivisionNames(cursor,2);
		final String[] arrayOfDivisionDirToNext = makeListOfDivisionNames(cursor,3);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.divisionlist, R.id.divisiontextview, arrayOfDivisionNames));
		
		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				intent.putExtra("dName", (arrayOfDivisionNames[position]));
				intent.putExtra("description", (arrayOfDivisionDescriptions[position]));
				intent.putExtra("dDirFromPrev", (arrayOfDivisionDirFromPrev[position]));
				intent.putExtra("dDirToNext", (arrayOfDivisionDirToNext[position]));
				startActivity(intent);
			}//end onItemClick
		});
	}//end onCreate method

	private Cursor getDivisions(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query("Place", FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method
	
	private String[] makeListOfDivisionNames(Cursor cursor, int cursorColumn){
		int numOfDivisions = cursor.getCount();
		String[] arrayFromCursor = new String[numOfDivisions];
		while (cursor.moveToNext()){
			arrayFromCursor[cursor.getPosition()] = cursor.getString(cursorColumn);
		}//end while
		cursor.moveToPosition(-1);
		return arrayFromCursor;
	}//end makeListOfDivisionNames
}//
