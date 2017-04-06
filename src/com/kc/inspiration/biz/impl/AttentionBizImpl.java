package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IAttentionBiz;
import com.kc.inspiration.dao.IAttentionDao;
import com.kc.inspiration.dao.impl.AttentionDaoImpl;
import com.kc.inspiration.po.Attention;

public class AttentionBizImpl implements IAttentionBiz {
      
      private IAttentionDao attentionDao;
	
	public AttentionBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.attentionDao = new AttentionDaoImpl();
	}


	@Override
	public boolean add(Attention attention) {
		// TODO Auto-generated method stub
		return this.attentionDao.insert(attention)>0?true:false;
	}

	@Override
	public List<Attention> findAll() {
		// TODO Auto-generated method stub
		return this.attentionDao.selectAll();
	}

	@Override
	public boolean deleteById(int aid) {
		// TODO Auto-generated method stub
		return this.attentionDao.deleteById(aid)>0?true:false;
	}

	@Override
	public Attention findById(int aid) {
		// TODO Auto-generated method stub
		return this.attentionDao.selectById(aid);
	}

	@Override
	public boolean modify(Attention attention) {
		// TODO Auto-generated method stub
		return this.attentionDao.update(attention)>0?true:false;
	}


	@Override
	public boolean delete(Attention attention) {
		// TODO Auto-generated method stub
		return this.attentionDao.delete(attention)>0?true:false;
	}

}
