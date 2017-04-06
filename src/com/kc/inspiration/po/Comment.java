package com.kc.inspiration.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Comment implements Serializable {
	private int cid;
	private int wid;
	private int uid;
	private String content;
	private Date time;
	private int replycid;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int cid, int wid, int uid, String content, Date time,
			int replycid) {
		super();
		this.cid = cid;
		this.wid = wid;
		this.uid = uid;
		this.content = content;
		this.time = time;
		this.replycid = replycid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getReplycid() {
		return replycid;
	}
	public void setReplycid(int replycid) {
		this.replycid = replycid;
	}
	@Override
	public String toString() {
		return "Comments [cid=" + cid + ", wid=" + wid + ", uid=" + uid
				+ ", content=" + content + ", time=" + time + ", replycid="
				+ replycid + "]";
	}
	
}
