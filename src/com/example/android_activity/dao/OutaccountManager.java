package com.example.android_activity.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_activity.model.Tb_outaccount;

public class OutaccountManager {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public OutaccountManager() {
		// TODO Auto-generated constructor stub
		super();
	}

	public OutaccountManager(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);// 初始化DBOpenHelper
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase

	}

	/**
	 * 添加收入信息
	 * 
	 * @param Tb_outaccount
	 */
	public void add(Tb_outaccount tb_outaccount) {

		

		String sql = "insert into tb_outaccount(_id,money,time,type,address,mark) values (?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] { tb_outaccount.get_id(),
				tb_outaccount.getMoney(), tb_outaccount.getTime(),
				tb_outaccount.getType(), tb_outaccount.getHandler(),
				tb_outaccount.getMark() };
		try {
			db.execSQL(sql, bindArgs);
		} catch (Exception e) {
			// TODO: handle exception

		}
	}
	
	/**
	 * 获取数据库表中的最大编号
	 * @return
	 */
	public int getMaxId(){
		String sql="select max(_id)from tb_outaccount";
		Cursor cursor=db.rawQuery(sql, null);
		while(cursor.moveToLast()){//访问最后一条数据
			return cursor.getInt(0);//获取访问到的数据 即最大的数据
			
		}
		return 0;
		
	}
	
	/***
	 * 获取支出信息
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Tb_outaccount> getScrollData(int start, int count) {
		List<Tb_outaccount> tb_outaccounts = new ArrayList<Tb_outaccount>();
		String sql = "select *from tb_outaccount limit ?,?";
		String[] selectionArgs = { String.valueOf(start), String.valueOf(count) };

		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while (cursor.moveToNext()) {
			tb_outaccounts.add(new Tb_outaccount(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("type")), cursor.getString(cursor
					.getColumnIndex("address")), cursor.getString(cursor
					.getColumnIndex("mark"))));

		}

		return tb_outaccounts;

	}

	/**
	 * 获取总的记录数
	 * 
	 * @return
	 */
	public long getCount() {
		String sql = "select count(_id) from tb_outaccount";
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
	public Tb_outaccount find(long id) {
		String sql = "select _id,money,time,type,address,mark from tb_outaccount where _id=?";
		String[] selectionArgs = { String.valueOf(id) };
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext()) {
			Tb_outaccount tb_outaccount=new Tb_outaccount(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("address")),
					cursor.getString(cursor.getColumnIndex("mark")));
			
			
			return tb_outaccount;

		}

		return null;

	}

	/***
	 * 根据ID修改数据库
	 * 
	 * @param Tb_outaccount
	 * @param id
	 */
	public void update(Tb_outaccount tb_outaccount) {
		String sql = "update tb_outaccount set money=?,time=?,type=?,address=?,mark=?where _id=?";
		Object[] bindArgs = { tb_outaccount.getMoney(), tb_outaccount.getTime(),
				tb_outaccount.getType(), tb_outaccount.getHandler(),
				tb_outaccount.getMark(), tb_outaccount.get_id() };

		db.execSQL(sql, bindArgs);

	}

	/**
	 * 删除数据
	 * 
	 * @param Tb_outaccount
	 */
	public void detele(Tb_outaccount tb_outaccount) {

		//String sql = "delete from Tb_outaccount where _id=?";
		String sql = "update tb_outaccount set money=?,time=?,type=?,address=?,mark=?where _id=?";
		Object[] bindArgs = {"","","","","", tb_outaccount.get_id() };
		db.execSQL(sql, bindArgs);

	}

}
