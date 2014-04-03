package com.example.teamjavatar.domain.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.teamjavatar.domain.Account;

/**
 * This class is used to access the account table.
 * @author Team Javatar
 *
 */
public class AccountDAO {

    /** Holds the sqlite database. */
    private SQLiteDatabase database;
    
    /** SQLhelper that can access the database. */
    private SQLHelper dbHelper;

    /** 
     * Constructor. 
     * @param context Takes in the context of the application.
     */
    public AccountDAO(Context context) {
        dbHelper = new SQLHelper(context);
    }

    /**
     * Open the database connection.
     * @throws SQLException When SQL cannot be connected.
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /** Close the database connection. */
    public void close() {
        dbHelper.close();
    }

    /**
     * Add the a new account to the database according to the specified
     * information. An account ID is automatically generated based on the row
     * ID. The newly generated account ID is returned.
     * 
     * The account creation date is not properly set in the database. Set the
     * account creation date after the new account has been created with the
     * generated account ID.
     * 
     * @param userID The id of the user
     * @param accountName Account name
     * @param displayName Display name of account
     * @param interestRate Interest Rate
     * @return The accountID
     */
    public int addAccount(String userID, String accountName,
            String displayName, double interestRate) {
        ContentValues values = new ContentValues();
        values.put(SQLHelper.COLUMN_USERID, userID);
        values.put(SQLHelper.COLUMN_ACCOUNTNAME, accountName);
        values.put(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME, displayName);
        values.put(SQLHelper.COLUMN_ACCOUNTCREATIONDATE, Calendar.getInstance()
                .getTimeInMillis());
        values.put(SQLHelper.COLUMN_BALANCE, 0);
        values.put(SQLHelper.COLUMN_INTERESTRATE, interestRate);
        long accountID = database
                .insert(SQLHelper.TABLE_ACCOUNTS, null, values);
        return (int) accountID;
    }

    /**
     * Get the object of account containing account information given the account ID.
     * @param accountID The account ID
     * @return The account object
     */
    public Account getAccount(int accountID) {
        String[] where = new String[] {Integer.toString(accountID)};
        //The query can not be put into a function because it is unique.
        Cursor cursor = dbResult(SQLHelper.TABLE_ACCOUNTS, SQLHelper.COLUMN_ACCOUNTID, where);
        cursor.moveToFirst();
        Account acc = cursorToAccount(cursor);
        cursor.close();
        return acc;
    }

    /**
     * Construct dynamic queries.
     * @param table Table name
     * @param column Column name
     * @param where String array containing conditions
     * @return Cursor containing information on a tuple
     */
    public Cursor dbResult(String table, String column, String[] where) {
        Cursor cursor = database.rawQuery("SELECT * FROM "
                + table + " WHERE "
                + column + " = ? ", where);
        return cursor;
    }
    
    /**
     * Get a hash of accounts from a user ID.
     * @param userID The user ID
     * @return A list of accounts belonging to that user.
     */
    @SuppressLint("UseSparseArrays")
    public Map<Integer, Account> getAccountsMap(String userID) {
        Map<Integer, Account> map = new HashMap<Integer, Account>();
        String[] where = new String[] {userID};
        Cursor cursor = dbResult(SQLHelper.TABLE_ACCOUNTS, SQLHelper.COLUMN_USERID, where);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account acc = cursorToAccount(cursor);
            map.put(acc.getID(), acc);
            cursor.moveToNext();
        }
        cursor.close();
        return map;
    }

    /**
     * Get a list of accounts from user ID.
     * @param userID The user ID
     * @return A list of accounts
     */
    public List<Account> getAccountsList(String userID) {
        List<Account> list = new ArrayList<Account>();
        String[] where = new String[] {userID};
        Cursor cursor = dbResult(SQLHelper.TABLE_ACCOUNTS, SQLHelper.COLUMN_USERID, where);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account acc = cursorToAccount(cursor);
            list.add(acc);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Update an account in the database.
     * @param account The account to update
     */
    public void updateAccount(Account account) {
        // TODO
    }

    /**
     * Change the account name in the database.
     * @param accountID Account ID
     * @param name The new name
     */
    public void changeAccountName(int accountID, String name) {
        // TODO
    }

    /**
     * Change the account display name in the database.
     * @param accountID Account ID
     * @param displayName The new display name
     */
    public void changeAccountDisplayName(int accountID, String displayName) {
        // TODO
    }

    /**
     * Change account creation date in the database.
     * @param accountID Account ID
     * @param creationDate The new date created
     */
    public void changeAccountCreationDate(int accountID, long creationDate) {
        ContentValues values = accountIDToValues(accountID);
        values.put(SQLHelper.COLUMN_ACCOUNTCREATIONDATE, creationDate);
        database.update(SQLHelper.TABLE_ACCOUNTS, values,
                SQLHelper.COLUMN_ACCOUNTID + " = " + accountID, null);
    }

    /**
     * Change the account balance in the database.
     * @param accountID Account ID
     * @param balance The new balance
     */
    public void changeAccountBalance(int accountID, double balance) {
        ContentValues args = new ContentValues();
        args.put(SQLHelper.COLUMN_BALANCE, balance);
        database.update(SQLHelper.TABLE_ACCOUNTS, args,
                SQLHelper.COLUMN_ACCOUNTID + "=" + accountID, null);
    }

    /**
     * Change the account interest rate in the database.
     * @param accountID Account ID 
     * @param interestRate New interest rate
     */
    public void changeAccountInterestRate(int accountID, double interestRate) {
        // TODO
    }

    /**
     * Prepare the query for an account.
     * @param accountID Account ID
     * @return The content that will be put into the SQL query
     */
    private ContentValues accountIDToValues(int accountID) {
        String[] where = new String[] {Integer.toString(accountID)};
        Cursor cursor = dbResult(SQLHelper.TABLE_ACCOUNTS, SQLHelper.COLUMN_ACCOUNTID, where);
        cursor.moveToFirst();
        String userID = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_USERID));
        String accountName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTNAME));
        String displayName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME));
        long creationDate = cursor.getLong(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTCREATIONDATE));
        double balance = cursor.getDouble(cursor
                .getColumnIndex(SQLHelper.COLUMN_BALANCE));
        double interestRate = cursor.getDouble(cursor
                .getColumnIndex(SQLHelper.COLUMN_INTERESTRATE));
        cursor.close();
        ContentValues values = accountInfoToValues(userID, accountName,
                displayName, creationDate, balance, interestRate);
        values.put(SQLHelper.COLUMN_ACCOUNTID, accountID);
        return values;
    }

    /**
     * Prepare the query for an account.
     * @param userID UserID
     * @param accountName Account Name
     * @param displayName Display Name
     * @param creationDate Creation Date
     * @param balance Balance
     * @param interestRate Interest Rate
     * @return The content that will be put into the SQL query
     */
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

    /**
     * Return account object from a database cursor.
     * @param cursor The pointer to the tuple in the result relation
     * @return The account object containing the tuple information
     */
    private Account cursorToAccount(Cursor cursor) {
        int id = cursor.getInt(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTID));
        String accountName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTNAME));
        String displayName = cursor.getString(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTDISPLAYNAME));
        long creationDate = cursor.getLong(cursor
                .getColumnIndex(SQLHelper.COLUMN_ACCOUNTCREATIONDATE));
        double balance = cursor.getDouble(cursor
                .getColumnIndex(SQLHelper.COLUMN_BALANCE));
        double interestRate = cursor.getDouble(cursor
                .getColumnIndex(SQLHelper.COLUMN_INTERESTRATE));
        Account account = new Account(id, accountName, displayName,
                creationDate, balance, interestRate);
        return account;
    }
}
