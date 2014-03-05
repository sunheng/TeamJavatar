package com.example.teamjavatar.application;

import java.util.ArrayList;
import java.util.List;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.database.AccountDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserIndexActivity extends Activity {

	private AccountDAO accountDataSource;
	public static final String PREFS_NAME = "MyPreferenceFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_index);
		
		accountDataSource = new AccountDAO(this);
		accountDataSource.open();
		
		UserApplication app = (UserApplication) this.getApplication();
		String userID = app.getUser().getID();
		List<Account> accListQuery = accountDataSource.getAccountsList(userID);
		List<String> list = new ArrayList<String>();
		for(Account a : accListQuery)
			list.add("Account Name: " + a.getDisplayName() + " \t Balance: " + a.getBalance() + " \t Interest Rate: " + a.getInterestRate());
		ListView listView = (ListView)findViewById( R.id.listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, list );
		listView.setAdapter( adapter );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_index, menu);
		return true;
	}

	public void addAccount(View view){
    	Intent intent = new Intent(this, AddAccountActivity.class);
    	startActivity(intent);
	}
}
