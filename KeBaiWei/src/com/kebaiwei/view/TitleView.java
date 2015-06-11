package com.kebaiwei.view;



import com.kebaiwei.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends RelativeLayout implements OnClickListener {

	private ImageButton btnLeft, btnRight;
	private TextView centerText, rightText, leftText;
	private OnLeftClickListener mOnLeftClickListener;
	private OnRightClickListener mOnRightClickListener;

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.title_view, null);
		btnLeft = (ImageButton) view.findViewById(R.id.back);
		btnRight = (ImageButton) view.findViewById(R.id.popu);
		centerText = (TextView) view.findViewById(R.id.info);
		rightText = (TextView) view.findViewById(R.id.set_fence);
		leftText = (TextView) view.findViewById(R.id.title_left_text);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		rightText.setOnClickListener(this);
		addView(view);
	}

	public interface OnRightClickListener {
		public void onClickListener(View ImageButton);
	}

	public interface OnLeftClickListener {
		public void onClickListener(View ImageButton);
	}

	public void setLeftbtnClickListener(OnLeftClickListener listener) {
		mOnLeftClickListener = listener;
	}
	public void setRightBtnClickListener(OnRightClickListener listener){
		
		mOnRightClickListener = listener;
	}
	public void removeLeft()
	{
		btnLeft.setVisibility(View.INVISIBLE);
	}
	public void removeRight()
	{
		btnRight.setVisibility(View.INVISIBLE);
		rightText.setVisibility(View.INVISIBLE);
	}
	public void setRightImage(int imageId)
	{
		btnRight.setBackgroundResource(imageId);
		rightText.setVisibility(View.GONE);
	}
	public void removeRightText()
	{
		rightText.setVisibility(View.INVISIBLE);
	}
	public void setLeftText(String text)
	{
		leftText.setVisibility(View.VISIBLE);
		leftText.setText(text);
	}
	public void setRightText(String text)
	{
		rightText.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.INVISIBLE);
		rightText.setText(text);
	}
	public void hiddenLeft()
	{
		btnLeft.setVisibility(View.GONE);
	}
	public void hiddenRight()
	{
		btnRight.setVisibility(View.GONE);
		rightText.setVisibility(View.GONE);
	}
	public void hiddenRightText()
	{
		rightText.setVisibility(View.GONE);
	}
	public void setCenterText(String text)
	{
		centerText.setText(text);
	}
	public void setRightTextColor(int color) {
		// TODO Auto-generated method stub
		rightText.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.INVISIBLE);
		rightText.setTextColor(color);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
        case R.id.back:
                if(mOnLeftClickListener != null)
                        mOnLeftClickListener.onClickListener(v);
                break;
        case R.id.set_fence:
                if(mOnRightClickListener != null)
                        mOnRightClickListener.onClickListener(v);
                break;
        case R.id.popu:
        	if(mOnRightClickListener != null)
                	mOnRightClickListener.onClickListener(v);
        		break;
        }
		
	}

	

}
