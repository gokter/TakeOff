package com.flyingh.takeoff;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final int M = 30;
	private ImageView backImageView;
	private ImageView frontImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		backImageView = (ImageView) findViewById(R.id.back);
		frontImageView = (ImageView) findViewById(R.id.front);
		backImageView.setImageResource(R.drawable.back);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.front);
		frontImageView.setImageBitmap(bitmap);
		final Bitmap mutableBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas = new Canvas(mutableBitmap);
		canvas.drawBitmap(bitmap, new Matrix(), new Paint());
		frontImageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (MotionEvent.ACTION_MOVE == event.getAction()) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					for (int i = -M; i <= M; i++) {
						for (int j = -M; j <= M; j++) {
							if (checkPoint(i + x, y + j)) {
								mutableBitmap.setPixel(i + x, y + j, Color.TRANSPARENT);
							}
						}
					}
					frontImageView.setImageBitmap(mutableBitmap);
				}
				return true;
			}

			private boolean checkPoint(int x, int y) {
				return x >= 0 && x < mutableBitmap.getWidth() && y >= 0 && y < mutableBitmap.getHeight();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
