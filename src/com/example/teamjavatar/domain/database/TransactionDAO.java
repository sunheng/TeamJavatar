package com.example.teamjavatar.domain.database;

import java.util.ArrayList;
import java.util.List;


import com.example.teamjavatar.domain.Deposit;
import com.example.teamjavatar.domain.Transaction;
import com.example.teamjavatar.domain.Withdrawal;

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
	
	public List<Transaction> getTransactionsList(int accountID) {
		List<Transaction> list = new ArrayList<Transaction>();
		String[] where = {String.valueOf(accountID)};
		Cursor cursor = database.rawQuery("SELECT * FROM "
				+ SQLHelper.TABLE_TRANSACTION + " WHERE " + SQLHelper.COLUMN_ACCOUNTID
				+ " = ? ", where);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Transaction trans = cursorToTransaction(cursor);
			list.add(trans);
			cursor.moveToNext();
		}
		cursor.close();
		return list;
	}
	
//	public Transaction getTrasaction(int accountID){
//		String[] where = {String.valueOf(accountID)};
//		Cursor cursor = database.rawQuery("SELECT * FROM "
//				+ SQLHelper.TABLE_TRANSACTION + " WHERE " + SQLHelper.COLUMN_ACCOUNTID
//				+ " = ? ", where);
//		cursor.moveToFirst();
//		int ID = cursor.getInt(cursor.getColumnIndex(
//				SQLHelper.COLUMN_TRANSID));
//		long effectiveDate = cursor.getLong(cursor.getColumnIndex(
//				SQLHelper.COLUMN_EFFECTIVETIMESTAMP));
//		long enteredDate = cursor.getLong(cursor.getColumnIndex(
//				SQLHelper.COLUMN_ENTEREDTIMESTAMP));
//		double amount = cursor.getInt(cursor.getColumnIndex(
//				SQLHelper.COLUMN_AMOUNT));
//		int committed = cursor.getInt(cursor.getColumnIndex(
//				SQLHelper.COLUMN_COMMITTED));
//		String name = cursor.getString(cursor.getColumnIndex(
//				SQLHelper.COLUMN_TRANSNAME));
//		return new Deposit(ID, name, amount, enteredDate, effectiveDate, true);
//	}
	

	public Transaction cursorToTransaction(Cursor cursor) {
		int ID = cursor.getInt(cursor.getColumnIndex(
				SQLHelper.COLUMN_TRANSID));
		long effectiveTimestamp = cursor.getLong(cursor.getColumnIndex(
				SQLHelper.COLUMN_EFFECTIVETIMESTAMP));
		long enteredTimestamp = cursor.getLong(cursor.getColumnIndex(
				SQLHelper.COLUMN_ENTEREDTIMESTAMP));
		double amount = cursor.getDouble(cursor.getColumnIndex(
				SQLHelper.COLUMN_AMOUNT));
		int committed = cursor.getInt(cursor.getColumnIndex(
				SQLHelper.COLUMN_COMMITTED));
		String name = cursor.getString(cursor.getColumnIndex(
				SQLHelper.COLUMN_TRANSNAME));
		boolean isCommited;
		if(committed == 0) isCommited = false;
		else	isCommited = true;
		if(amount > 0){
			return new Deposit(ID, name, amount, enteredTimestamp, effectiveTimestamp, isCommited);
		}else{
			String category = cursor.getString(cursor.getColumnIndex(
					SQLHelper.COLUMN_CATEGORY));
			return new Withdrawal(ID, name, amount, enteredTimestamp, effectiveTimestamp, category, isCommited);
		}
	}
		
//		* @param ID
//		 * @param name
//		 * @param enteredDate
//		 * @param effectiveDate
//		 * @param amount
//		 * @param isCommitted
		
//		 + COLUMN_TRANSID + " integer primary key autoincrement, "
//	      + COLUMN_ACCOUNTID + " integer not null, "
//	      + COLUMN_ENTEREDTIMESTAMP + " integer, "
//	      + COLUMN_EFFECTIVETIMESTAMP + " integer, "
//	      + COLUMN_SOURCE + " text, "
//	      + COLUMN_AMOUNT + " real not null, "
//	      + COLUMN_REASON + " text, "
//	      + COLUMN_CATEGORY + " text, "
//	      + COLUMN_COMMITTED + " integer not null);";
}

