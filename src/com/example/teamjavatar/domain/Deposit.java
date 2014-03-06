package com.example.teamjavatar.domain;

public class Deposit extends Transaction {
	
//	private String source;
	
	/**
	 * Constructor for creating a new deposit.
	 * 
	 * @param ID
	 * @param name
	 * @param amount
	 * @param effectiveDate
	 * @param source
	 */
	public Deposit(int ID, String name, double amount, long enteredDate, long effectiveDate, boolean isCommitted) {
		super(ID, name, enteredDate, effectiveDate, amount, isCommitted);
	}
	
}