package com.kebaiwei.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.kebaiwei.view.TitleView;
import com.kebaiwei.view.TitleView.OnLeftClickListener;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-30 ÏÂÎç4:54:34 
 * @description TODO
 */
public class AccountActivity extends Activity {
	private MainApplication mainApp = MainApplication.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		TitleView account_title = (TitleView)findViewById(R.id.account_title);
		account_title.setCenterText("ÕË»§");
		account_title.hiddenRight();
		account_title.setLeftbtnClickListener(new OnLeftClickListener() {
			
			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AccountActivity.this, MainActivity.class);
				intent.putExtra("flag", 3);
				startActivity(intent);
				AccountActivity.this.finish();
			}
		});
		TextView userTel = (TextView)findViewById(R.id.user_tel);
		TextView userEmail = (TextView)findViewById(R.id.user_email);
		Button quite = (Button)findViewById(R.id.quite);
		if(mainApp.getUser() != null)
		{
			userTel.setText(mainApp.getUser().getTel());
			userEmail.setText(mainApp.getUser().getEmail());
			quite.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mainApp.getUser().setUsername("");
					mainApp.getUser().setPwd("");
				}
			});
		}
	}
}
