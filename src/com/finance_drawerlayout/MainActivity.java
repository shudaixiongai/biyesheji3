package com.finance_drawerlayout;

import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_activity.dao.InaccountManager;
import com.example.android_activity.dao.OutaccountManager;
import com.example.android_activity.model.Tb_inaccount;
import com.example.android_activity.model.Tb_outaccount;
import com.nineoldandroids.view.ViewHelper;
import com.zhy.demo_zhy_17_drawerlayout.R;

/**
 * http://blog.csdn.net/lmj623565791/article/details/41531475
 * 
 * @author zhy
 * 
 */
public class MainActivity extends FragmentActivity {
	private TextView tv_name, tv_shouru, tv_chichu, tv_yusuan;
	private ListView listview;
	private Button button;
	double money;
	private DrawerLayout mDrawerLayout;
	double outmonney;
	private PopupWindow mPopupWindow;
	// ��Ļ��width
	private int mScreenWidth;
	// ��Ļ��height
	private int mScreenHeight;
	// PopupWindow��width
	private int mPopupWindowWidth;
	// PopupWindow��height
	private int mPopupWindowHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initEvents();
		setview();

	}

	private void setview() {
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_chichu = (TextView) findViewById(R.id.tv_chichu);
		tv_shouru = (TextView) findViewById(R.id.tv_shouru);
		tv_yusuan = (TextView) findViewById(R.id.tv_yusuan);
		// listview = (ListView) findViewById(R.id.button);
		button = (Button) findViewById(R.id.button);
		SharedPreferences sp = getApplicationContext().getSharedPreferences(
				"config", MODE_PRIVATE);
		Editor editor = sp.edit();
		tv_name.setText(sp.getString("re_name", "").toString());

		InaccountManager inaccountManager = new InaccountManager(
				MainActivity.this);
		List<Tb_inaccount> listifos = inaccountManager.getScrollData(0,
				(int) inaccountManager.getCount());// 获取所有收入信息,并存储到List泛型集合
		for (int i = 0; i < listifos.size(); i++) {
			money += listifos.get(i).getMoney();
			Log.i("money", String.valueOf(money));
		}
		tv_chichu.setText(String.valueOf(money));
		OutaccountManager outaccountManager = new OutaccountManager(
				MainActivity.this);
		List<Tb_outaccount> outlistifos = outaccountManager.getScrollData(0,
				(int) outaccountManager.getCount());// 获取所有收入信息,并存储到List泛型集合
		for (int i = 0; i < outlistifos.size(); i++) {
			outmonney += outlistifos.get(i).getMoney();
		}
		tv_shouru.setText(String.valueOf(outmonney));
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				getPopupWindowInstance();
				mPopupWindow.showAtLocation(arg0, Gravity.CENTER, 0, 0);
			}
		});
	}

	private void getPopupWindowInstance() {
		if (null != mPopupWindow) {
			mPopupWindow.dismiss();
			return;
		} else {
			initPopuptWindow();
		}
	}

	private void initPopuptWindow() {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupWindow = layoutInflater.inflate(R.layout.item, null);
		TextView tv_option1 = (TextView) popupWindow
				.findViewById(R.id.tv_option1);
		TextView tv_option2 = (TextView) popupWindow
				.findViewById(R.id.tv_option2);
		tv_option1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "1", 1).show();
			}
		});
		tv_option2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "2", 1).show();
			}
		});
		// RadioGroup radioGroup = (RadioGroup) popupWindow
		// .findViewById(R.id.radioGroup);
		// radioGroup.setOnCheckedChangeListener(this);

		// ����һ��PopupWindow
		mPopupWindow = new PopupWindow(popupWindow,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT);

		// ����SelectPicPopupWindow��������ɵ��
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);

		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0000000000);
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener
		// �����������ؼ��仯�Ȳ���
		mPopupWindow.setBackgroundDrawable(dw);

		// ��ȡ��Ļ��PopupWindow��width��height��
		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
		mPopupWindowWidth = mPopupWindow.getWidth();
		mPopupWindowHeight = mPopupWindow.getHeight();

		// ��ӡ��Ļ��popupwindow�Ŀ�ߣ�
		Log.i("Tag", "ScreenWidth : " + mScreenWidth + "\n" + "ScreenHeight : "
				+ mScreenHeight + "\n" + "PopupWindowWidth : "
				+ mPopupWindowWidth + "\n" + "PopupWindowHeight : "
				+ mPopupWindowHeight);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener
		// �����������ؼ��仯�Ȳ���

		// ����SelectPicPopupWindow�������嶯��Ч��
	}

	public void OpenRightMenu(View view) {
		setview();
		mDrawerLayout.openDrawer(Gravity.RIGHT);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);
	}

	private void initEvents() {
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			@Override
			public void onDrawerStateChanged(int newState) {
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT")) {

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else {
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView) {
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	private void initView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
	}

}
