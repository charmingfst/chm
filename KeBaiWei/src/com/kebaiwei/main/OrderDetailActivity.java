package com.kebaiwei.main;

import java.util.ArrayList;

import com.kebaiwei.entity.Order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/** 
 * @author 
 * @date 2015-5-14 ÏÂÎç3:29:41 
 * @description TODO
 */
public class OrderDetailActivity extends Activity{
	private MainApplication mainApp = MainApplication.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details);
		Intent _intent = getIntent();
		int index = _intent.getIntExtra("position", 0);
//		ArrayList<Order> orderList= mainApp.getOrderList();
//		Order odr = orderList.get(index);
//		System.out.println(odr.getAddTime()+" "+odr.getId());
	}
}
