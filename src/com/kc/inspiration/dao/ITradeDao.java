package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Trade;

public interface ITradeDao {
	public abstract int insert(final Trade trade);
	public abstract List<Trade> selectAll();
	public abstract int deleteById(final int tid);
	public abstract Trade selectById(final int tid);
	public abstract int update(final Trade trade);
	public abstract int check();
}
