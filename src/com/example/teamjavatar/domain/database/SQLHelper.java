package com.example.teamjavatar.domain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create the database on android device.
 * 
 * @author Team Javatar
 * 
 */
public class SQLHelper extends SQLiteOpenHelper {

    /** Table name users. */
    public static final String TABLE_USERS = "users";
    /** Column name userID. */
    public static final String COLUMN_USERID = "userID";
    /** Column name password. */
    public static final String COLUMN_PASSWORD = "password";
    /** Column name firstname. */
    public static final String COLUMN_FIRSTNAME = "firstName";
    /** Column name lastname. */
    public static final String COLUMN_LASTNAME = "lastName";

    /** Table name accounts. */
    public static final String TABLE_ACCOUNTS = "accounts";
    /** Column name accountID. */
    public static final String COLUMN_ACCOUNTID = "accountID";
    /** Column name accountName. */
    public static final String COLUMN_ACCOUNTNAME = "accountName";
    /** Column name accountDisplayName. */
    public static final String COLUMN_ACCOUNTDISPLAYNAME = "accountDisplayName";
    /** Column name accountCreationDate. */
    public static final String COLUMN_ACCOUNTCREATIONDATE = "accountCreationDate";
    /** Column name balance. */
    public static final String COLUMN_BALANCE = "balance";
    /** Column name interestRate. */
    public static final String COLUMN_INTERESTRATE = "interestRate";

    /** Table name transaction. */
    public static final String TABLE_TRANSACTION = "transactions";
    /** Column name transID. */
    public static final String COLUMN_TRANSID = "transID";
    /** Column name enteredTimestamp. */
    public static final String COLUMN_ENTEREDTIMESTAMP = "enteredTimestamp";
    /** Column name effectiveTimestamp. */
    public static final String COLUMN_EFFECTIVETIMESTAMP = "effectiveTimestamp";
    /** Column name amount. */
    public static final String COLUMN_AMOUNT = "amount";
    /** Column name category. */
    public static final String COLUMN_CATEGORY = "category";
    /** Column name commited. */
    public static final String COLUMN_COMMITTED = "committed";
    /** Column name transName. */
    public static final String COLUMN_TRANSNAME = "transName";

    /** String name for databse. */
    private static final String DATABASE_NAME = "teamjavatarapp.db";
    /** Specifies database version. */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor.
     * @param context The context of the application
     */
    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Query to create the user table. */
    //Cannot avoid checkstyle error due to each tables are unique.
    private static final String USER_CREATE = "create table " + TABLE_USERS
            + "(" + COLUMN_USERID + " text primary key, " + COLUMN_PASSWORD
            + " text not null, " + COLUMN_FIRSTNAME + " text, "
            + COLUMN_LASTNAME + " text);";
    
    /** Query to create the account table. */
    private static final String ACCOUNT_CREATE = "create table "
            + TABLE_ACCOUNTS + "(" + COLUMN_ACCOUNTID
            + " integer primary key autoincrement, " + COLUMN_USERID
            + " text not null, " + COLUMN_ACCOUNTNAME + " text, "
            + COLUMN_ACCOUNTDISPLAYNAME + " text, "
            + COLUMN_ACCOUNTCREATIONDATE + " integer not null, "
            + COLUMN_BALANCE + " real not null, " + COLUMN_INTERESTRATE
            + " real not null);";

    /** Query to create the transaction table. */
    private static final String TRANSACTIONS_CREATE = "create table "
            + TABLE_TRANSACTION + "(" + COLUMN_TRANSID
            + " integer primary key autoincrement, " + COLUMN_ACCOUNTID
            + " integer not null, " + COLUMN_TRANSNAME + " text, "
            + COLUMN_ENTEREDTIMESTAMP + " integer, "
            + COLUMN_EFFECTIVETIMESTAMP + " integer, " + COLUMN_AMOUNT
            + " real not null, " + COLUMN_CATEGORY + " text, "
            + COLUMN_COMMITTED + " integer not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_CREATE);
        db.execSQL(ACCOUNT_CREATE);
        db.execSQL(TRANSACTIONS_CREATE);
        // default user insertion
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
