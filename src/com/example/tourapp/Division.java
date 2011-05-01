package com.example.tourapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class Division extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.division);
		
		TextView name = (TextView) findViewById(R.id.division_name);
		TextView description = (TextView) findViewById(R.id.division_descript);
		description.setMovementMethod(new ScrollingMovementMethod());
		ImageView image = (ImageView) findViewById(R.id.divisionimage);

		Bundle extras = getIntent().getExtras();
		
		//ArrayList<DivisionObject> listOfDivisionObjects = extras.getParcelableArrayList("divisionArrayList");
		//ArrayList<DivisionObject> manualListOfDivisionObjects = new ArrayList<DivisionObject>(30);

		String divisionName = extras.getString("name");
		String divisionDescription = extras.getString("description");
		String divisionImageName = extras.getString("imageName");
		
		int resId = this.getResources().getIdentifier(divisionImageName, "drawable", "com.example.tourapp");
		name.setText(divisionName);
		description.setText(divisionDescription);
		image.setImageResource(resId);
		
		
	}
}
