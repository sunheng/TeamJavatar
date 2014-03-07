package com.example.teamjavatar.domain;

import android.text.format.DateFormat;

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
	public Withdrawal(int ID, String name,  long enteredDate, long effectiveDate, double amount, boolean isCommited, String category) {
		super(ID, name, enteredDate, effectiveDate, amount, isCommited);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		String date = DateFormat.format("M E y", effectiveDate).toString();
		String s = "Name: " + name + " \tAmount: " + amount + "\tDate: " + date + "\tCategory: " + category;
		return s;
	}

	
}