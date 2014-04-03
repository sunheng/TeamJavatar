package com.example.teamjavatar.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.User;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;
import com.example.teamjavatar.domain.report.AbstractReport;
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

/**
 * Information expert pattern
 * determines through given information which report class to call on.
 *
 * @author Team Javatar
 *
 * Class Fan Out Complexity Error
 * Relies on a lot of different classes because there are many different types
 * of reports, and it is nicer to display all reports on a single page rather
 * than on seperate pages.
 */
public class ReportDisplayActivity extends Activity
    implements OnItemSelectedListener {

    /**
     * Name of the report type.
     */
    private String reportType;

    /**
     * Object of type user.
     */
    private User user;

    /**
     * Account database.
     */
    private AccountDAO accountDataSource;

    /**
     * Transaction database.
     */
    private TransactionDAO transactionDataSource;

    /**
     * The beginning date.
     */
    private long fromDate;

    /**
     * The ending date.
     */
    private long toDate;
    
    /** The date format string. */
    private final String dateFormatString = "MM-dd-yyyy";

    // this is so we can handle all report displays on the same screen
    // ideally you can select which report you want to display on the same
    // screen

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataSource.close();
        transactionDataSource.close();
    }

    /**
     * Changes format of long date to date.
     *
     * @param view .
     */
    public void changeToDate(View view) {
        OnDateSetListener listener = new OnDateSetListener() {

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

    /**
     * Changes format of date back to long.
     * 
     * @param view .
     * 
     */
    public void changeFromDate(View view) {
        OnDateSetListener listener = new OnDateSetListener() {

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

    /**
     * Allows user to view report. 
     * 
     * @param view .
     */
    public void viewReport(View view) {
        if (fromDate > toDate) {
            CharSequence errorMessage = "Start date cannot be after end date.";
            Toast errorToast = Toast.makeText(this, errorMessage,
                    Toast.LENGTH_SHORT);
            errorToast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL,
                    0, 0);
            errorToast.show();
        } else if (reportType.equals(AbstractReport.SPENDING_REPORT)) {
            viewSpendingCategoryReport();
        } else if (reportType.equals(AbstractReport.INCOME_REPORT)) {
            viewIncomeReport();
        } else if (reportType.equals(AbstractReport.CASH_FLOW_REPORT)) {
            viewCashFlowReport();
        } else if (reportType.equals(AbstractReport.ACCOUNT_LISTING_REPORT)) {
            viewAccountListingReport();
        } else if (reportType.equals(AbstractReport.TRANSACTION_HISTORY_REPORT)) {
            viewTransactionHistoryReport();
        }
    }

    /**
     * Allows user to view Spending category report. 
     * 
     */
    private void viewSpendingCategoryReport() {
        AbstractReport report = new SpendingReport(user.getFullName(),
                fromDate, toDate, transactionDataSource.getWithdrawalsList(
                        user.getID(), fromDate, toDate));
        setText(report);
    }

    
    /**
     * Allows user to view Incomereport. 
     * 
     */
    private void viewIncomeReport() {
        // TODO
        TextView t = (TextView) findViewById(R.id.reportDisplayView);
        t.setText("");
    }

    /**
     * Allows user to view Cash Flow report. 
     * 
     */
    private void viewCashFlowReport() {
        // TODO
    }

    /**
     * Allows user to view Account Listing report. 
     * 
     */
    private void viewAccountListingReport() {
        // TODO
    }

    /**
     * Allows user to view Transaction History report. 
     * 
     */
    private void viewTransactionHistoryReport() {
        // TODO
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        reportType = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // doesn't need to do anything
    }

    /**
     * Populate the spinner with the names of the types of reports.
     */
    private void setSpinner() {
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        String[] reportTypes = AbstractReport.getReportTypes();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this, android.R.layout.simple_spinner_item, reportTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
    }

    /**
     * Sets the initial to and from dates.
     */
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

    /**
     * @param listener .
     * @param date .
     * @return .
     */
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

    /**
     * Allows user to set from date. 
     * 
     * @param date  the date in long form
     */
    @SuppressLint("SimpleDateFormat")
    private void setFromDate(long date) {
        fromDate = date;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        String d = dateFormat.format(c.getTime());
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText("From\n" + d);
    }

    /**
     * Allows user to set to date.
     * 
     * @param date  the date in long form
     */
    @SuppressLint("SimpleDateFormat")
    private void setToDate(long date) {
        toDate = date;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        String d = dateFormat.format(c.getTime());
        TextView t = (TextView) findViewById(R.id.textView2);
        t.setText("To\n" + d);
    }

    /**
     * Convert the specified date picker to a long.
     * 
     * @param picker    the date picker to convert
     * @return  the long representing the time in the date picker
     */
    private long datePickerToLong(DatePicker picker) {
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, 0, 0, 0);
        return c.getTimeInMillis();
    }

    /**
     * Set the display text to the specified report.
     * 
     * @param report    the report to display
     */
    private void setText(AbstractReport report) {
        TextView t = (TextView) findViewById(R.id.reportDisplayView);
        t.setText(report.toString());
    }

}
