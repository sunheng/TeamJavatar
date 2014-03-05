package com.example.teamjavatar.domain;

public class Withdrawal extends Transaction {
	
	private String expenseCategory;
	
	/**
	 * Constructor for creating a new withdrawal.
	 * 
	 * @param ID
	 * @param name
	 * @param amount
	 * @param effectiveDate
	 * @param expenseCategory
	 */
	public Withdrawal(int ID, String name, double amount,
			long effectiveDate, String expenseCategory) {
		super(ID, name, effectiveDate, amount);
		this.expenseCategory = expenseCategory;
	}
	
	/**
	 * Constructor for recreating an old withdrawal.
	 * 
	 * @param ID
	 * @param name
	 * @param enteredDate
	 * @param effectiveDate
	 * @param amount
	 * @param expenseCategory
	 */
	public Withdrawal(int ID, String name, long enteredDate,
			long effectiveDate, double amount, boolean isCommitted,
			String expenseCategory) {
		super(ID, name, enteredDate, effectiveDate, amount, isCommitted);
		this.expenseCategory = expenseCategory;
	}
	
	public String getExpenseCategory() {
		return this.expenseCategory;
	}
	
}