package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deposit, menu);
		return true;
	}
	
	public void deposit(View view){
		EditText source = (EditText) findViewById(R.id.transactionName);
		EditText amount = (EditText) findViewById(R.id.amount);
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth() + 1;
		int year = datePicker.getYear();
//		String accountName = an.getText().toString();
//		String displayName = dn.getText().toString();
//		String ir = in.getText().toString();
		CharSequence errorMessage = month + " ";
		Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
		errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
		errorToast.show();
//		Intent intent = new Intent(this, AccountHistoryActivity.class);
//    	startActivity(intent);
	}
}
