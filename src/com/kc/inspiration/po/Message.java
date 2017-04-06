package com.kc.inspiration.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private int mid;
	private int sid;
	private int rid;
	private String msgcontent;
	private Date msgtime;
	private int msgstatus;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int mid, int sid, int rid, String msgcontent, Date msgtime,
			int msgstatus) {
		super();
		this.mid = mid;
		this.sid = sid;
		this.rid = rid;
		this.msgcontent = msgcontent;
		this.msgtime = msgtime;
		this.msgstatus = msgstatus;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public Date getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(Date msgtime) {
		this.msgtime = msgtime;
	}
	public int getMsgstatus() {
		return msgstatus;
	}
	public void setMsgstatus(int msgstatus) {
		this.msgstatus = msgstatus;
	}
	@Override
	public String toString() {
		return "Messages [mid=" + mid + ", sid=" + sid + ", rid=" + rid
				+ ", msgcontent=" + msgcontent + ", msgtime=" + msgtime
				+ ", msgstatus=" + msgstatus + "]";
	}
	
}
