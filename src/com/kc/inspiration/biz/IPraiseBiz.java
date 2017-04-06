package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Praise;

public interface IPraiseBiz {
	public abstract boolean add(final Praise praise);
	public abstract List<Praise> findAll();
	public abstract boolean deleteById(final int pid);
	public abstract Praise findById(final int pid);
	public abstract boolean modify(final Praise praise);
}
