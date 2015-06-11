package com.kebaiwei.handler;

import android.os.Handler;
import android.os.Message;

import com.kebaiwei.main.LoginActivity;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-28 ÉÏÎç11:26:48 
 * @description TODO
 */
public class LoginHandler extends Handler {
	private LoginActivity activity;
	public LoginHandler(LoginActivity activity)
	{
		this.activity = activity;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
	}
}
