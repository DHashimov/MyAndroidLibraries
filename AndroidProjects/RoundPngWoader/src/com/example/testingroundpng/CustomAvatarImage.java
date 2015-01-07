package com.example.testingroundpng;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomAvatarImage extends ImageView {

	public CustomAvatarImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomAvatarImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomAvatarImage(Context context) {
		super(context);
	}

	// @Override
	// protected void onDraw(Canvas canvas) {
	//
	// Drawable drawable = getDrawable();
	//
	// if (drawable == null) {
	// return;
	// }
	//
	// if (getWidth() == 0 || getHeight() == 0) {
	// return;
	// }
	// Bitmap b = ((BitmapDrawable) drawable).getBitmap();
	// Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
	//
	// int w = getWidth();
	// int h = getHeight();
	//
	// Bitmap roundBitmap = transform(bitmap, w);
	// canvas.drawBitmap(roundBitmap, 0, 0, null);
	//
	// }

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(transform(bm, getWidth() / 2));

	}

	public Bitmap transform(Bitmap source, float radius) {
		Bitmap circleBitmap = Bitmap.createBitmap(source.getWidth(),
				source.getHeight(), Bitmap.Config.ARGB_8888);

		BitmapShader shader = new BitmapShader(source, TileMode.CLAMP,
				TileMode.CLAMP);

		Paint paint = new Paint();
		paint.setColor(0xFF000000);
		paint.setShader(shader);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);

		Canvas canvas = new Canvas(circleBitmap);
		canvas.drawCircle(source.getWidth() / 2, source.getHeight() / 2,
				radius, paint);

		source.recycle();
		return circleBitmap;
	}

}
