package com.example.tourapp;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class GuidedTour extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guidedtour);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
