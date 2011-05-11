package com.example.tourapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
//Activity class to display clickable list of Divisions
/**
 * Activity class to display clickable list of Divisions
 */
public class DivisionList<listOfDivisionObjects> extends Activity {
	//ArrayList of Divisions with which to populate the list
	/**
	 * ArrayList of divisions with which to populate the list
	 */
	private ArrayList<DivisionObject> mListOfDivisionObjects;
	//ArrayList of Places - will be captured from the intent and passed along to the next activity in case
	//the list is needed further down in the program.  It is not used in this class.
	/**
	 * declared here and will be assigned to the ArrayList of PlaceObject's from the intent.  It will only get
	 * passed along to the next intent and is not used in the class.
	 */
	private ArrayList<PlaceObject> mListOfPlaceObjects;
	//called when the method is first created
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		//gets extras packaged in the intent
		Bundle extras = getIntent().getExtras();
		//assigns local variables to the ArrayLists passed in the intent
		mListOfDivisionObjects = extras.getParcelableArrayList("com.example.tourapp.divisionArrayList");
		mListOfPlaceObjects = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");
        //populates the ListView with the data mListOfDivisionObjects using the DivisionBaseAdapter
        final ListView lv = (ListView) findViewById(R.id.divisionlistview);
		lv.setAdapter(new DivisionBaseAdapter(this, mListOfDivisionObjects));
		//make each item in the list clickable
		//rather than putting all the data from the object into the intent, we should probably
		//just pass the position and retrieve the object from the ArrayList in the Division activity.
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				intent.putParcelableArrayListExtra("divisionArrayList", mListOfDivisionObjects);
				intent.putParcelableArrayListExtra("placeArrayList", mListOfPlaceObjects);
				intent.putExtra("name", mListOfDivisionObjects.get(position).getName());
				intent.putExtra("description", mListOfDivisionObjects.get(position).getDescription());
				intent.putExtra("imageName", mListOfDivisionObjects.get(position).getImageName());
				intent.putExtra("phone", mListOfDivisionObjects.get(position).getPhone());
				intent.putExtra("email", mListOfDivisionObjects.get(position).getEmail());
				intent.putExtra("website", mListOfDivisionObjects.get(position).getWebsite());
				startActivity(intent);
			}//end onItemClick
		});
	}//end onCreate method
}//end DivisonList