package com.kc.inspiration.biz.impl;

import java.util.List;

import com.kc.inspiration.biz.IUserBiz;
import com.kc.inspiration.dao.IUserDao;
import com.kc.inspiration.dao.impl.UserDaoImpl;
import com.kc.inspiration.po.User;

public class UserBizImpl implements IUserBiz {

private IUserDao userDao;
	
	public UserBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.userDao = new UserDaoImpl();
	}


	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insert(user)>0?true:false;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userDao.selectAll();
	}

	@Override
	public boolean deleteById(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.deleteById(uid)>0?true:false;
	}

	@Override
	public User findById(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectById(uid);
	}

	@Override
	public boolean modify(User user) {
		// TODO Auto-generated method stub
		return this.userDao.update(user)>0?true:false;
	}


	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		//return 1：注册成功
		//		 2：用户名重复
		//		 3：邮箱已注册
		//		 -1:注册失败
		//步骤1：检测用户名是否已使用
		//测试
		System.out.println(user.toString());
		if(findByUsername(user.getUsername())==null){
			//步骤2：检测邮箱是否已注册
			if(findByEmail(user.getEmail())==null){
				//步骤3：如果通过上面的两部验证就在数据库中插入新的用户
				return this.userDao.insert(user)>0?1:-1;
			}
			else{
				return 3;
			}
		}
		else{
			return 2;
		}	
	}


	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userDao.selectByUsername(username);
	}


	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userDao.selectByEmail(email);
	}


	@Override
	public User isLogin(String username, String password) {
		// TODO Auto-generated method stub
		return this.userDao.selectByObjet(username, password);
	}


	@Override
	public List<User> findAllFollow(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectAllFollow(uid);
	}


	@Override
	public List<User> findAllFollower(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectAllFollower(uid);
	}


	@Override
	public List<User> findAllNotFollow(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectAllNotFollow(uid);
	}


	@Override
	public List<User> findByObject(String obj) {
		// TODO Auto-generated method stub
		return this.userDao.selectByObject(obj);
	}


	@Override
	public int getPraiseCount(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectPraiseCount(uid);
	}


	@Override
	public int getCollectCount(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectCollectCount(uid);
	}

}