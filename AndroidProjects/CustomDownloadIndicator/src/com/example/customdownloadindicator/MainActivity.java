package com.example.customdownloadindicator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	CustomDownloadIndicator customDownloadIndicator;
	ImageView emptyCircle;
	public int percente = 0;
	ImageView addPercentige;
	Button updatePercents;

	TextView txtPercents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customDownloadIndicator = (CustomDownloadIndicator) findViewById(R.id.custom_loader);
		emptyCircle = (ImageView) findViewById(R.id.button);
		txtPercents = (TextView) findViewById(R.id.percents);
		txtPercents.setText("");
		emptyCircle.setVisibility(View.VISIBLE);
		updatePercents = (Button) findViewById(R.id.update_percents);
		updatePercents.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (percente <= 100)
					startLoading();
			}
		});

	}

	private void startLoading() {

		customDownloadIndicator.update(percente);
		txtPercents.setText((int) percente + "%");
		percente++;

	}

}
