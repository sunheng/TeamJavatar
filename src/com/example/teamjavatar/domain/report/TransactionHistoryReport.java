package com.example.teamjavatar.domain.report;

import java.util.List;

import com.example.teamjavatar.domain.AbstractTransaction;

/**
 * For any given account, you can view 
 * transactions over a given time period that have affected the balance of that account.
 * 
 * @author Team Javatar
 *
 */
public class TransactionHistoryReport extends AbstractReport {

    /**
     * Constructor for TransactionHistoryReport.
     * 
     * @param fullName the full name of the user.
     */
    public TransactionHistoryReport(String fullName, long startDate, long endDate, List<AbstractTransaction> transactions) {
        super(fullName);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}