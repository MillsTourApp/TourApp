package com.example.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

//Activity class that displays information on a single division object
/**
 * Activity class that displays information on a single division object.
 * @see DivisionObject
 */
public class Division extends Activity{
	//final String to hold package name, used to find resource id from a String imageName
	/**
	 * holds package name used to find resource id from a String imageName
	 */
	private final String PACKAGE_NAME = "com.example.tourapp";
	
	//onCreate method that gets data from an intent and sets the content of the various views with
	//data from that intent
	/**
	 * gets the data from an intent and sets the content of the various views
	 * with data from that intent.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.division);

		//get references to the text views for displaying division data
		TextView nameTV = (TextView) findViewById(R.id.division_name);
		TextView descriptionTV = (TextView) findViewById(R.id.division_descript);
		descriptionTV.setMovementMethod(new ScrollingMovementMethod());
		ImageView imageIV = (ImageView) findViewById(R.id.divisionimage);
		TextView phoneTV = (TextView) findViewById(R.id.divisionphone);
		TextView emailTV = (TextView) findViewById(R.id.divisionemail);
		TextView websiteTV = (TextView) findViewById(R.id.divisionwebsite);

		//get division data from intent
		Bundle extras = getIntent().getExtras();
		String divisionName = extras.getString("name");
		String divisionDescription = extras.getString("description");
		String divisionImageName = extras.getString("imageName");
		String divisionPhone = extras.getString("phone");
		String divisionEmail = extras.getString("email");
		final String divisionWebsite = extras.getString("website");

		//find int resource from String imageName
		int resId = this.getResources().getIdentifier(divisionImageName, "drawable", PACKAGE_NAME);

		//set text of textviews to data from division object
		nameTV.setText(divisionName);
		descriptionTV.setText(divisionDescription);
		imageIV.setImageResource(resId);
		phoneTV.setText(divisionPhone);
		emailTV.setText(divisionEmail);
		websiteTV.setText(divisionName + " Website");

		//make web url clickable
		/**
		 * makes websiteTV clickable; loads URL in a web browser.
		 */
		websiteTV.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(divisionWebsite)));
			}
		});

	}
}
