package com.example.teamjavatar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterControlsActivity extends Activity {
	
	private UserDAO userDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_controls);
		
		//connect to db
		userDataSource = new UserDAO(this);
		userDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_controls, menu);
		return true;
	}
	
	/**
	 * Registers a new user based on the fields on the Register Activity.
	 * User ID must have at least 5 characters.
	 * Passwords must have at least 6 characters.
	 * 
	 * @param view
	 */
	public void register(View view) {
		EditText firstNameField = (EditText) findViewById(R.id.user_first_name_field);
		EditText lastNameField = (EditText) findViewById(R.id.user_last_name_field);
		EditText idField = (EditText) findViewById(R.id.user_id_field);
		EditText passField = (EditText) findViewById(R.id.password_field);
		EditText pass2Field = (EditText) findViewById(R.id.confirm_password_field);
		String firstName = firstNameField.getText().toString();
		String lastName = lastNameField.getText().toString();
		String userID = idField.getText().toString();
		String pass =  passField.getText().toString();
		String pass2 = pass2Field.getText().toString();
		if ( firstName.length() == 0 ) {
			CharSequence errorMessage = "Please enter a first name.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else if ( lastName.length() == 0 ) {
			CharSequence errorMessage = "Please enter a last name.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else if ( userID.length() < 5 ) {
			CharSequence errorMessage = "User ID must have at least 5 characters.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else if ( pass.length() < 6 ) {
			CharSequence errorMessage = "Password must have at least 6 characters.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else if ( !pass.equals( pass2 ) ) {
			CharSequence errorMessage = "Passwords do not match.";
			Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			errorToast.show();
		} else {
			User u = new User();
			u.setUserID( userID );
			u.setPassword( pass );
			u.setFirstname( firstName );
			u.setLastname( lastName );
			if ( userDataSource.registerUser( u ) ) {
				Intent intent = new Intent(this, LoginControlsActivity.class);
		    	startActivity(intent);
			} else {
				CharSequence errorMessage = "User ID already exists.";
				Toast errorToast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				errorToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				errorToast.show();
			}
		}
	}

}
