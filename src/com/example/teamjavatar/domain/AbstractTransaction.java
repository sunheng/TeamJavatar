package com.example.teamjavatar.domain;

import java.util.Calendar;
import java.util.Comparator;


/**
 * open/closed principle
 * this code does not need to change in order to extend a new transaction class.
 * 
 * @author Team Javatar
 *
 */
public abstract class AbstractTransaction implements ListItem {

    /**
     * Id of type integer.
     */
    protected int iD;
    
    
    /**
     * name of type String. 
     */
    protected String name;
    
    /**
     *  Entered date of type long.
     */
    protected long enteredDate;
    
    /**
     *  Effective date of type long.
     */
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
     * @param aID .
     * @param aName .
     * @param aEffectiveDate .
     * @param aAmount .
     */
    public AbstractTransaction(int aID, String aName, long aEffectiveDate,
            double aAmount) {
        this(aID, aName, Calendar.getInstance().getTimeInMillis(), aEffectiveDate,
                aAmount, false);
        // default isCommitted is false because the business logic should create
        // the transaction then commit it
    }

    
    /**
     * Constructor to recreate an old transaction.
     * 
     * @param aID .
     * @param aName .
     * @param aEnteredDate .
     * @param aEffectiveDate .
     * @param aAmount .
     * @param aIsCommitted .
     */
    public AbstractTransaction(int aID, String aName, long aEnteredDate,
            long aEffectiveDate, double aAmount, boolean aIsCommitted) {
        this.iD = aID;
        this.name = aName;
        this.enteredDate = aEnteredDate;
        this.effectiveDate = aEffectiveDate;
        this.amount = aAmount;
        this.isCommitted = aIsCommitted;
    }

    /**
     * Brian .
     * 
     * @return .
     */
    public static Comparator<AbstractTransaction> getTimeComparator() {
        return new TransactionTimeComparator();
    }

    @Override
    public int getID() {
        return iD;
    }

    /**
     * Sets the ID.
     * 
     * @param aID the aID.
     */
    public void setID(int aID) {
        this.iD = aID;
    }

    /**
     * Gets the name field.
     * 
     * @return name the Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param aName the name.
     */
    public void setName(String aName) {
        this.name = aName;
    }

    /**
     * @return
     */
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