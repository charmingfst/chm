package com.kebaiwei.main;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/** 
 * @author 陈明 
 * @date 2015-3-30 下午4:47:42 
 * @description TODO
 */
public class SettingActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);
		Preference wifiSetting = findPreference("wifi_setting");

		/*
		 * 无线与网络设置
		 */
		wifiSetting
				.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
				{

					@Override
					public boolean onPreferenceClick(Preference preference)
					{
						Intent intent = new Intent();
						intent.setAction(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						startActivity(intent);
						return false;
					}
				});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		Intent _intent = new Intent(SettingActivity.this, MainActivity.class);
		_intent.putExtra("flag", 3);
		startActivity(_intent);
		this.finish();
	}
}
