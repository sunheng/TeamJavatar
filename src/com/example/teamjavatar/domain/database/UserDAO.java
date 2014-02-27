package com.example.teamjavatar.domain.database;

import com.example.teamjavatar.domain.IUser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
	
	public boolean isUser(IUser user, String password){
		String[] where = new String[] {user.getUserID(), password};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ? AND " + SQLHelper.COLUMN_PASSWORD + " = ?", where);
		return cursor.getCount() == 1;
	}
	
	/**
	 * Registers the specified user as a new user if the userID does not
	 * already exist.
	 * 
	 * @param user	the user object containing the new user's information
	 * @return	true if the registration was successful, false if the user id
	 * 			already exists
	 */
	public boolean registerUser(IUser user, String password){
		String[] where = new String[] {user.getUserID()};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_USERS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ?", where);
		if (cursor.getCount() == 1) return false;
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, user.getUserID());
	    values.put(SQLHelper.COLUMN_PASSWORD, password);
	    values.put(SQLHelper.COLUMN_FIRSTNAME, user.getFirstName());
	    values.put(SQLHelper.COLUMN_LASTNAME, user.getLastName());
	    values.put(SQLHelper.COLUMN_NUM_ACCOUNTS, 0);
	    database.insert(SQLHelper.TABLE_USERS, null, values);
		return true;
	}
	
	public IUser getUser(IUser user, String password) {
		//TODO make isUser obsolete and replace with getUser, which returns null if user doesn't exist
		return null;
	}
	
	public void changeUserPassword(String userID, String password) {
		//do not change if userID == "admin"
		//TODO implement this method
	}
	
	public void changeUserNumAccounts(String userID, int numAccounts) {
		//TODO implement this method
	}
}
