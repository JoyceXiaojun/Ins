package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Resource;

/**
 * 文件名称：ICommentDao
 * 作者：wangrongkang
 * 编写时间：2014-08-04 12：48
 * 修改作者：xieguiyang
 * 最后修改时间：2014-08-08 9：48
 * */

public interface IResourceDao {
	public abstract int insert(final Resource resource);
	public abstract List<Resource> selectAll();
	public abstract int deleteById(final int rid);
	public abstract Resource selectById(final int rid);
	public abstract int update(final Resource resource);
	public abstract Resource selectByPath(final String path);
}
