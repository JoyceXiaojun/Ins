package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Comment;

public interface ICommentBiz {
	public abstract boolean add(final Comment comment);
	public abstract List<Comment> findAll();
	public abstract boolean deleteById(final int aid);
	public abstract Comment findById(final int aid);
	public abstract boolean modify(final Comment comment);
}
