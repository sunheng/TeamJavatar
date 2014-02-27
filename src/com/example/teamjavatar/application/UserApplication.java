package com.example.teamjavatar.application;

import android.app.Application;

import com.example.teamjavatar.domain.IUser;

public class UserApplication extends Application {
	private IUser user;
	
	public void setUser(IUser user) {
		this.user = user;
	}
	
	public IUser getUser() {
		return this.user;
	}
}