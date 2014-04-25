package com.example.teamjavatar.domain.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;

/**
 * Abstract class for different kinds of reports. 
 * 
 * @author Team Javatar
 *
 */
public abstract class AbstractReport {

    /**
     * constant storing the string Spending Report.
     */
    public static final String SPENDING_REPORT = "Spending Report";
    
    /**
     * constant storing the string Income Report.
     */
    public static final String INCOME_REPORT = "Income Report";
    
    /**
     * constant storing the string Cash Flow Report.
     */
    public static final String CASH_FLOW_REPORT = "Cash Flow Report";
    
    /**
     * constant storing the string Account Listing Report.
     */
    public static final String ACCOUNT_LISTING_REPORT = "Account Listing Report";
    
    /**
     * constant storing the string Transaction History Report.
     */
    public static final String TRANSACTION_HISTORY_REPORT = "Transaction History Report";

    /** The left margin for the report. */
    protected final String leftMargin = "    ";

    /* 
     * These are two separate instances of "    " which
     * just happen to be the same
     */
    /** The buffer space between columns of the report. */
    protected final String buffer = "    ";
    
    protected final String sep = "\n";

    /**
     * Stores the full name. 
     */
    protected String fullName;
    
    
    /**
     * Stores name of kind of report.
     */
    protected String report;

    /**
     * Constructor for abstract report.
     * 
     * @param aFullName full Name of users.
     */
    public AbstractReport(String aFullName) {
        this.fullName = aFullName;
        report = "";
    }

    /**
     * Returns the different report types. 
     * 
     * @return the report types. 
     */
    public static String[] getReportTypes() {
        String[] s = new String[] {
                SPENDING_REPORT,
                INCOME_REPORT,
                CASH_FLOW_REPORT,
                ACCOUNT_LISTING_REPORT,
                TRANSACTION_HISTORY_REPORT};
        return s;
    }

    @Override
    public abstract String toString();

    /**
     * Returns date in a string.
     * 
     * @param date the date. 
     * @return the date in a string.
     */
    @SuppressLint("SimpleDateFormat")
    protected String longToDateString(long date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(date);
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        String s = dateFormat.format(c1.getTime());
        return s;
    }
    
    protected String longToShortDateString(long date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(date);
        DateFormat dateFormat = new SimpleDateFormat("dd MMM");
        String s = dateFormat.format(c1.getTime());
        return s;
    }

    /**
     * Returns a strings of columns.
     *
     * @param columns the columns.
     * @param columnWidths .
     * @return String of columns.
     */
    protected final String listColumnsToBody(final List<String>[] columns,
            final int[] columnWidths) {
        @SuppressWarnings("unchecked")
        Iterator<String>[] iterators =
                (Iterator<String>[]) new Iterator<?>[columns.length];
        for (int i = columns.length - 1; i >= 0; i--) {
            iterators[i] = columns[i].iterator();
        }
        StringBuilder s = new StringBuilder();
        while (iterators[0].hasNext()) {
            s.append(String.format(leftMargin + "%-" + columnWidths[0] + "s",
                    iterators[0].next()));
            for (int i = 1; i < columns.length; i++) {
                s.append(String.format(buffer + "%" + columnWidths[i] + "s",
                        iterators[i].next()));
            }
            s.append(sep);
        }
        return s.toString();
    }

    /**
     * Converts float to string.
     * 
     * @param d the float. 
     * @return the float converted to a string.
     */
    @SuppressLint("DefaultLocale")
    protected String floatToString(double d) {
        return String.format("%.2f", d);
    }

    /**
     * Converts float to string.
     * 
     * @param width the width of the float.
     * @param d the float.
     * @return the float converted to a string. 
     */
    protected String floatToString(int width, double d) {
        return String.format("%" + width + ".2f", d);
    }
}