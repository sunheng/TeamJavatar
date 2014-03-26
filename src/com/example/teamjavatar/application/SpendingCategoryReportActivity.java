package com.example.teamjavatar.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.User;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Deprecated
 * 
 * @author TeamJavatar
 *
 */
public class SpendingCategoryReportActivity extends Activity {

	private TransactionDAO transactionDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_category_report);
		
		transactionDataSource = new TransactionDAO(this);
		transactionDataSource.open();
		
		UserApplication app = (UserApplication) this.getApplication();
		User user = (User) app.getUser();
		String userID = user.getID();
		String name = user.getFirstName() + " " + user.getLastName();
		long fromDate = app.getFromDate();
		long toDate = app.getToDate();

		Calendar c = Calendar.getInstance();
        c.setTimeInMillis(fromDate);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String f = dateFormat.format(c.getTime());
		c.setTimeInMillis(toDate);
		String t = dateFormat.format(c.getTime());
		String s = "Spending Report for " + name + "\n" + f + " to " + t + "\n"
				+ transactionDataSource.getSpendingCategoryReport(userID, fromDate, toDate);
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
	
	public void goBack(View view) {
		Intent intent = new Intent(this, UserIndexActivity.class);
		startActivity(intent);
	}
	
}
