package com.example.customsnappingscrollview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomHorizontalScrollView extends HorizontalScrollView implements
		OnTouchListener {

	// private GestureDetector mGestureDetector;
	private static int mActiveFeature = 0;
	public static String centralItem;
	boolean currentlyScrolling;
	boolean currentlyTouching;
	public static int featureWidth;
	public TextView textView;
	public static ArrayList<TextView> views;
	static TextView paintedTextView;
	static float textSize;
	static Activity activity;
	boolean touched;
	public static int averigePickedPoints;
	public static int central;

	static String[] items;

	public CustomHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle, Activity activity) {
		super(context, attrs, defStyle);
		CustomHorizontalScrollView.activity = activity;
		views = new ArrayList<TextView>();
	}

	public CustomHorizontalScrollView(Context context, AttributeSet attrs,
			Activity activity) {
		super(context, attrs);
		CustomHorizontalScrollView.activity = activity;
		views = new ArrayList<TextView>();
	}

	public CustomHorizontalScrollView(Context context, Activity activity) {
		super(context);
		CustomHorizontalScrollView.activity = activity;
		views = new ArrayList<TextView>();
	}

	/**
	 * Fill the list of values passed by the pivotal story
	 */
	public void setFeatureItems(final String[] items, int deviceWidth) {
		CustomHorizontalScrollView.items = items;
		// Measure the text size depending on the size of the textView
		textSize = deviceWidth / 6;
		LinearLayout internalWrapper = new LinearLayout(getContext());
		internalWrapper.setLayoutParams(new LayoutParams((deviceWidth * 3),
				deviceWidth));
		internalWrapper.setOrientation(LinearLayout.HORIZONTAL);
		addView(internalWrapper);
		addTextView(deviceWidth, "", internalWrapper);
		for (int i = 0; i < items.length; i++) {
			addTextView(deviceWidth, items[i], internalWrapper);
		}
		addTextView(deviceWidth, "", internalWrapper);
		setOnTouchListener(this);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			currentlyTouching = true;
		}
		return super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouch(final View view, final MotionEvent event) {
		touched = true;
		featureWidth = view.getMeasuredWidth() / 3;
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			final float xCordinates = event.getX();
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {

					if (xCordinates < (featureWidth)) {
						int scrollX = getScrollX();
						mActiveFeature = ((scrollX + (featureWidth / 2)) / featureWidth);
						int scrollTo = mActiveFeature * featureWidth
								- featureWidth;
						smoothScrollTo(scrollTo, 0);
						new Thread() {
							@Override
							public void run() {
								if (mActiveFeature > 0) {
									setCentralItem(items[mActiveFeature]);
								} else {
									setCentralItem(items[mActiveFeature]);
								}
							}
						}.start();
					} else if (xCordinates > (featureWidth * 2)) {

						int scrollX = getScrollX();
						mActiveFeature = ((scrollX + (featureWidth / 2)) / featureWidth);
						int scrollTo = mActiveFeature * featureWidth
								+ featureWidth;
						smoothScrollTo(scrollTo, 0);
						new Thread() {
							@Override
							public void run() {
								if (mActiveFeature < items.length - 1) {
									setCentralItem(items[mActiveFeature]);
								} else {
									setCentralItem(items[mActiveFeature]);

								}
							}
						}.start();
					}
				}
			});
		case MotionEvent.ACTION_DOWN: {
			setCenterPosition();
		}
		case MotionEvent.ACTION_CANCEL:
			currentlyTouching = false;
			if (!currentlyScrolling) {

				// I handle the release from a drag here
				return true;
			}
		}
		return false;
	}

	/**
	 * Center the item nearest by the middle position
	 */
	private void setCenterPosition() {
		int scrollX = getScrollX();
		mActiveFeature = ((scrollX + (featureWidth / 2)) / featureWidth);
		int scrollTo = mActiveFeature * featureWidth;
		smoothScrollTo(scrollTo, 0);
		setCentralItem(items[mActiveFeature]);
		if (paintedTextView != null) {
			paintedTextView.setTextSize(textSize);
			paintedTextView = views.get(mActiveFeature + 1);
			paintedTextView.setTextSize(textSize + (textSize / 2));
		}
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldX, int oldY) {

		if (Math.abs(x - oldX) > 1) {
			currentlyScrolling = true;
		} else {
			currentlyScrolling = false;
			if (!currentlyTouching && touched) {

				setCenterPosition();

			} else {
				if (paintedTextView != null) {
					paintedTextView.setTextSize(textSize);
				}
				paintedTextView = views.get(averigePickedPoints + 1);
				paintedTextView.setTextSize(textSize + (textSize / 2));
				setCentralItem(items[averigePickedPoints]);

			}
		}
		super.onScrollChanged(x, y, oldX, oldY);
	}

	/**
	 * Set the size and the index of the first element from the array if there
	 * is no slide event or no average points set
	 */
	public static void paintIndexOfZero() {

		// if (paintedTextView != null) {
		// paintedTextView.setTextSize(textSize);
		// }
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (paintedTextView != null) {
					paintedTextView.setTextSize(textSize);
				}
				paintedTextView = views.get(1);
				paintedTextView.setTextSize(textSize + (textSize / 2));
				setCentralItem(items[0]);
			}
		});

	}

	/**
	 * add the text views from array passed by Pivotal
	 */
	private void addTextView(int deviceWidth, String text,
			LinearLayout internalWrapper) {
		textView = new TextView(getContext());
		textView.setWidth(deviceWidth);
		textView.setHeight(deviceWidth);
		textView.setGravity(Gravity.CENTER);
		textView.setText(text);
		textView.setTextSize(textSize);
		textView.setTextColor(Color.BLACK);
		views.add(textView);
		internalWrapper.addView(textView);
	}

	/**
	 * Return the item value at middle position
	 */
	public String getCentralItem() {
		return centralItem;
	}

	public static void setCentralItem(String centralItems) {
		centralItem = centralItems;
	}
}