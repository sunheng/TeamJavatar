package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.database.UserDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows user to register a new user.
 * 
 * @author Team Javatar
 *
 */
public class RegisterControlsActivity extends Activity {

    /** The user database. */
    private UserDAO userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_controls);

        // connect to db
        userDataSource = new UserDAO(this);
        userDataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_controls, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDataSource.close();
    }

    /**
     * Registers a new user based on the fields on the Register Activity. User
     * ID must have at least 5 characters. Passwords must have at least 6
     * characters.
     * 
     * @param view  unused
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
        String pass = passField.getText().toString();
        String pass2 = pass2Field.getText().toString();
        if (firstName.length() == 0) {
            errorMessage("Please enter a first name.");
        } else if (lastName.length() == 0) {
            errorMessage("Please enter a last name.");
        } else if (userID.length() < 5) {
            errorMessage("User ID must have at least 5 characters.");
        } else if (pass.length() < 6) {
            errorMessage("Password must have at least 6 characters.");
        } else if (pass.equals(pass2)) {
            if (userDataSource.registerUser(userID, pass, firstName, lastName)) {
                Intent intent = new Intent(this, LoginControlsActivity.class);
                startActivity(intent);
            } else {
                errorMessage("User ID already exists.");
            }
        } else {
            errorMessage("Passwords do not match.");
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
