package com.example.teamjavatar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
	
	public void register(View view) {
		EditText passField = (EditText) findViewById(R.id.password_field);
		EditText pass2Field = (EditText) findViewById(R.id.confirm_password_field);
		String pass =  passField.getText().toString();
		String pass2 = pass2Field.getText().toString();
		TextView t = (TextView) findViewById(R.id.register_error_display);
		t.setTextColor(Color.RED);
		if ( pass.equals( pass2 ) ) {
			User u = new User();
			EditText idField = (EditText) findViewById(R.id.user_id_field);
			EditText firstNameField = (EditText) findViewById(R.id.user_first_name_field);
			EditText lastNameField = (EditText) findViewById(R.id.user_last_name_field);
			String userID = idField.getText().toString();
			String firstName = firstNameField.getText().toString();
			String lastName = lastNameField.getText().toString();
			u.setUserID( userID );
			u.setPassword( pass );
			u.setFirstname( firstName );
			u.setLastname( lastName );
			if ( userDataSource.registerUser( u ) ) {
				t.setText("");
				Intent intent = new Intent(this, LoginControlsActivity.class);
		    	startActivity(intent);
			} else {
				t.setText("User ID already exists.");
			}
		} else {
			t.setText("Passwords do not match.");
		}
		
	}

}
