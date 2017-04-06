package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IMessageBiz;
import com.kc.inspiration.dao.IMessageDao;

import com.kc.inspiration.dao.impl.MessageDaoImpl;
import com.kc.inspiration.po.Message;

public class MessageBizImpl implements IMessageBiz {

private IMessageDao messageDao;
	
	public MessageBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.messageDao = new MessageDaoImpl();
	}


	@Override
	public boolean add(Message message) {
		// TODO Auto-generated method stub
		return this.messageDao.insert(message)>0?true:false;
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return this.messageDao.selectAll();
	}

	@Override
	public boolean deleteById(int mid) {
		// TODO Auto-generated method stub
		return this.messageDao.deleteById(mid)>0?true:false;
	}

	@Override
	public Message findById(int mid) {
		// TODO Auto-generated method stub
		return this.messageDao.selectById(mid);
	}

	@Override
	public boolean modify(Message message) {
		// TODO Auto-generated method stub
		return this.messageDao.update(message)>0?true:false;
	}


	@Override
	public List<Message> findChatMessage(int rid, int sid) {
		// TODO Auto-generated method stub
		return this.messageDao.selectChatMessage(rid, sid);
	}

}
