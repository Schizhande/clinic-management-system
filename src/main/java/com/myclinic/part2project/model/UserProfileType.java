package com.myclinic.part2project.model;

public enum UserProfileType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("Admin"),
	PATIENT("Patient");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
