package com.example.tourapp;

import android.app.Activity;
import android.os.Bundle;

//Activity for all admission related content.  Mainly a placeholder for now.
//Will eventually be filled with information about how to apply to Mills, 
//Admission office events, and how to get in contact with the correct staff.
/**
 * Basic activity to hold information on Admission
 */
public class Admission extends Activity{
	
	//onCreate method to load basic view
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admission);
	}//end onCreate
}//end class Admission
