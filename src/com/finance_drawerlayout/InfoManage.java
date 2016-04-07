package com.finance_drawerlayout;

import java.util.Calendar;

import com.example.android_activity.dao.InaccountManager;
import com.example.android_activity.model.Tb_inaccount;
import com.example.android_activity.uitity.Uitits;
import com.zhy.demo_zhy_17_drawerlayout.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InfoManage extends Activity {
	private InaccountManager inaccountmanager;
	private Tb_inaccount tb_inaccount;
	private static final int DATE_DIALOG_ID = 0;
	private EditText et_Money;// 金额
	private EditText et_Time;// 时间
	private Spinner et_Type;// 类别
	private EditText et_Handler;// 付款方
	private EditText et_Mark;// 备注
	private int mYear;
	private int mMonth;
	private int mDay;
	private Calendar c = null;//
	Intent intent; // 获取已有的intent对象
	Bundle bundle; // 获取intent里面的bundle对象
	private long selsetid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomanage);

		et_Money = (EditText) findViewById(R.id.editMoney);// 金额
		et_Time = (EditText) findViewById(R.id.editTime);// 时间
		et_Type = (Spinner) findViewById(R.id.spinnerType);// 类别
		et_Handler = (EditText) findViewById(R.id.editHandler);// 付款方
		et_Mark = (EditText) findViewById(R.id.editMark);// 备注
		c = Calendar.getInstance();// 获取当前系统的时间
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		inaccountmanager = new InaccountManager(InfoManage.this);
		intent = this.getIntent(); // 获取已有的intent对象
		bundle = intent.getExtras(); // 获取intent里面的bundle对象
		selsetid = bundle.getLong("id");
		// selsetid = bundle.getInt("id");

		this.getDataFromDB();// 初始化显示
		updateDisplay();// 在窗口显示当前系统的时间

	}

	/**
	 * 为时间文本框设置单击监听事件
	 * 
	 * @param v
	 ***/
	public void clickSetTime(View v) {
		showDialog(DATE_DIALOG_ID);

	}

	/**
	 * 弹出对话框
	 * 
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub

		switch (id) {
		case DATE_DIALOG_ID:

			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);

		}
		return null;
	}

	/**
	 * 
	 * 显示用户设置的事件，选择用户所选择的时间
	 * 
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();

		}
	};

	/***
	 * 修改数据
	 * 
	 * @param v
	 */
	public void clickUpdate(View v) {

		try {
			tb_inaccount = new Tb_inaccount((int) selsetid + 1,
					Double.parseDouble(et_Money.getText().toString()), et_Time
							.getText().toString(), et_Type.getSelectedItem()
							.toString(), et_Handler.getText().toString(),
					et_Mark.getText().toString());

			inaccountmanager.update(tb_inaccount);
			
			Toast.makeText(this, "【更改数据成功！】", 1).show();

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "【更改数据失败！】", 1).show();
		}

	}

	/***
	 * 删除数据
	 * 
	 * @param v
	 */
	public void clickDelete(View v) {
		try {
			
			tb_inaccount=new Tb_inaccount();
			tb_inaccount.set_id((int) selsetid + 1);
			inaccountmanager.detele(tb_inaccount);
			Toast.makeText(this, "【数据删除成功】", 1).show();
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "【数据删除失败】", 1).show();
		}
		
         
	}

	/***
	 * 重置新增收入
	 * 
	 * @param v
	 */
	public void clickClean(View v) {
		et_Money.setText("");
		et_Money.setHint("0.00");
		et_Time.setText("");
		et_Time.setHint("2014-5-17");
		et_Type.setSelection(0);
		et_Handler.setText("");
		et_Mark.setText("");

	}

	/**
	 * 显示设置的时间
	 */
	private void updateDisplay() {
		et_Time.setText(new StringBuffer().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));

	}

	/***
	 * 初始化窗口显示
	 */
	public void getDataFromDB() {

		tb_inaccount = inaccountmanager.find(selsetid + 1);// 将查询的数据封装到tb_inaccount中

		et_Money.setText("" + tb_inaccount.getMoney());

		et_Time.setText("" + tb_inaccount.getTime());
		Resources res = getResources();
		String[] types = res.getStringArray(R.array.intype);// 获取字符串资源

		et_Type.setSelection(Uitits.TypeSelectId(tb_inaccount.getType(), types));

		et_Handler.setText("" + tb_inaccount.getHandler());
		et_Mark.setText("" + tb_inaccount.getMark());

	}

}
