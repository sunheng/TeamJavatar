package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		String accountName = an.getText().toString();
		String displayName = dn.getText().toString();
		String ir = in.getText().toString();
		if(accountName.length() < 1 || displayName.length() < 1 || ir.isEmpty()){
			CharSequence errorMessage = "Fields cannot be blank.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		}else{
			Double interestRate = Double.parseDouble(ir);
			if (interestRate < 0) {
				CharSequence errorMessage = "Interest cannot be negative.";
				Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				errorToast.show();
			} else {
				UserApplication app = (UserApplication) this.getApplication();
				User user = (User) app.getUser();
				String userID = user.getID();
				accountDataSource.addAccount(userID, accountName, displayName, interestRate);
				Intent intent = new Intent(this, UserIndexActivity.class);
		    	startActivity(intent);
			}
//			CharSequence errorMessage = "Can't have duplicate accounts";
//			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
//			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
//			errorToast.show();
			
//			CharSequence errorMessage = String.valueOf(accountDataSource.addAccount(userID, account));
//			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
//			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
//			errorToast.show();
		}
			
			
	}
	
	
}
