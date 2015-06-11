package com.kebaiwei.fragment;

import com.kebaiwei.main.LoginActivity;
import com.kebaiwei.main.MainActivity;
import com.kebaiwei.main.MainApplication;
import com.kebaiwei.main.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-26 ÏÂÎç4:42:59 
 * @description TODO
 */
@SuppressLint("NewApi")
public class ToolbarFragment extends Fragment {
	private MainActivity mainActivity ;
	private MainApplication mainApp = MainApplication.getInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (MainActivity)this.getActivity();
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.footer_kw,container, false);
		LinearLayout ll_home = (LinearLayout)view.findViewById(R.id.home);
		ll_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mainActivity.setMainContent(1);
			}
		});
		LinearLayout ll_dingdan = (LinearLayout)view.findViewById(R.id.dingdan);
		ll_dingdan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mainActivity.setMainContent(2);
			}
		});
		
		LinearLayout ll_myself = (LinearLayout)view.findViewById(R.id.myself);
		ll_myself.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mainApp.getUser()==null )
				{
					Intent _intent = new Intent(mainActivity, LoginActivity.class);
					_intent.putExtra("flag", 3); 
					startActivity(_intent);
					mainActivity.finish();
				}else
				{
					mainActivity.setMainContent(3);
				}
			}
		});
		
		return view;
	}
}
