package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.INworkBiz;
import com.kc.inspiration.dao.INworkDao;
import com.kc.inspiration.dao.impl.NworkDaoImpl;
import com.kc.inspiration.vo.Nwork;

public class NworkBizImpl implements INworkBiz {
	private INworkDao nworkDao;
	
	
	public NworkBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.nworkDao = new NworkDaoImpl();
	}


	@Override
	public List<Nwork> findByUid(int uid) {
		// TODO Auto-generated method stub
		return this.nworkDao.selectByUid(uid);
	}


	@Override
	public Nwork findById(int wid) {
		// TODO Auto-generated method stub
		return this.nworkDao.selectById(wid);
	}

}
