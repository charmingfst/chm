package com.kebaiwei.main;

import android.view.animation.Interpolator;

/** 
 * @author ���� 
 * @date 2015-4-1 ����4:41:50 
 * @description TODO
 */
public class MyInterpolator implements Interpolator {

	@Override
	public float getInterpolation(float i) {
		// TODO Auto-generated method stub
		return i*i*i;
	}

}
