package com.example.teamjavatar.domain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERID = "userID";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_FIRSTNAME = "firstName";
	public static final String COLUMN_LASTNAME = "lastName";
	public static final String COLUMN_NUM_ACCOUNTS = "numAccounts";
	
	public static final String TABLE_ACCOUNTS = "accounts";
	public static final String COLUMN_ACCOUNTID = "accountID";
	public static final String COLUMN_ACCOUNTNAME = "accountName";
	public static final String COLUMN_ACCOUNTDISPLAYNAME = "accountDisplayName";
	public static final String COLUMN_ACCOUNTDATECREATED = "accountDateCreated";
	public static final String COLUMN_BALANCE = "balance";
	public static final String COLUMN_INTERESTRATE = "interestRate";
	public static final String COLUMN_NUM_TRANSACTIONS = "numTransactions";
	
	public static final String TABLE_TRANSACTIONS = "transactions";

	private static final String DATABASE_NAME = "teamjavatarapp.db";
	//needs to be incremented when database schemas changes.
	private static final int DATABASE_VERSION = 2;
	  
	public SQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private static final String USER_CREATE = "create table "
		      + TABLE_USERS + "(" + COLUMN_USERID
		      + " text primary key, " 
		      + COLUMN_PASSWORD + " text not null, "
		      + COLUMN_FIRSTNAME + " text, "
		      + COLUMN_LASTNAME + " text, "
		      + COLUMN_NUM_ACCOUNTS + " integer not null);";
	
	private static final String ACCOUNT_CREATE = "create table "
		      + TABLE_ACCOUNTS + "(" + COLUMN_ACCOUNTID
		      + " integer primary key, "
		      + COLUMN_USERID + " text not null, "
		      + COLUMN_ACCOUNTNAME + " text, "
		      + COLUMN_ACCOUNTDISPLAYNAME + " text, "
		      + COLUMN_ACCOUNTDATECREATED + " integer not null, "
		      + COLUMN_BALANCE + " real not null, "
		      + COLUMN_INTERESTRATE + " real not null, "
		      + COLUMN_NUM_TRANSACTIONS + " integer not null);";
		
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USER_CREATE);	
		db.execSQL(ACCOUNT_CREATE);		
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, "admin");
	    values.put(SQLHelper.COLUMN_PASSWORD, "pass123");
	    values.put(SQLHelper.COLUMN_NUM_ACCOUNTS, 0);
	    db.insert(SQLHelper.TABLE_USERS, null,
	        values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
		onCreate(db);
	}
	
}
