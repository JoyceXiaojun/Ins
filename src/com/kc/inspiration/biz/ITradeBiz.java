package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.po.Trade;

public interface ITradeBiz {
	public abstract boolean add(final Trade Trade);
	public abstract List<Trade> findAll();
	public abstract boolean deleteById(final int tid);
	public abstract Trade findById(final int tid);
	public abstract boolean modify(final Trade trade);
}
