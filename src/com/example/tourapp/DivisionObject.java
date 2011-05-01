package com.example.tourapp;

import android.os.Parcel;
import android.os.Parcelable;

public class DivisionObject implements Parcelable{
	
	int ID;
	String name;
	String description;
	String imageName;
	String phone;
	String email;
	String website;
	String building;
	
	public DivisionObject (
			int IdPassed, 
			String namePassed, 
			String descriptionPassed,  
			String imageNamePassed,
			String phonePassed,
			String emailPassed,
			String websitePassed,
			String buildingPassed
	){
		ID = IdPassed;
		name = namePassed;
		description = descriptionPassed;
		imageName = imageNamePassed;
		phone = phonePassed;
		email = emailPassed;
		website = websitePassed;
		building = buildingPassed;
	}//end constructor
	
	//constructor for Parcel object
    public DivisionObject (Parcel source){
    	ID = source.readInt();
        name = source.readString();
        description = source.readString();
        imageName = source.readString();
        phone = source.readString();
        email = source.readString();
        website = source.readString();
        building = source.readString();
  }//end constructor for Parcel object
	
	public int getId(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getImageName(){
		return imageName;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getWebsite(){
		return website;
	}
	
	public String getBuilding(){
		return building;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(ID);
		parcel.writeString(name);
		parcel.writeString(description);
		parcel.writeString(imageName);
		parcel.writeString(phone);
		parcel.writeString(email);
		parcel.writeString(website);
		parcel.writeString(building);
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
