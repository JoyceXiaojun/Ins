package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IWorkBiz;
import com.kc.inspiration.dao.IWorkDao;
import com.kc.inspiration.dao.impl.WorkDaoImpl;
import com.kc.inspiration.po.Work;

public class WorkBizImpl implements IWorkBiz {

private IWorkDao workDao;
	
	public WorkBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.workDao = new WorkDaoImpl();
	}


	@Override
	public boolean add(Work work) {
		// TODO Auto-generated method stub
		return this.workDao.insert(work)>0?true:false;
	}

	@Override
	public List<Work> findAll() {
		// TODO Auto-generated method stub
		return this.workDao.selectAll();
	}

	@Override
	public boolean deleteById(int wid) {
		// TODO Auto-generated method stub
		return this.workDao.deleteById(wid)>0?true:false;
	}

	@Override
	public Work findById(int wid) {
		// TODO Auto-generated method stub
		return this.workDao.selectById(wid);
	}

	@Override
	public boolean modify(Work work) {
		// TODO Auto-generated method stub
		return this.workDao.update(work)>0?true:false;
	}


	@Override
	public Work findByObject(int uid, String workname) {
		// TODO Auto-generated method stub
		return this.workDao.selectByObject(uid,workname);
	}


	@Override
	public List<Work> findAll(int uid) {
		// TODO Auto-generated method stub
		return this.workDao.selectAll(uid);
	}


	@Override
	public List<Work> findAllPraised(int uid) {
		// TODO Auto-generated method stub
		return this.workDao.selectAllPraised(uid);
	}


	@Override
	public List<Work> findAllCollected(int uid) {
		// TODO Auto-generated method stub
		return this.workDao.selectAllCollected(uid);
	}


	@Override
	public int getReplyCountById(int wid) {
		// TODO Auto-generated method stub
		return this.workDao.getReplyCount(wid);
	}


	@Override
	public List<Work> findByUid(int uid) {
		// TODO Auto-generated method stub
		return this.workDao.selectByUid(uid);
	}


	@Override
	public List<Work> findByObject(String obj) {
		// TODO Auto-generated method stub
		return this.workDao.selectByObject(obj);
	}


	@Override
	public List<Work> findHot(int begin, int end) {
		// TODO Auto-generated method stub
		return this.workDao.selectHot(begin,end);
	}
}
