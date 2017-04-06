package com.kc.inspiration.vo;

import java.util.Date;

public class Nwork {
	private int wid;
	private int uid;
	private String workname;
	private String workdescription;
	private Date publishtime;
	private int sharecount;
	private int visitcount;
	private int replycount;
	private int praisecount;
	private int collectcount;
	private int rid;
	private String category;
	private String path;
	public Nwork() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nwork(int wid, int uid, String workname, String workdescription,
			Date publishtime, int sharecount, int visitcount, int replycount,
			int praisecount, int collectcount, int rid, String category,
			String path) {
		super();
		this.wid = wid;
		this.uid = uid;
		this.workname = workname;
		this.workdescription = workdescription;
		this.publishtime = publishtime;
		this.sharecount = sharecount;
		this.visitcount = visitcount;
		this.replycount = replycount;
		this.praisecount = praisecount;
		this.collectcount = collectcount;
		this.rid = rid;
		this.category = category;
		this.path = path;
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
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public String getWorkdescription() {
		return workdescription;
	}
	public void setWorkdescription(String workdescription) {
		this.workdescription = workdescription;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public int getSharecount() {
		return sharecount;
	}
	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public int getPraisecount() {
		return praisecount;
	}
	public void setPraisecount(int praisecount) {
		this.praisecount = praisecount;
	}
	public int getCollectcount() {
		return collectcount;
	}
	public void setCollectcount(int collectcount) {
		this.collectcount = collectcount;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Nwork [wid=" + wid + ", uid=" + uid + ", workname=" + workname
				+ ", workdescription=" + workdescription + ", publishtime="
				+ publishtime + ", sharecount=" + sharecount + ", visitcount="
				+ visitcount + ", replycount=" + replycount + ", praisecount="
				+ praisecount + ", collectcount=" + collectcount + ", rid="
				+ rid + ", category=" + category + ", path=" + path + "]";
	}
	
	
	
}
