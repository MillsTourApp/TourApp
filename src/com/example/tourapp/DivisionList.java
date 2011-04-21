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
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"idPlace", "name"};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.divisionlist);

		database = new DivisionDatabase(this);
		Cursor cursor = getPlaces();
		final String[] arrayOfPlaceNames = makeListOfPlaceNames(cursor);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.divisionlist, R.id.divisiontextview, arrayOfPlaceNames));

		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {

			String divisionName;
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				divisionName = lv.getItemAtPosition(position).toString();
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				intent.putExtra("dName", divisionName);
				startActivity(intent);
			}//end onItemClick
		});
	}//end onCreate method

	private Cursor getPlaces(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method

	private String[] makeListOfPlaceNames(Cursor cursor){
		int numOfPlaces = cursor.getCount();
		String[] placeNames = new String[numOfPlaces];
		int i = 0;
		while (cursor.moveToNext()){
			String name = cursor.getString(1);
			placeNames[i] = name;
			i++;
		}//end while
		return placeNames;
	}//end makeListOfPlaceNames

}//
