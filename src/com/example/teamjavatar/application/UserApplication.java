package com.example.teamjavatar.application;

import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.Transaction;

import android.app.Application;

public class UserApplication extends Application {
	private AbstractUser user;
	private Account account;
	private Transaction transaction;
	private long fromDate;
	private long toDate;
	
	public UserApplication() {
		super();
	}
	
	public void setUser(AbstractUser user) {
		this.user = user;
	}
	
	public AbstractUser getUser() {
		return this.user;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransaction() {
		return this.transaction;
	}

	public long getFromDate() {
		return fromDate;
	}

	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}

	public long getToDate() {
		return toDate;
	}

	public void setToDate(long toDate) {
		this.toDate = toDate;
	}
	
	
}