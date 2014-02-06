package com.example.teamjavatar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RegisterControlsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_controls);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_controls, menu);
		return true;
	}

}
