package com.example.tourapp;

import java.util.ArrayList;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GuidedTour extends MapActivity {
	
    MapView mapView; 
    MapController mc;
    GeoPoint p;

    static final String MILLS_HALL_LAT = "37.77946909500844";
    static final String MILLS_HALL_LONG = "-122.18220055103302";
    
    TextView descView;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidedtour);
        
        //map stuff
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
 
        mc = mapView.getController();
        String coordinates[] = {MILLS_HALL_LAT, MILLS_HALL_LONG};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(18);
        mapView.setSatellite(true);
        mapView.invalidate();
        
        
        //ArrayList test stuff
        ArrayList<DivisionObject> manualListOfDivisionObjects = new ArrayList<DivisionObject>(30);
        
        DivisionObject obj1 = new DivisionObject(1, "Mills Hall", "4", "5", "This is a description of Mills Hall","These are directions to the building before Mills Hall", "This is the name of an image for Mills Hall");
        manualListOfDivisionObjects.add(obj1);
        DivisionObject obj2 = new DivisionObject(1, "Natural Sciences Building", "4", "5", "This is a description of Natural Sciences Building","These are directions to the building before the Natural Sciences Building", "This is the name of an image for the Natural Sciences Building");
        manualListOfDivisionObjects.add(obj2);
        DivisionObject obj3 = new DivisionObject(1, "Carnegie Hall", "4", "5", "This is a description of Carnegie Hall","These are directions to the building before Carnegie Hall", "This is the name of an image for Carnegie Hall");
        manualListOfDivisionObjects.add(obj3);
        DivisionObject obj4 = new DivisionObject(1, "CPM", "4", "5", "This is a description of CPM","These are directions to the building before CPM", "This is the name of an image for CPM");
        manualListOfDivisionObjects.add(obj4);
       
        String testManualArray = "";
        CharSequence testMArray2 = "";
        
        
        for (DivisionObject obj:manualListOfDivisionObjects){
            testManualArray+= obj.getName() + "\n";
        }
        
        
        
        descView = (TextView) findViewById(R.id.text_view);
        descView.setText(testManualArray);
    }
	
    @Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
