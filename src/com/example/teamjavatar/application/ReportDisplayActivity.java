package com.example.teamjavatar.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.User;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;
import com.example.teamjavatar.domain.report.Report;
import com.example.teamjavatar.domain.report.SpendingReport;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * information expert pattern
 * determines through given information which report class to call on
 */
public class ReportDisplayActivity extends Activity implements OnItemSelectedListener {
	
	private String reportType;
	private User user;
	private AccountDAO accountDataSource;
	private TransactionDAO transactionDataSource;
	private long fromDate;
	private long toDate;
	
	//this is so we can handle all report displays on the same screen
	//ideally you can select which report you want to display on the same screen

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_display);
		
		UserApplication app = (UserApplication) getApplication();
		user = (User) app.getUser();
		accountDataSource = new AccountDAO(this);
		accountDataSource.open();
		transactionDataSource = new TransactionDAO(this);
		transactionDataSource.open();
		
		setSpinner();
		setInitialDate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_display, menu);
		return true;
	}
	
	public void changeToDate(View view) {
		OnDateSetListener listener = new OnDateSetListener(){
			
			@Override
			public void onDateSet(DatePicker view, int year, int month, int day) {
				long date = datePickerToLong(view);
				setToDate(date);
			}
		};
		Dialog d = makeDialog(listener, toDate);
		d.setTitle("Set start date");
		d.show();
	}
	
	public void changeFromDate(View view) {
		OnDateSetListener listener = new OnDateSetListener(){
			
			@Override
			public void onDateSet(DatePicker view, int year, int month, int day) {
				long date = datePickerToLong(view);
				setFromDate(date);
			}
		};
		Dialog d = makeDialog(listener, fromDate);
		d.setTitle("Set end date");
		d.show();
	}
	
	public void viewReport(View view) {
		if (fromDate > toDate) {
			CharSequence errorMessage = "Start date cannot be after end date.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else if (reportType.equals(Report.SPENDING_REPORT)) {
			viewSpendingCategoryReport();
		} else if (reportType.equals(Report.INCOME_REPORT)) {
			viewIncomeReport();
		} else if (reportType.equals(Report.CASH_FLOW_REPORT)) {
			viewCashFlowReport();
		} else if (reportType.equals(Report.ACCOUNT_LISTING_REPORT)) {
			viewAccountListingReport();
		} else if (reportType.equals(Report.TRANSACTION_HISTORY_REPORT)) {
			viewTransactionHistoryReport();
		}
	}
	
	private void viewSpendingCategoryReport() {
		Report report = new SpendingReport(user, fromDate, toDate,
				transactionDataSource.getWithdrawalsList(user.getID(),
						fromDate, toDate));
		setText(report);
	}
	
	private void viewIncomeReport() {
		//TODO
		TextView t = (TextView) findViewById(R.id.reportDisplayView);
		t.setText("");
	}
	
	private void viewCashFlowReport() {
		//TODO
	}
	
	private void viewAccountListingReport() {
		//TODO
	}
	
	private void viewTransactionHistoryReport() {
		//TODO
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		reportType = (String) parent.getItemAtPosition(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//doesn't need to do anything
	}
	
	private void setSpinner() {
		Spinner s = (Spinner) findViewById(R.id.spinner1);
		String[] reportTypes = new String[]{
			Report.SPENDING_REPORT,
			Report.INCOME_REPORT,
			Report.CASH_FLOW_REPORT,
			Report.ACCOUNT_LISTING_REPORT,
			Report.TRANSACTION_HISTORY_REPORT };
		ArrayAdapter<CharSequence> adapter =
				new ArrayAdapter<CharSequence>(this,
						android.R.layout.simple_spinner_item, reportTypes);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(this);
	}
	
	private void setInitialDate() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.set(year, month, day, 0, 0, 0);
		setFromDate(c.getTimeInMillis());
		c.add(Calendar.DAY_OF_MONTH, 1);
		setToDate(c.getTimeInMillis());
	}
	
	private Dialog makeDialog(OnDateSetListener listener, long date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
	    Dialog d = new DatePickerDialog(this, listener, year, month, day);
	    d.setCancelable(false);
	    return d;
	}
	
	@SuppressLint("SimpleDateFormat")
	private void setFromDate(long date) {
		fromDate = date;
		Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String d = dateFormat.format(c.getTime());
		TextView t = (TextView) findViewById(R.id.textView1);
		t.setText("From\n" + d);
	}
	
	@SuppressLint("SimpleDateFormat")
	private void setToDate(long date) {
		toDate = date;
		Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String d = dateFormat.format(c.getTime());
		TextView t = (TextView) findViewById(R.id.textView2);
		t.setText("To\n" + d);
	}
	
	private long datePickerToLong(DatePicker picker) {
		int day = picker.getDayOfMonth();
		int month = picker.getMonth();
		int year = picker.getYear();
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, 0, 0, 0);
		return c.getTimeInMillis();
	}
	
	private void setText(Report report) {
		TextView t = (TextView) findViewById(R.id.reportDisplayView);
		t.setText(report.toString());
	}

}
