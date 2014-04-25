package com.example.teamjavatar.domain.report;

import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.Withdrawal;

/**
 * For any given account, you can view 
 * transactions over a given time period that have affected the balance of that account.
 * 
 * @author Team Javatar
 *
 */
public class TransactionHistoryReport extends AbstractReport {

    /** Star Date of type long. */
    private long startDate;
    /** End Date of type long. */
    private long endDate;
    /** A list of strings of dates */
    private List<String> dates;
    /** A list of strings of transaction types */
    private List<String> types;
    /** A List of Strings of transaction names. */
    private List<String> names;
    /** List of strings of transaction amounts. */
    private List<String> amounts;
    /** */
    private int dateMax;
    /** */
    private int typeMax;
    /** Maximum source of type int. */
    private int nameMax;
    /** Maximum amount of type int. */
    private int amountMax;
    
    /**
     * Constructor for TransactionHistoryReport.
     * 
     * @param fullName the full name of the user.
     */
    public TransactionHistoryReport(String accountName, long startDate, long endDate, List<AbstractTransaction> transactions) {
        super(accountName);
        this.startDate = startDate;
        this.endDate = endDate;
        dateMax = 8;
        String[] types = AbstractTransaction.getTransactionTypes();
        typeMax = 0;
        for (String s : types) {
            typeMax = s.length() > typeMax ? s.length() : typeMax;
        }
        if (typeMax%4 != 0) {
            typeMax += 4 - (typeMax % 4);
        }
        processTransactions(transactions);
    }

    @Override
    public String toString() {
        if (!report.equals("")) {
            return report;
        }
        String sep = "\n";
        report += leftMargin + "Transaction History Report for " + fullName + sep;
        report += leftMargin + longToDateString(startDate) + " - "
                + longToDateString(endDate) + sep;
        @SuppressWarnings("unchecked")
        List<String>[] columns = (List<String>[]) new List<?>[4];
        columns[0] = dates;
        columns[1] = types;
        columns[2] = names;
        columns[3] = amounts;
        report += listColumnsToBody(columns, new int[] {dateMax, typeMax,
                nameMax, amountMax});
        return report;
    }

    private void processTransactions(List<AbstractTransaction> transactions) {
        dates = new LinkedList<String>();
        types = new LinkedList<String>();
        names = new LinkedList<String>();
        amounts = new LinkedList<String>();
        nameMax = 0;
        amountMax = 0;
        String s = "";
        for (AbstractTransaction t : transactions) {
            dates.add(longToShortDateString(t.getEffectiveDate()));
            if (t.getAmount() > 0) {
                types.add(AbstractTransaction.DEPOSIT);
                s =  t.getName();
            } else {
                types.add(AbstractTransaction.WITHDRAWAL);
                s = ((Withdrawal) t).getCategory();
            }
            names.add(s);
            nameMax = s.length() > nameMax ? s.length() : nameMax;
            s = floatToString(Math.abs(t.getAmount()));
            amounts.add(s);
            amountMax = s.length() > amountMax ? s.length() : amountMax;
        }
        if (nameMax%4 != 0) {
            nameMax += 4 - (nameMax % 4);
        }
        if (amountMax%4 != 0) {
            amountMax += 4 - (amountMax % 4);
        }
    }

}