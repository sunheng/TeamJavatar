package com.example.teamjavatar.domain.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.Deposit;
import com.example.teamjavatar.domain.Transaction;
import com.example.teamjavatar.domain.Withdrawal;

import android.content.ContentValues;
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
	
	public List<Deposit> getDepositsList() {
		//TODO implement this method
		return null;
	}
	
	public List<Deposit> getDepositsList(String userID, long fromDate, long toDate) {
		//TODO implement this method
		return null;
	}
	
	public List<Withdrawal> getWithdrawalsList(String userID) {
		//TODO implement this method
		return null;
	}
	
	public List<Withdrawal> getWithdrawalsList(String userID, long fromDate, long toDate) {
		List<Withdrawal> list = new LinkedList<Withdrawal>();
		Cursor cursor = database.rawQuery(
				"SELECT t.*"
				+ " FROM " + SQLHelper.TABLE_ACCOUNTS + " AS a, " + SQLHelper.TABLE_TRANSACTION + " AS t " 
				+ " WHERE a." + SQLHelper.COLUMN_USERID + " = ? "
				+ " AND t." + SQLHelper.COLUMN_EFFECTIVETIMESTAMP + " BETWEEN ? AND ? " 
				+ " AND a." + SQLHelper.COLUMN_ACCOUNTID + " = t." + SQLHelper.COLUMN_ACCOUNTID
				+ " AND t." + SQLHelper.COLUMN_AMOUNT + " < 0 "
				, new String[] {userID, String.valueOf(fromDate), String.valueOf(toDate)});
		if(cursor.getCount() <= 0)
			return list;
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Transaction trans = cursorToTransaction(cursor);
			list.add((Withdrawal)trans);
			cursor.moveToNext();
		}
		return list;
	}
	
	public void addDeposit(int accountID, String transName, long efDate, double amount){
		ContentValues tran = new ContentValues();
	    tran.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
	    tran.put(SQLHelper.COLUMN_TRANSNAME, transName);
	    tran.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, Calendar.getInstance().getTimeInMillis());
	    tran.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, efDate);
	    tran.put(SQLHelper.COLUMN_AMOUNT, amount);
	    tran.put(SQLHelper.COLUMN_COMMITTED, 1);
	    database.insert(SQLHelper.TABLE_TRANSACTION, null,
	        tran);
	}

	public void addWithdrawal(int accountID, String transName, long efDate, double amount, String category){
		ContentValues tran = new ContentValues();
	    tran.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
	    tran.put(SQLHelper.COLUMN_TRANSNAME, transName);
	    tran.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, Calendar.getInstance().getTimeInMillis());
	    tran.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, efDate);
	    tran.put(SQLHelper.COLUMN_AMOUNT, amount);
	    tran.put(SQLHelper.COLUMN_CATEGORY, category);
	    tran.put(SQLHelper.COLUMN_COMMITTED, 1);
	    database.insert(SQLHelper.TABLE_TRANSACTION, null,
	        tran);
		
	}
	
	private Transaction cursorToTransaction(Cursor cursor) {
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
			return new Deposit(ID, name,  enteredTimestamp, effectiveTimestamp, amount, isCommited);
		}else{
			String category = cursor.getString(cursor.getColumnIndex(
					SQLHelper.COLUMN_CATEGORY));
			return new Withdrawal(ID, name,  enteredTimestamp, effectiveTimestamp, amount, isCommited, category);
		}
	}
	
}

