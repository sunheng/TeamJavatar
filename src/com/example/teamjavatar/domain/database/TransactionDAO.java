package com.example.teamjavatar.domain.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.Deposit;
import com.example.teamjavatar.domain.Withdrawal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Access to transaction table.
 * @author Team Javatar
 *
 */
public class TransactionDAO {
    /** Set up database connection. */
    private SQLiteDatabase database;
    /** Set up database and access table/columns. */
    private SQLHelper dbHelper;

    /**
     * Constructor.
     * @param context Context of the application
     */
    public TransactionDAO(Context context) {
        dbHelper = new SQLHelper(context);
    }

    /**
     * Open connection to the database.
     * @throws SQLException cannot establish connection with database
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /** Close database connection. */
    public void close() {
        dbHelper.close();
    }

    /**
     * Get list of transaction from accountID.
     * @param accountID Account ID
     * @return A list of transaction
     */
    public List<AbstractTransaction> getTransactionsList(int accountID) {
        List<AbstractTransaction> list = new ArrayList<AbstractTransaction>();
        String[] where = {String.valueOf(accountID) };
        Cursor cursor = database.rawQuery("SELECT * FROM "
                + SQLHelper.TABLE_TRANSACTION + " WHERE "
                + SQLHelper.COLUMN_ACCOUNTID + " = ? ", where);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AbstractTransaction trans = cursorToTransaction(cursor);
            list.add(trans);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Get a list of deposits.
     * @return A list of deposits
     */
    public List<Deposit> getDepositsList() {
        // TODO implement this method
        return null;
    }

    /**
     * Get a list of deposits with userID and fromDate.
     * @param userID UserID
     * @param fromDate FromDate
     * @param toDate ToDate
     * @return A list of deposit with those conditions
     */
    public List<Deposit> getDepositsList(String userID, long fromDate,
            long toDate) {
        // TODO implement this method
        return null;
    }

    /**
     * Get a list of withdrawals for a userID.
     * @param userID UserID
     * @return A list of withdrawals
     */
    public List<Withdrawal> getWithdrawalsList(String userID) {
        // TODO implement this method
        return null;
    }

    /**
     * Get a list of withdrawals given userID, fromDate and toDate.
     * @param userID UserID
     * @param fromDate FromDate
     * @param toDate ToDate
     * @return A list of withdrawals according to specification
     */
    public List<Withdrawal> getWithdrawalsList(String userID, long fromDate,
            long toDate) {
        List<Withdrawal> list = new LinkedList<Withdrawal>();
        Cursor cursor = database.rawQuery("SELECT t.*" + " FROM "
                + SQLHelper.TABLE_ACCOUNTS + " AS a, "
                + SQLHelper.TABLE_TRANSACTION + " AS t " + " WHERE a."
                + SQLHelper.COLUMN_USERID + " = ? " + " AND t."
                + SQLHelper.COLUMN_EFFECTIVETIMESTAMP + " BETWEEN ? AND ? "
                + " AND a." + SQLHelper.COLUMN_ACCOUNTID + " = t."
                + SQLHelper.COLUMN_ACCOUNTID + " AND t."
                + SQLHelper.COLUMN_AMOUNT + " < 0 ", new String[] {userID, String.valueOf(fromDate), String.valueOf(toDate)});
        if (cursor.getCount() <= 0) {
            return list;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AbstractTransaction trans = cursorToTransaction(cursor);
            list.add((Withdrawal) trans);
            cursor.moveToNext();
        }
        return list;
    }

    /**
     * Add deposit to account ID.
     * @param accountID AccountID
     * @param transName Transaction Name
     * @param efDate Effective Date
     * @param amount Amount
     */
    public void addDeposit(int accountID, String transName, long efDate,
            double amount) {
        ContentValues tran = new ContentValues();
        tran.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
        tran.put(SQLHelper.COLUMN_TRANSNAME, transName);
        tran.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, Calendar.getInstance()
                .getTimeInMillis());
        tran.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, efDate);
        tran.put(SQLHelper.COLUMN_AMOUNT, amount);
        tran.put(SQLHelper.COLUMN_COMMITTED, 1);
        database.insert(SQLHelper.TABLE_TRANSACTION, null, tran);
    }
    public void removeTransaction(String transName) { 
        database.delete(SQLHelper.TABLE_TRANSACTION, SQLHelper.COLUMN_TRANSNAME + "=?", new String[] {transName});
    }

    /**
     * Add a withdrawal for account ID into the database.
     * @param accountID AccountID
     * @param transName Transaction Name
     * @param efDate Effective Date
     * @param amount Amount  
     * @param category Category
     */
    public void addWithdrawal(int accountID, String transName, long efDate,
            double amount, String category) {
        ContentValues tran = new ContentValues();
        tran.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
        tran.put(SQLHelper.COLUMN_TRANSNAME, transName);
        tran.put(SQLHelper.COLUMN_ENTEREDTIMESTAMP, Calendar.getInstance()
                .getTimeInMillis());
        tran.put(SQLHelper.COLUMN_EFFECTIVETIMESTAMP, efDate);
        tran.put(SQLHelper.COLUMN_AMOUNT, amount);
        tran.put(SQLHelper.COLUMN_CATEGORY, category);
        tran.put(SQLHelper.COLUMN_COMMITTED, 1);
        database.insert(SQLHelper.TABLE_TRANSACTION, null, tran);

    }
     /**
     * Get data from query to a transaction object.
     * @param cursor The tuple from the query
     * @return Transaction object containing information from database
     */
    private AbstractTransaction cursorToTransaction(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(SQLHelper.COLUMN_TRANSID));
        long effectiveTimestamp = cursor.getLong(cursor
                .getColumnIndex(SQLHelper.COLUMN_EFFECTIVETIMESTAMP));
        long enteredTimestamp = cursor.getLong(cursor
                .getColumnIndex(SQLHelper.COLUMN_ENTEREDTIMESTAMP));
        double amount = cursor.getDouble(cursor
                .getColumnIndex(SQLHelper.COLUMN_AMOUNT));
        int committed = cursor.getInt(cursor
                .getColumnIndex(SQLHelper.COLUMN_COMMITTED));
        String name = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_TRANSNAME));
        boolean isCommited;
        if (committed == 0) {
            isCommited = false;
        } else {
            isCommited = true;
        }
        if (amount > 0) {
            return new Deposit(id, name, enteredTimestamp, effectiveTimestamp,
                    amount, isCommited);
        } else {
            String category = cursor.getString(cursor
                    .getColumnIndex(SQLHelper.COLUMN_CATEGORY));
            return new Withdrawal(id, name, enteredTimestamp,
                    effectiveTimestamp, -amount, isCommited, category);
        }
    }

}
