package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class SpendingCategoryReportActivity extends Activity {

	private TransactionDAO transactionDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_category_report);
		
		transactionDataSource = new TransactionDAO(this);
		transactionDataSource.open();
		
		UserApplication app = (UserApplication) this.getApplication();
		String userID = app.getUser().getID();
		long fromDate = app.getFromDate();
		long toDate = app.getToDate();
		String s = transactionDataSource.getSpendingCategoryReport(userID, fromDate, toDate);
//		String s = "i";
		TextView tv = (TextView) findViewById(R.id.text);
		tv.setText(s);
//		tv.setText(fromDate + " " + toDate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending_category_report, menu);
		return true;
	}
	
}
