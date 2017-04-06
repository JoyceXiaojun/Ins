package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.Work;


public interface IWorkDao {
	public abstract int insert(final Work work);
	public abstract List<Work> selectAll();
	public abstract int deleteById(final int wid);
	public abstract Work selectById(final int wid);
	public abstract int update(final Work work);
	public abstract Work selectByObject(final int uid,final String workname);
	public abstract List<Work> selectAll(final int uid);
	public abstract List<Work> selectAllPraised(final int uid);
	public abstract List<Work> selectAllCollected(final int uid);
	public abstract int getReplyCount(final int wid);
	public abstract List<Work> selectByUid(final int uid);
	public abstract List<Work> selectByObject(final String obj);
	public abstract List<Work> selectHot(final int begin,final int end);
}
