package com.example.teamjavatar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERID = "userID";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_FIRSTNAME = "firstname";
	public static final String COLUMN_LASTNAME = "lastname";

	private static final String DATABASE_NAME = "teamjavatarapp.db";
	//needs to be incremented when database schemas changes.
	private static final int DATABASE_VERSION = 1;
	  
	public SQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_USERS + "(" + COLUMN_USERID
		      + " text primary key, " 
		      + COLUMN_PASSWORD + " text not null, "
		      + COLUMN_FIRSTNAME + " text, "
		      + COLUMN_LASTNAME + " text);";
		
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);	
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, "admin");
	    values.put(SQLHelper.COLUMN_PASSWORD, "pass123");
	    db.insert(SQLHelper.TABLE_USERS, null,
	        values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}
	
	/**
	 * Adds the specified information to the user database as a new user. 
	 * This does not check if a user already exists.
	 * 
	 * @param userID	user id
	 * @param password	password
	 * @param firstName	first name
	 * @param lastName	last name
	 */
	public void addUser( String userID, String password, String firstName,
			String lastName ) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, userID);
	    values.put(SQLHelper.COLUMN_PASSWORD, password);
	    values.put(SQLHelper.COLUMN_FIRSTNAME, firstName);
	    values.put(SQLHelper.COLUMN_LASTNAME, lastName);
	    db.insert(SQLHelper.TABLE_USERS, null,
	        values);
	}
}
