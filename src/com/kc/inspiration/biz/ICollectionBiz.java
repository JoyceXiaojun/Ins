package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Collection;

public interface ICollectionBiz {
	public abstract boolean add(final Collection collection);
	public abstract List<Collection> findAll();
	public abstract boolean deleteById(final int cid);
	public abstract Collection findById(final int cid);
	public abstract boolean modify(final Collection collection);
}
