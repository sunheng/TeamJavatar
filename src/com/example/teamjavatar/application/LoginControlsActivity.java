package com.example.teamjavatar.application;

import com.example.teamjavatar.R;
import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.database.UserDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class manages all the login controls on the screen.
 * 
 * @author Team Javatar
 *
 */
public class LoginControlsActivity extends Activity {

    /**
     * Object of type UserDAO. 
     */
    private UserDAO userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_controls);
        // connect to db
        userDataSource = new UserDAO(this);
        userDataSource.open();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    // @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    // private void setupActionBar() {
    // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    // getActionBar().setDisplayHomeAsUpEnabled(true);
    // }
    // }

    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     * 
     * @ param menu .
     * @ return .
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_controls, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDataSource.close();
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
                // NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Lets user login. 
     * 
     * @param view .
     */
    public void login(View view) {
        EditText idField = (EditText) findViewById(R.id.user_id_field);
        EditText passField = (EditText) findViewById(R.id.password_field);
        String userID = idField.getText().toString();
        String pass = passField.getText().toString();
        AbstractUser u = userDataSource.getUser(userID, pass);
        if (u != null) {
            UserApplication app = (UserApplication) this.getApplication();
            app.setUser(u);
            // TODO transition to admin activity if user is admin
            Intent intent = new Intent(this, UserIndexActivity.class);
            startActivity(intent);
        } else {
            errorMessage("Invalid Login Credentials");
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
