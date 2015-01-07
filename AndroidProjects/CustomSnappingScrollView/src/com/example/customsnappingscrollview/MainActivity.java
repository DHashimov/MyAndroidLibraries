package com.example.customsnappingscrollview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	CustomHorizontalScrollView scrollView;
	private int deviceWidth;
	private int deviceHidth;
	private String[] values = new String[] { "5", "6", "8", "13", "7", "12",
			"9", "1" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		deviceHidth = size.y;
		deviceWidth = size.x;
		deviceWidth = deviceWidth / 5;
		scrollView = new CustomHorizontalScrollView(
				this.getApplicationContext(), this);
		scrollView.setLayoutParams(new LayoutParams((deviceWidth * 3),
				deviceWidth));
		scrollView.setVerticalScrollBarEnabled(false);
		scrollView.setHorizontalScrollBarEnabled(false);
		scrollView.setSmoothScrollingEnabled(true);
		// scrollView.setOverScrollMode(HorizontalScrollView.OVER_SCROLL_NEVER);
		scrollView.setFeatureItems(values, deviceWidth);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.horizontalScrollView);
		linearLayout.addView(scrollView);

	}

}
