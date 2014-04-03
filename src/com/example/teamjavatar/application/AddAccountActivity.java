package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class allows user to add an account. 
 * 
 * @author Team Javatar
 *
 */
public class AddAccountActivity extends Activity {

    /**
     * Object of type AccountDAO.
     */
    private AccountDAO accountDataSource;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataSource.close();
    }

    /**
     * Method that adds a new account. 
     * 
     * @param view  unused
     */
    public void addNewAccount(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        EditText displayNameField = (EditText) findViewById(R.id.display_name_field);
        EditText interestField = (EditText) findViewById(R.id.interest_field);
        String accountName = nameField.getText().toString();
        String displayName = displayNameField.getText().toString();
        String interest = interestField.getText().toString();
        if (accountName.length() < 1 || displayName.length() < 1
                || interest.isEmpty()) {
            errorMessage("Fields cannot be blank.");
        } else {
            Double interestRate = Double.parseDouble(interest);
            if (interestRate < 0) {
                errorMessage("Interest cannot be negative.");
            } else {
                UserApplication app = (UserApplication) this.getApplication();
                User user = (User) app.getUser();
                String userID = user.getID();
                accountDataSource.addAccount(userID, accountName, displayName,
                        interestRate);
                finish();
            }
        }
    }

    /**
     * Generate an error toast with the specified message.
     * 
     * @param message   the message to display
     */
    private void errorMessage(String message) {
        Toast errorToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        errorToast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        errorToast.show();
    }
}
