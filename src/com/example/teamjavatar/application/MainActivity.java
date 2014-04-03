package com.example.teamjavatar.application;

import com.example.teamjavatar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Brian .
 * 
 * @author Team Javatar. 
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Called when user clicks login.
     * 
     * Brian
     * 
     * @param view .
     */
    public void login(View view) {
        Intent intent = new Intent(this, LoginControlsActivity.class);
        startActivity(intent);
    }

    
    /**
     * Called when user clicks register. 
     * 
     * Brian
     * @param view .
     */
    public void register(View view) {
        Intent intent = new Intent(this, RegisterControlsActivity.class);
        startActivity(intent);
    }
}
