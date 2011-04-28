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
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DivisionList<listOfDivisionObjects> extends Activity {
	private Database database;
	private static String TABLE_NAME = "Place";
	private static String[] FROM = {"_id", "name", "description", "directionsFromPrevious", "directionsToNext"};
	private ArrayList<DivisionObject> listOfDivisionObjects;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		database = new Database(this, "division.txt", "division.db");
		final Cursor cursor = getDivisions();
		listOfDivisionObjects = new ArrayList<DivisionObject>(30);

		//use cursor to add DivisionObject's to ArrayList
		while(cursor.moveToNext()){
			int ID = cursor.getInt(0);
			String name = cursor.getString(1);
			String description = cursor.getString(2);
			String dirFromPrev = cursor.getString(3);
			DivisionObject objectToAdd = new DivisionObject(ID, name, description, dirFromPrev);
			listOfDivisionObjects.add(objectToAdd);
		}//end while loop for iterating through the cursor
		
		//load list of names - for now...
		String divisionNames = "";
		TextView tv = (TextView) findViewById(R.id.divisiontextview);
		
		for (DivisionObject divisionObj:listOfDivisionObjects){
			divisionNames+= divisionObj.getName() +"\n";
		}
		
		tv.setText(divisionNames);
		
		//setListAdapter(new DivisionListAdapter(this));
		
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

	private Cursor getDivisions(){
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}//end getPlaces method
	
/*	private class DivisionListAdapter extends BaseAdapter{
		public DivisionListAdapter (Context context){
			mContext = context;
		}
		
		public int getCount(){
			return listOfDivisionObjects.size();
		}
		
		public Object getItem(int position){
			return position;
		}
		
		public int getItemID(int position){
			return position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			DivisionView dv;
			if (convertView == null){
				sv = new DivisionView (mContext, listOfDivisonObjects.)
			}
			return dv;
		}
		
		private Context mContext;
	}
	
	private class DivisionView extends LinearLayout{
		
	}*/


}//end DivisonList