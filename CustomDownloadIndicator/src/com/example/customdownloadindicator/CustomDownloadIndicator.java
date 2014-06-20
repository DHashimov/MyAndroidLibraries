package com.example.customdownloadindicator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.DropBoxManager;
import android.os.Handler;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomDownloadIndicator extends View {

	int xCentercoordinates = 0;
	int yCentercoordinates = 0;
	Paint paint = new Paint();
	private Bitmap mBitmap;
	private float percentage;

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percatange) {
		this.percentage = percatange;
	}

	public CustomDownloadIndicator(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CustomDownloadIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomDownloadIndicator(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint.setColor(Color.BLACK);
		percentage = 0;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		xCentercoordinates = widthMeasureSpec;
		yCentercoordinates = heightMeasureSpec;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		final RectF rect1 = new RectF();
		int mWidth = this.getWidth();
		int mHeight = this.getHeight();

		int mRadius = 130, mRadius1 = 50;
		rect1.set(0, 0, mWidth, mHeight);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth((mRadius - mRadius1) / 2);
		paint.setAntiAlias(true);
		paint.setStrokeCap(Paint.Cap.BUTT);
		paint.setStyle(Paint.Style.STROKE);

		canvas.drawArc(rect1, 270, getPercentage() * 3.6f, false, paint);

	}

	public void update(int percentage) {
		this.percentage = percentage;
		invalidate();
	}

}
