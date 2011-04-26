package com.example.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OffLocationMenu extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlocationmenu);

		findViewById(R.id.button_virtual_tour).setOnClickListener(this);
		findViewById(R.id.button_division_list2).setOnClickListener(this);
		findViewById(R.id.button_apply2).setOnClickListener(this);
	}//end method onCreate

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button_virtual_tour:
			Intent i1 = new Intent (this, VirtualTour.class);
			startActivity(i1);
			break;
		case R.id.button_division_list2:
			Intent i2 = new Intent (this, DivisionList.class);
			startActivity(i2);
			break;
		case R.id.button_apply2:
			Intent i3 = new Intent (this, Admission.class);
			startActivity(i3);
			break;
		}//end switch statement
	}//end method onClick
}
