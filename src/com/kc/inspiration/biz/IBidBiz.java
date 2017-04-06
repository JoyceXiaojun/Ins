package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Bid;

public interface IBidBiz {
	public abstract boolean add(final Bid Bid);
	public abstract List<Bid> findAll();
	public abstract boolean deleteById(final int bidn);
	public abstract Bid findById(final int bidn);
	public abstract boolean modify(final Bid bid);

}
