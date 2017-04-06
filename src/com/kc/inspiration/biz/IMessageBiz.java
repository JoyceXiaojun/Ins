package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Message;

public interface IMessageBiz {
	public abstract boolean add(final Message message);
	public abstract List<Message> findAll();
	public abstract boolean deleteById(final int mid);
	public abstract Message findById(final int mid);
	public abstract boolean modify(final Message message);
	public abstract List<Message> findChatMessage(final int rid,final int sid);
}
