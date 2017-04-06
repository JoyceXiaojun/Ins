package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.ITradeBiz;

import com.kc.inspiration.dao.ITradeDao;

import com.kc.inspiration.dao.impl.TradeDaoImpl;

import com.kc.inspiration.po.Trade;

public class TradeBizImpl implements ITradeBiz {

private ITradeDao tradeDao;
	
	public TradeBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.tradeDao = new TradeDaoImpl();
	}


	@Override
	public boolean add(Trade trade) {
		// TODO Auto-generated method stub
		return this.tradeDao.insert(trade)>0?true:false;
	}

	@Override
	public List<Trade> findAll() {
		// TODO Auto-generated method stub
		return this.tradeDao.selectAll();
	}

	@Override
	public boolean deleteById(int mid) {
		// TODO Auto-generated method stub
		return this.tradeDao.deleteById(mid)>0?true:false;
	}

	@Override
	public Trade findById(int mid) {
		// TODO Auto-generated method stub
		return this.tradeDao.selectById(mid);
	}

	@Override
	public boolean modify(Trade trade) {
		// TODO Auto-generated method stub
		return this.tradeDao.update(trade)>0?true:false;
	}
}
