package com.kebaiwei.main;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Service;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.kebaiwei.fragment.HomeFragment;
import com.kebaiwei.fragment.MyselfFragment;
import com.kebaiwei.fragment.OrderFragment;
import com.kebaiwei.fragment.ToolbarFragment;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	public int flag = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		overridePendingTransition(R.anim.slade_into_from_right, R.anim.slade_out_from_left);
		flag = getIntent().getIntExtra("flag", 0);
		setMainContent(flag);
		getToolbar();
		
	}
	

	public void setMainContent(int flag) {
		// TODO Auto-generated method stub
		FragmentManager manager= getFragmentManager();
		Fragment fragment = new HomeFragment();
		FragmentTransaction  transaction = manager.beginTransaction();;
		switch (flag) {
		case 0:
			fragment = new HomeFragment();
			transaction.add(R.id.home_content, fragment);
			break;
		case 1:
			transaction.replace(R.id.home_content, fragment);
			break;
		case 2:
			fragment = new OrderFragment();
			transaction.replace(R.id.home_content, fragment);
			break;
		case 3:
			fragment = new MyselfFragment();
			transaction.replace(R.id.home_content, fragment);
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void getToolbar() {
		// TODO Auto-generated method stub
		FragmentManager manager= getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.home_toolbar, new ToolbarFragment());
		transaction.commit();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Builder builder = new Builder(MainActivity.this);
		builder.setTitle("提示").setMessage("您确定要退出客户端吗？")
				;
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				System.exit(0);
//				ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//				if( android.os.Build.VERSION.SDK_INT < 8){
//					am.restartPackage(getPackageName());
//				}else{
//					am.killBackgroundProcesses(getPackageName());
//				}
				// android.os.Process.killProcess(android.os.Process.myPid());
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		Dialog dialog = builder.create();
		dialog.show();
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
	}

}
