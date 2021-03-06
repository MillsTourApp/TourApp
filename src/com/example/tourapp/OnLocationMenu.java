package com.example.tourapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Activity for loading on location options.
 * @author Fatima Padojino
 * 
 */
public class OnLocationMenu extends Activity implements OnClickListener {
	//declare ArrayLists- will be assigned to ArrayLists packaged in the intent
	private ArrayList<DivisionObject> mListOfDivisions;
	private ArrayList<PlaceObject> mListOfPlaces;
	private static final int TOUR_START_LOCATION = 0; //the guided tour starts at the first object
	/**
	 * Called when the activity is first created
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlocationmenu);
		//make buttons clickable
		findViewById(R.id.button_guided_tour).setOnClickListener(this);
		findViewById(R.id.button_division_list).setOnClickListener(this);
		findViewById(R.id.button_division_1).setOnClickListener(this);
		//get ArrayLists from the intent
		Bundle extras = getIntent().getExtras();
		mListOfDivisions = extras.getParcelableArrayList("com.example.tourapp.divisionArrayList");
		mListOfPlaces = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");
	}//end method onCreate

	/**
	 * required with the implementation of OnClickListener, defines the intent for each button
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_guided_tour:
			GuidedTour.mCurrentPlaceId = TOUR_START_LOCATION;
			Intent i1 = new Intent (this, GuidedTour.class);
			i1.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", mListOfDivisions);
			i1.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", mListOfPlaces);
			startActivity(i1);
			break;
		case R.id.button_division_list:
			Intent i2 = new Intent (this, DivisionList.class);
			i2.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", mListOfDivisions);
			i2.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", mListOfPlaces);
			startActivity(i2);
			break;
		case R.id.button_division_1:
			Intent i3 = new Intent (this, Admission.class);
			i3.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", mListOfDivisions);
			i3.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", mListOfPlaces);
			startActivity(i3);
			break;
		}//end switch statement
	}//end method onClick
}//end OnLocationMenu class
