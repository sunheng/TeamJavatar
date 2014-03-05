package com.example.teamjavatar.domain;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Account {
	
	private int ID;
	private String name;
	private String displayName;
	private long creationDate;
	private double balance;
	private double interestRate;
	private LinkedList<Transaction> transactions;
	
	/**
	 * Constructor for creating a new Account.
	 * 
	 * @param ID
	 * @param name
	 * @param displayName
	 */
	public Account(int ID, String name, String displayName, double interestRate) {
		this(ID, name, displayName, Calendar.getInstance().getTimeInMillis(),
				0, 0, new LinkedList<Transaction>());
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
	 * @param transactions
	 */
	public Account(int ID, String name, String displayName, long creationDate, double balance,
			double interestRate, LinkedList<Transaction> transactions) {
		this.ID = ID;
		this.name = name;
		this.displayName = displayName;
		this.creationDate = creationDate;
		this.balance = balance;
		this.interestRate = interestRate;
		this.transactions = transactions;
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
	 * Returns a list of this account's transactions.
	 * 
	 * The primary use of this method is to get detailed information of this
	 * account's transactions.
	 * The returned list is a clone of this account's list, so you should
	 * remove transactions using the undoTransaction() method.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactions() {
		return (List<Transaction>) this.transactions.clone();
	}
	
	public int getNumTransactions() {
		return this.transactions.size();
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
	
	public void addWithdrawal() {
		//TODO add parameters and process
	}
	
	public void addDeposit() {
		//TODO add parameters and process
	}
	
	public void undoLastTransaction() {
		Transaction t =	(Transaction) this.transactions.removeLast();
		//TODO process the removed transaction, update this account's balance
		//     changes to the database should be processed externally
	}
	
	public void undoTransaction(Transaction transaction) {
		this.transactions.remove( transaction );
		//TODO process the removed transaction, update this account's balance
		//     changes to the database should be processed externally
	}
}