package com.example.teamjavatar.domain;



public class Admin implements IUser {

	private String userID;
	private String firstName;
	private String lastName;
	
	@Override
	public String getUserID() {
		return userID;
	}

	@Override
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}