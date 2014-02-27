package com.example.teamjavatar.domain;

import java.util.LinkedList;
import java.util.List;

public class Account {
	
	private int ID;
	private String name;
	private String displayName;
	private int dateCreated;
	private double balance;
	private double interestRate;
	private List<Transaction> transactions;
	
	public Account(int ID, String name, String displayName) {
		this(ID, name, displayName, 0, 0, new LinkedList<Transaction>());
	}
	
	public Account(int ID, String name, String displayName, double balance,
			double interestRate, List<Transaction> transactions) {
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
		//TODO
	}
	
	public void addDeposit() {
		//TODO
	}
	
	public void undoLastTransaction() {
		//TODO
	}
	
	public void undoTransaction(Transaction transaction) {
		//TODO
	}
}