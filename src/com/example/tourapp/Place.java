package com.example.tourapp;

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
		//get View for which to set content
		TextView name = (TextView) findViewById(R.id.place_name);
		TextView description = (TextView) findViewById(R.id.place_descript);
		description.setMovementMethod(new ScrollingMovementMethod());
		ImageView image = (ImageView) findViewById(R.id.placeimage);
		//get data from intent
		Bundle extras = getIntent().getExtras();
		String placeName = extras.getString("name");
		String placeDescription = extras.getString("description");
		String placeImageName = extras.getString("imageName");
		//convert string placeImageName to int resource id
		int resId = this.getResources().getIdentifier(placeImageName, "drawable", "com.example.tourapp");
		//set text of textviews to data from place object
		name.setText(placeName);
		description.setText(placeDescription);
		image.setImageResource(resId);
	}
}
