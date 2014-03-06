package com.example.teamjavatar.domain.database;

import java.util.ArrayList;
import java.util.List;

import com.example.teamjavatar.domain.Account;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TransactionDAO {
	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	
	public TransactionDAO(Context context){
		dbHelper = new SQLHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public List<Account> getTransactionsList(String userID, int accountID) {
//		List<Account> list = new ArrayList<Account>();
//		String[] where = new String[] {userID};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_ACCOUNTS + " WHERE " + SQLHelper.COLUMN_USERID 
//				+ " = ? ", where);
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Account acc = cursorToAccount(cursor);	
//			list.add(acc);
//			cursor.moveToNext();
//		}
//		cursor.close();
//		return list;
		return new ArrayList();
	}
	
}
