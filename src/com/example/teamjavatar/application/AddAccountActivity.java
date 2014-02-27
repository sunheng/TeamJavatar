package com.example.teamjavatar.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.teamjavatar.R;

public class AddAccountActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_account);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_controls, menu);
		return true;
	}
}