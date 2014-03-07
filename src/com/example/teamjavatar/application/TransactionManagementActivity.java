package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TransactionManagementActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_management);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_management, menu);
		return true;
	}

}
