package com.kebaiwei.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kebaiwei.adapter.OrderPagerAdapter;
import com.kebaiwei.entity.Order;
import com.kebaiwei.main.LoginActivity;
import com.kebaiwei.main.MainActivity;
import com.kebaiwei.main.MainApplication;
import com.kebaiwei.main.OrderDetailActivity;
import com.kebaiwei.main.R;
import com.kebaiwei.utils.KbwUtils;
import com.kebaiwei.view.TitleView;

/**
 * @author 陈明
 * @date 2015-3-26 下午5:43:34
 * @description TODO
 */
@SuppressLint("NewApi")
public class OrderFragment extends Fragment {
	private MainActivity mainActivity;
	private MainApplication mainApp = MainApplication.getInstance();
	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2, t3;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currentIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainActivity = (MainActivity) this.getActivity();
		View view = null;
		if (mainApp.getUser() == null ) {
			view = inflater.inflate(R.layout.no_login, container, false);
			TitleView titleView = (TitleView) view
					.findViewById(R.id.order_nologin_title);
			titleView.setCenterText("订单");
			titleView.hiddenLeft();
			titleView.hiddenRight();
			Button btn = (Button) view.findViewById(R.id.loginReg);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent _intent = new Intent(mainActivity,
							LoginActivity.class);
					_intent.putExtra("flag", 2);
					startActivity(_intent);
					mainActivity.finish();
				}
			});
			return view;
		} else {
			view = inflater.inflate(R.layout.order_kw, container, false);
			TitleView titleView = (TitleView) view
					.findViewById(R.id.order_title);
			titleView.setCenterText("订单");
			titleView.hiddenLeft();
			titleView.hiddenRight();

			t1 = (TextView) view.findViewById(R.id.text1);
			t2 = (TextView) view.findViewById(R.id.text2);
			t1.setOnClickListener(new MyOnClickListener(0));
			t2.setOnClickListener(new MyOnClickListener(1));
//			t3.setOnClickListener(new MyOnClickListener(2));

			mPager = (ViewPager) view.findViewById(R.id.vPager);
			listViews = new ArrayList<View>();
			LayoutInflater mInflater = mainActivity.getLayoutInflater();
			View payedView = mInflater.inflate(R.layout.order_payed, null);
			View payingView = mInflater.inflate(R.layout.order_paying, null);
			listViews.add(payedView);
			listViews.add(payingView);
			
//			listViews.add(mInflater.inflate(R.layout.lay3, null));
			mPager.setAdapter(new OrderPagerAdapter(listViews));
			mPager.setCurrentItem(0);

			cursor = (ImageView) view.findViewById(R.id.cursor);
			bmpW = BitmapFactory.decodeResource(getResources(),
					R.drawable.tab_icon).getWidth();// 获取图片宽度
			DisplayMetrics dm = new DisplayMetrics();
			mainActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
			int screenW = dm.widthPixels;// 获取分辨率宽度
			offset = (KbwUtils.getScreenWidth(mainActivity)/ 2 - bmpW) / 2;// 计算偏移量
			Matrix matrix = new Matrix();
			matrix.postTranslate(offset, 0);
			cursor.setImageMatrix(matrix);// 设置动画初始位置

			mPager.setOnPageChangeListener(new MyOnPageChangeListener());
			
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("name", "订单1");
//			map.put("time", "20150506");
//			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
//			list.add(map);
//			SimpleAdapter simpleAdapter_Wu = new SimpleAdapter(this.getActivity(), list,
//				    R.layout.order_list,
//				    new String[]{"name", "time"},
//				    new int[]{R.id.order_name, R.id.order_time});
			ListView orderNopay = (ListView)payingView.findViewById(R.id.order_nopay);
			orderNopay.setAdapter(new PayingAdapter(this.getActivity()));
			orderNopay.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent _intent = new Intent(OrderFragment.this.getActivity(), OrderDetailActivity.class);
					_intent.putExtra("position",  position);
					startActivity(_intent);
				}
			});
			
			ListView orderpay = (ListView)payedView.findViewById(R.id.order_pay);
			orderpay.setAdapter(new PayedAdapter(this.getActivity()));
			orderpay.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent _intent = new Intent(OrderFragment.this.getActivity(), OrderDetailActivity.class);
					_intent.putExtra("position",  position);
					startActivity(_intent);
				}
			});
			
		}

		return view;
	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	}

	/**
	 * 2 * 页卡切换监听 3
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(one * currentIndex,
					one * arg0, 0, 0);
			currentIndex = arg0;
			animation.setFillAfter(true);// true:图片停留在动画结束的位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
//			 Toast.makeText(OrderFragment.this.getActivity(), "卡片移向了第" + arg0 + "页",
//			 Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	class PayingAdapter extends BaseAdapter
	{
		private ArrayList<Order> orderList;
		private Context cxt;
		public PayingAdapter(Context cxt)
		{
			orderList = mainApp.getPayingOrderList();
		
			this.cxt = cxt;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return orderList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			System.out.println("size:"+orderList.size());
			if(convertView == null)
			{
				convertView = LayoutInflater.from(cxt).inflate(R.layout.order_list, null);
			}
			TextView order_name = (TextView)convertView.findViewById(R.id.order_name);
			order_name.setText("订单("+(position+1)+")");
			TextView order_time = (TextView)convertView.findViewById(R.id.order_time);
			order_time.setText("时间: "+orderList.get(position).getAddTime());
			return convertView;
		}
		
	}
	
	class PayedAdapter extends BaseAdapter
	{
		private ArrayList<Order> orderList;
		private Context cxt;
		public PayedAdapter(Context cxt)
		{
			orderList = mainApp.getPayedOrderList();
			this.cxt = cxt;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return orderList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			System.out.println("size:"+orderList.size());
			if(convertView == null)
			{
				convertView = LayoutInflater.from(cxt).inflate(R.layout.order_list, null);
			}
			TextView order_name = (TextView)convertView.findViewById(R.id.order_name);
			order_name.setText("订单("+(position+1)+")");
			TextView order_time = (TextView)convertView.findViewById(R.id.order_time);
			order_time.setText("时间: "+orderList.get(position).getAddTime());
			return convertView;
		}
		
	}
}
