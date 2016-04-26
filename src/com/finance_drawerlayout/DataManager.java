package com.finance_drawerlayout;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zhy.demo_zhy_17_drawerlayout.R;


public class DataManager extends Activity implements OnClickListener {
	private Button btInManager;
	private Button btOutManager;
	private Button btBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datamanage);
		btInManager = (Button) this.findViewById(R.id.button1);
		btOutManager = (Button) this.findViewById(R.id.button2);
		btBack = (Button) this.findViewById(R.id.button3);
		btBack.setOnClickListener(this);
		btInManager.setOnClickListener(this);
		btOutManager.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			Intent intent=new Intent(DataManager.this, Inaccountinfo.class);
			startActivity(intent);

			break;
		case R.id.button2:
			Intent intent1=new Intent(DataManager.this, Outaccountinfo.class);
			startActivity(intent1);

			break;
		case R.id.button3:
			finish();

			break;

		default:
			break;
		}

	}

}
