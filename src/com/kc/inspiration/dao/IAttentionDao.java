package com.kc.inspiration.dao;

import java.util.List;
import com.kc.inspiration.po.Attention;

public interface IAttentionDao {
	public abstract int insert(final Attention attention);
	public abstract List<Attention> selectAll();
	public abstract int deleteById(final int aid);
	public abstract Attention selectById(final int aid);
	public abstract int update(final Attention attention);
	public abstract int delete(final Attention attention);
}
