package com.example.tourapp;
/*Totally taking code from this guy:
 * http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
 */

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DivisionList<listOfDivisionObjects> extends Activity {
	private ArrayList<DivisionObject> listOfDivisionObjects;
	private ArrayList<PlaceObject> listOfPlaceObjects;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisionlist);
		Bundle extras = getIntent().getExtras();
		listOfDivisionObjects = extras.getParcelableArrayList("com.example.tourapp.divisionArrayList");
		listOfPlaceObjects = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");
        
        final ListView lv = (ListView) findViewById(R.id.divisionlistview);
		lv.setAdapter(new DivisionBaseAdapter(this, listOfDivisionObjects));

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent().setClass(DivisionList.this, Division.class);
				intent.putParcelableArrayListExtra("divisionArrayList", listOfDivisionObjects);
				intent.putParcelableArrayListExtra("placeArrayList", listOfPlaceObjects);
				intent.putExtra("name", listOfDivisionObjects.get(position).getName());
				intent.putExtra("description", listOfDivisionObjects.get(position).getDescription());
				intent.putExtra("imageName", listOfDivisionObjects.get(position).getImageName());
				intent.putExtra("phone", listOfDivisionObjects.get(position).getPhone());
				intent.putExtra("email", listOfDivisionObjects.get(position).getEmail());
				intent.putExtra("website", listOfDivisionObjects.get(position).getWebsite());
				startActivity(intent);
			}//end onItemClick
		});
	}//end onCreate method
}//end DivisonList