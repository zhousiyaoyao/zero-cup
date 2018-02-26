package com.lingbei.bean;
/**
 * Created by gxy
 */

import java.util.Date;

public class UserLoginBean {
	private String name="";
	private String PASSWORD="";
	private String realName="";
	private Date birthday;
	private Date regtime;
	private String address;
	private String selfintro;
	private String headpic;
	private String smallpic;

	public String getSmallpic() {
		return smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	private String description;
	private int diaryNum;
	private int trailNum;

	private int result=0;
	private String message="";

	public int getDiaryNum() {
		return diaryNum;
	}

	public void setDiaryNum(int diaryNum) {
		this.diaryNum = diaryNum;
	}

	public int getTrailNum() {
		return trailNum;
	}

	public void setTrailNum(int trailNum) {
		this.trailNum = trailNum;
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


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSelfintro() {
		return selfintro;
	}

	public void setSelfintro(String selfintro) {
		this.selfintro = selfintro;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {this.headpic = headpic;}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}







	
}
