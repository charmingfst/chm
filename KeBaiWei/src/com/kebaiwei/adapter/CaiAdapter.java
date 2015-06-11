package com.kebaiwei.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kebaiwei.entity.Food;
import com.kebaiwei.fragment.HomeFragment;
import com.kebaiwei.main.MainApplication;
import com.kebaiwei.main.R;


/** 
 * @author 
 * @date 2015-3-28 ÏÂÎç3:05:32 
 * @description TODO
 */
public class CaiAdapter extends BaseAdapter {
	
	private Activity activity;
	private HomeFragment fragment;
	private int[] cai_id;
	private int[] cai_phote;
	private String[] cai_name;
	private int[] price;
	private ArrayList<Integer> food_id;
	private MainApplication mainApp;
//	private ArrayList<Food> foodList;
	@SuppressLint("NewApi")
	public CaiAdapter(HomeFragment fragment)
	{
		
		this.fragment = fragment;
		this.activity = fragment.getActivity();
		mainApp = MainApplication.getInstance();
		food_id = mainApp.getFood_id();
//		foodList = mainApp.getFoodList();
	}
	public void setData(int[] cai_id, int[] cai_phote, String[] cai_name, int[] price)
	{
		this.cai_id = cai_id;
		this.cai_phote = cai_phote;
		this.cai_name = cai_name;
		this.price = price;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cai_name.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
		{
			convertView = LayoutInflater.from(activity).inflate(R.layout.cai_list, null);
		}
		ImageView imageView = (ImageView)convertView.findViewById(R.id.cai_tp);
		imageView.setBackgroundResource(cai_phote[position]);
		TextView textView = (TextView)convertView.findViewById(R.id.cai_name);
		textView.setText(cai_name[position]);
		TextView cai_price = (TextView)convertView.findViewById(R.id.cai_price);
		cai_price.setText("£¤"+price[position]);
		final ImageButton btn_plus = (ImageButton)convertView.findViewById(R.id.cai_plus);
		final ImageButton btn_remove = (ImageButton)convertView.findViewById(R.id.cai_remove);
		final TextView btn_sum = (TextView)convertView.findViewById(R.id.cai_sum);
		final int post = position;
		btn_plus.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				food_id.add(cai_id[post]);
				int[] location = new int[2];  
				v.getLocationInWindow(location);  
	            int x = location[0];  
	            int y = location[1];  
				System.out.println("X:"+x);
				System.out.println("Y:"+y);
				fragment.redHot(x, y-300, price[post]);
				String cai_sum = btn_sum.getText().toString();
				int cSum = Integer.parseInt(cai_sum);
				cSum++;
				btn_sum.setVisibility(View.VISIBLE);
				btn_sum.setText(cSum+"");
				btn_remove.setVisibility(View.VISIBLE);
				
				
				ArrayList<Food> foodList = mainApp.getFoodList();
				if(foodList == null)
				{
					foodList = new ArrayList<Food>();
					mainApp.setFoodList(foodList);
				}
				
				if(foodList.size() == 0)
				{
					Food food = new Food();
					food.setId(cai_id[post]);
					food.setName(cai_name[post]);
					food.setCount(cSum);
					food.setPrice(price[post]*cSum);
					foodList.add(food);
				}else
				{
					int flag = 0;
					
					for(int i =0; i < foodList.size(); i ++)
					{
						Food food2 = foodList.get(i);
						int cid = food2.getId();
						if(cid == cai_id[post])
						{
							flag = 1;
							food2.setCount(cSum);
							food2.setPrice(price[post]*cSum);
						}
					}
					if(flag == 0)
					{
						Food food = new Food();
						food.setId(cai_id[post]);
						food.setName(cai_name[post]);
						food.setCount(cSum);
						food.setPrice(price[post]*cSum);
						foodList.add(food);
					}
				}
				
			}
		});
		btn_remove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cai_sum = btn_sum.getText().toString();
				int cSum = Integer.parseInt(cai_sum);
				cSum--;
				btn_sum.setText(cSum+"");
				fragment.removeFood(price[post]);
				
				ArrayList<Food> foodList = mainApp.getFoodList();
				for(int i = 0; i < foodList.size();i++)
				{
					Food food = foodList.get(i);
					int foodId = food.getId();
					if(foodId == cai_id[post])
					{
						food.setCount(cSum);
						food.setPrice(price[post]*cSum);
						if("1".equals(cai_sum))
						{
							foodList.remove(food);
							food = null;
						}
					}
				}
				if("1".equals(cai_sum))
				{
					btn_sum.setVisibility(View.GONE);
					btn_remove.setVisibility(View.GONE);
					return;
				}
				
			}
		}); 
		return convertView;
	}

}
