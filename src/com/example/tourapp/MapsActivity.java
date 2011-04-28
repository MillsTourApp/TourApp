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

public class MapsActivity extends MapActivity {
    private MapController mc;
    private GeoPoint p;
	private MapView mapView;
	private List<Overlay> mapOverlays;
	private MapController controller;
	private Drawable drawable;
	private Drawable drawable2;
	private MyItemizedOverlay itemizedOverlay;
	private MyItemizedOverlay itemizedOverlay2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);
        initMapView();
        initMyLocation();
        
     
		mapOverlays = mapView.getOverlays();
		
		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		
		GeoPoint point = new GeoPoint((int)(37.77946909500844*1E6),(int)(-122.18220055103302*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Mills Hall", 
				"(Start at Mills Hall: From the Office of Undergraduate Admission, walk downstairs to the living room)");
		itemizedOverlay.addOverlay(overlayItem);
		
		GeoPoint point2 = new GeoPoint((int)(37.778664*1E6),(int)(-122.182402*1E6));
		OverlayItem overlayItem2 = new OverlayItem(point2, "El Campanil", 
				"(Walk out the front door to the oval-shaped lawn, known as 'the Oval')");		
		itemizedOverlay.addOverlay(overlayItem2);
		
		// second overlay
		drawable2 = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay2 = new MyItemizedOverlay(drawable2, mapView);
		
		GeoPoint point3 = new GeoPoint((int)(37.778726*1E6),(int)(-122.182791*1E6));
		OverlayItem overlayItem3 = new OverlayItem(point3, "CPM", 
				"(Walk behind El Campanil)");
		itemizedOverlay.addOverlay(overlayItem3);
		
		GeoPoint point4 = new GeoPoint((int)(37.777539*1E6),(int)(-122.183971*1E6));
		OverlayItem overlayItem4 = new OverlayItem(point4, "Education Complex", 
				"(Walk down the road next to CPM (called Post Rd.), to the Education Complex)");		
		itemizedOverlay.addOverlay(overlayItem4);
		
		GeoPoint point5 = new GeoPoint((int)(37.778995*1E6),(int)(-122.181645*1E6));
		OverlayItem overlayItem5 = new OverlayItem(point5, "Carnegie Hall", 
				"(Turn left and walk toward the two-story building with the arched windows framed in blue)");		
		itemizedOverlay.addOverlay(overlayItem5);
		
		GeoPoint point6 = new GeoPoint((int)(37.780143*1E6),(int)(-122.180666*1E6));
		OverlayItem overlayItem6 = new OverlayItem(point6, "Natural Sciences Building", 
				"(Turn left down Wetmore Road and walk to the Betty Irene Moore Natural Sciences Building)");		
		itemizedOverlay.addOverlay(overlayItem6);
		
		GeoPoint point7 = new GeoPoint((int)(37.781336*1E6),(int)(-122.1793*1E6));
		OverlayItem overlayItem7 = new OverlayItem(point7, "Founders Commons", 
				"(Continue along Wetmore Road toward Founders Commons, our main dining hall)");		
		itemizedOverlay.addOverlay(overlayItem7);
		
		GeoPoint point8 = new GeoPoint((int)(37.78297*1E6),(int)(-122.18190*1E6));
		OverlayItem overlayItem8 = new OverlayItem(point8, "Art Museum and Aron Art Center", 
				"(Continue down Post Road and turn right on Kapiolani Road—you are now facing the " +
				"Mills College Art Museum and the Aron Art Center)");		
		itemizedOverlay.addOverlay(overlayItem8);
		
		GeoPoint point9 = new GeoPoint((int)(37.782449*1E6),(int)(-122.182329*1E6));
		OverlayItem overlayItem9= new OverlayItem(point9, "Warren Olney Hall", 
				"(Walk back toward the intersection of Post and Kapiolani Roads and face " +
				"Warren Olney Hall on the right)");		
		itemizedOverlay.addOverlay(overlayItem9);
		
		GeoPoint point10 = new GeoPoint((int)(37.782067*1E6),(int)(-122.183359*1E6));
		OverlayItem overlayItem10 = new OverlayItem(point10, "Orchard Meadow Hall", 
				"(Walk past Warren Olney Hall’s front entrance and go under the walkway " +
				"arch on the right; continue across the courtyard (behind Olney Hall) and through " +
				"the second underpass—you will arrive at the front of Orchard Meadow Hall)");		
		itemizedOverlay.addOverlay(overlayItem10);
		
		GeoPoint point11 = new GeoPoint((int)(37.781568*1E6),(int)(-122.184762*1E6));
		OverlayItem overlayItem11 = new OverlayItem(point11, "Music Building", 
				"(Continue on Orchard Meadow Road and walk toward the Music Building)");		
		itemizedOverlay.addOverlay(overlayItem11);
		
		GeoPoint point12 = new GeoPoint((int)(37.782652*1E6),(int)(-122.184722*1E6));
		OverlayItem overlayItem12 = new OverlayItem(point12, "Haas Pavilion", 
				"(Turn right on Richards Road and walk toward Haas Pavilion)");		
		itemizedOverlay.addOverlay(overlayItem12);
		
		GeoPoint point13 = new GeoPoint((int)(37.783265*1E6),(int)(-122.185231*1E6));
		OverlayItem overlayItem13 = new OverlayItem(point13, "Trefethen Aquatic Center", 
				"(Walk to the left of Haas Pavilion, around the back, and up the stairs " +
				"to the Trefethen Aquatic Center (pool, 4 on map))");		
		itemizedOverlay.addOverlay(overlayItem13);
		
		GeoPoint point14 = new GeoPoint((int)(37.783571*1E6),(int)(-122.187468*1E6));
		OverlayItem overlayItem14 = new OverlayItem(point14, "Alderwood Hall", 
				"(Walk to Alderwood Hall, located on the other end of the soccer " +
				"field, near the front gate)");		
		itemizedOverlay.addOverlay(overlayItem14);
		
		GeoPoint point15 = new GeoPoint((int)(37.78217*1E6),(int)(-122.185781*1E6));
		OverlayItem overlayItem15 = new OverlayItem(point15, "Chapel", 
				"(Walk back toward Richards Road; you will see the Chapel)");		
		itemizedOverlay.addOverlay(overlayItem15);
		
		GeoPoint point16 = new GeoPoint((int)(37.7817*1E6),(int)(-122.182933*1E6));
		OverlayItem overlayItem16 = new OverlayItem(point16, "Graduate School of Business", 
				"(Walk back down Richards Road toward the center of campus)");		
		itemizedOverlay.addOverlay(overlayItem16);
		
		GeoPoint point17 = new GeoPoint((int)(37.780948*1E6),(int)(-122.181289*1E6));
		OverlayItem overlayItem17 = new OverlayItem(point17, "Rothwell Center", 
				"(Turn left and walk toward the two-story building with the arched " +
				"windows framed in blue)");		
		itemizedOverlay.addOverlay(overlayItem17);
		
		GeoPoint point18 = new GeoPoint((int)(37.78074*1E6),(int)(-122.18279*1E6));
		OverlayItem overlayItem18 = new OverlayItem(point18, "Lisser Hall", 
				"(Cross Kapiolani Road to Lisser Hall and the F. W. Olin Library )");		
		itemizedOverlay.addOverlay(overlayItem18);
		
		GeoPoint point19 = new GeoPoint((int)(37.78115*1E6),(int)(-122.18236*1E6));
		OverlayItem overlayItem19 = new OverlayItem(point19, "F. W. Olin Library", 
				"(Cross Kapiolani Road to Lisser Hall and the F. W. Olin Library )");		
		itemizedOverlay.addOverlay(overlayItem19);
		
		GeoPoint point20 = new GeoPoint((int)(37.780276*1E6),(int)(-122.181884*1E6));
		OverlayItem overlayItem20 = new OverlayItem(point20, "Toyon Meadow", 
				"(Turn right onto the walkway over the graduation mound, back toward Mills Hall)");		
		itemizedOverlay.addOverlay(overlayItem20);

		GeoPoint point21 = new GeoPoint((int)(37.781564*1E6),(int)(-122.180621*1E6));
		OverlayItem overlayItem21 = new OverlayItem(point21, "Vera M. Long Building", 
				"(Turn left at Post Road and continue to the Vera M.Long Building for the Social Sciences)");		
		itemizedOverlay.addOverlay(overlayItem21);
		
		mapOverlays.add(itemizedOverlay);
		
		final MapController mc = mapView.getController();
		//mc.animateTo(point);
		//mc.setZoom(17);
		//mapView.setSatellite(true);
       // mapView.invalidate();
		
    }
	
	 /** Find and initialize the map view. */
	   private void initMapView() {
	      mapView = (MapView) findViewById(R.id.mapView);
	      controller = mapView.getController();
	      mapView.setSatellite(true);
	      mapView.setBuiltInZoomControls(true);
	   }

	   /** Start tracking the position on the map. */
	   private void initMyLocation() {
	      final MyLocationOverlay overlay = new MyLocationOverlay(this, mapView);
	      overlay.enableMyLocation();
	      overlay.enableCompass(); // does not work in emulator
	      overlay.runOnFirstFix(new Runnable() {
	         public void run() {
	            // Zoom in to current location
	            controller.setZoom(10);
	            controller.animateTo(overlay.getMyLocation());
	         }
	      });
	      mapView.getOverlays().add(overlay);
	   }
	/*
	public void createItem (double lat, double lon, String name, String directions){
		GeoPoint point = new GeoPoint((int)(lat*1E6),(int)(lon*1E6));
		OverlayItem overlayItem = new OverlayItem(point, name, 
				directions);
		itemizedOverlay.addOverlay(overlayItem);
	}*/
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
