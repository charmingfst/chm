package com.kebaiwei.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kebaiwei.entity.User;
import com.kebaiwei.utils.DBOperator;
import com.kebaiwei.view.TitleView;
import com.kebaiwei.view.TitleView.OnLeftClickListener;
import com.kebaiwei.view.TitleView.OnRightClickListener;

/** 
 * @author 陈明 
 * @date 2015-3-27 下午2:37:01 
 * @description TODO
 */
public class LoginActivity extends Activity {
	private int flag;
	private MainApplication mainApp = MainApplication.getInstance();
	private DBOperator mDbOperator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_kw);
//		mDbOperator = new DBOperator();
//		mDbOperator.openOrCreateDatabase(this);
		TitleView login_title = (TitleView)findViewById(R.id.login_title);
		login_title.setCenterText("登陆");
		login_title.setRightText("注册");
		login_title.setRightTextColor(Color.parseColor("#64CBF5"));
		
		flag = getIntent().getIntExtra("flag", 0);
		login_title.setLeftbtnClickListener(new OnLeftClickListener() {
			
			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(LoginActivity.this, MainActivity.class);
				if(flag == 3)
					flag = 1; //
				_intent.putExtra("flag", flag);
				startActivity(_intent);
				LoginActivity.this.finish();
				
			}
		});
		login_title.setRightBtnClickListener(new OnRightClickListener() {

			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(_intent);
			}
		});
		
		final EditText useract = (EditText)findViewById(R.id.useract);
		final EditText userpwd = (EditText)findViewById(R.id.userpwd);
		Button login_btn = (Button)findViewById(R.id.login_btn);
		login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username  = useract.getText().toString();
				if(!username.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$"))
				{
					Toast.makeText(LoginActivity.this, "账户名不正确!", Toast.LENGTH_SHORT).show();
					return;
				}
//				User user = mDbOperator.selectData(username);
				User user = mainApp.getUser();
				if(user == null)
				{
					return;
				}
				String pwd = user.getPwd();
				String password = userpwd.getText().toString();
				if(password.equals(pwd))
				{
//					mainApp.setUser(user);
					Intent _intent = new Intent(LoginActivity.this, MainActivity.class);
					_intent.putExtra("flag", flag);
					startActivity(_intent);
					LoginActivity.this.finish();
				}else
				{
					Toast.makeText(LoginActivity.this, "密码或用户名不正确!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent _intent = new Intent(LoginActivity.this, MainActivity.class);
		if(flag == 3)
			flag = 1; //
		_intent.putExtra("flag", flag);
		startActivity(_intent);
		LoginActivity.this.finish();
		
	}
}
