package com.finance_drawerlayout;

import java.util.Calendar;

import com.example.android_activity.dao.OutaccountManager;
import com.example.android_activity.model.Tb_outaccount;
import com.zhy.demo_zhy_17_drawerlayout.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOutaccountinfo extends Activity {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoutaccount);
		et_Money = (EditText) findViewById(R.id.editMoney);// 金额
		et_Time = (EditText) findViewById(R.id.editTime);// 时间
		et_Type = (Spinner) findViewById(R.id.spinnerType);// 类别
		et_Handler = (EditText) findViewById(R.id.editHandler);// 付款方
		et_Mark = (EditText) findViewById(R.id.editMark);// 备注
		c = Calendar.getInstance();// 获取当前系统的时间
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
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

			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);

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
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();

		}
	};

	/***
	 * 将数据保存到数据表中
	 * 
	 * @param v
	 */
	public void clickSave(View v) {

		String Smoney = et_Money.getText().toString();
		if (!Smoney.isEmpty()) {// 判断金额不为空

			double money = Double.parseDouble(Smoney);
			String time = et_Time.getText().toString();
			String type = et_Type.getSelectedItem().toString();
			String handler = et_Handler.getText().toString();
			String mark = et_Mark.getText().toString();
			if (!(Smoney.isEmpty() || time.isEmpty() || type.isEmpty() || handler.isEmpty() || mark.isEmpty())) {
				try {
					OutaccountManager outaccountManager = new OutaccountManager(AddOutaccountinfo.this);

					Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountManager.getMaxId() + 1, money, time, type,
							handler, mark);

					outaccountManager.add(tb_outaccount);// 添加收入信息
					Toast.makeText(this, "【数据添加成功】", 1).show();

				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(this, "【数据添加失败】", 1).show();
				}

			} else {
				Toast.makeText(this, "【收入信息不完善，重新输入】", 1).show();

			}
		} else {
			Toast.makeText(this, "【金额不能为空！请输入金额】", 1).show();
		}

	}

	public void clickBack(View v) {
		finish();

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
		et_Time.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));

	}

}
