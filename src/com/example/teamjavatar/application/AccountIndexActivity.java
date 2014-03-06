package com.example.teamjavatar.application;

import com.example.teamjavatar.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class AccountIndexActivity extends Activity {
	
	private int accountID;
//	private TransactionDAO transactionDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_index);
		
		
		
//		transactionDataSource = new TransactionDAO(this);
//		transactionDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.account_index, menu);
		return true;
	}
	
}
