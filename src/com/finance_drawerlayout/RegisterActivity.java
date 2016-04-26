package com.finance_drawerlayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.demo_zhy_17_drawerlayout.R;

public class RegisterActivity extends Activity {
	private EditText et_name, et_psd, et_psd2;
	private Button bt_register, bt_back;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		setview();
	}

	private void setview() {
		et_name = (EditText) findViewById(R.id.re_name);
		et_psd = (EditText) findViewById(R.id.re_psd);
		et_psd2 = (EditText) findViewById(R.id.re_psd2);
		bt_register = (Button) findViewById(R.id.re_register);
		bt_back = (Button) findViewById(R.id.back);
		bt_register.setOnClickListener(new btnclick());
		// bt_register.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// String re_name = et_name.getText().toString();
		// String re_psd = et_psd.getText().toString();
		// String re_psd2 = et_psd2.getText().toString();
		// SharedPreferences sp =
		// getApplicationContext().getSharedPreferences("config",
		// MODE_PRIVATE);
		// Editor editor = sp.edit();
		// editor.putString("re_name", re_name);
		// editor.putString("re_psd", re_psd);
		// editor.putString("re_psd2", re_psd2);
		// editor.commit();
		// Toast.makeText(getApplicationContext(), "注册成功", 1).show();
		// }
		// });
		 bt_back.setOnClickListener(new OnClickListener() {
		
		 @Override
		 public void onClick(View arg0) {
		 finish();
		 }
		 });
	}

	class btnclick implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			String re_name = et_name.getText().toString();
			String re_psd = et_psd.getText().toString();
			String re_psd2 = et_psd2.getText().toString();
			SharedPreferences sp = getApplicationContext()
					.getSharedPreferences("config", MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString("re_name", re_name);
			editor.putString("re_psd", re_psd);
			editor.putString("re_psd2", re_psd2);
			editor.commit();
			Toast.makeText(getApplicationContext(), "注册成功", 1).show();
		}

	}

}
