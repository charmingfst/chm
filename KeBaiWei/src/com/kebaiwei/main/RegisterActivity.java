package com.kebaiwei.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kebaiwei.entity.User;
import com.kebaiwei.utils.DBOperator;
import com.kebaiwei.view.TitleView;

/** 
 * @author ���� 
 * @date 2015-3-28 ����10:41:03 
 * @description TODO
 */
public class RegisterActivity extends Activity {
	private MainApplication mainApp = MainApplication.getInstance();
	private DBOperator mDbOperator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_kw);
		mDbOperator = new DBOperator();
		mDbOperator.openOrCreateDatabase(this);
		TitleView register_title = (TitleView)findViewById(R.id.register_title);
		register_title.setCenterText("ע��");
		register_title.hiddenLeft();
		register_title.hiddenRight();
		
		final EditText name = (EditText)findViewById(R.id.register_name);
		final EditText pwd = (EditText)findViewById(R.id.register_pwd);
		final EditText repwd = (EditText)findViewById(R.id.register_repwd);
//		final EditText tel = (EditText)findViewById(R.id.register_tel);
//		final EditText email = (EditText)findViewById(R.id.register_email);
		
		final Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.shake);
		
		Button register_btn = (Button)findViewById(R.id.register_btn);
		register_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nametxt = name.getText().toString();
				String pwdtxt= pwd.getText().toString();
				String repwdtxt = repwd.getText().toString();
				if("".equals(nametxt) )
				{
					name.setAnimation(animation);
					Toast.makeText(RegisterActivity.this, "�������û���!", Toast.LENGTH_SHORT).show();
					return;
				}else
				{
					if(!(nametxt.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$") && nametxt.length() >= 2 && nametxt.length() <= 16))
					{
						name.startAnimation(animation);
						Toast.makeText(RegisterActivity.this, "�û�������ĸ�����֡����֡��»������,���鳤��2~16.", Toast.LENGTH_SHORT).show();
						return ;
					}
				}
				if("".equals(pwdtxt))
				{
					pwd.setAnimation(animation);
					Toast.makeText(RegisterActivity.this, "����������!", Toast.LENGTH_SHORT).show();
					return ;
				}else
				{
					if(!(pwdtxt.matches("[0-9a-zA-Z]+")&& pwdtxt.length()>=5 && pwdtxt.length() <= 10))
					{
						pwd.startAnimation(animation);
						Toast.makeText(RegisterActivity.this, "�������뽨����5~10����ĸ���������.", Toast.LENGTH_SHORT).show();
						return ;
					}
				}
				if(!pwdtxt.equals(repwdtxt))
				{
					repwd.startAnimation(animation);
					Toast.makeText(RegisterActivity.this, "�����������벻һ��!", Toast.LENGTH_SHORT).show();
					return ;
				}
//				if(!"".equals(emailtxt))
//				{
//					if(!emailtxt.matches("((?<=^)|(?<=\\s))[-\\w]+([-.]\\w+)*@\\w+([-.]\\w+)*\\.([A-Za-z])+"))
//					{
//						email.setAnimation(animation);
//						Toast.makeText(RegisterActivity.this, "���������ʽ����ȷ!", Toast.LENGTH_SHORT).show();
//						return;
//					}
//				}
//				if(!"".equals(teltxt))
//				{
//					if(!teltxt.matches("^(?:(?:1\\d{10})|(?:0(?:10|2[0-57-9]|[3-9]\\d{2})-)?\\d{7,8})$"))
//					{
//						tel.setAnimation(animation);
//						Toast.makeText(RegisterActivity.this, "�����ֻ���������!", Toast.LENGTH_SHORT).show();
//						return ;
//					}
//				}
				User user = new User();
				user.setUsername(nametxt);
				user.setPwd(pwdtxt);
			
				
//				mDbOperator.insertData(user);
//				mDbOperator.closeDatabase();
				mainApp.setUser(user);
				
				Intent _intent = new Intent(RegisterActivity.this, RegisterNextActivity.class);
				_intent.putExtra("previous", "register");
				startActivity(_intent);
				RegisterActivity.this.finish();
				// TODO Auto-generated method stub
//				new Thread(new Runnable(){
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						User user = new User();
//						user.setUsername(nametxt);
//						user.setPwd(pwdtxt);
//						user.setTel(teltxt);
//						user.setEmail(emailtxt);
//						String url = "http://192.168.1.106:8080/kebaiwei/RegisterAction.action"; //�����,��ͬ����IP��ͬ
//						Map<String, String> map = new HashMap<String, String>();
//						map.put("username", nametxt);
//						map.put("pwd", pwdtxt);
//						map.put("tel", teltxt);
//						map.put("email", emailtxt);
//						HttpUtils.sendDataTOserverByPost(url, map);
//					}
//					
//				}).start();
			}
		});
	}
	
}
