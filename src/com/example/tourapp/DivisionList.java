package com.example.tourapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DivisionList extends ListActivity {
	private Database database;
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"idPlace", "name"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new Database(this);
		Cursor cursor = getPlaces();
		//StringBuilder places = new StringBuilder ("");
		String[] arrayOfPlaceNames = makeListOfPlaceNames(cursor);

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayOfPlaceNames));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}//end onCreate method
	
	//
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
