package com.example.teamjavatar.domain.report;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.example.teamjavatar.domain.User;

public abstract class Report {
	
	public static final String SPENDING_REPORT = "Spending Report";
	public static final String INCOME_REPORT = "Income Report";
	public static final String CASH_FLOW_REPORT = "Cash Flow Report";
	public static final String ACCOUNT_LISTING_REPORT = "Account Listing Report";
	public static final String TRANSACTION_HISTORY_REPORT = "Transaction History Report";
	
	protected String userFullName;
	protected String report;
	
	public Report(User user) {
		this.userFullName = user.getFirstName() + " " + user.getLastName();
		report = "";
	}
	
	public abstract String toString();
	
	@SuppressLint("SimpleDateFormat")
	protected String longToDateString(long date) {
		Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(date);
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		String s = dateFormat.format(c1.getTime());
		return s;
	}
	
	protected String listColumnsToBody(List<Object>[] columns, int[] columnWidths) {
		String s = "";
		//TODO implement a generic way to construct the body of the report
		return s;
	}
	
	@SuppressLint("DefaultLocale")
	protected String floatToString(double d) {
		return String.format("%.2f", d);
	}
	
	protected String floatToString(int width, double d) {
		return String.format("%" + width + ".2f", d);
	}
}