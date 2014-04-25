package com.example.teamjavatar.domain.report;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.teamjavatar.domain.Deposit;

/**
 * Summarizes deposit transactions by source for a specified time period.
 * 
 * @author Team Javatar
 *
 */
public class IncomeReport extends AbstractReport {

    /** Star Date of type long. */
    private long startDate;
    /** End Date of type long. */
    private long endDate;
    /** A List of Strings of different income sources. */
    private List<String> sources;
    /** List of strings of different amounts. */
    private List<String> amounts;
    /** Maximum source of type int. */
    private int sourceMax;
    /** Maximum amount of type int. */
    private int amountMax;
    
    /**
     * Constructor for IncomeReport.
     * 
     * @param fullName the full name of the user.
     */
    public IncomeReport(String fullName, long startDate, long endDate, List<Deposit> deposits) {
        super(fullName);
        this.startDate = startDate;
        this.endDate = endDate;
        processDeposits(deposits);
    }

    /*
     * (non-Javadoc)
     * The following string related checkstyle errors can be ignored because
     * %- is a string format string, and "Total" is a constant that will
     * never be changed
     */
    @Override
    public String toString() {
        if (!report.equals("")) {
            return report;
        }
        String sep = "\n";
        report += leftMargin + "Income Report for " + fullName + sep;
        report += leftMargin + longToDateString(startDate) + " - "
                + longToDateString(endDate) + sep;
        @SuppressWarnings("unchecked")
        List<String>[] columns = (List<String>[]) new List<?>[2];
        columns[0] = sources;
        columns[1] = amounts;
        report += listColumnsToBody(columns, new int[] {sourceMax, amountMax});
        return report;
    }

    /**
     * Processes the list of deposits.
     * 
     * @param withdrawals the list of deposits.
     */
    private void processDeposits(List<Deposit> deposits) {
        Map<String, Double> map = new HashMap<String, Double>();
        double total = 0;
        sourceMax = 0;
        amountMax = 0;
        for (Deposit d : deposits) {
            String k = d.getName();
            sourceMax = k.length() > sourceMax ? k.length() : sourceMax;
            double v = d.getAmount();
            total += v;
            String vs = floatToString(v);
            amountMax = vs.length() > amountMax ? vs.length() : amountMax;
            if (map.containsKey(k)) {
                v += map.remove(k);
            }
            map.put(k, v);
        }
        String c = "Total";
        sourceMax = c.length() > sourceMax ? c.length() : sourceMax;
        if (sourceMax%4 != 0) {
            sourceMax += 4 - (sourceMax % 4);
        }
        String t = floatToString(total);
        amountMax = t.length() > amountMax ? t.length() : amountMax;
        if (amountMax%4 != 0) {
            amountMax += 4 - (amountMax % 4);
        }
        sources = new LinkedList<String>();
        amounts = new LinkedList<String>();
        for (Entry<String, Double> e : map.entrySet()) {
            sources.add(e.getKey());
            amounts.add(floatToString(e.getValue()));
        }
        sources.add("Total");
        amounts.add(floatToString(total));
    }

}