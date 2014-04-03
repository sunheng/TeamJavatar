package com.example.teamjavatar.application;

import java.util.Calendar;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.Withdrawal;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows user to make a withdrawal and manages the transaction.
 * 
 * @author Team Javatar.
 *
 */
public class WithdrawalActivity extends Activity {

    /**
     * object of type TransactionDAO.
     */
    private TransactionDAO transactionDataSource;
    
    /**
     * Object of type AccountDAO.
     */
    private AccountDAO accountDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        accountDataSource = new AccountDAO(this);
        accountDataSource.open();
        transactionDataSource = new TransactionDAO(this);
        transactionDataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.withdrawal, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataSource.close();
        transactionDataSource.close();
    }

    /**
     * Withdraws money and changes account balance.
     * 
     * @param view  unused
     */
    public void withdrawal(View view) {
        EditText source = (EditText) findViewById(R.id.transactionName);
        EditText amountField = (EditText) findViewById(R.id.amount);
        EditText categoryField = (EditText) findViewById(R.id.expenseCategory);
        String transName = source.getText().toString();
        String amount = amountField.getText().toString();
        String category = categoryField.getText().toString();
        if (transName.isEmpty() || amount.isEmpty() || category.isEmpty()) {
            errorMessage("Fields cannot be blank.");
        } else {
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            // +1 to make it inclusive
            long efDate = c.getTimeInMillis() + 1;
            UserApplication app = (UserApplication) this.getApplication();
            int accountID = app.getAccount().getID();
            transactionDataSource.addWithdrawal(accountID, transName, efDate,
                    Double.parseDouble(amount) * -1, category);
            // make a dummy withdrawal
            AbstractTransaction withdrawal = new Withdrawal(1, transName,
                    efDate, Double.parseDouble(amount), category);
            Account account = app.getAccount();
            account.commitTransaction(withdrawal);
            double newBalance = account.getBalance();
            accountDataSource.changeAccountBalance(accountID, newBalance);
            finish();
        }
    }

    /**
     * Generate an error toast with the specified message.
     * 
     * @param message   the message to display
     */
    private void errorMessage(String message) {
        Toast errorToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        errorToast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        errorToast.show();
    }

}
