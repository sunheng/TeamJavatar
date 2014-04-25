package com.example.teamjavatar.domain.report;

import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.AbstractTransaction;
/**
 * Compares withdrawals to deposits for a given period of time.
 * 
 * @author Team Javatar. 
 *
 */
public class CashFlowReport extends AbstractReport {

    /** Star Date of type long. */
    private long startDate;
    /** End Date of type long. */
    private long endDate;
    /** A List of Strings of differen totals. */
    private List<String> totals;
    /** List of strings of different amounts. */
    private List<String> amounts;
    /** Maximum source of type int. */
    private int totalsMax;
    /** Maximum amount of type int. */
    private int amountMax;
    
    /**
     * Constructor for CashFlowReport.
     * 
     * @param fullName the full name of the user.
     */
    public CashFlowReport(String fullName, long startDate, long endDate,
            List<AbstractTransaction> transactions) {
        super(fullName);
        this.startDate = startDate;
        this.endDate = endDate;
        totals = new LinkedList<String>();
        totals.add("Income");
        totals.add("Expenses");
        totals.add("Flow");
        totalsMax = 12;
        amounts = new LinkedList<String>();
        processTransactions(transactions);
    }

    @Override
    public String toString() {
        if (!report.equals("")) {
            return report;
        }
        String sep = "\n";
        report += leftMargin + "Cash Flow Report for " + fullName + sep;
        report += leftMargin + longToDateString(startDate) + " - "
                + longToDateString(endDate) + sep;
        @SuppressWarnings("unchecked")
        List<String>[] columns = (List<String>[]) new List<?>[2];
        columns[0] = totals;
        columns[1] = amounts;
        report += listColumnsToBody(columns, new int[] {totalsMax, amountMax});
        return report;
    }
    
    private void processTransactions(List<AbstractTransaction> transactions) {
        double income = 0;
        double expenses = 0;
        amountMax = 0;
        for (AbstractTransaction t : transactions) {
            if (t.getAmount() > 0) {
                income += t.getAmount();
            } else {
                expenses -= t.getAmount();
            }
        }
        double flow = income - expenses;
        amounts.add(floatToString(income));
        amounts.add(floatToString(expenses));
        amounts.add(floatToString(flow));
        for (String s : amounts) {
            amountMax = s.length() > amountMax ? s.length() : amountMax;
        }
        amountMax += 4 - (amountMax%4);
    }
}