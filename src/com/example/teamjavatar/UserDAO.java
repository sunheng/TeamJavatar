package com.example.teamjavatar;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
	
	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	public UserDAO(Context context){
		dbHelper = new SQLHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public boolean isUser(User user){
		String[] where = new String[] {user.getUserID(), user.getPassword()};
		Cursor cursor = database.rawQuery("SELECT * FROM users where userID = ? AND password = ?", where);
		return cursor.getCount() == 1;
	}
	
	
}
