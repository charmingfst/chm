package com.kebaiwei.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/** 
 * @author ���� 
 * @date 2015-3-26 ����11:01:17 
 * @description TODO
 */
public class GuidanceAdapter extends PagerAdapter {
	private List<View> listView;
	public GuidanceAdapter(List<View> listView)
	{
		this.listView = listView;
	}
	//���ص���ҳ��������
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listView.size();
	}
	//�ж�View�Ƿ����Զ���
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	//ʵ����һ��ҳ��
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(listView.get(position));
		return listView.get(position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(listView.get(position));
	}
}
