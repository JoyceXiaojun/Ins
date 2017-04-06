package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IBidBiz;

import com.kc.inspiration.dao.IBidDao;

import com.kc.inspiration.dao.impl.BidDaoImpl;

import com.kc.inspiration.po.Bid;

public class BidBizImpl implements IBidBiz {

private IBidDao bidDao;
	
	public BidBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.bidDao = new BidDaoImpl();
	}


	@Override
	public boolean add(Bid bid) {
		// TODO Auto-generated method stub
		return this.bidDao.insert(bid)>0?true:false;
	}

	@Override
	public List<Bid> findAll() {
		// TODO Auto-generated method stub
		return this.bidDao.selectAll();
	}

	@Override
	public boolean deleteById(int bid) {
		// TODO Auto-generated method stub
		return this.bidDao.deleteById(bid)>0?true:false;
	}

	@Override
	public Bid findById(int aid) {
		// TODO Auto-generated method stub
		return this.bidDao.selectById(aid);
	}

	@Override
	public boolean modify(Bid bid) {
		// TODO Auto-generated method stub
		return this.bidDao.update(bid)>0?true:false;
	}

}
