package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.vo.WorkUser;

public interface IWorkUserDao {
	public abstract List<WorkUser> selectByUid(final int uid);
}
