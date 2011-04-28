package com.example.tourapp;
/*Totally taking code from this guy:
 * http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DivisionList extends ListActivity {
	private Database database;
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"_id", "name", "description", "directionsFromPrevious", "directionsToNext"};
	ArrayList<DivisionObject> listOfDivisionObjects = new ArrayList<DivisionObject>(30);
	private Runnable viewDivisions;
	private DivisonObjectsAdapter mDivisionObjectsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		database = new Database(this, "division.txt", "division.db");
		final Cursor cursor = getDivisions();

		/*		
		int numOfDivisions = listOfDivisionObjects.size();
		int cursorLength = cursor.getCount();

		TextView tv = (TextView) findViewById(R.id.divisiontextview);

		//tv.setText(objectToAdd.getName());

		String printNameOfObject = "";

		for (DivisionObject division : listOfDivisionObjects){
			printNameOfObject+= (division.getName()) + "\n";
		}

		tv.setText(printNameOfObject);
		 */

		setListAdapter(new ListAdapter(this, R.id.divisiontextview, listOfDivisionObjects));

		/*		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		 */
		/*		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				//intent.putExtra("dName", (arrayOfDivisionNames[position]));
				//intent.putExtra("description", (arrayOfDivisionDescriptions[position]));
				//intent.putExtra("dDirFromPrev", (arrayOfDivisionDirFromPrev[position]));
				//intent.putExtra("dDirToNext", (arrayOfDivisionDirToNext[position]));
				startActivity(intent);
			}//end onItemClick
		});*/
	}//end onCreate method
	
	private Runnable returnRes = new Runnable(){
		@Override
		public void run(){
			
		}
	}

	private void addDivisions(Cursor cursor){
		while(cursor.moveToNext()){
			int ID = cursor.getInt(0);
			String name = cursor.getString(1);
			String description = cursor.getString(2);
			String dirFromPrev = cursor.getString(3);
			DivisionObject objectToAdd = new DivisionObject(ID, name, description, dirFromPrev);
			listOfDivisionObjects.add(objectToAdd);
		}//end while loop for iterating through the cursor
	}


	private Cursor getDivisions(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method


	private class DivisonObjectsAdapter extends ArrayAdapter<DivisionObject>{
		private ArrayList<DivisionObject> divisions;

		public DivisonObjectsAdapter(Context context, int textViewResourceId,
				ArrayList<DivisionObject> divisions) {
			super(context, textViewResourceId, divisions);
			this.divisions = divisions;
		}//

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View v = convertView;

			DivisionObject division = divisions.get(position);
			TextView tv = (TextView) findViewById(R.id.divisiontextview);
			tv.setText(division.getName());
			return v;
		}//end getView
	}//end class DivisionObjectsAdapter

	/*	private String[] makeListOfDivisionNames(Cursor cursor, int cursorColumn){
	int numOfDivisions = cursor.getCount();
	String[] arrayFromCursor = new String[numOfDivisions];
	while (cursor.moveToNext()){
		arrayFromCursor[cursor.getPosition()] = cursor.getString(cursorColumn);
	}//end while
	cursor.moveToPosition(-1);
	return arrayFromCursor;
}//end makeListOfDivisionNames
	 */	
}//end DivisonList