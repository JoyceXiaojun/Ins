package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.po.Auction;

public interface IAuctionBiz {
	public abstract boolean add(final Auction auction);
	public abstract List<Auction> findAll();
	public abstract boolean deleteById(final int aid);
	public abstract Auction findById(final int aid);
	public abstract boolean modify(final Auction auction);
	public abstract List<Auction> findAuctionByStatus(final int status);
	public abstract Auction findByUidWid(int uid, int wid);
	public abstract List<Auction> findAllByUid(int uid);
	public abstract int update();
	public abstract List<Auction> findAuctionByUidStatus(final int uid,final int status);
}
