package com.example.teamjavatar.domain.database;

import com.example.teamjavatar.domain.Account;
import com.example.teamjavatar.domain.IUser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
	
	public Account getAccount(String userID, int accountID) {
		//TODO
		return null;
	}
	
	public boolean addAccount(String userID, Account account) {
		//TODO
		return false;
	}
	
	public void changeAccountBalance(String userID, Account account,
			double balance) {
		//TODO
	}
	
	public void changeAccountInterestRate(String userID, Account account,
			double interestRate) {
		//TODO
	}
	
	public void changeAccountNumTransactions(String userID, Account account,
			int numTransactions) {
		//TODO
	}
}
