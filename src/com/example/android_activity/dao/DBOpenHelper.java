package com.example.android_activity.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	private final static int VERSION = 1;
	private final static String DB_NAME = "account.db";

	public DBOpenHelper(Context context) {

		super(context, DB_NAME, null, VERSION);

		// TODO Auto-generated constructor stub
	}

	/**
	 * ������ݿ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub
		try {
			db.execSQL("create table tb_pwd(userName varchar(20),password varchar(20))");// 创建用户名和密码表
			db.execSQL("create table tb_inaccount(_id integer primary key,money decimal,time varchar(10),type varchar(10),address varchar(100),mark varchar(200))");// 创建收入信息表

			db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,time varchar(10),type varchar(10),address varchar(100),mark varchar(200))");// 创建支出信息表
            
		} catch (Exception e) {
			System.out.println("------》》建表失败！");
			// TODO: handle exception
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
