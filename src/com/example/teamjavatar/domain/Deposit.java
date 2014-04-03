package com.example.teamjavatar.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

/**
 * Deposit class for transaction.
 * @author Team Javatar
 *
 */
public class Deposit extends AbstractTransaction {

    /**
     * Constructor to create a new deposit.
     * 
     * @param id ID of deposit
     * @param source Source of deposit
     * @param effectiveDate Effective date of that deposit
     * @param amount Amount to deposit
     */
    public Deposit(int id, String source, long effectiveDate, double amount) {
        super(id, source, effectiveDate, amount);
    }

    /**
     * Constructor to recreate an old deposit.
     * 
     * @param id ID of deposit
     * @param source Source of deposit
     * @param enteredDate Entered Date for deposit
     * @param effectiveDate Effective Date for deposit
     * @param amount Amount to deposit
     * @param isCommitted Deposit is commited
     */
    public Deposit(int id, String source, long enteredDate, long effectiveDate,
            double amount, boolean isCommitted) {
        super(id, source, enteredDate, effectiveDate, amount, isCommitted);
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