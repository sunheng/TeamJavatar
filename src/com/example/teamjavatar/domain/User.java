package com.example.teamjavatar.domain;

import java.util.HashMap;
import java.util.Map;

public class User extends AbstractUser {
	
//	private String userID;
	private String firstName;
	private String lastName;
	private Map<Integer,Account> accounts;
	
	/**
	 * Constructor for creating a new user.
	 * 
	 * @param userID
	 * @param firstName
	 * @param lastName
	 */
	public User(String userID, String firstName, String lastName) {
		this(userID, firstName, lastName, new HashMap<Integer,Account>());
	}
	
	/**
	 * Constructor for recreating an old user.
	 * 
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param accounts
	 */
	public User( String userID, String firstName, String lastName,
			Map<Integer,Account> accounts) {
		super(userID);
		this.firstName = firstName;
		this.lastName = lastName;
		this.accounts = accounts;
	}
	
//	@Override
//	public String getID() {
//		return userID;
//	}

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
	
	public void addAccount(Account account) {
		this.accounts.put(account.getID(), account);
	}
	
	public void removeAccount(Account account) {
		this.accounts.remove(account.getID());
	}
	
	public void changePassword() {
		//TODO
		//not sure if this should be here or as part of the database
		//i think just call on requestChangePassword on database.
	}
	
}
