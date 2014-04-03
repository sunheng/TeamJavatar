package com.example.teamjavatar.application;

import java.util.List;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.database.AccountDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Lists every account of the user in a summary.
 * 
 * @author Team Javatar
 *
 */
public class UserIndexActivity extends Activity {

    /**
     * Creator pattern creates an accountDAO object to manage accounts in the
     * database .
     */
    private AccountDAO accountDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_index);
        accountDataSource = new AccountDAO(this);
        accountDataSource.open();
        setListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_index, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataSource.close();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setListView();
        setIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView();
    }

    /**
     * Adds an account to the index. 
     * 
     * @param view  unused
     */
    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Lets user manage an account. 
     * 
     * @param view      unused
     * @param account   the account to manage
     */
    public void manageAccount(View view, Account account) {
        UserApplication app = (UserApplication) getApplication();
        app.setAccount(account);
        Intent intent = new Intent(this, AccountHistoryActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * Goes to report display screen. 
     * 
     * @param view  unused
     */
    public void goToReportDisplay(View view) {
        Intent intent = new Intent(this, ReportDisplayActivity.class);
        startActivity(intent);
    }

    /**
     * Populates the list from the database.
     */
    public void setListView() {
        UserApplication app = (UserApplication) this.getApplication();
        String userID = app.getUser().getID();
        List<Account> accounts = accountDataSource.getAccountsList(userID);
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(this,
                R.layout.list_item, R.id.listItemTextView, accounts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnListItemClickListener());
        listView.setClickable(true);
        if (accounts.size() == 0) {
            disableInstructions();
        } else {
            enableInstructions();
        }
    }

    /**
     * Makes instruction visible. 
     */
    private void enableInstructions() {
        TextView t = (TextView) findViewById(R.id.listInstructions);
        t.setVisibility(TextView.VISIBLE);
    }
    
	/**
	 * Makes instructions invisible.
	 */
    private void disableInstructions() {
        TextView t = (TextView) findViewById(R.id.listInstructions);
        t.setVisibility(TextView.INVISIBLE);
    }
    
    /**
     * Item click listener to manage the account when selected.
     * 
     * @author Team Javatar. 
     */
    private class OnListItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            Account account = (Account) parent.getItemAtPosition(position);
            manageAccount(view, account);
        }

    }

}
