package com.example.tourapp;

import android.os.Parcel;
import android.os.Parcelable;

public class DivisionObject implements Parcelable{
	
	int ID;
	String name;
	String description;
	String lon;
	String lat;
	String imageName;
	
	public DivisionObject (
			int IdPassed, 
			String namePassed, 
			String lonPassed, 
			String latPassed, 
			String descriptionPassed,  
			String imageNamePassed){
		ID = IdPassed;
		name = namePassed;
		lon = lonPassed;
		lat = latPassed;
		description = descriptionPassed;
		imageName = imageNamePassed;
	}//end constructor
	
	//constructor for Parcel object
    public DivisionObject (Parcel source){
    	ID = source.readInt();
        name = source.readString();
        lon = source.readString();
        lat = source.readString();
        description = source.readString();
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
		parcel.writeString(imageName);
	}
	//http://prasanta-paul.blogspot.com/2010/06/android-parcelable-example.html
	
	public static final Parcelable.Creator<DivisionObject> CREATOR = new Parcelable.Creator<DivisionObject>(){
		public DivisionObject createFromParcel(Parcel in){
			return new DivisionObject(in);
		}
		
	      public DivisionObject[] newArray(int size) {
	            return new DivisionObject[size];
	      }
	};
}
