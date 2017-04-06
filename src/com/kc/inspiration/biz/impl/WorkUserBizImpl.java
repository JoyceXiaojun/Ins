package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IWorkUserBiz;
import com.kc.inspiration.dao.IWorkUserDao;
import com.kc.inspiration.dao.impl.WorkUserDaoImpl;
import com.kc.inspiration.vo.WorkUser;

public class WorkUserBizImpl implements IWorkUserBiz {

	private IWorkUserDao workUserDao=new WorkUserDaoImpl();
	public WorkUserBizImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<WorkUser> findByUid(int uid) {
		// TODO Auto-generated method stub
		return this.workUserDao.selectByUid(uid);
	}

}
