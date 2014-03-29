package com.example.teamjavatar.application;

import java.util.Calendar;

import com.example.teamjavatar.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

/**
 * DEPRECATED
 * 
 * @author TeamJavatar
 *
 */
public class SelectSpendingCategoryDateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_spending_category_date);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_spending_category_date, menu);
		return true;
	}
	
	public void gotoSpendingReport(View view) {
		DatePicker fromPicker = (DatePicker) findViewById(R.id.from);
		int day = fromPicker.getDayOfMonth();
		int month = fromPicker.getMonth();
		int year = fromPicker.getYear();
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		long fromDate = c.getTimeInMillis();
		DatePicker toPicker = (DatePicker) findViewById(R.id.to);
		day = toPicker.getDayOfMonth();
		month = toPicker.getMonth();
		year = toPicker.getYear();
		c = Calendar.getInstance();
		c.set(year, month, day);
		long toDate = c.getTimeInMillis();
		UserApplication app = (UserApplication) this.getApplication();
		app.setFromDate(fromDate);
		app.setToDate(toDate);
		Intent intent = new Intent(this, SpendingCategoryReportActivity.class);
		startActivity(intent);
	}
}
