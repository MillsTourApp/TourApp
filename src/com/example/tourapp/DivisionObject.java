package com.example.tourapp;
//Fatima used the website below for guidance on making DivisionObject parcelable
//so that it could be passed through an intent
//http://prasanta-paul.blogspot.com/2010/06/android-parcelable-example.html


import android.os.Parcel;
import android.os.Parcelable;
//DivisionObject object which holds all the information on a single division.
/**
 * an object that holds all the information on a single division.  Provides simple get methods
 * for all variables.  Set methods are not provided since the data should not be changed within
 * the app.
 */
public class DivisionObject implements Parcelable{
	private int id;
	private String name;
	private String description;
	private String imageName;
	private String phone;
	private String email;
	private String website;
	private String building;
	//Constructor
	/**
	 * Constructor
	 * @param idPassed int id of the division within the ArrayList
	 * @param namePassed name of the division
	 * @param descriptionPassed description of the division
	 * @param imageNamePassed name of image file of the division
	 * @param phonePassed phone number of the division
	 * @param emailPassed email address of the division
	 * @param websitePassed website url of the division
	 * @param buildingPassed name of building within which the division resides
	 */
	public DivisionObject (int idPassed, String namePassed, String descriptionPassed, String imageNamePassed, String phonePassed,
			String emailPassed,String websitePassed,String buildingPassed){
		id = idPassed;
		name = namePassed;
		description = descriptionPassed;
		imageName = imageNamePassed;
		phone = phonePassed;
		email = emailPassed;
		website = websitePassed;
		building = buildingPassed;
	}//end constructor

	//constructor for Parcel object
	/**
	 * Constructor for Parcel object
	 * @param source parcel from which to read/extract data to create a new DivisionObject
	 */
	public DivisionObject (Parcel source){
		id = source.readInt();
		name = source.readString();
		description = source.readString();
		imageName = source.readString();
		phone = source.readString();
		email = source.readString();
		website = source.readString();
		building = source.readString();
	}//end constructor for Parcel object
	//getId method
	/**
	 * @return the id of the division
	 */
	public int getId(){
		return id;
	}//end getId
	//getName method
	/**
	 * @return the name of the division
	 */
	public String getName(){
		return name;
	}//end getName
	//getDescription method
	/**
	 * @return the description of the division
	 */
	public String getDescription(){
		return description;
	}//end getDescription
	//getImageName method
	/**
	 * @return the name of the image file in the drawable folder associated with the division
	 */
	public String getImageName(){
		return imageName;
	}//end getImageName
	//getPhone method
	/**
	 * @return the phone number of the division
	 */
	public String getPhone(){
		return phone;
	}//end getPhone method
	//getEmail method
	/**
	 * @return the email address of the division
	 */
	public String getEmail(){
		return email;
	}//end getEmail method
	//getWebsite method
	/**
	 * @return the website of the division
	 */
	public String getWebsite(){
		return website;
	}//end getWebsite
	//getBuilding method
	/**
	 * @return the name of the building within which the division resides
	 */
	public String getBuilding(){
		return building;
	}//end getBuilding
	//method to override from Parcel
	/**
	 * overrides the default describeContents method from the interface Parcelable
	 * @return 0
	 */
	@Override
	public int describeContents() {
		return 0;
	}//end describeContents
	//writeToParcel method specifies each primitive to write to the parcel
	/**
	 * specifies each primitive object to write to the parcel object.  Will be read and passed to Parcel object constructor
	 * and made back into a DivisionObject after being passed through an intent.
	 * @param parcel parcel object to which the primitives should be written
	 * @param flags additional flags about how the object should be written - these are unused in our implementation
	 */
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(id);
		parcel.writeString(name);
		parcel.writeString(description);
		parcel.writeString(imageName);
		parcel.writeString(phone);
		parcel.writeString(email);
		parcel.writeString(website);
		parcel.writeString(building);
	}//end writeToParcel
	//CREATOR object required with parcelable implementation
	//uses constructor with Parcel parameter to reconstruct a DivisionObject from a parcel object
	/**
	 * required with the parcelable implementation
	 */
	public static final Parcelable.Creator<DivisionObject> CREATOR = new Parcelable.Creator<DivisionObject>(){
		/**
		 * uses DivisionObject constructor with Parcel parameter
		 * to reconstruct a DivisionObject from a parcel object
		 * @param in Parcel object that holds the data to read out and use to reconstruct a DivisionObject
		 * @return a new DivisionObject constructed from the parcel object
		 */
		public DivisionObject createFromParcel(Parcel in){
			return new DivisionObject(in);
		}//end createFromParcel method
		/**
		 * @return Array
		 */
		public DivisionObject[] newArray(int size) {
			return new DivisionObject[size];
		}//end newArray method
	};
}//end class DivisionObject
