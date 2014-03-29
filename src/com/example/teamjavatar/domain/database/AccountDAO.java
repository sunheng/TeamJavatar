package com.example.teamjavatar.domain.database;

import java.util.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.teamjavatar.domain.Account;

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
	
	/**
	 * Add the a new account to the database according to the specified
	 * information. An account ID is automatically generated based on the row
	 * ID. The newly generated account ID is returned.  
	 * 
	 * The account creation date is not properly set in the database.
	 * Set the account creation date after the new account has been created
	 * with the generated account ID.
	 * 
	 * @param userID
	 * @param accountName
	 * @param displayName
	 * @param interestRate
	 * @return
	 */
	public int addAccount(String userID, String accountName, String displayName, double interestRate) {
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, userID);
	    values.put(SQLHelper.COLUMN_ACCOUNTNAME, accountName);
	    values.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, displayName);
	    values.put(SQLHelper.COLUMN_ACCOUNTCREATIONDATE, 
	    		Calendar.getInstance().getTimeInMillis());
	    values.put(SQLHelper.COLUMN_BALANCE, 0);
	    values.put(SQLHelper.COLUMN_INTERESTRATE, interestRate);
	    long accountID = database.insert(SQLHelper.TABLE_ACCOUNTS, null,
	    		values);
		return (int) accountID;
	}
	
	public Account getAccount(int accountID) {
		String[] where = new String[] {Integer.toString(accountID)};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_ACCOUNTID 
				+ " = ? ", where);
		cursor.moveToFirst();
		Account acc = cursorToAccount(cursor);
		cursor.close();
		return acc;
	}
	
	@SuppressLint("UseSparseArrays")
	public Map<Integer,Account> getAccountsMap(String userID) {
		Map<Integer,Account> map = new HashMap<Integer,Account>();
		String[] where = new String[] {userID};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
				+ " = ? ", where);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Account acc = cursorToAccount(cursor);
			map.put(acc.getID(), acc);
			cursor.moveToNext();
		}
		cursor.close();
		return map;
	}
	
	public List<Account> getAccountsList(String userID) {
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
	
	public void updateAccount(Account account) {
		//TODO
	}
	
	public void changeAccountName(int accountID, String name) {
		//TODO
	}
	
	public void changeAccountDisplayName(int accountID, String displayName) {
		//TODO
	}
	
	public void changeAccountCreationDate(int accountID, long creationDate) {
		ContentValues values = accountIDToValues(accountID);
		values.put(SQLHelper.COLUMN_ACCOUNTCREATIONDATE, creationDate);
		database.update(SQLHelper.TABLE_ACCOUNTS, values, SQLHelper.COLUMN_ACCOUNTID + " = " + accountID, null);
	}
	
	public void changeAccountBalance(int accountID, double balance) {
		ContentValues args = new ContentValues();
	    args.put(SQLHelper.COLUMN_BALANCE, balance);
	    database.update(SQLHelper.TABLE_ACCOUNTS, args, SQLHelper.COLUMN_ACCOUNTID + "=" + accountID, null);
	}
	
	public void changeAccountInterestRate(int accountID, double interestRate) {
		//TODO
	}
	
	private ContentValues accountIDToValues(int accountID) {
		String[] where = new String[]{Integer.toString(accountID)};
		Cursor cursor = database.rawQuery("SELECT * FROM " 
				+ SQLHelper.TABLE_ACCOUNTS + " WHERE "
				+ SQLHelper.COLUMN_ACCOUNTID + " = ?", where);
		cursor.moveToFirst();
		String userID = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_USERID));
		String accountName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTNAME));
		String displayName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTDISPLAYNAME));
		long creationDate = cursor.getLong(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTCREATIONDATE));
		double balance = cursor.getDouble(cursor.getColumnIndex(
				SQLHelper.COLUMN_BALANCE));
		double interestRate = cursor.getDouble(cursor.getColumnIndex(
				SQLHelper.COLUMN_INTERESTRATE));
		cursor.close();
		ContentValues values = accountInfoToValues(userID, accountName,
				displayName, creationDate, balance, interestRate);
		values.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
		return values;
	}
	
	private ContentValues accountInfoToValues(String userID,
			String accountName, String displayName, long creationDate,
			double balance, double interestRate) {
		ContentValues values = new ContentValues();
	    values.put(SQLHelper.COLUMN_USERID, userID);
	    values.put(SQLHelper.COLUMN_ACCOUNTNAME, accountName);
	    values.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, displayName);
	    values.put(SQLHelper.COLUMN_ACCOUNTCREATIONDATE, creationDate);
	    values.put(SQLHelper.COLUMN_BALANCE, balance);
	    values.put(SQLHelper.COLUMN_INTERESTRATE, interestRate);
	    return values;
	}
	
	private Account cursorToAccount(Cursor cursor){
		int ID = cursor.getInt(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTID));
		String accountName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTNAME));
		String displayName = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTDISPLAYNAME));
		long creationDate = cursor.getLong(cursor.getColumnIndex(
				SQLHelper.COLUMN_ACCOUNTCREATIONDATE));
		double balance = cursor.getDouble(cursor.getColumnIndex(
				SQLHelper.COLUMN_BALANCE));
		double interestRate = cursor.getDouble(cursor.getColumnIndex(
				SQLHelper.COLUMN_INTERESTRATE));
		Account account = new Account(ID, accountName, displayName,
				creationDate, balance, interestRate);
		return account;
	}

//	public void updateBalance(int accountID, double newBalance) {
//	    ContentValues args = new ContentValues();
//	    args.put(SQLHelper.COLUMN_BALANCE, newBalance);
//	    database.update(SQLHelper.TABLE_ACCOUNTS, args, SQLHelper.COLUMN_ACCOUNTID + "=" + accountID, null);
//	}
}
