package com.example.tourapp;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MapView.LayoutParams;

public class GuidedTour extends MapActivity {
	
	private MapView mapView;
	private List<Overlay> mapOverlays;
	private MapController controller;
	private Drawable drawable;
	private MyItemizedOverlay itemizedOverlay;
	//test arraylist
	private ArrayList<DivisionObject> manualListOfDivisionObjects;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//create the array and fill it
		manualListOfDivisionObjects = new ArrayList<DivisionObject>(30);
		DivisionObject obj1 = new DivisionObject(0, "Mills Hall", "-122.18212", "37.779496", "This is a description of Mills Hall.","Start your tour here!", "This is the name of an image for Mills Hall");
		manualListOfDivisionObjects.add(obj1);
		DivisionObject obj2 = new DivisionObject(1, "Natural Sciences Building", "-122.180666", "37.780143", "This is a description of Natural Sciences Building","Directions from Mills Hall: These are directions to the building before the Natural Sciences Building", "This is the name of an image for the Natural Sciences Building");
		manualListOfDivisionObjects.add(obj2);
		DivisionObject obj3 = new DivisionObject(2, "Carnegie Hall", "-122.181645", "37.778995", "This is a description of Carnegie Hall","Directions from NSB: These are directions from the building before Carnegie Hall", "This is the name of an image for Carnegie Hall");
		manualListOfDivisionObjects.add(obj3);
		DivisionObject obj4 = new DivisionObject(3, "CPM", "-122.182791", "37.778726", "This is a description of CPM","From Carnegie Hall: These are directions from the building before CPM", "This is the name of an image for CPM");
		manualListOfDivisionObjects.add(obj4);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		initMapView();
		initMyLocation();
		mapOverlays = mapView.getOverlays();
		// create itemized overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		drawable.setAlpha(200);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		//Create balloon overlays using test arraylist
		for (DivisionObject obj:manualListOfDivisionObjects){
			double lat = Double.parseDouble(obj.getLat());
			double lon = Double.parseDouble(obj.getLon());
			createOverlay(lat, lon, obj.getId(), obj.getName(), obj.getDirFromPrev());
		}
		mapOverlays.add(itemizedOverlay);
		
	}
	
	/** Find and initialize the map view. (From Hello Android tutorial)*/
	private void initMapView() {
		mapView = (MapView) findViewById(R.id.mapView2);
		controller = mapView.getController();
		mapView.setSatellite(true);
		mapView.setBuiltInZoomControls(true);
	}

	/** Start tracking the position on the map. (From Hello Android tutorial)*/
	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, mapView);
		overlay.enableMyLocation();
		overlay.enableCompass(); // does not work in emulator
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				// Zoom in to current location
				controller.setZoom(17);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		mapView.getOverlays().add(overlay);
	}

	/**
	 * Creates a balloon overlay that puts a point on the map of the building specified 
	 * in the parameters
	 * @param lat Latitude of the building
	 * @param lon Longitude of the building
	 * @param name Name of the building
	 * @param directions Directions to the building from the previous building
	 */
	public void createOverlay (double lat, double lon, int id, String name, String directions){
		GeoPoint point = new GeoPoint((int)(lat*1E6),(int)(lon*1E6));
		id++; //So id starts at 1, not 0
		OverlayItem overlayItem = new OverlayItem(point, id + ". "+ name, directions);
		itemizedOverlay.addOverlay(overlayItem);
	}

	public String getTextOfSelectedBalloon(int index){
		DivisionObject obj = manualListOfDivisionObjects.get(index);
		return obj.getDescription();
	}
	/**
	 * Specifies whether there is a route displayed
	 * @return True is a route is displayed, false if there is not a route displayed
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
