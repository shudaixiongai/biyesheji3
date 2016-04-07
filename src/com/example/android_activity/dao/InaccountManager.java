package com.example.android_activity.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.android_activity.model.Tb_inaccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InaccountManager {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public InaccountManager() {
		// TODO Auto-generated constructor stub
		super();
	}

	public InaccountManager(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);// 初始化DBOpenHelper����
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase����

	}

	/**
	 * 添加收入信息
	 * 
	 * @param tb_inaccount
	 */
	public void add(Tb_inaccount tb_inaccount) {

		// db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����

		String sql = "insert into tb_inaccount(_id,money,time,type,address,mark) values (?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] { tb_inaccount.get_id(),
				tb_inaccount.getMoney(), tb_inaccount.getTime(),
				tb_inaccount.getType(), tb_inaccount.getHandler(),
				tb_inaccount.getMark() };
		try {
			db.execSQL(sql, bindArgs);
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * 获取数据库表中的最大编号
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(_id)from tb_inaccount";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToLast()) {// 访问最后一条数据
			return cursor.getInt(0);// 获取访问到的数据 即最大的数据

		}
		return 0;

	}

	/***
	 * 获取收入信息
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Tb_inaccount> getScrollData(int start, int count) {
		List<Tb_inaccount> tb_inaccounts = new ArrayList<Tb_inaccount>();
		String sql = "select *from tb_inaccount limit ?,?";
		String[] selectionArgs = { String.valueOf(start), String.valueOf(count) };

		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while (cursor.moveToNext()) {
			tb_inaccounts.add(new Tb_inaccount(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("type")), cursor.getString(cursor
					.getColumnIndex("address")), cursor.getString(cursor
					.getColumnIndex("mark"))));

		}

		return tb_inaccounts;

	}

	/**
	 * 获取总的记录数
	 * 
	 * @return
	 */
	public long getCount() {
		String sql = "select count(_id) from tb_inaccount";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);

		}
		return 0;

	}

	/***
	 * 根据ID 查询数据库
	 * 
	 * @param id
	 * @return
	 */
	public Tb_inaccount find(long id) {
		String sql = "select _id,money,time,type,address,mark from tb_inaccount where _id=?";
		String[] selectionArgs = { String.valueOf(id) };
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext()) {
			return new Tb_inaccount(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("address")),
					cursor.getString(cursor.getColumnIndex("mark")));

		}

		return null;

	}

	/***
	 * 根据ID修改数据库
	 * 
	 * @param tb_inaccount
	 * @param id
	 */
	public void update(Tb_inaccount tb_inaccount) {
		String sql = "update tb_inaccount set money=?,time=?,type=?,address=?,mark=?where _id=?";
		Object[] bindArgs = { tb_inaccount.getMoney(), tb_inaccount.getTime(),
				tb_inaccount.getType(), tb_inaccount.getHandler(),
				tb_inaccount.getMark(), tb_inaccount.get_id() };

		db.execSQL(sql, bindArgs);

	}

	/**
	 * 删除数据
	 * 
	 * @param tb_inaccount
	 */
	public void detele(Tb_inaccount tb_inaccount) {

		//String sql = "delete from tb_inaccount where _id=?";
		String sql = "update tb_inaccount set money=?,time=?,type=?,address=?,mark=?where _id=?";
		Object[] bindArgs = {"","","","","", tb_inaccount.get_id() };
		db.execSQL(sql, bindArgs);

	}

	

	

}
