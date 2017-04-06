package com.kc.inspiration.dao;

import java.util.List;
import com.kc.inspiration.po.Collection;


public interface ICollectionDao {
	public abstract int insert(final Collection collection);
	public abstract List<Collection> selectAll();
	public abstract int deleteById(final int cid);
	public abstract Collection selectById(final int cid);
	public abstract int update(final Collection collection);

}
