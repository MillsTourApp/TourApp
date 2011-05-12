package com.example.tourapp;
//From an example that uses androidballoonoverlays libraries
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

import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import com.androidmapballoons.library.BalloonItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * Manages the overlays used in the GuidedTour class
 * @author creator of androidballoonslibrary
 */
public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {
	//ArrayLists for overlayItems that will be mapped
	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	
	/**
	 * Defines the default marker and map view for each of the overlay items
	 * @param defaultMarker image used to create overlays
	 * @param mapView MapView for overlays
	 */
	public MyItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
	}//MyItemizedOverlay constructor

	/**
	 * Adds overlay items to m_overlays
	 * @param overlay OverlayItem to be added
	 */
	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}//addOverlay

	/**
	 * Retrieves each overlay item
	 * @param i index of overlay to be retrieved
	 */
	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}//createItem

	/**
	 * Finds size of m_overlay ArrayList
	 * @return size of m_overlay ArrayList
	 */
	@Override
	public int size() {
		return m_overlays.size();
	}//size()
}//MyItemizedOverlay
