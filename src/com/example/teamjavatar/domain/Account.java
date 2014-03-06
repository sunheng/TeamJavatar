package com.example.teamjavatar.domain;

public class Account {
	
	private int ID;
	private String name;
	private String displayName;
	private long creationDate;
	private double balance;
	private double interestRate;
	
	/**
	 * Constructor for creating a new Account.
	 * 
	 * @param ID
	 * @param name
	 * @param displayName
	 */
	public Account(int ID, String name, String displayName, long creationDate, double interestRate) {
		this(ID, name, displayName, creationDate,
				0, 0);
	}
	
	/**
	 * Constructor for recreating an old Account.
	 * 
	 * @param ID
	 * @param name
	 * @param displayName
	 * @param creationDate
	 * @param balance
	 * @param interestRate
	 */
	public Account(int ID, String name, String displayName, long creationDate, double balance,
			double interestRate) {
		this.ID = ID;
		this.name = name;
		this.displayName = displayName;
		this.creationDate = creationDate;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public long getCreationDate() {
		return this.creationDate;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	/**
	 * Change this account's name to the specified name.
	 * 
	 * This should only be used when we are changing the account details.
	 * 
	 * @param name
	 */
	public void changeName(String name) {
		this.name = name;
	}

	/**
	 * Change this account's display name to the specified name.
	 * 
	 * This should only be used when we are changing the account details.
	 * 
	 * @param displayName
	 */
	public void changeDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Change this account's interest rate to the specified rate.
	 * 
	 * This should only be used when we are changing the account details.
	 * 
	 * @param interestRate
	 */
	public void changeInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public void applyInterest() {
		//TODO created a new deposit with the interest of this account
		//not necessary to implement this; extra credit
	}
	
	public void commitTransaction(Transaction transaction) {
		//TODO check if this transaction is already committed
	}
	
	public void rollbackTransaction(Transaction transaction) {
		//TODO check if this transaction is already rolledback
	}
}