package com.example.teamjavatar.application;

import android.app.Application;

import com.example.teamjavatar.domain.AbstractUser;

public class UserApplication extends Application {
	private AbstractUser user;
	
	public UserApplication() {
		super();
	}
	
	public void setUser(AbstractUser user) {
		this.user = user;
	}
	
	public AbstractUser getUser() {
		return this.user;
	}
}