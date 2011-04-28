package com.example.tourapp;

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
		TextView dirToNext = (TextView) findViewById(R.id.divisiondirtonext);
		TextView dirFromPrev = (TextView) findViewById(R.id.divisiondirfromprev);
		ImageView image = (ImageView) findViewById(R.id.divisionimage);

		Bundle extras = getIntent().getExtras();
		String divisionName = extras.getString("dName");
		String divisionDescription = extras.getString("description");
		//String divisionDirToNext = extras.getString("dDirToNext");
		String divisionDirFromPrev = extras.getString("dDirFromPrev");
		String divisionImageName = extras.getString("dImageName");
		int resId = this.getResources().getIdentifier(divisionImageName, "drawable", "com.example.tourapp");
		name.setText(divisionName);
		description.setText(divisionDescription);
		dirFromPrev.setText(divisionDirFromPrev);
		//dirToNext.setText(divisionDirToNext);
		image.setImageResource(resId);
	}
}
