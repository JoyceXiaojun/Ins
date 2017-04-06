package com.kc.inspiration.dao;

import java.util.List;
import com.kc.inspiration.po.Bid;


public interface IBidDao {
	public abstract int insert(final Bid bid);
	public abstract List<Bid> selectAll();
	public abstract int deleteById(final int bid);
	public abstract Bid selectById(final int bid);
	public abstract int update(final Bid bid);
	public abstract List<Bid> selectByAid(final int aid);
	public abstract Bid selectMax(final int aid);
}
