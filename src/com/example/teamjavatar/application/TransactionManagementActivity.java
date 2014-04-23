package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.database.TransactionDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * Screen to manage the current transaction.
 * Can rollback or commit a transaction.
 * 
 * @author Team Javatar.
 *
 */
public class TransactionManagementActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_management);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_management, menu);
        return true;
    }
    public void gotoRollback(View view) {
        Intent intent = new Intent(this, RollbackActivity.class);
        startActivityForResult(intent, 0);
    }

}
