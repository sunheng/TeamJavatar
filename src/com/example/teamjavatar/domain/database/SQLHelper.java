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
	
	public static final String TABLE_ACCOUNTS = "accounts";
	public static final String COLUMN_ACCOUNTID = "accountID";
	public static final String COLUMN_ACCOUNTNAME = "accountName";
	public static final String COLUMN_ACCOUNTDISPLAYNAME = "accountDisplayName";
	public static final String COLUMN_ACCOUNTCREATIONDATE = "accountCreationDate";
	public static final String COLUMN_BALANCE = "balance";
	public static final String COLUMN_INTERESTRATE = "interestRate";
	
	public static final String TABLE_TRANSACTION = "transactions";
	public static final String COLUMN_TRANSID = "transID";
	public static final String COLUMN_ENTEREDTIMESTAMP = "enteredTimestamp";
	public static final String COLUMN_EFFECTIVETIMESTAMP = "effectiveTimestamp";
	public static final String COLUMN_SOURCE = "source";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_REASON = "reason";
	public static final String COLUMN_CATEGORY = "category";
	

	private static final String DATABASE_NAME = "teamjavatarapp.db";
	//needs to be incremented when database schemas changes.
	private static final int DATABASE_VERSION = 1;
	  
	public SQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private static final String USER_CREATE = "create table "
		      + TABLE_USERS + "(" 
		      + COLUMN_USERID + " text primary key, " 
		      + COLUMN_PASSWORD + " text not null, "
		      + COLUMN_FIRSTNAME + " text, "
		      + COLUMN_LASTNAME + " text);";
	
	private static final String ACCOUNT_CREATE = "create table "
		      + TABLE_ACCOUNTS + "(" 
		      + COLUMN_ACCOUNTID + " integer primary key autoincrement, "
		      + COLUMN_USERID + " text not null, "
		      + COLUMN_ACCOUNTNAME + " text, "
		      + COLUMN_ACCOUNTDISPLAYNAME + " text, "
		      + COLUMN_ACCOUNTCREATIONDATE + " integer not null, "
		      + COLUMN_BALANCE + " real not null, "
		      + COLUMN_INTERESTRATE + " real not null);";
	
	private static final String TRANSACTIONS_CREATE = "create table "
		      + TABLE_TRANSACTION + "(" 
		      + COLUMN_TRANSID + " integer primary key autoincrement, "
		      + COLUMN_ACCOUNTID + " text not null, "
		      + COLUMN_ENTEREDTIMESTAMP + " integer, "
		      + COLUMN_EFFECTIVETIMESTAMP + " integer, "
		      + COLUMN_SOURCE + " text, "
		      + COLUMN_AMOUNT + " real not null, "
		      + COLUMN_REASON + " text, "
		      + COLUMN_CATEGORY + " text);";
		
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USER_CREATE);	
		db.execSQL(ACCOUNT_CREATE);	
		db.execSQL(TRANSACTIONS_CREATE);
		//default user insertion
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, "admin");
	    values.put(SQLHelper.COLUMN_PASSWORD, "pass123");
	    db.insert(SQLHelper.TABLE_USERS, null, values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		onCreate(db);
	}
	
}
