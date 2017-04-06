package com.kc.inspiration.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Bid implements Serializable {
	private int bid;
	private int uid;
	private int auctionid;
	private double price;
	private Date bidtime;
	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bid(int bid, int uid, int auctionid, double price, Date bidtime) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.auctionid = auctionid;
		this.price = price;
		this.bidtime = bidtime;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAuctionid() {
		return auctionid;
	}
	public void setAuctionid(int auctionid) {
		this.auctionid = auctionid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getBidtime() {
		return bidtime;
	}
	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}
	@Override
	public String toString() {
		return "Bid [bid=" + bid + ", uid=" + uid + ", auctionid=" + auctionid
				+ ", price=" + price + ", bidtime=" + bidtime + "]";
	}
	
	
}
