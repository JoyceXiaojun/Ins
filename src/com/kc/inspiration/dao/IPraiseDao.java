package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Praise;

/**
 * 文件名称：IPraiseDao
 * 作者：wangrongkang
 * 编写时间：2014-08-04 12：44
 * */

public interface IPraiseDao {
	public abstract int insert(final Praise praise);
	public abstract List<Praise> selectAll();
	public abstract Praise selectById(final int pid);
	public abstract int deleteById(final int pid);
	public abstract int update(final Praise praise);
}
