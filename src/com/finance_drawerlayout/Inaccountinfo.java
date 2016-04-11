package com.finance_drawerlayout;

import java.util.List;

import com.example.android_activity.dao.InaccountManager;
import com.example.android_activity.model.Tb_inaccount;
import com.zhy.demo_zhy_17_drawerlayout.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Inaccountinfo extends Activity {
	public static final String FLAG = "id";
	ListView listView = null;
	String strType = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccount);

		listView = (ListView) findViewById(R.id.listView1);
		ShowInfo();
		// 为listView 添加单击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Inaccountinfo.this, InfoManage.class);
				Bundle bundle = new Bundle();
				bundle.putLong("id", id);
				// 将获取所点击listView的行号 传递给下一个页面
				intent.putExtras(bundle);
				// startActivity(intent);
				startActivityForResult(intent, 2);
				finish();
			}
		});

	}

	private void ShowInfo() {
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		// strType = "btnininfo";
		InaccountManager inaccountManager = new InaccountManager(
				Inaccountinfo.this);
		List<Tb_inaccount> listifos = inaccountManager.getScrollData(0,
				(int) inaccountManager.getCount());// 获取所有收入信息,并存储到List泛型集合
		strInfos = new String[listifos.size()];// 设置字符串数组的长度
		int m = 0;
		for (Tb_inaccount tb_inaccount : listifos) {
			// 将输入相关信息组合成一个字符串 储存到字符串数组的中
			strInfos[m] = tb_inaccount.get_id()
					+ "||"
					+ tb_inaccount.getType()
					+ ""
					+ String.valueOf(tb_inaccount.getMoney() + "元"
							+ tb_inaccount.getTime());
			m++;

		}

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		listView.setAdapter(arrayAdapter);

	}

}
