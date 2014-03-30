package com.example.teamjavatar.application;

import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Account;

import android.app.Application;

public class UserApplication extends Application {
	private AbstractUser user;
	private Account account;
	private AbstractTransaction transaction;
	
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
	
	public void setTransaction(AbstractTransaction transaction) {
		this.transaction = transaction;
	}
	
	public AbstractTransaction getTransaction() {
		return this.transaction;
	}
	
}