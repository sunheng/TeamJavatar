package com.example.teamjavatar.domain;

public class Deposit extends Transaction {
	
	/**
	 * Constructor for creating a new deposit.
	 * 
	 * @param ID
	 * @param name
	 * @param amount
	 * @param effectiveDate
	 * @param source
	 */
	public Deposit(int ID, String source, long enteredDate, long effectiveDate, double amount, boolean isCommitted) {
		super(ID, source, enteredDate, effectiveDate, amount, isCommitted);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}