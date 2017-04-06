package com.kc.inspiration.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Auction implements Serializable {
	private int aid;
	private int wid;
	private int uid;
	private double baseprice;
	private Date applicationtime;
	private Date begintime;
	private Date endtime;
	private int status;
	private double topprice;
	private String mark;

	public Auction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Auction(int aid, int wid, int uid, double baseprice,
			Date applicationtime, Date begintime, Date endtime, int status,
			double topprice, String mark) {
		super();
		this.aid = aid;
		this.wid = wid;
		this.uid = uid;
		this.baseprice = baseprice;
		this.applicationtime = applicationtime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.status = status;
		this.topprice = topprice;
		this.mark = mark;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
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

	public double getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(double baseprice) {
		this.baseprice = baseprice;
	}

	public Date getApplicationtime() {
		return applicationtime;
	}

	public void setApplicationtime(Date applicationtime) {
		this.applicationtime = applicationtime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTopprice() {
		return topprice;
	}

	public void setTopprice(double topprice) {
		this.topprice = topprice;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Auction [aid=" + aid + ", wid=" + wid + ", uid=" + uid
				+ ", baseprice=" + baseprice + ", applicationtime="
				+ applicationtime + ", begintime=" + begintime + ", endtime="
				+ endtime + ", status=" + status + ", topprice=" + topprice
				+ ", mark=" + mark + "]";
	}

	
	
}
