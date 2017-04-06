package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.vo.Nwork;

public interface INworkDao {
	public abstract List<Nwork> selectByUid(int uid);
	public abstract Nwork selectById(int wid);
}
