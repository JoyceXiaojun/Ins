package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IResourceBiz;
import com.kc.inspiration.dao.IResourceDao;
import com.kc.inspiration.dao.impl.ResourceDaoImpl;
import com.kc.inspiration.po.Resource;

public class ResourceBizImpl implements IResourceBiz {

private IResourceDao resourceDao;
	
	public ResourceBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.resourceDao = new ResourceDaoImpl();
	}


	@Override
	public boolean add(Resource resource) {
		// TODO Auto-generated method stub
		return this.resourceDao.insert(resource)>0?true:false;
	}

	@Override
	public List<Resource> findAll() {
		// TODO Auto-generated method stub
		return this.resourceDao.selectAll();
	}

	@Override
	public boolean deleteById(int rid) {
		// TODO Auto-generated method stub
		return this.resourceDao.deleteById(rid)>0?true:false;
	}

	@Override
	public Resource findById(int rid) {
		// TODO Auto-generated method stub
		return this.resourceDao.selectById(rid);
	}

	@Override
	public boolean modify(Resource resource) {
		// TODO Auto-generated method stub
		return this.resourceDao.update(resource)>0?true:false;
	}


	@Override
	public Resource findByPath(String path) {
		// TODO Auto-generated method stub
		return this.resourceDao.selectByPath(path);
	}

}
