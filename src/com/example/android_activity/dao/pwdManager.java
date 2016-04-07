package com.example.android_activity.dao;

import com.example.android_activity.model.Tb_pwd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class pwdManager {

	private DBOpenHelper helper;// ����DBOpenHelper����
	private SQLiteDatabase db;// ����SQLiteDatabase����

	public pwdManager() {
		super();
	}

	public pwdManager(Context context)// ���幹�캯��

	{

		helper = new DBOpenHelper(context);// ��ʼ��DBOpenHelper����
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����

	}

	/**
	 * ����ݿ�д���û��������
	 * 
	 * @param tb_pwd
	 */
	public void addpwd(Tb_pwd tb_pwd) {

		String sql = "insert into tb_pwd (userName,password) values (?,?)";
		Object[] bindArgs = new Object[] { tb_pwd.getUserName(),
				tb_pwd.getPassword() };
		try {
			db.execSQL(sql, bindArgs);

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(null, "pwdManagerд����ݿ�ʧ��", 1).show();
		}

	}

	public Tb_pwd find() {
		// �������벢�洢��Cursor����
		String sql = "select userName,password from tb_pwd";

		Cursor cursor = db.rawQuery(sql, null);

		int cols_len = cursor.getColumnCount();

		if (cursor.moveToLast()) {// ������ҵ���������Ϣ
			Tb_pwd tb_pwd = new Tb_pwd(cursor.getString(cursor
					.getColumnIndex("password")), cursor.getString(cursor
					.getColumnIndex("userName")));
			return tb_pwd;

		}

		return null;

	}

	public long getCount() {

		Cursor cursor = db.rawQuery("select count(userName) from tb_pwd", null);// ��ȡ������Ϣ�ļ�¼��
		if (cursor.moveToNext())// �ж�Cursor���Ƿ������
		{
			return cursor.getLong(0);// �����ܼ�¼��
		}
		return 0;// ���û����ݣ��򷵻�0
	}

	/**
	 * 检测 用户名是否注册
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean isRegist(String userName, String password) {

		String sql = "select userName,password from tb_pwd";
		Cursor cursor = db.rawQuery(sql, null);
		int cols_len = cursor.getColumnCount();

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {// 遍历数据库所有的数据

			String pwd = cursor.getString(cursor.getColumnIndex("password"));
			String name = cursor.getString(cursor.getColumnIndex("userName"));

			if (userName.equals(name)) {
				return true;

			}

		}
		return false;

	}

	/**
	 * 检测用户名和密码是否正确
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean isRightNamepwd(String userName, String password) {

		String sql = "select userName,password from tb_pwd";
		Cursor cursor = db.rawQuery(sql, null);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {// 遍历数据库所有的数据

			String pwd = cursor.getString(cursor.getColumnIndex("password"));
			String name = cursor.getString(cursor.getColumnIndex("userName"));

			if (userName.equals(name) && password.equals(pwd)) {
				return true;

			}

		}
		return false;

	}
/***
 * 
 * @param password
 * @return
 */
	public boolean isRightPassword(String password){
		
		String sql = "select userName,password from tb_pwd";
		Cursor cursor = db.rawQuery(sql, null);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {// 遍历数据库所有的数据
			String pwd = cursor.getString(cursor.getColumnIndex("password"));
			if ( password.equals(pwd)) {
				return true;

			}

		}
		
		
		return false;
		
	}
	/***
	 * 修改密码
	 * @param password
	 */
	public void updatePassword(String password ){
		String sql = "update tb_pwd set password=?";
		String [] bindArgs={password};
		
		db.execSQL(sql, bindArgs);
		
		
		
	}
}
