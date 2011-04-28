package com.example.tourapp;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle; 

public class MapsActivity extends MapActivity 
{    
	MapView mapView; 
	MapController mc;
	GeoPoint p;

	static final String MILLS_HALL_LAT = "37.77946909500844";
	static final String MILLS_HALL_LONG = "-122.18220055103302";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps_layout);

		mapView = (MapView) findViewById(R.id.mapView);

		String coordinates[] = {MILLS_HALL_LAT, MILLS_HALL_LONG};
		double lat = Double.parseDouble(coordinates[0]);
		double lng = Double.parseDouble(coordinates[1]);

		p = new GeoPoint(
				(int) (lat * 1E6), 
				(int) (lng * 1E6));

		mc = mapView.getController();
		mc.animateTo(p);
		mc.setZoom(18);
		mapView.setSatellite(true);
		mapView.setBuiltInZoomControls(true);

		mapView.invalidate();
	} //onCreate

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	} //isRouteDisplayed
}