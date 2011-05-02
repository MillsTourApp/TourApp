package com.example.tourapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OffLocationMenu extends Activity implements OnClickListener {
	public ArrayList<DivisionObject> listOfDivisions;
	public ArrayList<PlaceObject> listOfPlaces;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlocationmenu);

		findViewById(R.id.button_virtual_tour).setOnClickListener(this);
		findViewById(R.id.button_division_list2).setOnClickListener(this);
		findViewById(R.id.button_apply2).setOnClickListener(this);

		Bundle extras = getIntent().getExtras();
		listOfDivisions = extras.getParcelableArrayList("com.example.tourapp.divisionArrayList");
		listOfPlaces = extras.getParcelableArrayList("com.example.tourapp.placeArrayList");

		String testManualArray = "";
		for (DivisionObject obj:listOfDivisions){
			testManualArray+= obj.getName() + "\n";
		}



	}//end method onCreate

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_virtual_tour:
			Intent i1 = new Intent (this, VirtualTour.class);
			i1.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", listOfDivisions);
			i1.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", listOfPlaces);
			startActivity(i1);
			break;
		case R.id.button_division_list2:
			Intent i2 = new Intent (this, DivisionList.class);
			i2.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", listOfDivisions);
			i2.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", listOfPlaces);
			startActivity(i2);
			break;
		case R.id.button_apply2:
			Intent i3 = new Intent (this, Admission.class);
			i3.putParcelableArrayListExtra("com.example.tourapp.divisionArrayList", listOfDivisions);
			i3.putParcelableArrayListExtra("com.example.tourapp.placeArrayList", listOfPlaces);
			startActivity(i3);
			break;
		}//end switch statement
	}//end method onClick
}
