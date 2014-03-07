package com.example.teamjavatar.domain;

import android.text.format.DateFormat;

public class Deposit extends Transaction {
	
	/**
	 * Constructor for creating a new deposit.
	 * 
	 * @param ID
	 * @param source
	 * @param enteredDate
	 * @param effectiveDate
	 * @param amount
	 * @param isCommitted
	 */
	public Deposit(int ID, String source, long enteredDate, long effectiveDate, double amount, boolean isCommitted) {
		super(ID, source, enteredDate, effectiveDate, amount, isCommitted);
	}

	@Override
	public String toString() {
		String date = DateFormat.format("M E y", effectiveDate).toString();
		String s = "Name: " + name + " \tAmount: " + amount + "\tDate: " + date;
		return s;
	}
	
}