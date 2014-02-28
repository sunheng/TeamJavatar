package com.example.teamjavatar.application;

import android.app.Application;

import com.example.teamjavatar.domain.IUser;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.UserDAO;

public class UserApplication extends Application {
	private IUser user;
	
	public UserApplication() {
		super();
	}
	
	public void setUser(IUser user) {
		this.user = user;
	}
	
	public IUser getUser() {
		return this.user;
	}
}