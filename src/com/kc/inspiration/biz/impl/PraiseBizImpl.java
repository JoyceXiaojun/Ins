package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IPraiseBiz;
import com.kc.inspiration.dao.IPraiseDao;
import com.kc.inspiration.dao.impl.PraiseDaoImpl;
import com.kc.inspiration.po.Praise;

public class PraiseBizImpl implements IPraiseBiz {

private IPraiseDao praiseDao;
	
	public PraiseBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.praiseDao = new PraiseDaoImpl();
	}


	@Override
	public boolean add(Praise praise) {
		// TODO Auto-generated method stub
		return this.praiseDao.insert(praise)>0?true:false;
	}

	@Override
	public List<Praise> findAll() {
		// TODO Auto-generated method stub
		return this.praiseDao.selectAll();
	}

	@Override
	public boolean deleteById(int pid) {
		// TODO Auto-generated method stub
		return this.praiseDao.deleteById(pid)>0?true:false;
	}

	@Override
	public Praise findById(int pid) {
		// TODO Auto-generated method stub
		return this.praiseDao.selectById(pid);
	}

	@Override
	public boolean modify(Praise praise) {
		// TODO Auto-generated method stub
		return this.praiseDao.update(praise)>0?true:false;
	}

}
