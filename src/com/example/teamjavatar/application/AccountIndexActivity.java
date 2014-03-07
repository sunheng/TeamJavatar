package com.example.teamjavatar.application;

import java.util.List;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.Transaction;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AccountIndexActivity extends Activity {
	
	private TransactionDAO transactionDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_index);
		
//		transactionDataSource = new TransactionDAO(this);
//		transactionDataSource.open();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.account_index, menu);
		return true;
	}
	
	public void setListView() {
		UserApplication app = (UserApplication) this.getApplication();
		String userID = app.getUser().getID();
		List<Transaction> transactions = null;// = transactionDataSource.getTransactionsList(accountID); 
		ListView listView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.list_item, R.id.listItemTextView, transactions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnListItemClickListener());
        listView.setClickable(true);
	}
	
	public void manageTransaction(View view, Transaction transaction) {
		UserApplication app = (UserApplication) getApplication();
		app.setTransaction(transaction);
		Intent intent = new Intent(this, AccountHistoryActivity.class);
		startActivity(intent);
	}
	
	private class OnListItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Transaction transaction = (Transaction) parent.getItemAtPosition(position);
			manageTransaction(view, transaction);
		}
	}
	
}
