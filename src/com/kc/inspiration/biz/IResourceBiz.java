package com.kc.inspiration.biz;

import java.util.List;


import com.kc.inspiration.po.Resource;

public interface IResourceBiz {
	public abstract boolean add(final Resource resource);
	public abstract List<Resource> findAll();
	public abstract boolean deleteById(final int rid);
	public abstract Resource findById(final int rid);
	public abstract boolean modify(final Resource resource);
	public abstract Resource findByPath(final String path);
}
