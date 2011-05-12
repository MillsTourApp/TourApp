package com.example.tourapp;
//Fatima used the website below for guidance on making PlaceObject parcelable
//so that it could be passed through an intent
//http://prasanta-paul.blogspot.com/2010/06/android-parcelable-example.html

import android.os.Parcel;
import android.os.Parcelable;
/**
 * An object that holds all the information on a single place.  Provides simple
 * get methods for all variables.  Set methods are not provided since the data should not 
 * be changed within the app.
 * @author Fatima Padojino
 *
 */
public class PlaceObject implements Parcelable {
	private int id;
	private String name;
	public double lon;
	public double lat;
	private String description;
	private String dirFromPrev;
	private String dirToNext;
	private String imageName;
	
	/**
	 * Constructor
	 * @param idPassed place ID
	 * @param namePassed place name
	 * @param lonPassed longitude
	 * @param latPassed latitude
	 * @param descriptionPassed description of place
	 * @param dirFromPrevPassed directions to this place from the previous place (id--)
	 * @param dirToNextPassed directions to the next place (id++) from this place
	 * @param imageNamePassed filename of image
	 */
	public PlaceObject (
			int idPassed, String namePassed, double lonPassed,double latPassed,
			String descriptionPassed,String dirFromPrevPassed, String dirToNextPassed,
			String imageNamePassed){
		id = idPassed;
		name = namePassed;
		lon = lonPassed;
		lat = latPassed;
		description = descriptionPassed;
		dirFromPrev = dirFromPrevPassed;
		dirToNext = dirToNextPassed;
		imageName = imageNamePassed;
	}//end constructor
	
	//constructor for Parcel object
	/**
	 * Constructor for Parcel object
	 * @param source parcel from which to read/extract data to create a new PlaceObject
	 */
    public PlaceObject (Parcel source){
    	id = source.readInt();
        name = source.readString();
        lon = source.readDouble();
        lat = source.readDouble();
        description = source.readString();
        dirFromPrev = source.readString();
        dirToNext = source.readString();
        imageName = source.readString();
  }//end constructor for Parcel object
    /**
     * 
     * @return id of the place
     */
	public int getId(){
		return id;
	}//end getId
	/**
	 * 
	 * @return name of the place
	 */
	public String getName(){
		return name;
	}//end getName
	/**
	 * @return longitude of the place
	 */
	public double getLon(){
		return lon;
	}//end getLon
	/**
	 * @return latitude of the place
	 */
	public double getLat(){
		return lat;
	}//end getLat
	/**
	 * @return description of the place
	 */
	public String getDescription(){
		return description;
	}//end getDescription
	/**
	 * @return directions from the previous place
	 */
	public String getDirFromPrev(){
		return dirFromPrev;
	}//end getDirFromPrev
	/**
	 * @return directions to the next place
	 */
	public String getDirToNext(){
		return dirToNext;
	}//end getDirToNext
	/**
	 * @return filename of the image in the drawable folder associated with the place
	 */
	public String getImageName(){
		return imageName;
	}//end getImageName
	/**
	 * overrides the default describeContents method from the interface Parcelable
	 * @return 0
	 */	
	@Override
	public int describeContents() {
		return 0;
	}//end describeContents
	/**
	 * specifies each primitive object to write to the parcel object.  Will be read and passed to Parcel object constructor
	 * and made back into a PlaceObject after being passed through an intent.
	 * @param parcel parcel object to which the primitives should be written
	 * @param flags additional flags about how the object should be written - these are unused in our implementation
	 */
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(id);
		parcel.writeString(name);
		parcel.writeDouble(lon);
		parcel.writeDouble(lat);
		parcel.writeString(description);
		parcel.writeString(dirFromPrev);
		parcel.writeString(dirToNext);
		parcel.writeString(imageName);
	}//end writeToParcel
	/**
	 * required with the parcelable implementation
	 */
	public static final Parcelable.Creator<PlaceObject> CREATOR = new Parcelable.Creator<PlaceObject>(){
		public PlaceObject createFromParcel(Parcel in){
			return new PlaceObject(in);
		}//end createFromParcel
	      public PlaceObject[] newArray(int size) {
	            return new PlaceObject[size];
	      }//end newArray
	};
}//end class PlaceObject
