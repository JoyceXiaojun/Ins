package com.kc.inspiration.biz;

import java.util.List;

import com.kc.inspiration.po.User;

public interface IUserBiz {
	public abstract boolean add(final User user);
	public abstract List<User> findAll();
	public abstract boolean deleteById(final int uid);
	public abstract User findById(final int uid);
	public abstract User findByUsername(final String username);
	public abstract User findByEmail(final String email);
	public abstract boolean modify(final User user);
	public abstract int register(final User user);
	public abstract User isLogin(final String username,final String password);
	public abstract List<User> findAllFollow(final int uid);
	public abstract List<User> findAllFollower(final int uid);
	public abstract List<User> findAllNotFollow(final int uid);
	public abstract List<User> findByObject(final String obj);
	public abstract int getPraiseCount(final int uid);
	public abstract int getCollectCount(final int uid);
}
