package com.example.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OnLocationMenu extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlocationmenu);

		findViewById(R.id.button_guided_tour).setOnClickListener(this);
		findViewById(R.id.button_division_activity).setOnClickListener(this);
		findViewById(R.id.button_division_1).setOnClickListener(this);
	}//end method onCreate


	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_guided_tour:
			Intent i1 = new Intent (this, GuidedTour.class);
			startActivity(i1);
			break;
		case R.id.button_division_activity:
			Intent i2 = new Intent (this, DivisionActivity.class);
			startActivity(i2);
			break;
		case R.id.button_division_1:
			Intent i3 = new Intent (this, Division1.class);
			startActivity(i3);
			break;
		}//end switch statement
	}//end method onClick
}//end OnLocationMenu class
