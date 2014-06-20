package com.example.testingroundpng;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView customAvatarImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customAvatarImage = (ImageView) findViewById(R.id.custom_avatar);

		new Thread() {
			public void run() {
				DownloadFullFromUrl("http://d2nbqvnluu765z.cloudfront.net/avatars/fastbreak10-138617407634.jpg");
			}
		}.start();

	}

	public Bitmap DownloadFullFromUrl(String imageFullURL) {
		Bitmap bm = null;
		try {
			URL url = new URL(imageFullURL);
			URLConnection ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			bm = BitmapFactory.decodeByteArray(baf.toByteArray(), 0,
					baf.toByteArray().length);
		} catch (IOException e) {
			Log.d("ImageManager", "Error: " + e);
		}
		loadBitmap(bm);
		return bm;
	}

	private void loadBitmap(final Bitmap bm) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				customAvatarImage.setImageBitmap(getCroppedBitmap(bm));

			}
		});
	}

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
				bitmap.getWidth() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
		// return _bmp;
		return output;
	}

}
