package com.example.tourapp;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MapView.LayoutParams;

public class GuidedTour extends MapActivity {
	//map fields
	private MapView mMapView;
	private List<Overlay> mMapOverlays;
	private MapController mController;
	private Drawable mDrawable;
	private MyItemizedOverlay mItemizedOverlay;
	
	//text fields
	private TextView titleView;
	private TextView descView;

	//constants
	private static final int NUM_PLACE_OBJ = 30; //temp
	private static final int BALLOON_OPACITY = 200;
	private static final int MAP_ZOOM = 18;
	private static final double NORMALIZE_COORDINATES = 1E6;
	
	//test arraylist
	private ArrayList<PlaceObject> mManualListOfPlaceObjects;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guidedtour);
		
		//temporary testing stuff
		mManualListOfPlaceObjects = new ArrayList<PlaceObject>(NUM_PLACE_OBJ);
		PlaceObject obj1 = new PlaceObject(0, "Mills Hall", "-122.18212", "37.779496", "This is a description of Mills Hall.","Start your tour here!", "this is a description of where you came from", "This is the name of an image for Mills Hall");
		mManualListOfPlaceObjects.add(obj1);
		PlaceObject obj2 = new PlaceObject(1, "Natural Sciences Building", "-122.180666", "37.780143", "This is a description of Natural Sciences Building","Directions from Mills Hall: These are directions to the building before the Natural Sciences Building", "this is a description of where you came from", "This is the name of an image for the Natural Sciences Building");
		mManualListOfPlaceObjects.add(obj2);
		PlaceObject obj3 = new PlaceObject(2, "Carnegie Hall", "-122.181645", "37.778995", "This is a description of Carnegie Hall","Directions from NSB: These are directions from the building before Carnegie Hall", "this is a description of where you came from", "This is the name of an image for Carnegie Hall");
		mManualListOfPlaceObjects.add(obj3);
		PlaceObject obj4 = new PlaceObject(3, "CPM", "-122.182791", "37.778726", "This is a description of CPM","From Carnegie Hall: These are directions from the building before CPM", "this is a description of where you came from", "This is the name of an image for CPM");
		mManualListOfPlaceObjects.add(obj4);

		PlaceObject currentPlace = obj1; //set to Mills Hall for testing purposes
		
		//map view
		initMapView();
		initMyLocation();
		mMapOverlays = mMapView.getOverlays();
		
		// create itemized overlay
		mDrawable = getResources().getDrawable(R.drawable.marker);
		mDrawable.setAlpha(BALLOON_OPACITY);
		mItemizedOverlay = new MyItemizedOverlay(mDrawable, mMapView);
		
		//Create balloon overlays using test arraylist
		for (PlaceObject obj : mManualListOfPlaceObjects){
			double lat = Double.parseDouble(obj.getLat());
			double lon = Double.parseDouble(obj.getLon());
			createOverlay(lat, lon, obj.getId(), obj.getName(), obj.getDirFromPrev());
		} //for
		mMapOverlays.add(mItemizedOverlay);
		
		//view stuff		
		titleView = (TextView) findViewById(R.id.title_view);
		titleView.setText(currentPlace.getName());
		
		descView = (TextView) findViewById(R.id.desc_view);
		descView.setText(currentPlace.getDescription());
	} //onCreate
	
	//helper methods
	/** Find and initialize the map view. (From Hello Android tutorial)*/
	private void initMapView() {
		mMapView = (MapView) findViewById(R.id.mapview);
		mController = mMapView.getController();
		mMapView.setSatellite(true);
		mMapView.setBuiltInZoomControls(true);
	} //initMapView

	/** Start tracking the position on the map. (From Hello Android tutorial)*/
	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, mMapView);
		overlay.enableMyLocation();
		overlay.enableCompass(); // does not work in emulator
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				// Zoom in to current location
				mController.setZoom(MAP_ZOOM);
				mController.animateTo(overlay.getMyLocation());
			} //run
		}); //runOnFirstFix
		mMapView.getOverlays().add(overlay);
	} //initMyLocation

	/**
	 * Creates a balloon overlay that puts a point on the map of the building specified 
	 * in the parameters
	 * @param lat Latitude of the building
	 * @param lon Longitude of the building
	 * @param name Name of the building
	 * @param directions Directions to the building from the previous building
	 */
	public void createOverlay (double lat, double lon, int id, String name, String directions){
		GeoPoint point = new GeoPoint((int)(lat*NORMALIZE_COORDINATES ),(int)(lon*NORMALIZE_COORDINATES));
		id++; //So id starts at 1, not 0
		OverlayItem overlayItem = new OverlayItem(point, id + ". "+ name, directions);
		mItemizedOverlay.addOverlay(overlayItem);
	} //createOverlay

	public String getTextOfSelectedBalloon(int index){
		PlaceObject obj = mManualListOfPlaceObjects.get(index);
		return obj.getDescription();	
	} //getTextOfSelectedBalloon
	
	/**
	 * Specifies whether there is a route displayed
	 * @return True is a route is displayed, false if there is not a route displayed
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	} //isRouteDisplayed
} //GuidedTour