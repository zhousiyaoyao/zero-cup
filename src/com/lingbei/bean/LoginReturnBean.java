package com.lingbei.bean;
/**
 * Created by gxy
 */

public class LoginReturnBean {
	private int result=0;
	private String message="";

	private UserLoginBean user;

	public UserLoginBean getUser() {
		return user;
	}

	public void setUser(UserLoginBean user) {
		this.user = user;
	}

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
