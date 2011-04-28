package com.example.tourapp;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
 
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
 
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
        setContentView(R.layout.map_layout);
        
 
/*        mapView = (MapView) findViewById(R.id.mapView2);
        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
        @SuppressWarnings("deprecation")
		View zoomView = mapView.getZoomControls();
 
        zoomLayout.addView(zoomView, 
            new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, 
                LayoutParams.WRAP_CONTENT)); 
        mapView.displayZoomControls(true);
 
        mc = mapView.getController();
        String coordinates[] = {MILLS_HALL_LAT, MILLS_HALL_LONG};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(17);
        mapView.setSatellite(true);
        mapView.invalidate();*/
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
}