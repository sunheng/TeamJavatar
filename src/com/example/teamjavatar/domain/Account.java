package com.example.teamjavatar.domain;

import java.util.LinkedList;

public class Account {
	
	private int ID;
	private String name;
	private String displayName;
	private int dateCreated;
	private double balance;
	private double interestRate;
	private LinkedList<Transaction> transactions;
	
	public Account(int ID, String name, String displayName) {
		this(ID, name, displayName, 0, 0, new LinkedList<Transaction>());
	}
	
	public Account(int ID, String name, String displayName, double balance,
			double interestRate, LinkedList<Transaction> transactions) {
		this.ID = ID;
		this.name = name;
		this.displayName = displayName;
		//set date created
		this.balance = balance;
		this.interestRate = interestRate;
		this.transactions = transactions;
	}
	
	public int getID() {
		return this.ID;
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
	
	public int getDateCreated() {
		return this.dateCreated;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return this.displayName;
		
	}
	
	public void addWithdrawal() {
		//TODO add parameters and process
	}
	
	public void addDeposit() {
		//TODO add parameters and process
	}
	
	public void undoLastTransaction() {
		Transaction t =	(Transaction) this.transactions.removeLast();
		//TODO process the removed transaction
	}
	
	public void undoTransaction(Transaction transaction) {
		this.transactions.remove( transaction );
		//TODO process removed transaction
	}
}