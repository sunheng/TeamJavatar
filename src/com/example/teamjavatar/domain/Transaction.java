package com.example.teamjavatar.domain;

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
	
	public Transaction(int ID, String name, long enteredDate, long effectiveDate, double amount, boolean isCommitted) {
		this.ID = ID;
		this.name = name;
		this.enteredDate = enteredDate;
		this.effectiveDate = effectiveDate;
		this.amount = amount;
		this.isCommitted = isCommitted;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(long enteredDate) {
		this.enteredDate = enteredDate;
	}

	public long getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(long effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isCommitted() {
		return isCommitted;
	}

	public void setCommitted(boolean isCommitted) {
		this.isCommitted = isCommitted;
	}
	
}