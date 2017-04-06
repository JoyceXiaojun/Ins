package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Comment;
/**
 * 文件名称：ICommentDao
 * 作者：wangrongkang
 * 编写时间：2014-08-04 12：14
 * */

public interface ICommentDao {
	public abstract int insert(final Comment comment);
	public abstract List<Comment> selectAll();
	public abstract int deleteById(final int cid);
	public abstract Comment selectById(final int cid);
	public abstract int update(final Comment comment);
	public abstract  List<Comment> selectByReplyId(final int replycid,final int wid);
	
}
