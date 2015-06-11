package com.kebaiwei.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.kebaiwei.adapter.CaiAdapter;
import com.kebaiwei.entity.User;
import com.kebaiwei.main.AnimationEndListener;
import com.kebaiwei.main.DetailsActivity;
import com.kebaiwei.main.LoginActivity;
import com.kebaiwei.main.MainActivity;
import com.kebaiwei.main.MainApplication;
import com.kebaiwei.main.MyInterpolator;
import com.kebaiwei.main.R;
import com.kebaiwei.main.VerifyOrderActivity;
import com.kebaiwei.view.TitleView;

/**
 * @author 
 * @date 2015-3-26 下午4:36:11
 * @description TODO
 */
@SuppressLint("NewApi")
public class HomeFragment extends Fragment {
	private MainActivity mainActivity;
	private View view;
	private int cai_type = 0; // 0海鲜，1川菜
	private int sum_price;
	private Button btn_choice;
//	private FoodDBOperator mFoodDBOperator;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		mFoodDBOperator = new FoodDBOperator();
//		mFoodDBOperator.openOrCreateDatabase(this.getActivity());
		mainActivity = (MainActivity) this.getActivity();
		view = inflater.inflate(R.layout.home_content, null);
		TitleView titleView = (TitleView) view.findViewById(R.id.home_title);
		titleView.hiddenLeft();
		titleView.setCenterText("食谱");
		titleView.hiddenRight();

		int[] images = new int[] { R.drawable.hongshaorou,
				R.drawable.tiebanyouyue, R.drawable.yinger };
		ViewFlipper home_flipper = (ViewFlipper) view
				.findViewById(R.id.home_flipper);

		for (int i = 0; i < images.length; i++) {
			home_flipper.addView(getImageView(images[i]));
		}
		home_flipper.setInAnimation(mainActivity, R.anim.slade_into_from_right);
		home_flipper.setOutAnimation(mainActivity, R.anim.slade_out_from_left);
		home_flipper.setFlipInterval(1000);
		// 开始播放
		home_flipper.startFlipping();

//		final RotateAnimation rotateanim = new RotateAnimation(0, 180,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		rotateanim.setDuration(400);
//		rotateanim.setFillAfter(true);
//		final RotateAnimation rotateanim2 = new RotateAnimation(180, 0,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		rotateanim2.setDuration(400);
//		rotateanim2.setFillAfter(true);
		RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.haixian);
		final ImageView imageView = (ImageView) view
				.findViewById(R.id.haixiang_icon);
		int[] cai_phote = new int[] { R.drawable.shenghao, R.drawable.huahe };
		String[] cai_name = new String[] { "生蚝", "花哈" };
		final ListView haixian_list = (ListView) view
				.findViewById(R.id.haixian_list);
		int[] cai_ids = new int[] { 101, 102};
		int[] cai_price = new int[]{18,24};
		setListAdapter(haixian_list, cai_ids, cai_phote, cai_name, cai_price);
		listOpenOrClose(haixian_list, rl, imageView);
//		final RotateAnimation rotateanim_cc = new RotateAnimation(0, 180,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		rotateanim_cc.setDuration(400);
//		rotateanim_cc.setFillAfter(true);
//		final RotateAnimation rotateanim2_cc = new RotateAnimation(180, 0,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		rotateanim2_cc.setDuration(400);
//		rotateanim2_cc.setFillAfter(true);
		int[] cc_phote = new int[] { R.drawable.huiguorou,R.drawable.duijiaoyvtou, R.drawable.ganguoji };
		String[] cc_name = new String[] { "回锅肉", "剁椒鱼头", "苗家干锅鸡" };
		int[] cc_price = new int[]{20,18,24};
		int[] cc_ids = new int[] { 201, 202, 203 };
		final ListView cc_list = (ListView) view
				.findViewById(R.id.chuancai_list);
		setListAdapter(cc_list, cc_ids, cc_phote, cc_name,cc_price);
		RelativeLayout chuancai_rl = (RelativeLayout) view
				.findViewById(R.id.chuancai);
		final ImageView chuancai_imageView = (ImageView) view
				.findViewById(R.id.chuancai_icon);
		listOpenOrClose(cc_list, chuancai_rl, chuancai_imageView);
		
		int[] tang_phote = new int[] { R.drawable.huiguorou,R.drawable.duijiaoyvtou, R.drawable.ganguoji };
		String[] tang_name = new String[] { "西红柿蛋汤", "青菜蛋汤", "苗家干锅鸡" };
		int[] tang_price = new int[]{11,12,20};
		int[] tang_ids = new int[] { 301,302,303 };
		final ListView tang_list = (ListView) view.findViewById(R.id.tang_list);
		setListAdapter(tang_list, tang_ids, tang_phote, tang_name,tang_price);
		RelativeLayout tang_rl = (RelativeLayout) view.findViewById(R.id.tang);
		final ImageView tang_imageView = (ImageView) view.findViewById(R.id.tang_icon);
		listOpenOrClose(tang_list, tang_rl, tang_imageView);
		btn_choice = (Button)view.findViewById(R.id.choice_ok);
		btn_choice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				User user = MainApplication.getInstance().getUser();
//				if(user == null)
//				{
//					startActivity(new Intent(HomeFragment.this.getActivity(), LoginActivity.class));
//				}else
				{
					Intent _intent = new Intent(HomeFragment.this.getActivity(), VerifyOrderActivity.class);;
					startActivity(_intent);
					HomeFragment.this.getActivity().finish();
				}
				
			}
		});
		
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}
	private void listOpenOrClose(final ListView list, RelativeLayout rl,final ImageView imageView)
	{
		final RotateAnimation rotateanim_tang = new RotateAnimation(0, 180,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateanim_tang.setDuration(400);
		rotateanim_tang.setFillAfter(true);
		final RotateAnimation rotateanim2_tang = new RotateAnimation(180, 0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateanim2_tang.setDuration(400);
		rotateanim2_tang.setFillAfter(true);
		rl.setOnClickListener(new OnClickListener() {
			boolean rotateflag = false;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!rotateflag) {
					imageView.clearAnimation();
					imageView.startAnimation(rotateanim_tang);
					rotateflag = true;
				} else {
					imageView.clearAnimation();
					imageView.startAnimation(rotateanim2_tang);
					rotateflag = false;
				}
			}
		});
		rotateanim_tang.setAnimationListener(new AnimationEndListener() {

			@Override
			public void myAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				list.setVisibility(View.GONE);
			}
		});
		rotateanim2_tang.setAnimationListener(new AnimationEndListener() {

			@Override
			public void myAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				list.setVisibility(View.VISIBLE);
			}
		});
	}
	
	// 绑定数据
	private void setListAdapter(ListView list, int[] cai_ids, int[] cai_phote,
			String[] cai_name,int[] price) {
		CaiAdapter caiAdapter = new CaiAdapter(this);
		caiAdapter.setData(cai_ids, cai_phote, cai_name,price);
		list.setAdapter(caiAdapter);
		setListViewHeightBasedOnChildren(list);
		mySetListItemListener(list, cai_ids);
	}

	// 设置listview每项点击事件
	private void mySetListItemListener(ListView list, final int[] cai_ids) {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 根据postion得到cai_id
				Intent _intent = new Intent(mainActivity, DetailsActivity.class);
				_intent.putExtra("cai_id", cai_ids[position]);
				startActivity(_intent);

			}
		});
	}

	private View getImageView(int imageId) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(mainActivity);
		imageView.setBackgroundResource(imageId);
		return imageView;
	}
	//重新计算listview的高度，否则listview只显示一行
	public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
	public void shoppingCarAnimation()
	{
		ScaleAnimation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,  Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(100);
		ImageView shoppingcar = (ImageView)view.findViewById(R.id.shoppingcar);
		shoppingcar.startAnimation(animation);
	}
	public void redHot(int x, int y, final int price)
	{
		ImageView shoppingcar = (ImageView)view.findViewById(R.id.shoppingcar);
		final ImageView redHot = (ImageView)view.findViewById(R.id.red_hot);
		int[] location = new int[2];  
        shoppingcar.getLocationInWindow(location);  
        int shoppingcarx = location[0];  
        int shoppingcary = location[1];  
		System.out.println("shoppingcarx:"+shoppingcarx);
		System.out.println("shoppingcary:"+shoppingcary);
		TranslateAnimation rotateanim = new TranslateAnimation((float)x,(float)shoppingcarx,(float)y,(float)(shoppingcary-300));
		rotateanim.setDuration(1000);
		rotateanim.setInterpolator(new MyInterpolator());
		rotateanim.setFillAfter(true);
		final ImageView red_hot_move = (ImageView)view.findViewById(R.id.red_hot_move);
		red_hot_move.startAnimation(rotateanim);
		red_hot_move.setVisibility(View.VISIBLE);
		final TextView priceSum = (TextView)view.findViewById(R.id.sum_price);
		final TextView choiceOk = (TextView)view.findViewById(R.id.choice_ok);
		sum_price += price;
		rotateanim.setAnimationListener(new AnimationEndListener() {
			
			@Override
			public void myAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redHot.setVisibility(View.VISIBLE);
				choiceOk.setText("选好了");
				red_hot_move.clearAnimation();
				red_hot_move.setVisibility(View.GONE);
				shoppingCarAnimation();
				priceSum.setText("￥"+ sum_price);
				btn_choice.setEnabled(true);
				btn_choice.setBackgroundResource(R.drawable.btn_bg);
			}
		});
		
	}
	public void removeFood(int price)
	{
		ImageView redHot = (ImageView)view.findViewById(R.id.red_hot);
		final TextView priceSum = (TextView)view.findViewById(R.id.sum_price);
		sum_price -= price;
		if(sum_price == 0)
		{
			btn_choice.setBackgroundResource(R.drawable.btn_bg_press);
			btn_choice.setEnabled(false);
			redHot.setVisibility(View.GONE);
		}
		priceSum.setText("￥"+ sum_price);
	}
}
