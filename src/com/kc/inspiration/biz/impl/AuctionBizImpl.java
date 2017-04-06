package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IAuctionBiz;

import com.kc.inspiration.dao.IAuctionDao;

import com.kc.inspiration.dao.impl.AuctionDaoImpl;

import com.kc.inspiration.po.Auction;

public class AuctionBizImpl implements IAuctionBiz {

	private IAuctionDao auctionDao;
	
	public AuctionBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.auctionDao = new AuctionDaoImpl();
	}


	@Override
	public boolean add(Auction auction) {
		// TODO Auto-generated method stub
		return this.auctionDao.insert(auction)>0?true:false;
	}

	@Override
	public List<Auction> findAll() {
		// TODO Auto-generated method stub
		return this.auctionDao.selectAll();
	}

	@Override
	public boolean deleteById(int aid) {
		// TODO Auto-generated method stub
		return this.auctionDao.deleteById(aid)>0?true:false;
	}

	@Override
	public Auction findById(int aid) {
		// TODO Auto-generated method stub
		return this.auctionDao.selectById(aid);
	}

	@Override
	public boolean modify(Auction auction) {
		// TODO Auto-generated method stub
		return this.auctionDao.update(auction)>0?true:false;
	}

	@Override
	public List<Auction> findAuctionByStatus(int status) {
		// TODO Auto-generated method stub
		return this.auctionDao.selectByStatus(status);
	}
	@Override
	public List<Auction> findAllByUid(int uid) {
		// TODO Auto-generated method stub
		
		return this.auctionDao.selectAllByUid(uid);
	}
	@Override
	public Auction findByUidWid(int uid, int wid) {
		// TODO Auto-generated method stub
		return this.auctionDao.selectByUidWid(uid, wid);
	}


	@Override
	public int update() {
		// TODO Auto-generated method stub
		return this.auctionDao.update();
	}


	@Override
	public List<Auction> findAuctionByUidStatus(int uid, int status) {
		// TODO Auto-generated method stub
		return this.auctionDao.selectByStatusUid(uid, status);
	}
}
