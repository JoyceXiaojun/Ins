package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.po.Work;

public interface IWorkBiz {
	public abstract boolean add(final Work Work);
	public abstract List<Work> findAll();
	public abstract boolean deleteById(final int wid);
	public abstract Work findById(final int wid);
	public abstract boolean modify(final Work work);
	public abstract Work findByObject(final int uid,final String workname);
	public abstract List<Work> findAll(final int uid);
	public abstract List<Work> findAllPraised(final int uid);
	public abstract List<Work> findAllCollected(final int uid);
	public abstract int getReplyCountById(final int wid);
	public abstract List<Work> findByUid(final int uid);
	public abstract List<Work> findByObject(final String obj);
	public abstract List<Work> findHot(final int begin,final int end);
}
