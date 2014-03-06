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
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_COMMITTED = "committed";
	public static final String COLUMN_TRANSNAME = "transName";
	

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
		      + COLUMN_ACCOUNTID + " integer not null, "
		      + COLUMN_TRANSNAME + " text, "
		      + COLUMN_ENTEREDTIMESTAMP + " integer, "
		      + COLUMN_EFFECTIVETIMESTAMP + " integer, "
		      + COLUMN_AMOUNT + " real not null, "
		      + COLUMN_CATEGORY + " text, "
		      + COLUMN_COMMITTED + " integer not null);";
		
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
	    
	    //testing transaction insertion [testing purposes - should remove before production]
//	    ContentValues acc = new ContentValues();
//	    acc.put(SQLHelper.COLUMN_USERID, "admin");
//	    acc.put(SQLHelper.COLUMN_ACCOUNTNAME, "test");
//	    acc.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, "test");
//	    acc.put(SQLHelper.COLUMN_ACCOUNTDATECREATED, 12345667);
//	    acc.put(SQLHelper.COLUMN_BALANCE, 100);
//	    acc.put(SQLHelper.COLUMN_INTERESTRATE, 10);
//	    db.insert(SQLHelper.TABLE_ACCOUNTS, null,
//	        acc);
	    
	    //tran 1
	    ContentValues tran = new ContentValues();
	    tran.put(SQLHelper.COLUMN_ACCOUNTID, 1);
	    tran.put(SQLHelper.COLUMN_TRANSNAME, "Tname1");
	    tran.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, 123455);
	    tran.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, 1234455);
	    tran.put(SQLHelper.COLUMN_AMOUNT, 1000.25);
	    tran.put(SQLHelper.COLUMN_COMMITTED, 1);
	    db.insert(SQLHelper.TABLE_TRANSACTION, null,
	        tran);
	    
	    
	    //tran2
	    ContentValues tran2 = new ContentValues();
	    tran2.put(SQLHelper.COLUMN_ACCOUNTID, 1);
	    tran2.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, 123455);
	    tran2.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, 1234455);
	    tran2.put(SQLHelper.COLUMN_AMOUNT, -10000.25);
	    tran2.put(SQLHelper.COLUMN_COMMITTED, 1);
	    tran2.put(SQLHelper.COLUMN_TRANSNAME, "Tname2");
	    tran2.put(SQLHelper.COLUMN_CATEGORY, "cat1");
	    db.insert(SQLHelper.TABLE_TRANSACTION, null,
	        tran2);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		onCreate(db);
	}
	
}
