package com.example.teamjavatar.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.UserDAO;

public class AddAccountActivity extends Activity {
	
	private AccountDAO accountDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_account);

		accountDataSource = new AccountDAO(this);
		accountDataSource.open();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_controls, menu);
		return true;
	}
}