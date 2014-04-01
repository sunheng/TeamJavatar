package com.example.teamjavatar.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

public class Deposit extends AbstractTransaction {
	
	/**
	 * Constructor to create a new deposit.
	 * 
	 * @param ID
	 * @param source
	 * @param effectiveDate
	 * @param amount
	 */
	public Deposit(int ID, String source, long effectiveDate, double amount) {
		super(ID, source, effectiveDate, amount);
	}
	
	/**
	 * Constructor to recreate an old deposit.
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

	@SuppressLint("SimpleDateFormat")
	@Override
	public String toString() {
		Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(effectiveDate);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(c1.getTime());
		String s = "Name: " + name + " \tAmount: " + amount + "\tDate: " + date;
		return s;
	}
	
}