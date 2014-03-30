package com.example.teamjavatar.domain.database;

import com.example.teamjavatar.domain.AbstractUser;
import com.example.teamjavatar.domain.Admin;
import com.example.teamjavatar.domain.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 * single responsibility principle
 * only handles access to user table in database
 * 
 * command query separation principle
 * queries do not affect the database
 */
public class UserDAO {
	
	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	public UserDAO(Context context){
		dbHelper = new SQLHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	/**
	 * Registers the specified user as a new user if the userID does not
	 * already exist.
	 * 
	 * @param user	the user object containing the new user's information
	 * @return	true if the registration was successful, false if the user id
	 * 			already exists
	 * @throws Exception 
	 */
	public boolean registerUser(String userID, String password,
			String firstName, String lastName) {
		String[] where = new String[] {userID};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID
				+ " = ?", where);
		if (cursor.getCount() == 1) return false;
		ContentValues values = userInfoToValues(userID, password, firstName,
				lastName);
	    database.insert(SQLHelper.TABLE_USERS, null, values);
		return true;
	}
	
	/**
	 * Returns a user based on the specified user ID and password.
	 * Returns null if the user ID and password do not match, otherwise returns
	 * a user or admin based on the user ID.
	 * 
	 * @param userID
	 * @param password
	 * @return
	 */
	public AbstractUser getUser(String userID, String password) {
		String[] where = new String[] {userID, password};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ? AND " + SQLHelper.COLUMN_PASSWORD + " = ?", where);
		if (cursor.getCount() == 0 ) return null;
		if (userID.equals("admin")) {
			return new Admin();
		}
		cursor.moveToFirst();
		return cursorToUser(cursor);
//		return new User(userID, "ASDf", "ASDf");
	}
	
	public void changeUserPassword(String userID, String password) {
		//do not change if userID == "admin"
		//TODO implement this method
	}
	
	private ContentValues userInfoToValues(String userID, String password,
			String firstName, String lastName) {
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, userID);
	    values.put(SQLHelper.COLUMN_PASSWORD, password);
	    values.put(SQLHelper.COLUMN_FIRSTNAME, firstName);
	    values.put(SQLHelper.COLUMN_LASTNAME, lastName);
	    return values;
	}
	
	private User cursorToUser(Cursor cursor) {
		String userID = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_USERID));
		String firstName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_FIRSTNAME));
		String lastName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_LASTNAME));
		User user = new User(userID, firstName, lastName);
		return user;
	}
	
}
