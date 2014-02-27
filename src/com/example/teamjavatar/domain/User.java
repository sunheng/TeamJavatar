package com.example.teamjavatar.domain;

import java.util.Map;

public class User implements IUser {
	
	private String userID;
	private String firstName;
	private String lastName;
	private Map<Integer,Account> accounts;
	
	public User( String userID, String firstName, String lastName,
			Map<Integer,Account> accounts) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accounts = accounts;
	}

	public User() {}

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
	
	public void addAccount(Account account) {
		this.accounts.put(account.getID(), account);
		//TODO increment num_accounts in user database
		//TODO add new account to account database
	}
	
	public void removeAccount(Account account) {
		this.accounts.remove(account.getID());
		//TODO decrement num_accounts in user database
		//TODO remove account from account database
	}
}
