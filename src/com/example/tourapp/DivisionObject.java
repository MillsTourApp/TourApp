package com.example.tourapp;

public class DivisionObject {
	
	int ID;
	String name;
	String description;
	String lon;
	String lat;
	String dirFromPrev;
	String imageName;
	
	public DivisionObject (int IdPassed, String namePassed, String lonPassed, String latPassed, String descriptionPassed, String dirFromPrevPassed, String imageNamePassed){
		ID = IdPassed;
		name = namePassed;
		lon = lonPassed;
		lat = latPassed;
		description = descriptionPassed;
		dirFromPrev = dirFromPrevPassed;
		imageName = imageNamePassed;
	}//end constructor
	
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
	
	public String getImageName(){
		return imageName;
	}
}
