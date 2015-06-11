package com.kebaiwei.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kebaiwei.fragment.MyselfFragment;
import com.kebaiwei.view.TitleView;

/** 
 * @author 陈明 
 * @date 2015-3-31 下午2:04:47 
 * @description TODO
 */
public class AboutActivity extends Activity {
	private String date="2015-05-06";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_kw);
		
		TitleView titleView = (TitleView)findViewById(R.id.about_title);
		titleView.setCenterText("关于");
		titleView.hiddenRight();
		titleView.setLeftbtnClickListener(new TitleView.OnLeftClickListener() {
			
			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AboutActivity.this, MainActivity.class);
				intent.putExtra("flag", 3);
				startActivity(intent);
				AboutActivity.this.finish();
			}
		});
		TextView version_tv = (TextView) findViewById(R.id.about_version);
		TextView releasedate_tv = (TextView) findViewById(R.id.about_releasedate);
		// 获取当前软件版本号
		try {
			PackageManager pm = getPackageManager();
			PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
			String verName = pi.versionName;
			version_tv.setText("软件版本:" + "V" + verName);
			releasedate_tv.setText("发布日期:" + date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
