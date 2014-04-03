package com.example.teamjavatar.domain.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.teamjavatar.domain.Withdrawal;

/**
 * Summarizes withdrawal transactions by category for a specified time period.
 * 
 * @author Team Javatar. 
 *
 */
public class SpendingReport extends AbstractReport {

    /**
     * Star Date of type long.
     */
    private long startDate;
    
    /**
     * End Date of type long.
     */
    private long endDate;
    
    /**
     * A List of Strings of different spending categories.
     */
    private List<String> categories;
    
    /**
     * List of Doubles of different amounts.
     */
    private List<Double> amounts;
    
    /**
     * The total of spending of type double.
     */
    private double total;
    
    /**
     * Maximum categories of type int.
     */
    private int categoryMax;
   
    /**
     * Maximum amount of type int.
     */
    private int amountMax;

    /** The left margin for the report. */
    private final String leftMargin = "    ";

    /* 
     * These are two separate instances of "    " which
     * just happen to be the same
     */
    /** The buffer space between columns of the report. */
    private final String buffer = "    ";

    /**
     * Returns the start date.
     * 
     * @return the start date.
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date.
     * 
     * @return the end date.
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Returns the list of categories.
     * 
     * @return the list of categories..
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Returns the list of amounts.
     * 
     * @return the list of amounts.
     */
    public List<Double> getAmounts() {
        return amounts;
    }

    /**
     * Returns the total.
     * 
     * @return the total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Returns the maximum categories.
     * 
     * @return the maximum number of categories.
     */
    public int getCategoryMax() {
        return categoryMax;
    }

    /**
     * Returns the maximum amounts.
     * 
     * @return the maximum number of amounts.
     */
    public int getAmountMax() {
        return amountMax;
    }

    /**
     * generates spending report.
     * 
     * @param fullName the full name.
     * @param aStartDate the start date.
     * @param aEndDate the end date.
     * @param withdrawals a list of withdrawals.
     */
    public SpendingReport(String fullName, long aStartDate, long aEndDate,
            List<Withdrawal> withdrawals) {
        super(fullName);
        this.startDate = aStartDate;
        this.endDate = aEndDate;
        processWithdrawals(withdrawals);
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
        report += leftMargin + "Spending Report for " + fullName + sep;
        report += leftMargin + longToDateString(startDate) + " - "
                + longToDateString(endDate) + sep;
        Iterator<String> c = categories.iterator();
        Iterator<Double> a = amounts.iterator();
        while (c.hasNext()) {
            report += String.format(leftMargin + "%-" + categoryMax + "s",
                    c.next()) + buffer + floatToString(amountMax, a.next())
                    + sep;
        }
        report += String.format(leftMargin + "%-" + categoryMax + "s", "Total")
                + buffer + floatToString(amountMax, total);
        return report;
    }

    /**
     * Processes the list of withdrawals. 
     * 
     * @param withdrawals the list of withdrawals. 
     */
    private void processWithdrawals(List<Withdrawal> withdrawals) {
        Map<String, Double> map = new HashMap<String, Double>();
        total = 0;
        categoryMax = 0;
        amountMax = 0;
        for (Withdrawal w : withdrawals) {
            String k = w.getCategory();
            categoryMax = k.length() > categoryMax ? k.length() : categoryMax;
            double v = -w.getAmount();
            total += v;
            String vs = floatToString(v);
            amountMax = vs.length() > amountMax ? vs.length() : amountMax;
            if (map.containsKey(k)) {
                v += map.remove(k);
            }
            map.put(k, v);
        }
        String c = "Total";
        categoryMax = c.length() > categoryMax ? c.length() : categoryMax;
        categoryMax += 4 - (categoryMax % 4);
        String t = floatToString(total);
        amountMax = t.length() > amountMax ? t.length() : amountMax;
        amountMax += 4 - (amountMax % 4);
        categories = new LinkedList<String>();
        amounts = new LinkedList<Double>();
        for (Entry<String, Double> e : map.entrySet()) {
            categories.add(e.getKey());
            amounts.add(e.getValue());
        }
    }

}
