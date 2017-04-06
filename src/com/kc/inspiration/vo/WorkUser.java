package com.kc.inspiration.vo;

import java.io.Serializable;

import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;

@SuppressWarnings("serial")
public class WorkUser implements Serializable {

	private Work work;
	private User user;
	public WorkUser() {
		// TODO Auto-generated constructor stub
	}
	public WorkUser(Work work, User user) {
		super();
		this.work = work;
		this.user = user;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "WorkUser [work=" + work + ", user=" + user + "]";
	}
	
}
