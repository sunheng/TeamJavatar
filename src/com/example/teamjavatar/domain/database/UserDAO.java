package com.example.teamjavatar.domain.database;

import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Admin;
import com.example.teamjavatar.domain.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * single responsibility principle
 * only handles access to user table in database
 * 
 * command query separation principle
 * queries do not affect the database.
 */
public class UserDAO {

    /** Set up database connection. */
    private SQLiteDatabase database;
    /** Set up database and access table/columns. */
    private SQLHelper dbHelper;

    /**
     * Constructor.
     * @param context Context of the application
     */
    public UserDAO(Context context) {
        dbHelper = new SQLHelper(context);
    }

    /**
     * Open connection to the database.
     * @throws SQLException cannot establish connection with database
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /** Close database connection. */
    public void close() {
        dbHelper.close();
    }

    /**
     * Registers the specified user as a new user if the userID does not already
     * exist.
     * 
     * @param userID UserID
     * @param password Password
     * @param firstName First name
     * @param lastName Last name
     *            the user object containing the new user's information
     * @return true if the registration was successful, false if the user id
     *         already exists
     */
    public boolean registerUser(String userID, String password,
            String firstName, String lastName) {
        String[] where = new String[] {userID};
        Cursor cursor = database.rawQuery("SELECT * FROM "
                + SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID
                + " = ?", where);
        if (cursor.getCount() == 1) {
            return false;
        }
        ContentValues values = userInfoToValues(userID, password, firstName,
                lastName);
        database.insert(SQLHelper.TABLE_USERS, null, values);
        return true;
    }

    /**
     * Returns a user based on the specified user ID and password. Returns null
     * if the user ID and password do not match, otherwise returns a user or
     * admin based on the user ID.
     * 
     * @param userID User ID
     * @param password Password
     * @return User containing information from database
     */
    public AbstractUser getUser(String userID, String password) {
        String[] where = new String[] {userID, password};
        Cursor cursor = database.rawQuery("SELECT * FROM "
                + SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID
                + " = ? AND " + SQLHelper.COLUMN_PASSWORD + " = ?", where);
        if (cursor.getCount() == 0) {
            return null;
        }
        if (userID.equals("admin")) {
            return new Admin();
        }
        cursor.moveToFirst();
        return cursorToUser(cursor);
    }

    /**
     * Change password of a user except admin.
     * @param userID User ID 
     * @param password New Password
     */
    public void changeUserPassword(String userID, String password) {
        // do not change if userID == "admin"
        // TODO implement this method
    }

//    /**
//     * Create content to insert to the database.
//     * @param userID UserID
//     * @return The content from the database
//     */
//    private ContentValues userIDToValues(String userID) {
//        String[] where = new String[] {userID};
//        Cursor cursor = database.rawQuery("SELECT * FROM "
//                + SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID
//                + " = ?", where);
//        String password = cursor.getString(cursor
//                .getColumnIndex(SQLHelper.COLUMN_PASSWORD));
//        String firstName = cursor.getString(cursor
//                .getColumnIndex(SQLHelper.COLUMN_FIRSTNAME));
//        String lastName = cursor.getString(cursor
//                .getColumnIndex(SQLHelper.COLUMN_LASTNAME));
//        ContentValues values = userInfoToValues(userID, password, firstName,
//                lastName);
//        return values;
//    }

    /**
     * Create content to insert to the database.
     * @param userID UserID
     * @param password Password
     * @param firstName First name
     * @param lastName Last name
     * @return The content to put into the database
     */
    private ContentValues userInfoToValues(String userID, String password,
            String firstName, String lastName) {
        ContentValues values = new ContentValues();
        values.put(SQLHelper.COLUMN_USERID, userID);
        values.put(SQLHelper.COLUMN_PASSWORD, password);
        values.put(SQLHelper.COLUMN_FIRSTNAME, firstName);
        values.put(SQLHelper.COLUMN_LASTNAME, lastName);
        return values;
    }

    /**
     * Get the user object from database tuple.
     * @param cursor Tuple returned by database
     * @return The user object
     */
    private User cursorToUser(Cursor cursor) {
        String userID = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_USERID));
        String firstName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_FIRSTNAME));
        String lastName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_LASTNAME));
        User user = new User(userID, firstName, lastName);
        return user;
    }

}
