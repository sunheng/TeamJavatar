package com.example.teamjavatar.domain.report;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.Account;

/**
 * @author Team Javatar
 * 
 * Shows all the accounts for the user with their current balance. 
 *
 */
public class AccountListingReport extends AbstractReport {

    /** today's Date of type long. */
    private long date;
    /** A List of Strings of different account names. */
    private List<String> names;
    /** List of strings of different amounts. */
    private List<String> amounts;
    /** Maximum source of type int. */
    private int nameMax;
    /** Maximum amount of type int. */
    private int amountMax;
    
    /**
     * Constructor for AccountListingReport.
     * 
     * @param fullName the full name of the user.
     */
    public AccountListingReport(String fullName, List<Account> accounts) {
        super(fullName);
        this.date = Calendar.getInstance().getTimeInMillis();
        processAccounts(accounts);
    }

    @Override
    public String toString() {
        if (!report.equals("")) {
            return report;
        }
        String sep = "\n";
        report += leftMargin + "Account Listing Report for " + fullName + sep;
        report += leftMargin + "as of " + longToDateString(date) + sep;
        @SuppressWarnings("unchecked")
        List<String>[] columns = (List<String>[]) new List<?>[2];
        columns[0] = names;
        columns[1] = amounts;
        report += listColumnsToBody(columns, new int[] {nameMax, amountMax});
        return report;
    }
    
    private void processAccounts(List<Account> accounts) {
        names = new LinkedList<String>();
        amounts = new LinkedList<String>();
        nameMax = 0;
        amountMax = 0;
        String s = null;
        for (Account a : accounts) {
            s = a.getName();
            names.add(s);
            nameMax = s.length() > nameMax ? s.length() : nameMax;
            s = floatToString(a.getBalance());
            amounts.add(s);
            amountMax = s.length() > amountMax ? s.length() : amountMax;
        }
    }

}