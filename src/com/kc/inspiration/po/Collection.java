package com.kc.inspiration.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Collection implements Serializable {
	private int cid;
	private int wid;
	private int uid;
	public Collection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collection(int cid, int wid, int uid) {
		super();
		this.cid = cid;
		this.wid = wid;
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Collections [cid=" + cid + ", wid=" + wid + ", uid=" + uid
				+ "]";
	}
	
}
