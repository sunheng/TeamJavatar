package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RollbackActivity extends Activity {
	  private AbstractTransaction transaction;
	  private TransactionDAO dao;
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_rollback);
	        UserApplication app = (UserApplication) getApplication();
	        transaction = app.getTransaction();
	        dao = new TransactionDAO(this);
	        dao.open();
	  }
	  public void deleteTransaction(View view) {
		  String name = transaction.getName();
		  UserApplication app = (UserApplication) this.getApplication();
		  Account acc = app.getAccount();
		  dao.removeTransaction(name);
		  Intent intent = new Intent(this, AccountHistoryActivity.class);
		  startActivity(intent);
	  }

}
