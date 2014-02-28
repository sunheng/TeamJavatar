package com.example.teamjavatar.domain;

import java.util.Calendar;
import java.util.LinkedList;

public class Account {
	
	private int accountID;
	private String userID;
	private String name;
	private String displayName;
	private long dateCreated;
	private double balance;
	private double interestRate;
//	private LinkedList<Transaction> transactions;
	
//	public Account(int ID, String name, String displayName) {
//		this(ID, name, displayName, Calendar.getInstance().getTimeInMillis(),
//				0, 0, new LinkedList<Transaction>());
//	}
//	
//	public Account(int ID, String name, String displayName, long dateCreated, double balance,
//			double interestRate, LinkedList<Transaction> transactions) {
//		this.ID = ID;
//		this.name = name;
//		this.displayName = displayName;
//		this.dateCreated = dateCreated;
//		this.balance = balance;
//		this.interestRate = interestRate;
//		this.transactions = transactions;
//	}
	
//	public int getID() {
//		return this.ID;
//	}
	
	//without accountID
	public Account(){
		
	}
	
	public Account(String userID, String accountName, String displayName, String interestRate, String balance){
		this.name = accountName;
		this.userID = userID;
		this.name = accountName;
		this.displayName = displayName;
		this.dateCreated = Calendar.getInstance().getTimeInMillis();
		this.interestRate = Double.parseDouble(interestRate);
		this.balance = Double.parseDouble(balance);
		
	}
	
	//with accountID
		public Account(String accountID, String userID, String accountName, String displayName, String interestRate, String balance){
			this.accountID = Integer.parseInt(accountID);
			this.name = accountName;
			this.userID = userID;
			this.name = accountName;
			this.displayName = displayName;
			this.dateCreated = Calendar.getInstance().getTimeInMillis();
			this.interestRate = Double.parseDouble(interestRate);
			this.balance = Double.parseDouble(balance);
			
		}
	
	public int getAccountID() {
			return accountID;
		}

		public void setAccountID(int accountID) {
			this.accountID = accountID;
		}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return this.interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public long getDateCreated() {
		return this.dateCreated;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return this.displayName;
		
	}
	
//	public int getNumTransactions() {
//		return this.transactions.size();
//	}
	
	public void addWithdrawal() {
		//TODO add parameters and process
	}
	
	public void addDeposit() {
		//TODO add parameters and process
	}
	
//	public void undoLastTransaction() {
//		Transaction t =	(Transaction) this.transactions.removeLast();
//		//TODO process the removed transaction
//	}
//	
//	public void undoTransaction(Transaction transaction) {
//		this.transactions.remove( transaction );
//		//TODO process removed transaction
//	}
}