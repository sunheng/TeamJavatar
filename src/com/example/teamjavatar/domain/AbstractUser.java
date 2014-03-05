package com.example.teamjavatar.domain;

public abstract class AbstractUser {
	
	protected String userID;
	
	public AbstractUser(String ID) {
		this.userID = ID;
	}
	
	public String getID() {
		return this.userID;
	}
	
}