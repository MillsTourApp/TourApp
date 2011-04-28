package com.example.tourapp;

public class DivisionObject {
	
	int ID;
	String name;
	String description;
	String dirFromPrev;
	
	public DivisionObject (int IdPassed, String namePassed, String descriptionPassed, String dirFromPrevPassed){
		ID = IdPassed;
		name = namePassed;
		description = descriptionPassed;
		dirFromPrev = dirFromPrevPassed;
	}//end constructor
	
	public int getId(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDecscription(){
		return description;
	}
	
	public String getDirFromPrev(){
		return dirFromPrev;
	}
	
	public String toString(){
		return name;
	}
}
