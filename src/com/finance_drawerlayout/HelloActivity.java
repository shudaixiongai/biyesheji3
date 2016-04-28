package com.finance_drawerlayout;

import com.zhy.demo_zhy_17_drawerlayout.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HelloActivity extends Activity {
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello);
		// 定时器
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(HelloActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
}
