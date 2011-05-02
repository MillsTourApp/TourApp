package com.example.tourapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class Place extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);
		
		TextView name = (TextView) findViewById(R.id.place_name);
		TextView description = (TextView) findViewById(R.id.place_descript);
		description.setMovementMethod(new ScrollingMovementMethod());
		ImageView image = (ImageView) findViewById(R.id.placeimage);

		Bundle extras = getIntent().getExtras();
		String placeName = extras.getString("name");
		String placeDescription = extras.getString("description");
		String placeImageName = extras.getString("imageName");
		
		int resId = this.getResources().getIdentifier(placeImageName, "drawable", "com.example.tourapp");
		//set text of textviews to data from place object
		name.setText(placeName);
		description.setText(placeDescription);
		image.setImageResource(resId);
	}
}
