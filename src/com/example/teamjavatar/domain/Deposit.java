package com.example.teamjavatar.domain;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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