package com.example.teamjavatar.domain;

import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.report.Report;
import com.example.teamjavatar.domain.report.SpendingReport;


public class User extends AbstractUser {
	
	private String firstName;
	private String lastName;
	
	/**
	 * Constructor for creating a user.
	 * 
	 * @param userID
	 * @param firstName
	 * @param lastName
	 */
	public User(String userID, String firstName, String lastName) {
		super(userID);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setID(String userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public Report makeSpendingReport(long startDate, long endDate) {
		List<Withdrawal> withdrawals = new LinkedList<Withdrawal>();
		//TODO process and obtain withdrawals
		Report report = new SpendingReport(getFullName(), startDate, endDate, withdrawals);
		return report;
	}
	
	public void changePassword() {
		//TODO
		//not sure if this should be here or as part of the database
		//i think just call on requestChangePassword on database.
	}
	
}
