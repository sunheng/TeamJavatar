package com.example.teamjavatar.application;

import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class WithdrawalActivity extends Activity {

	private TransactionDAO transactionDataSource; 
	private AccountDAO accountDataSource; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
		
		transactionDataSource = new TransactionDAO(this);
		transactionDataSource.open();
		accountDataSource = new AccountDAO(this);
		accountDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.withdrawal, menu);
		return true;
	}
	
	public void withdrawal(View view){
		EditText source = (EditText) findViewById(R.id.transactionName);
		EditText amountField = (EditText) findViewById(R.id.amount);
		EditText categoryField = (EditText) findViewById(R.id.expenseCategory);
		String transName = source.getText().toString();
		String amount = amountField.getText().toString();
		String category = categoryField.getText().toString();
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		long efDate = c.getTimeInMillis();
		UserApplication app = (UserApplication) this.getApplication();
		int accountID = app.getAccount().getID();
		transactionDataSource.addWithdrawal(accountID, transName, efDate, Double.parseDouble(amount) * -1, category);
		//update account balance
		double newBalance = app.getAccount().getBalance() + Double.parseDouble(amount) * - 1;
		accountDataSource.updateBalance(accountID, newBalance);
				
		Intent intent = new Intent(this, AccountHistoryActivity.class);
    	startActivity(intent);
	}

}
