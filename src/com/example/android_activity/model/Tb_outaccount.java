package com.example.android_activity.model;

public class Tb_outaccount {

	private int _id;
	private double money;
	private String time;
	private String type;
	private String handler;
	private String mark;

	public Tb_outaccount() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Tb_outaccount(int _id, double money, String time, String type,
			String handler, String mark) {
		super();
		this._id = _id;
		this.money = money;
		this.time = time;
		this.type = type;
		this.handler = handler;
		this.mark = mark;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getTime() {
		return time;
	}

	

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Tb_outaccount [_id=" + _id + ", money=" + money + ", time="
				+ time + ", type=" + type + ", handler=" + handler + ", mark="
				+ mark + "]";
	}

	

}
