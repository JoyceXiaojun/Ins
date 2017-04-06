package com.kc.inspiration.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Attention implements Serializable {
	private int aid;
	private int uid;
	private int buid;
	public Attention() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attention(int aid, int uid, int buid) {
		super();
		this.aid = aid;
		this.uid = uid;
		this.buid = buid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBuid() {
		return buid;
	}
	public void setBuid(int buid) {
		this.buid = buid;
	}
	@Override
	public String toString() {
		return "Attention [aid=" + aid + ", uid=" + uid + ", buid=" + buid
				+ "]";
	}
	
}
