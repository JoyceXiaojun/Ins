package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.ICommentBiz;

import com.kc.inspiration.dao.ICommentDao;

import com.kc.inspiration.dao.impl.CommentDaoImpl;

import com.kc.inspiration.po.Comment;

public class CommentBizImpl implements ICommentBiz {

private ICommentDao commentDao;
	
	public CommentBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.commentDao = new CommentDaoImpl();
	}


	@Override
	public boolean add(Comment comment) {
		// TODO Auto-generated method stub
		return this.commentDao.insert(comment)>0?true:false;
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return this.commentDao.selectAll();
	}

	@Override
	public boolean deleteById(int cid) {
		// TODO Auto-generated method stub
		return this.commentDao.deleteById(cid)>0?true:false;
	}

	@Override
	public Comment findById(int cid) {
		// TODO Auto-generated method stub
		return this.commentDao.selectById(cid);
	}

	@Override
	public boolean modify(Comment comment) {
		// TODO Auto-generated method stub
		return this.commentDao.update(comment)>0?true:false;
	}

}
