package com.example.tourapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Division extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.division);
		
		TextView tv = (TextView) findViewById(R.id.division_name);
		Bundle extras = getIntent().getExtras();
		String divisionName = extras.getString("dName");
		tv.setText(divisionName);
	}
}
