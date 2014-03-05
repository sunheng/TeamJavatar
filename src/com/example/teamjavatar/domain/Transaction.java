package com.example.teamjavatar.domain;

import java.util.Calendar;

public abstract class Transaction {
	
	protected int ID;
	protected String name;
	protected long enteredDate;
	protected long effectiveDate;
	/** Amount is positive if it's a deposit, negative if it's a withdrawal. */
	protected double amount;
	/**
	 * True if this transaction is active, false if it is undone. A transaction
	 * is active if it is or will be applied to an account.
	 */
	protected boolean isCommitted;
	
	/**
	 * Constructor for creating a new transaction.
	 * 
	 * Only to be used as a super constructor.
	 * 
	 * @param ID
	 * @param name
	 * @param effectiveDate
	 * @param amount
	 */
	public Transaction(int ID, String name, long effectiveDate,
			double amount) {
		this(ID, name, Calendar.getInstance().getTimeInMillis(), effectiveDate,
				amount, true);
	}
	
	/**
	 * Constructor for recreating an old transaction.
	 * 
	 * Only to be used as a super constructor.
	 * 
	 * @param ID
	 * @param name
	 * @param enteredDate
	 * @param effectiveDate
	 * @param amount
	 * @param isCommitted
	 */
	public Transaction(int ID, String name, long enteredDate,
			long effectiveDate, double amount, boolean isCommitted) {
		this.ID = ID;
		this.name = name;
		this.enteredDate = enteredDate;
		this.effectiveDate = effectiveDate;
		this.amount = amount;
		this.isCommitted = isCommitted;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public long getEnteredDate() {
		return this.enteredDate;
	}
	
	public long getEffectiveDate() {
		return this.effectiveDate;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Returns true if this transaction is committed and is modifying the account;
	 * returns false if this transaction is undone.
	 * 
	 * @return
	 */
	public boolean isCommitted() {
		return this.isCommitted;
	}
}