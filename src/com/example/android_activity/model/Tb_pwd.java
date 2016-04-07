package com.example.android_activity.model;

public class Tb_pwd {
	private String password;
	private String userName;

	public Tb_pwd() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Tb_pwd(String password, String userName) {
		super();
		this.password = password;
		this.userName = userName;
	}


	public Tb_pwd(String userName) {
		super();
		this.userName = userName;
	}
	

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
