package com.example.teamjavatar.domain.database;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.Transaction;

public class AccountDAO {
	
	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	
	public AccountDAO(Context context){
		dbHelper = new SQLHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
//	public Account getAccount(int accountID) {
//		String[] where = new String[] {String.valueOf(accountID)};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_ACCOUNTS + " WHERE "
//				+ SQLHelper.COLUMN_ACCOUNTID + " = ?", where);
//		if (cursor.getCount() == 0) return null;
//		String[] result = cursor.getColumnNames();
//		LinkedList<Transaction> transactions = new LinkedList<Transaction>();
//		//TODO search through transactions table for corresponding transactions
//		//		and add them to list in order by date
//		return new Account(accountID, result[2], result[3],
//				Long.valueOf(result[4]), Double.valueOf(result[5]),
//				Double.valueOf(result[6]), transactions);
//	}
//	
//	public boolean addAccount(String userID, Account account) {
//		String[] where = new String[] {String.valueOf(account.getID())};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_ACCOUNTS + " WHERE "
//				+ SQLHelper.COLUMN_ACCOUNTID + " = ?", where);
//		if (cursor.getCount() >= 1) return false;
//		ContentValues values = new ContentValues();
//		values.put(SQLHelper.COLUMN_ACCOUNTID, account.getID());
//	    values.put(SQLHelper.COLUMN_USERID, userID);
//	    values.put(SQLHelper.COLUMN_ACCOUNTNAME, account.getName());
//	    values.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, account.getDisplayName());
//	    values.put(SQLHelper.COLUMN_ACCOUNTDATECREATED, account.getDateCreated());
//	    values.put(SQLHelper.COLUMN_BALANCE, account.getBalance());
//	    values.put(SQLHelper.COLUMN_INTERESTRATE, account.getInterestRate());
//	    database.insert(SQLHelper.TABLE_ACCOUNTS, null, values);
//		return true;
//	}
	
	public boolean addAccount(String userID, Account account){
		String[] where = new String[] {account.getUserID(), account.getDisplayName(), account.getName()};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ? AND " + SQLHelper.COLUMN_ACCOUNTDISPLAYNAME + " = ? AND " 
				+ SQLHelper.COLUMN_ACCOUNTNAME + " = ? ", where);
		if (cursor.getCount() >= 1) return false;
		
		ContentValues accountInsertData = new ContentValues();
		accountInsertData.put(SQLHelper.COLUMN_USERID, account.getUserID());
		accountInsertData.put(SQLHelper.COLUMN_ACCOUNTNAME, account.getName());
		accountInsertData.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, account.getDisplayName());
		accountInsertData.put(SQLHelper.COLUMN_ACCOUNTDATECREATED, account.getDateCreated());
		accountInsertData.put(SQLHelper.COLUMN_BALANCE, account.getBalance());
		accountInsertData.put(SQLHelper.COLUMN_INTERESTRATE, account.getInterestRate());
	    database.insert(SQLHelper.TABLE_ACCOUNTS, null,
	    		accountInsertData);
		return true;
	
//		String[] where = new String[] {"admin"};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
//				+ " = ? ", where);
//		return cursor.getCount();
	}
	
//<<<<<<< HEAD
//=======
	public Map<Integer,Account> getAccounts(String userID) {
		String[] where = new String[] {userID};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE "
				+ SQLHelper.COLUMN_USERID + " = ?", where);
		//pull all of the corresponding acocunts, and then 
		//map them based on accountID, Account
		Map<Integer,Account> accounts = new HashMap<Integer,Account>();
		return accounts;
	}
//>>>>>>> 0a953d558811dde0e57b4384ed9f607dcd7c68bb
	
	public void changeAccountBalance(int accountID, double balance) {
		//TODO
	}
	
	public void changeAccountInterestRate(int accountID, double interestRate) {
		//TODO
	}
	
	public void changeAccountNumTransactions(int accountID, int numTransactions) {
		//TODO
	}

	public List<Account> getAllAccounts(String userID) {
		List<Account> list = new ArrayList<Account>();
		String[] where = new String[] {userID};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ? ", where);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Account acc = cursorToAccount(cursor);	
			list.add(acc);
			cursor.moveToNext();
		}
		cursor.close();
		return list;
	}
	
//	public ArrayList<String> getAllAccounts(String userID) {
//		ArrayList<String> list = new ArrayList<String>();
//		String[] where = new String[] {userID};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
//				+ " = ? ", where);
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Account acc = cursorToAccount(cursor);	
//			list.add(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME)));
//			cursor.moveToNext();
//		}
//		cursor.close();
//		return list;
//	}
	
	public Account cursorToAccount(Cursor cursor){
		Account account = new Account();
		account.setDisplayName(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME)));
		account.setBalance(cursor.getDouble(cursor.getColumnIndex(SQLHelper.COLUMN_BALANCE)));
		account.setInterestRate(cursor.getDouble(cursor.getColumnIndex(SQLHelper.COLUMN_INTERESTRATE)));
		return account;
	}
}
