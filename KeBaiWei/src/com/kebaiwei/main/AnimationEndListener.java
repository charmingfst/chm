package com.kebaiwei.main;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/** 
 * @author ³ÂÃ÷ 
 * @date 2015-3-31 ÏÂÎç4:59:31 
 * @description TODO
 */
public abstract class AnimationEndListener implements AnimationListener {

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		myAnimationEnd(animation);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}
	public abstract void myAnimationEnd(Animation animation);
}
