package com.example.customanimatedview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class SpriteDroid extends View {
	int deviceWidth;
	int deviceHeight;
	int height;
	int width;
	Bitmap droid;
	int currentFrame = 0;
	int direction = 0;
	int countFrames = 0;
	private Paint p;
	private int mWidth;
	private int mHeight;

	public SpriteDroid(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public SpriteDroid(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public SpriteDroid(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		deviceHeight = size.y;
		deviceWidth = size.x;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		droid = decodeSampledBitmapFromResource(getResources(),
				R.drawable.sprite_droid, deviceWidth, deviceHeight);
		height = droid.getHeight() / 8;
		width = droid.getWidth() / 10;
		p = new Paint();
		p.setAntiAlias(true);
		p.setDither(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
		mHeight = View.MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(mWidth, mHeight);
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		new Thread() {
			@Override
			public void run() {
				update();
				if (countFrames == 10) {
					direction = ++direction % 8;
				}
			}
		}.start();
		int srcX = currentFrame * width;
		int srcY = direction * height;
		Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		Rect dst = new Rect(0, 0, mWidth, mHeight);

		canvas.drawBitmap(droid, src, dst, p);
		invalidate();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		currentFrame = ++currentFrame % 10;
		if (countFrames == 11) {
			countFrames = 0;
		} else {
			countFrames += 1;
		}

	}
}
