package com.kebaiwei.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.kebaiwei.adapter.GuidanceAdapter;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-26 ÉÏÎç9:36:44 
 * @description TODO
 */
public class GuidanceActivity extends Activity{
	private List<View> listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kw_guidance);
		listView = new ArrayList<View>();
		int[] images = new int[]{R.drawable.guidance_01, R.drawable.guidance_02, R.drawable.guidance_03};
		for(int i = 0; i < images.length; i++)
		{
			ImageView image = new ImageView(GuidanceActivity.this);
			image.setBackgroundResource(images[i]);
			listView.add(image);
		}
		View view = View.inflate(this, R.layout.guidance_end, null);
		listView.add(view);
		GuidanceAdapter guidanceAdapter = new GuidanceAdapter(listView);
		ViewPager viewPager = (ViewPager)findViewById(R.id.page);
		viewPager.setAdapter(guidanceAdapter);
		
		Button btn_go = (Button)view.findViewById(R.id.startgo);
		btn_go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(GuidanceActivity.this, MainActivity.class);
				startActivity(_intent);
				GuidanceActivity.this.finish();
			}
		});
	}
	
}
