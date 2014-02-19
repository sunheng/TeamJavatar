package com.example.teamjavatar;

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
	
	public boolean isUser(User user){
		String[] where = new String[] {user.getUserID(), user.getPassword()};
		Cursor cursor = database.rawQuery("SELECT * FROM users WHERE userID = ? AND password = ?", where);
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
	public boolean registerUser(User user){
		String[] where = new String[] {user.getUserID()};
		Cursor cursor = database.rawQuery("SELECT * FROM users WHERE userID = ?", where);
		if (cursor.getCount() == 1) return false;
		dbHelper.addUser( user.getUserID(), user.getPassword(), user.getFirstname(), user.getLastname());
		return true;
	}
	
	
}
