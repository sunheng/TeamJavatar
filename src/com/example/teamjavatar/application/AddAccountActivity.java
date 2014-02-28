package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.R.layout;
import com.example.teamjavatar.R.menu;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.Account;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddAccountActivity extends Activity {

	private AccountDAO accountDataSource;
	public static final String PREFS_NAME = "MyPreferenceFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_account);
		
		accountDataSource = new AccountDAO(this);
		accountDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_account, menu);
		return true;
	}

	public void addNewAccount(View view){
		EditText an = (EditText) findViewById(R.id.name_field);
		EditText dn = (EditText) findViewById(R.id.display_name_field);
		EditText in = (EditText) findViewById(R.id.interest_field);
		EditText ba = (EditText) findViewById(R.id.balance_field);
		String accountName = an.getText().toString();
		String displayName = dn.getText().toString();
		String interestRate = in.getText().toString();
		String balance = ba.getText().toString();
		
		if(accountName.length() < 1 || displayName.length() < 1 || interestRate.isEmpty() || balance.isEmpty()){
			CharSequence errorMessage = "Fields cannot be blank";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		}else{
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			String userID = settings.getString("userid", "Default");
			Account account = new Account(userID, accountName, displayName, interestRate, balance);
			if(accountDataSource.addAccount(userID, account)){
				Intent intent = new Intent(this, UserIndexActivity.class);
		    	startActivity(intent);
			}else{
				CharSequence errorMessage = "Can't have duplicate accounts";
				Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				errorToast.show();
			}
//			CharSequence errorMessage = String.valueOf(accountDataSource.addAccount(userID, account));
//			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
//			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
//			errorToast.show();
		}
			
			
	}
	
	
}
