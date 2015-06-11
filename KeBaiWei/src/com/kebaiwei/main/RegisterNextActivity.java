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
import android.widget.TextView;
import android.widget.Toast;

import com.kebaiwei.entity.User;
import com.kebaiwei.view.TitleView;
import com.kebaiwei.view.TitleView.OnLeftClickListener;

/** 
 * @author 
 * @date 2015-5-14 上午10:54:00 
 * @description TODO
 */
public class RegisterNextActivity extends Activity{
	private MainApplication mainApp = MainApplication.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_next);
		TitleView titleview = (TitleView)findViewById(R.id.register_next_title);
		titleview.setCenterText("设置信息");
		titleview.removeRight();
		titleview.setLeftbtnClickListener(new OnLeftClickListener() {
			
			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				if("register".equals(intent.getStringExtra("previous")))
				{
					startActivity(new Intent(RegisterNextActivity.this, LoginActivity.class));
					RegisterNextActivity.this.finish();
				}else //订单页面进入
				{
					RegisterNextActivity.this.finish();
				}
			}
		});
		
		final EditText tel = (EditText)findViewById(R.id.register_tel);
		final EditText email = (EditText)findViewById(R.id.register_email);
		final EditText pay_key = (EditText)findViewById(R.id.pay_key);
		Button register_next = (Button)findViewById(R.id.register_next_btn);
		final Animation animation = AnimationUtils.loadAnimation(RegisterNextActivity.this, R.anim.shake);
		register_next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String teltxt = tel.getText().toString();
				String emailtxt = email.getText().toString();
				String payKey = pay_key.getText().toString();
				if(payKey.matches("\\d+")&&payKey.length()==6)
				{
				
				}else
				{
					pay_key.startAnimation(animation);
					Toast.makeText(RegisterNextActivity.this, "密码设置有误!", Toast.LENGTH_LONG).show();
					return ;
				}

				if(!"".equals(emailtxt))
				{
					if(!emailtxt.matches("((?<=^)|(?<=\\s))[-\\w]+([-.]\\w+)*@\\w+([-.]\\w+)*\\.([A-Za-z])+"))
					{
						email.startAnimation(animation);
						Toast.makeText(RegisterNextActivity.this, "输入邮箱格式不正确!", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				if(!"".equals(teltxt))
				{
					if(!teltxt.matches("^(?:(?:1\\d{10})|(?:0(?:10|2[0-57-9]|[3-9]\\d{2})-)?\\d{7,8})$"))
					{
						tel.startAnimation(animation);
						Toast.makeText(RegisterNextActivity.this, "输入手机号码有误!", Toast.LENGTH_SHORT).show();
						return ;
					}
				}
				
				User user = mainApp.getUser();
				if(user != null)
				{
					user.setEmail(emailtxt);
					user.setTel(teltxt);
					user.setPaykey(payKey);
				}
				
				Intent intent = getIntent();
				if("register".equals(intent.getStringExtra("previous")))
				{
					startActivity(new Intent(RegisterNextActivity.this, LoginActivity.class));
					RegisterNextActivity.this.finish();
				}else //订单页面进入
				{
					RegisterNextActivity.this.finish();
				}
			}
			
		});
		
	}
}
