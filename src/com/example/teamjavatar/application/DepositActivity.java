package com.example.teamjavatar.application;

import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class DepositActivity extends Activity {
	private TransactionDAO transactionDataSource; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
		transactionDataSource= new TransactionDAO(this);
		transactionDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deposit, menu);
		return true;
	}
	
	public void deposit(View view){
		EditText source = (EditText) findViewById(R.id.transactionName);
		EditText amountField = (EditText) findViewById(R.id.amount);
		String transName = source.getText().toString();
		String amount = amountField.getText().toString();
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		long efDate = c.getTimeInMillis();
		//need to change accountID to dynamic
//		int accountID = 1;
		UserApplication app = (UserApplication) this.getApplication();
		int accountID = app.getAccount().getID();
		transactionDataSource.addDeposit(accountID, transName, efDate, Double.parseDouble(amount));

		Intent intent = new Intent(this, AccountHistoryActivity.class);
    	startActivity(intent);
	}
}
