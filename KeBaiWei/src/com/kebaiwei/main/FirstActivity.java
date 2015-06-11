package com.kebaiwei.main;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

/**
 * @author 陈明
 * @date 2015-3-26 上午9:21:15
 * @description TODO
 */
public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPreferences = getSharedPreferences("kebaiwei",
				MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		final boolean guidance = sharedPreferences.getBoolean("guidance", false); // false进入引导
		setContentView(R.layout.kw_first);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!guidance) {
					Intent _intent = new Intent(FirstActivity.this,
							GuidanceActivity.class);
					startActivity(_intent);
					FirstActivity.this.finish();
				}else
				{
					Intent _intent = new Intent(FirstActivity.this,
							MainActivity.class);
					_intent.putExtra("flag", 0);
					startActivity(_intent);
					FirstActivity.this.finish();
				}
			}

		}, 1000);
	}
}
