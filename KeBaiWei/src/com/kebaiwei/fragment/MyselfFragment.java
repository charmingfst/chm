package com.kebaiwei.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kebaiwei.main.AboutActivity;
import com.kebaiwei.main.AccountActivity;
import com.kebaiwei.main.MainActivity;
import com.kebaiwei.main.MainApplication;
import com.kebaiwei.main.R;
import com.kebaiwei.main.SettingActivity;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-27 ÉÏÎç9:41:09 
 * @description TODO
 */
@SuppressLint("NewApi")
public class MyselfFragment extends Fragment{
	private MainApplication mainApp = MainApplication.getInstance();
	private MainActivity mainActivity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainActivity = (MainActivity)this.getActivity();
		View view = inflater.inflate(R.layout.myself_kw, container, false);
		TextView username = (TextView)view.findViewById(R.id.username);
		username.setText(mainApp.getUser().getUsername());
		RelativeLayout setting = (RelativeLayout)view.findViewById(R.id.more_setting);
		RelativeLayout account = (RelativeLayout)view.findViewById(R.id.more_account);
		RelativeLayout about = (RelativeLayout)view.findViewById(R.id.more_about);
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(mainActivity,SettingActivity.class));
			}
		});
		account.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(mainActivity, AccountActivity.class));
			}
		});
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(mainActivity, AboutActivity.class));
			}
		});
		
		return view;
	}
}
