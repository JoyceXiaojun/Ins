package com.kc.inspiration.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class User implements Serializable {
	private int uid;
	private int account;
	private String username;
	private String password;
	private String email;
	private Date registertime;
	private int level;
	private int honestylevel;
	private int status;
	private int accstatus;
	private int fanscount;
	private int attentioncount;
	private String mark;
	private String photo;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int uid, int account, String username, String password,
			String email, Date registertime, int level, int honestylevel,
			int status, int accstatus, int fanscount, int attentioncount,
			String mark, String photo) {
		super();
		this.uid = uid;
		this.account = account;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registertime = registertime;
		this.level = level;
		this.honestylevel = honestylevel;
		this.status = status;
		this.accstatus = accstatus;
		this.fanscount = fanscount;
		this.attentioncount = attentioncount;
		this.mark = mark;
		this.photo = photo;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAccstatus() {
		return accstatus;
	}
	public void setAccstatus(int accstatus) {
		this.accstatus = accstatus;
	}
	public int getFanscount() {
		return fanscount;
	}
	public void setFanscount(int fanscount) {
		this.fanscount = fanscount;
	}
	public int getAttentioncount() {
		return attentioncount;
	}
	public void setAttentioncount(int attentioncount) {
		this.attentioncount = attentioncount;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getHonestylevel() {
		return honestylevel;
	}

	public void setHonestylevel(int honestylevel) {
		this.honestylevel = honestylevel;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", account=" + account + ", username="
				+ username + ", password=" + password + ", email=" + email
				+ ", registertime=" + registertime + ", level=" + level
				+ ", honestylevel=" + honestylevel + ", status=" + status
				+ ", accstatus=" + accstatus + ", fanscount=" + fanscount
				+ ", attentioncount=" + attentioncount + ", mark=" + mark
				+ ", photo=" + photo + "]";
	}

}
