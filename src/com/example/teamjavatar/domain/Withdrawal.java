package com.example.teamjavatar.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

public class Withdrawal extends AbstractTransaction {

    private String category;

    /**
     * Constructor to create a new withdrawal.
     * 
     * @param ID
     * @param name
     * @param effectiveDate
     * @param amount
     * @param category
     */
    public Withdrawal(int ID, String name, long effectiveDate, double amount,
            String category) {
        super(ID, name, effectiveDate, amount);
        this.category = category;
    }

    /**
     * Constructor to recreate an old withdrawal.
     * 
     * @param ID
     * @param name
     * @param enteredDate
     * @param effectiveDate
     * @param amount
     * @param isCommited
     * @param category
     */
    public Withdrawal(int ID, String name, long enteredDate,
            long effectiveDate, double amount, boolean isCommited,
            String category) {
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