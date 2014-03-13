package com.example.teamjavatar.domain.report;

public abstract class Report {
	
	private String userID;
	
	public Report(String userID) {
		this.userID = userID;
	}
	
	public abstract String toString();
	
}