package com.kebaiwei.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kebaiwei.entity.User;

/** 
 * @author 陈明 
 * @date 2015-4-21 下午9:26:10 
 * @description TODO
 */
public class DBOperator {
	private final String DATABASE_NAME="kbw";
	private SQLiteDatabase mSqlLiteDatabase;
	private final String COLUMN_USERNAME="loginName";
	private final String COLUMN_PWD = "pwd";
	private final String COLUMN_TEL="phone";
	private final String COLUMN_EMAIL="email";
	private final String TABLE_NAME="kw_vip";
	private Context mContext;
	public void openOrCreateDatabase(Context mContext)
	{
		this.mContext = mContext;
		DBOperatorHelper helper = new DBOperatorHelper(mContext, DATABASE_NAME, null, 1);
		mSqlLiteDatabase = helper.getWritableDatabase();
		
	}
	public long insertData(User user)
	{
		if(mSqlLiteDatabase != null && user!= null)
		{
			
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_USERNAME, user.getUsername() );
			contentValues.put(COLUMN_PWD, user.getPwd());
			contentValues.put(COLUMN_TEL, user.getTel());
			contentValues.put(COLUMN_EMAIL, user.getEmail());
			return mSqlLiteDatabase.insert(TABLE_NAME, "", contentValues);
		}
		return -1;
	}
	public User selectData(String username)
	{
		if(mSqlLiteDatabase != null)
		{
			Cursor cursor = mSqlLiteDatabase.query(TABLE_NAME, new String[]{"_id", "pwd"},  "loginName=?", new String[]{username}, null, null, null);
			
			// 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false  
			if(cursor.moveToNext()) {  
				String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
				String tel = cursor.getString(cursor.getColumnIndex("phone"));
				String email = cursor.getString(cursor.getColumnIndex("emial"));
				User user = new User();
				user.setUsername(username);
				user.setPwd(pwd);
				user.setTel(tel);
				user.setEmail(email);
			    return user;  
			} 
		}
		return null;
	}
	
	/*
	 * 关闭数据库
	 */
	public void closeDatabase()
	{
		mSqlLiteDatabase.close();
	}
}
