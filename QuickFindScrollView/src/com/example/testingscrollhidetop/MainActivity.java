package com.example.testingscrollhidetop;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.testingscrollhidetop.CustomScrollView.OnTouchEvent;

public class MainActivity extends Activity {

	LinearLayout hideMe;
	CustomScrollView scrollView;
	LinearLayout addMe;

	int translation = 0;
	int viewSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hideMe = (LinearLayout) findViewById(R.id.layout);
		scrollView = (CustomScrollView) findViewById(R.id.scrollview);
		addMe = (LinearLayout) findViewById(R.id.hide_me);
		viewSize = getResources().getDimensionPixelSize(R.dimen.show_me_size);
		translation = getResources().getDimensionPixelSize(R.dimen.show_me_size);
		scrollView.setOnScrollViewListener(new OnScrollViewListener() {

			@Override
			public void onScrollChanged(CustomScrollView v,
										int l,
										int t,
										int oldl,
										int oldt) {

				if ((oldt - t) > 3) {

					// Move in View
					int move = (oldt - t);
					if (translation < viewSize) {
						Log.d(getClass().getSimpleName(), "================================");
						Log.d(getClass().getSimpleName(), "Transition on show: " + translation);
						Log.d(getClass().getSimpleName(), "Vertical movement: " + t);
						Log.d(getClass().getSimpleName(), "Old vertical movement: " + oldt);
						if ((translation + move) < viewSize) {
							// addMe.animate().translationYBy(move).start();
							scrollTo(-move);

						} else {
							move = viewSize - translation;
							// addMe.animate().translationYBy(move).start();
							scrollTo(-move);
						}
						translation += move;

					}

				} else if ((t - oldt) > 3) {

					// Remove View
					int move = (t - oldt);
					if (translation > 0) {
						Log.d(getClass().getSimpleName(), "================================");
						Log.d(getClass().getSimpleName(), "Transition on hide: " + translation);
						Log.d(getClass().getSimpleName(), "Vertical movement: " + t);
						Log.d(getClass().getSimpleName(), "Old vertical movement: " + oldt);
						if ((translation - move) > 0) {
							// addMe.animate().translationYBy(-move).start();
							scrollTo(move);

						} else {
							move = translation;
							// addMe.animate().translationYBy(-move).start();
							scrollTo(move);
						}
						translation -= move;

					}

				}

			}
		});

		scrollView.setOnTouchEvent(new OnTouchEvent() {

			@Override
			public void OnTouchEvent(MotionEvent ev) {
				if (ev.getAction() == MotionEvent.ACTION_UP) {
					if (translation != 0 && translation != viewSize) {
						if (translation >= viewSize / 2) {
							scrollTo(-(viewSize - translation));
							translation = viewSize;
						} else if (translation < viewSize / 2) {
							scrollTo(translation);
							translation = 0;
						}
					}
				}
			}
		});
	}

	public void scrollTo(int with) {

		Log.d(getClass().getSimpleName(), "This is the height with which the view should be moved: " + with);
		Log.d(getClass().getSimpleName(), "================================");
		// addMe.animate().translationYBy(with);
		hideMe.scrollBy(0, with);
	}

}
