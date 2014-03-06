package com.example.teamjavatar.domain;

public class Withdrawal extends Transaction {
	
	private String category;
	
	/**
	 * Constructor for creating a new withdrawal.
	 * 
	 * @param ID
	 * @param name
	 * @param amount
	 * @param effectiveDate
	 * @param expenseCategory
	 */
	public Withdrawal(int ID, String name, double amount, long enteredDate, long effectiveDate, String category, boolean isCommited) {
		super(ID, name, enteredDate, effectiveDate, amount, isCommited);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
}