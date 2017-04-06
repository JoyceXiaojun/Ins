package com.kc.inspiration.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trade implements Serializable {
	private int tid;
	private int bid;
	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trade(int tid, int bid) {
		super();
		this.tid = tid;
		this.bid = bid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "Traderecoder [tid=" + tid + ", bid=" + bid + "]";
	}
	
}
