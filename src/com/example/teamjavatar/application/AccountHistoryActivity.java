package com.example.teamjavatar.application;

import java.util.ArrayList;
import java.util.List;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.Transaction;
import com.example.teamjavatar.domain.Withdrawal;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AccountHistoryActivity extends Activity {

	private TransactionDAO transactionDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_history);
		
		transactionDataSource = new TransactionDAO(this);
		transactionDataSource.open();
		
		UserApplication app = (UserApplication) this.getApplication();
		String userID = app.getUser().getID();
		int accountID = app.getAccount().getID();
		
		List<Transaction> accListQuery = transactionDataSource.getTransactionsList(accountID);
		List<String> list = new ArrayList<String>();
		for(Transaction a : accListQuery){
			if(a.getAmount() > 0)
				list.add("TransID " + a.getID() +" \t AccName: " + a.getName() + " \t EnDate: " + a.getEnteredDate() + " \t EfDate: " + a.getEffectiveDate() + " \t amount: " + a.getAmount() + " \t comm: " + a.isCommitted());
			else
				list.add("TransID " + a.getID() +" \t AccName: " + a.getName() + " \t EnDate: " + a.getEnteredDate() + " \t EfDate: " + a.getEffectiveDate() + " \t amount: " + a.getAmount() + " \t comm: " + a.isCommitted() + " /t categ" + ((Withdrawal)a).getCategory());

		}
		ListView listView = (ListView)findViewById( R.id.listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, list );
		listView.setAdapter( adapter );
////	CharSequence errorMessage = t.getName() + t.getAmount() + t.getEffectiveDate() + t.getEnteredDate() + t.getID() + t.getClass();
//		CharSequence errorMessage = list.size()  + " ";
//		Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
//		errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
//		errorToast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_history, menu);
		return true;
	}
	
	public void gotoWithdrawal(View view){
		Intent intent = new Intent(this, WithdrawalActivity.class);
    	startActivity(intent);
	}
	
	public void gotoDeposit(View view){
		Intent intent = new Intent(this, DepositActivity.class);
    	startActivity(intent);
	}
	
	public void manageTransaction(View view, Transaction transaction) {
		UserApplication app = (UserApplication) getApplication();
		app.setTransaction(transaction);
		Intent intent = new Intent(this, AccountHistoryActivity.class);
		startActivity(intent);
	}
	
	public void setListView() {
		UserApplication app = (UserApplication) this.getApplication();
		int accountID = app.getAccount().getID();
		List<Transaction> transactions = transactionDataSource.getTransactionsList(accountID); 
		ListView listView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.list_item, R.id.listItemTextView, transactions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnListItemClickListener());
        listView.setClickable(true);
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
