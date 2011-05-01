package com.example.tourapp;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceObject implements Parcelable {
	int ID;
	String name;
	String lon;
	String lat;
	String description;
	String dirFromPrev;
	String dirToNext;
	String imageName;
	
	public PlaceObject (
			int IdPassed, 
			String namePassed, 
			String lonPassed, 
			String latPassed,
			String descriptionPassed,
			String dirToNextPassed,
			String dirFromPrevPassed, 
			String imageNamePassed){
		ID = IdPassed;
		name = namePassed;
		lon = lonPassed;
		lat = latPassed;
		description = descriptionPassed;
		dirFromPrev = dirFromPrevPassed;
		dirToNext = dirToNextPassed;
		imageName = imageNamePassed;
	}//end constructor
	
	//constructor for Parcel object
    public PlaceObject (Parcel source){
    	ID = source.readInt();
        name = source.readString();
        lon = source.readString();
        lat = source.readString();
        description = source.readString();
        dirFromPrev = source.readString();
        dirToNext = source.readString();
        imageName = source.readString();
  }//end constructor for Parcel object
	
	public int getId(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getLon(){
		return lon;
	}
	
	public String getLat(){
		return lat;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getDirFromPrev(){
		return dirFromPrev;
	}
	
	public String getDirToNext(){
		return dirToNext;
	}

	public String getImageName(){
		return imageName;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(ID);
		parcel.writeString(name);
		parcel.writeString(lon);
		parcel.writeString(lat);
		parcel.writeString(description);
		parcel.writeString(dirFromPrev);
		parcel.writeString(dirToNext);
		parcel.writeString(imageName);
	}
	//http://prasanta-paul.blogspot.com/2010/06/android-parcelable-example.html
	
	public static final Parcelable.Creator<PlaceObject> CREATOR = new Parcelable.Creator<PlaceObject>(){
		public PlaceObject createFromParcel(Parcel in){
			return new PlaceObject(in);
		}
		
	      public PlaceObject[] newArray(int size) {
	            return new PlaceObject[size];
	      }
	};
}
