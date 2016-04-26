package com.finance_drawerlayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.demo_zhy_17_drawerlayout.R;

public class MenuLeftFragment extends Fragment {
	private TextView one, two, three, four, five;
	private Fragment onefragment, twoActivity, threeActivity, fourActivity, fiveActivity;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_menu, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setview();
	}

	private void setview() {
		one = (TextView) getActivity().findViewById(R.id.text1);
		two = (TextView) getActivity().findViewById(R.id.text2);
		three = (TextView) getActivity().findViewById(R.id.text3);
		four = (TextView) getActivity().findViewById(R.id.text4);
		five = (TextView) getActivity().findViewById(R.id.text5);
		one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), AddInaccountinfo.class);
				startActivity(intent);
			}
		});
		two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), AddOutaccountinfo.class);
				startActivity(intent);
			}
		});
		three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), DataManager.class);
				startActivity(intent);
			}
		});
		four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), FourActivity.class);
				startActivity(intent);
			}
		});
		five.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), FiveActivity.class);
				startActivity(intent);
			}
		});
	}

}
