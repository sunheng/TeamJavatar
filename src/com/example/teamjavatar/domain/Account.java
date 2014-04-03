package com.example.teamjavatar.domain;

/**
 * Account class information holder.
 * @author Team Javatar
 *
 */
public class Account implements ListItem {

    /** Account id. */
    private int id;
    /** Account Name. */
    private String name;
    /** Account Display Name. */
    private String displayName;
    /** Account Creation Date. */
    private long creationDate;
    /** Account balance. */
    private double balance;
    /** Account interest rate. */
    private double interestRate;

    /**
     * Constructor to create a new account.
     * 
     * @param idInput Id of account
     * @param nameInput Name of account
     * @param displayNameInput Display name of account
     * @param creationDateInput Date of creation
     * @param interestRateInput Account interest rate
     */
    public Account(int idInput, String nameInput, String displayNameInput, long creationDateInput,
            double interestRateInput) {
        this(idInput, nameInput, displayNameInput, creationDateInput, 0, 0);
        // transactions = new
        // TreeMap<Transaction,Transaction>(Transaction.getTimeComparator());
        // treemap to sort by time
    }

    /**
     * Constructor to recreate an old account.
     * 
     * @param idI AccountID
     * @param nameI Name
     * @param displayNameI DisplayName
     * @param creationDateI Creation Date
     * @param balanceI Balance
     * @param interestRateI Interest Rate
     */
    public Account(int idI, String nameI, String displayNameI, long creationDateI,
            double balanceI, double interestRateI) {
        this.id = idI;
        this.name = nameI;
        this.displayName = displayNameI;
        this.creationDate = creationDateI;
        this.balance = balanceI;
        this.interestRate = interestRateI;
        // this.transactions = transactions;
    }

    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Get name of account.
     * @return account's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get display name for account.
     * @return Account name
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Get account creation date.
     * @return Creation date
     */
    public long getCreationDate() {
        return this.creationDate;
    }

    /**
     * Get account balance.
     * @return Account balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Get interest rate.
     * @return interest rate of account
     */
    public double getInterestRate() {
        return this.interestRate;
    }

    /**
     * Change this account's name to the specified name.
     * 
     * This should only be used when we are changing the account details.
     * 
     * @param nameI New name
     */
    public void changeName(String nameI) {
        this.name = nameI;
    }

    /**
     * Change this account's display name to the specified name.
     * 
     * This should only be used when we are changing the account details.
     * 
     * @param displayNameI New display name
     */
    public void changeDisplayName(String displayNameI) {
        this.displayName = displayNameI;
    }

    /**
     * Change this account's interest rate to the specified rate.
     * 
     * This should only be used when we are changing the account details.
     * 
     * @param interestRateI New interest rate
     */
    public void changeInterestRate(double interestRateI) {
        this.interestRate = interestRateI;
    }

    /**
     * Plan to do to apply interest to account.
     */
    public void applyInterest() {
        // TODO created a new deposit with the interest of this account
        // not necessary to implement this; extra credit
    }

    /*
     * listov substitution principle, subclasses should work fine so long as
     * they override proper methods
     * 
     * dependency inversion principle, depends on abstraction of transaction
     * class
     * 
     * polymorphism pattern each transaction object determines on its own how it
     * will affect the balance
     * 
     * high cohesion pattern account focuses solely on how it works, not on how
     * each type of transaction works
     */
    
    /**
     * Commit transaction.
     * @param transaction The current transaction to commit
     */
    public void commitTransaction(AbstractTransaction transaction) {
        this.balance += transaction.commit();
    }

    /**
     * Roll back and return a transaction.
     * @param transaction The current transaction to roll back
     */
    public void rollbackTransaction(AbstractTransaction transaction) {
        this.balance += transaction.rollback();
    }

    @Override
    public String toString() {
        String s = "Account Name: " + displayName + " \t Balance: " + balance
                + " \t Interest Rate: " + interestRate;
        return s;
    }
}