package com.kebaiwei.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-4-21 ÏÂÎç9:26:44 
 * @description TODO
 */
public class DBOperatorHelper extends SQLiteOpenHelper {
	private final String DATABASE_NAME="kw";
	private final String TABLE_NAME="kw_vip";
	private final String COLUMN_USERNAME="loginName";
	private final String COLUMN_PWD = "pwd";
	private final String COLUMN_TEL="phone";
	private final String COLUMN_EMAIL="email";
	public DBOperatorHelper(Context context, String name, 
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String tableString = SQLiteDatabase.findEditTable(TABLE_NAME);
		if(tableString == null || "".equals(tableString))
			db.execSQL("create table "+TABLE_NAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_USERNAME+" VARCHAR(50) NOT NULL,"+COLUMN_PWD+" VARCHAR(50) NOT NULL,"+COLUMN_TEL+" VARCHAR(50) NOT NULL,"+COLUMN_EMAIL+" VARCHAR(50) NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
