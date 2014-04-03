package com.example.teamjavatar.application;

import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Account;

import android.app.Application;

/**
 * User application is a persistent set of global data used throughout the
 * entire application.
 * 
 * @author Team Javatar
 *
 */
public class UserApplication extends Application {
    /** The currently logged in user. */
    private AbstractUser currentUser;
    /** The currently viewed account. */
    private Account currentAccount;
    /** The currently viewed transaction. */
    private AbstractTransaction currentTransaction;

    /**
     * Set the current user to the specified user.
     * 
     * @param user  the user currently logged into the system
     */
    public void setUser(AbstractUser user) {
        this.currentUser = user;
    }

    /**
     * Returns the user currently logged into the system.
     * 
     * @return  the user currently logged into the system.
     */
    public AbstractUser getUser() {
        return this.currentUser;
    }

    /**
     * Set the current account to the specified account.
     * 
     * @param account   the current account
     */
    public void setAccount(Account account) {
        this.currentAccount = account;
    }

    /**
     * Returns the current account.
     * 
     * @return  the current account
     */
    public Account getAccount() {
        return this.currentAccount;
    }

    /**
     * Sets the current transaction to the specified transaction.
     * 
     * @param transaction   the current transaction
     */
    public void setTransaction(AbstractTransaction transaction) {
        this.currentTransaction = transaction;
    }

    /**
     * Returns the current transaction.
     * 
     * @return  the current transaction
     */
    public AbstractTransaction getTransaction() {
        return this.currentTransaction;
    }

}