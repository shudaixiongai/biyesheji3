package com.finance_drawerlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.sms.listener.InitListener;

import com.zhy.demo_zhy_17_drawerlayout.R;

public class LoginActivity extends Activity {
	private EditText lg_name, lg_psd;
	private Button bt_login, bt_register;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_login);
		// setView();
		InitView();
	}

	private void InitView() {
		Button btn_login_ok = (Button) findViewById(R.id.btn_login_ok);
		btn_login_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,
						TestActivity.class);
				startActivity(intent);

			}
		});
		Button btn_login = (Button) findViewById(R.id.login_button);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setView() {
		lg_name = (EditText) findViewById(R.id.et_name);
		lg_psd = (EditText) findViewById(R.id.et_psd);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_register = (Button) findViewById(R.id.bt_register);
		bt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String login_name = lg_name.getText().toString();
				String login_psd = lg_psd.getText().toString();
				SharedPreferences sp = getApplicationContext()
						.getSharedPreferences("config", MODE_PRIVATE);
				Editor editor = sp.edit();
				String ch_name = sp.getString("re_name", "").toString();
				String ch_psd = sp.getString("re_psd", "").toString();
				if (login_name.equals(ch_name) && login_psd.equals(ch_psd)) {
					Toast.makeText(getApplicationContext(), "登录成功！！", 1).show();
					try {
						Thread.sleep(2000);
						Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						startActivity(intent);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(), "用户名和密码输入错误！！", 1)
							.show();
				}
				if (TextUtils.isEmpty(login_name)
						&& TextUtils.isEmpty(login_psd)) {
					Toast.makeText(getApplicationContext(), "请输入用户名和密码！！", 1)
							.show();
				}
			}
		});
		bt_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Intent intent = new Intent(LoginActivity.this,
				// RegisterActivity.class);
				// startActivity(intent);
			}
		});
	}
}
