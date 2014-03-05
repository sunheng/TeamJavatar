package com.example.teamjavatar.domain;

public class Deposit extends Transaction {
	
	private String source;
	
	/**
	 * Constructor for creating a new deposit.
	 * 
	 * @param ID
	 * @param name
	 * @param amount
	 * @param effectiveDate
	 * @param source
	 */
	public Deposit(int ID, String name, double amount,
			long effectiveDate, String source) {
		super(ID, name, effectiveDate, amount);
		this.source = source;
	}
	
	
	/**
	 * Constructor for recreating an old deposit.
	 * 
	 * @param ID
	 * @param name
	 * @param enteredDate
	 * @param effectiveDate
	 * @param amount
	 * @param isCommitted
	 * @param source
	 */
	public Deposit(int ID, String name, long enteredDate, long effectiveDate,
			double amount, boolean isCommitted, String source) {
		super(ID, name, enteredDate, effectiveDate, amount, isCommitted);
	    this.source = source;
	}
	
	public String getSource() {
		return this.source;
	}
}