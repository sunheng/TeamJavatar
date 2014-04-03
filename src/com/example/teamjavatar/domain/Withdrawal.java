package com.example.teamjavatar.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

/**
 * Withdrawal class representing withdrawals by a user.
 * @author Team Javatar
 *
 */
public class Withdrawal extends AbstractTransaction {

    /** Category. */
    private String category;

    /**
     * Constructor to create a new withdrawal.
     * 
     * @param id ID of withdrawal
     * @param name Name of withdrawal
     * @param effectiveDate Effective date of withdrawal
     * @param amount Amount of withdrawal
     * @param categoryInput Category for withdrawal
     */
    public Withdrawal(int id, String name, long effectiveDate, double amount,
            String categoryInput) {
        super(id, name, effectiveDate, amount);
        this.category = categoryInput;
    }

    /**
     * Constructor to recreate an old withdrawal.
     * 
     * @param id ID of withdrawal
     * @param name Name of withdrawal
     * @param effectiveDate Effective date of withdrawal
     * @param amount Amount of withdrawal
     * @param categoryInput Category for withdrawal
     * @param enteredDate Date withdrawal happened
     * @param isCommited Whether transaction is commited
     */
    public Withdrawal(int id, String name, long enteredDate,
            long effectiveDate, double amount, boolean isCommited,
            String categoryInput) {
        super(id, name, enteredDate, effectiveDate, amount, isCommited);
        this.category = categoryInput;
    }

    /** Get the category of withdrawal.
     * @return The category in withdrawal  
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category.
     * @param categoryInput Category of expense
     */
    public void setCategory(String categoryInput) {
        this.category = categoryInput;
    }

    @Override
    public double getAmount() {
        return -amount;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public String toString() {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(effectiveDate);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String date = dateFormat.format(c1.getTime());
        String s = "Name: " + name + " \tAmount: " + getAmount() + "\tDate: "
                + date + "\tCategory: " + category;
        return s;
    }

}