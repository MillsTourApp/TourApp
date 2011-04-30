/***
 * Copyright (c) 2010 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.example.tourapp;

import java.util.ArrayList;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity {
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
		DivisionObject obj1 = new DivisionObject(1, "Mills Hall", "-122.18212", "37.779496", "This is a description of Mills Hall","These are directions to the building before Mills Hall", "This is the name of an image for Mills Hall");
		manualListOfDivisionObjects.add(obj1);
		DivisionObject obj2 = new DivisionObject(1, "Natural Sciences Building", "-122.180666", "37.780143", "This is a description of Natural Sciences Building","These are directions to the building before the Natural Sciences Building", "This is the name of an image for the Natural Sciences Building");
		manualListOfDivisionObjects.add(obj2);
		DivisionObject obj3 = new DivisionObject(1, "Carnegie Hall", "-122.181645", "37.778995", "This is a description of Carnegie Hall","These are directions to the building before Carnegie Hall", "This is the name of an image for Carnegie Hall");
		manualListOfDivisionObjects.add(obj3);
		DivisionObject obj4 = new DivisionObject(1, "CPM", "-122.182791", "37.778726", "This is a description of CPM","These are directions to the building before CPM", "This is the name of an image for CPM");
		manualListOfDivisionObjects.add(obj4);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		initMapView();
		initMyLocation();
		mapOverlays = mapView.getOverlays();
		// create itemized overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		//Create balloon overlays using test arraylist
		for (DivisionObject obj:manualListOfDivisionObjects){
			double lat = Double.parseDouble(obj.getLat());
			double lon = Double.parseDouble(obj.getLon());
			createOverlay(lat, lon, obj.getName(), obj.getDirFromPrev());
		}
		mapOverlays.add(itemizedOverlay);
	}
	
	/** Find and initialize the map view. (From Hello Android tutorial)*/
	private void initMapView() {
		mapView = (MapView) findViewById(R.id.mapView);
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
	public void createOverlay (double lat, double lon, String name, String directions){
		GeoPoint point = new GeoPoint((int)(lat*1E6),(int)(lon*1E6));
		OverlayItem overlayItem = new OverlayItem(point, name, directions);
		itemizedOverlay.addOverlay(overlayItem);
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
