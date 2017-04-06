package com.kc.inspiration.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Praise implements Serializable {
	private int pid;
	private int uid;
	private int wid;
	public Praise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Praise(int pid, int uid, int wid) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.wid = wid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "Praise [pid=" + pid + ", uid=" + uid + ", wid=" + wid + "]";
	}
	
}
