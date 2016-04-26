package com.finance_drawerlayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.demo_zhy_17_drawerlayout.R;

public class FourActivity extends Activity {
	private Button bt_clear;
	private TextView manger_name;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manger_login);
		manger_name = (TextView) findViewById(R.id.manger_login);
		SharedPreferences sp = getApplicationContext().getSharedPreferences(
				"config", MODE_PRIVATE);
		editor = sp.edit();
		manger_name.setText(sp.getString("re_name", "").toString());
		bt_clear = (Button) findViewById(R.id.clear);
		bt_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				editor.clear();
				Intent intent = new Intent(FourActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		});
	}
}
