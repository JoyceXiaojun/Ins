package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.vo.Nwork;

public interface INworkBiz {
	public abstract List<Nwork> findByUid(int uid);
	public abstract Nwork findById(int wid);
}
