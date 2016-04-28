package com.finance_drawerlayout;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

import com.zhy.demo_zhy_17_drawerlayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends Activity {
	Button button;
	EditText et_naEditText;
	EditText et_nameEditText;
	Button send_button;
	String string;
	String string2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.new_main);
		BmobSMS.initialize(getApplicationContext(),
				"9654712ef670010e06fc776113d3b63f");
		// setview();
	}

	private void setview() {
		et_nameEditText = (EditText) findViewById(R.id.et_iphone);
		et_naEditText = (EditText) findViewById(R.id.et_et_et);
		button = (Button) findViewById(R.id.button);
		send_button = (Button) findViewById(R.id.send_button);
		send_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				send();
			}
		});
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				jieshou();
			}
		});

	}

	private void send() {
		string = et_nameEditText.getText().toString();
		Log.i("1", string);
		BmobSMS.requestSMSCode(getApplicationContext(), string, "短信1",
				new RequestSMSCodeListener() {

					@Override
					public void done(Integer arg0, BmobException arg1) {
						if (arg1 == null) {
							Log.i("bmob", "短信di" + arg0);
						} else {
							Log.i("bmob", "发送失败" + arg0);

						}
					}
				});
	}

	private void jieshou() {
		string2 = et_naEditText.getText().toString();
		BmobSMS.verifySmsCode(getApplicationContext(), string, string2,
				new VerifySMSCodeListener() {

					@Override
					public void done(BmobException arg0) {
						if (arg0 == null) {
							Toast.makeText(getApplicationContext(), "验证成功", 1)
									.show();
						} else {
							Toast.makeText(
									getApplicationContext(),
									"验证失败：code =" + arg0.getErrorCode()
											+ ",msg = "
											+ arg0.getLocalizedMessage(), 1)
									.show();
						}
					}
				});
	}
}
