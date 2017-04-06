package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.po.Attention;


public interface IAttentionBiz {
	public abstract boolean add(final Attention attention);
	public abstract List<Attention> findAll();
	public abstract boolean deleteById(final int aid);
	public abstract Attention findById(final int aid);
	public abstract boolean modify(final Attention attention);
	public abstract boolean delete(final Attention attention);
}
