package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Message;
/**
 * 文件名称：IMessageDao
 * 作者：wangrongkang
 * 编写时间：2014-08-04 12：18
 * */

public interface IMessageDao {
	public abstract int insert(final Message message);
	public abstract List<Message> selectAll();
	public abstract int deleteById(final int mid);
	public abstract Message selectById(final int mid);
	public abstract int update(final Message message);
	public abstract List<Message> selectChatMessage(final int rid,final int sid);
}
