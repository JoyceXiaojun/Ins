package com.kc.inspiration.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Resource implements Serializable {
	private int rid;
	private String type;
	private String path;
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resource(int rid, String type, String path) {
		super();
		this.rid = rid;
		this.type = type;
		this.path = path;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Resource [rid=" + rid + ", type=" + type + ", path=" + path
				+ "]";
	}
	
}
