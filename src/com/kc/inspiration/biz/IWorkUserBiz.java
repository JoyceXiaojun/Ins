package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.vo.WorkUser;

public interface IWorkUserBiz {
	public abstract List<WorkUser> findByUid(final int uid);
}
