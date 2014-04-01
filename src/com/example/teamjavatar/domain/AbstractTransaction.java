package com.example.teamjavatar.domain;

import java.util.Calendar;
import java.util.Comparator;

/*
 * open/closed principle
 * this code does not need to change in order to extend a new transaction class
 */
public abstract class AbstractTransaction implements ListItem {

    protected int ID;
    protected String name;
    protected long enteredDate;
    protected long effectiveDate;
    /** Amount is positive if it's a deposit, negative if it's a withdrawal. */
    protected double amount;
    /**
     * True if this transaction is active, false if it is undone. A transaction
     * is active if it is or will be applied to an account.
     */
    protected boolean isCommitted;

    /**
     * Constructor to create a new transaction.
     * 
     * @param ID
     * @param name
     * @param effectiveDate
     * @param amount
     */
    public AbstractTransaction(int ID, String name, long effectiveDate,
            double amount) {
        this(ID, name, Calendar.getInstance().getTimeInMillis(), effectiveDate,
                amount, false);
        // default isCommitted is false because the business logic should create
        // the transaction then commit it
    }

    /**
     * Constructor to recreate an old transaction.
     * 
     * @param ID
     * @param name
     * @param enteredDate
     * @param effectiveDate
     * @param amount
     * @param isCommitted
     */
    public AbstractTransaction(int ID, String name, long enteredDate,
            long effectiveDate, double amount, boolean isCommitted) {
        this.ID = ID;
        this.name = name;
        this.enteredDate = enteredDate;
        this.effectiveDate = effectiveDate;
        this.amount = amount;
        this.isCommitted = isCommitted;
    }

    public static Comparator<AbstractTransaction> getTimeComparator() {
        return new TransactionTimeComparator();
    }

    @Override
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(long enteredDate) {
        this.enteredDate = enteredDate;
    }

    public long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCommitted() {
        return isCommitted;
    }

    public void setCommitted(boolean isCommitted) {
        this.isCommitted = isCommitted;
    }

    /*
     * any changes to commit and rollback can override
     */
    /**
     * Returns the change in account balance after an attempted commit.
     * 
     * @return
     */
    public double commit() {
        if (isCommitted)
            return 0;
        isCommitted = true;
        return (this.getAmount());
    }

    /**
     * Returns the change in account balance after an attempted rollback.
     * 
     * @return
     */
    public double rollback() {
        if (!isCommitted)
            return 0;
        isCommitted = false;
        return (-this.getAmount());
    }

    @Override
    public abstract String toString();

    /**
     * Comparator for transactions which compares them by effective date.
     * 
     * @author Brian
     * 
     */
    private static class TransactionTimeComparator implements
            Comparator<AbstractTransaction> {

        @Override
        public int compare(AbstractTransaction transaction1,
                AbstractTransaction transaction2) {
            return Long.signum(transaction2.getEffectiveDate()
                    - transaction1.getEffectiveDate());
        }

    }
}