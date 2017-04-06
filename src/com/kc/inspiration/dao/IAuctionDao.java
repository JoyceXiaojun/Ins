package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Auction;

public interface IAuctionDao {
	public abstract int insert(final Auction auction);
	public abstract List<Auction> selectAll();
	public abstract int deleteById(final int aid);
	public abstract Auction selectById(final int aid);
	public abstract int update(final Auction auction);
	public abstract List<Auction> selectByStatus(final int status);
	public abstract Auction selectByUidWid(final int uid,final int wid);
	public abstract List<Auction> selectAllByUid(int uid);
	public abstract int update();
	public abstract boolean updateStatus(final int aid,final int status);
	public abstract List<Auction> selectByStatusUid(final int uid,final int status);
}
