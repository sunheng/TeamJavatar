package com.example.teamjavatar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginControlsActivity extends Activity {

	private UserDAO userDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_controls);
		
		//connect to db
		userDataSource = new UserDAO(this);
		userDataSource.open();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	private void setupActionBar() {
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//			getActionBar().setDisplayHomeAsUpEnabled(true);
//		}
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_controls, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
//			NavUtils.navigateUpFromSameTask(this);
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void login(View view){
		User u = new User();
		EditText idField = (EditText) findViewById(R.id.user_id_field);
		EditText passField = (EditText) findViewById(R.id.password_field);
		String userID = idField.getText().toString();
		String pass =  passField.getText().toString();
		u.setUserID(userID);
		u.setPassword(pass);
		if(userDataSource.isUser(u)){
			Intent intent = new Intent(this, UserIndexActivity.class);
	    	startActivity(intent);
		}else{
			TextView t = (TextView) findViewById(R.id.invalid_login);
			t.setTextColor(Color.RED);
			t.setText("Invalid Login Credentials");
		}
	}

}
