package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.ICollectionBiz;

import com.kc.inspiration.dao.ICollectionDao;

import com.kc.inspiration.dao.impl.CollectionDaoImpl;

import com.kc.inspiration.po.Collection;

public class CollectionBizImpl implements ICollectionBiz {

private ICollectionDao collectionDao;
	
	public CollectionBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.collectionDao = new CollectionDaoImpl();
	}


	@Override
	public boolean add(Collection collection) {
		// TODO Auto-generated method stub
		return this.collectionDao.insert(collection)>0?true:false;
	}

	@Override
	public List<Collection> findAll() {
		// TODO Auto-generated method stub
		return this.collectionDao.selectAll();
	}

	@Override
	public boolean deleteById(int cid) {
		// TODO Auto-generated method stub
		return this.collectionDao.deleteById(cid)>0?true:false;
	}

	@Override
	public Collection findById(int cid) {
		// TODO Auto-generated method stub
		return this.collectionDao.selectById(cid);
	}

	@Override
	public boolean modify(Collection collection) {
		// TODO Auto-generated method stub
		return this.collectionDao.update(collection)>0?true:false;
	}

}
