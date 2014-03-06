package com.example.teamjavatar.application;

import android.app.Application;

import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.Transaction;

public class UserApplication extends Application {
	private AbstractUser user;
	private Account account;
	private Transaction transaction;
	
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
}