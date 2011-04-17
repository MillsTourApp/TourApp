package com.example.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

public class TourApp extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button onLocation = (Button) findViewById(R.id.button_on_location);
		onLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OnLocationMenu.class);
				startActivity(intent);			
			}
		});	

		Button offLocation = (Button) findViewById(R.id.button_off_location);
		offLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//start new activity : OnLocationMenu.class
				Intent intent = new Intent(TourApp.this, OffLocationMenu.class);
				startActivity(intent);			
			}
		});	
	}
}