package com.example.testingscrollhidetop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {

	public CustomScrollView(Context context,
							AttributeSet attrs,
							int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomScrollView(Context context,
							AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private OnScrollViewListener mOnScrollViewListener;

	public void setOnScrollViewListener(OnScrollViewListener l) {
		this.mOnScrollViewListener = l;
	}

	@Override
	protected void onScrollChanged(	int l,
									int t,
									int oldl,
									int oldt) {
		mOnScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
		super.onScrollChanged(l, t, oldl, oldt);
	}

	private OnTouchEvent onTouchEvent;

	public void setOnTouchEvent(OnTouchEvent e) {
		this.onTouchEvent = e;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		onTouchEvent.OnTouchEvent(ev);
		return super.onTouchEvent(ev);

	}

	public interface OnTouchEvent {

		public void OnTouchEvent(MotionEvent ev);

	}
}
