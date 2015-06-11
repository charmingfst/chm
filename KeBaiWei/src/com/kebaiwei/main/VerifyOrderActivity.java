package com.kebaiwei.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kebaiwei.entity.Food;
import com.kebaiwei.entity.Order;
import com.kebaiwei.utils.KbwUtils;
import com.kebaiwei.view.TitleView;
import com.kebaiwei.view.TitleView.OnLeftClickListener;

/** 
 * @author 
 * @date 2015-4-20 下午7:49:15 
 * @description TODO
 */
public class VerifyOrderActivity extends ListActivity {
	private MainApplication mainApp;
	private int count;
	private ArrayList<Food> foodList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.verify_order);
		mainApp = MainApplication.getInstance();
		foodList = mainApp.getFoodList();
		
		TitleView verify_title = (TitleView)findViewById(R.id.verify_title);
		verify_title.setCenterText("确认订单");
		verify_title.removeRight();
		verify_title.setLeftbtnClickListener(new OnLeftClickListener() {
			
			@Override
			public void onClickListener(View ImageButton) {
				// TODO Auto-generated method stub
				mainApp.getFoodList().clear();
				startActivity(new Intent(VerifyOrderActivity.this,MainActivity.class));
				VerifyOrderActivity.this.finish();
			}
		});

		ListView list = getListView();
		list.setAdapter(new MenuAdapter(this));
		
//		ArrayList<Food> foodList = mainApp.getFoodList();
//		System.out.println("size:"+foodList.size());
//		for(Food food: foodList)
//		{
//			System.out.println(food.getCount()+ " " +food.getName()+" " + food.getPrice());
//		}
		final EditText tableNum = (EditText)findViewById(R.id.table_numble);
		Button menu_ok = (Button)findViewById(R.id.verify_menu);
		menu_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String payKey = mainApp.getUser().getPaykey();
				if("".equals(payKey)|| payKey == null)
				{
					startActivity(new Intent(VerifyOrderActivity.this, RegisterNextActivity.class));
					
				}else
				{
					String num = tableNum.getText().toString();
					if(num.matches("\\d+")&&num.length()>0&&num.length()<3)
					{
						Order odr = null;
						int tabNum = Integer.parseInt(num);
						if(tabNum>=0&&tabNum<=100)
						{
							odr = new Order();
							odr.setTableNo(num);
							String id = KbwUtils.orderNumGenerator();
							odr.setId(id);
							odr.setPay(false);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
							String addTime = sdf.format(Calendar.getInstance().getTime());
							odr.setAddTime(addTime);
							HashMap<String, ArrayList> hashFood = mainApp.getHashFood();
							hashFood.put(id, foodList);
							ArrayList<Order> orderList= mainApp.getPayingOrderList();
							orderList.add(odr);
						}
						
						showPay( odr);
					}
				}
				
			}
		});
	}
	
	public void showPay(final Order odr)
	{
		final Dialog payDialog = new Dialog(VerifyOrderActivity.this, R.style.MyDialog);
		payDialog.setContentView(R.layout.pay_dialog);
//		String[] payChoice = new String[]{"支付宝支付", "网银支付", "微信支付"};
//		ArrayList<String> payListData = new ArrayList<String>();
//		payListData.add("支付宝支付");
//		payListData.add("网银支付");
//		payListData.add("微信支付");
//		ListView payList = (ListView)payDialog.findViewById(R.id.pay_list);
//		ArrayAdapter<String> payAdapter = new ArrayAdapter<String>(VerifyOrderActivity.this, R.layout.pay_list, R.id.pay_text, payListData);
//		payList.setAdapter(payAdapter);
		payDialog.show();
		Button payOk = (Button)payDialog.findViewById(R.id.pay_ok);
		Button payCancel = (Button)payDialog.findViewById(R.id.pay_cancel);
		payOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(odr != null)
				{
					odr.setPay(true);
					payDialog.dismiss();
					ArrayList<Order> payingOrder= mainApp.getPayingOrderList();
					payingOrder.remove(odr);
					ArrayList<Order> payedOrder= mainApp.getPayedOrderList();
					payedOrder.add(odr);
				}
			}
		});
		payCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				payDialog.dismiss();
			}
		});
		
	
	}
	 
	class MenuAdapter extends BaseAdapter{

		private Context ctxt;
		public MenuAdapter(Context ctxt)
		{
			this.ctxt = ctxt;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count = foodList.size();
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
			if(convertView == null)
			{
				convertView = LayoutInflater.from(ctxt).inflate(R.layout.verify_menu, null);
			}
			Food food = foodList.get(position);
			TextView menu_id = (TextView)convertView.findViewById(R.id.menu_id);
			menu_id.setText((position+1)+"");
			TextView menu_name = (TextView)convertView.findViewById(R.id.menu_name);
			menu_name.setText(food.getName());
			TextView food_count = (TextView)convertView.findViewById(R.id.food_count);
			food_count.setText("X"+food.getCount());
			TextView menu_price = (TextView)convertView.findViewById(R.id.menu_price);
			menu_price.setText("￥"+food.getPrice());
			return convertView;
		}
		
	}

}
