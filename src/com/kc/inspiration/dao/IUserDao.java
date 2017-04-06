package com.kc.inspiration.dao;

import java.util.List;

import com.kc.inspiration.po.User;

public interface IUserDao {
	public abstract int insert(final User user);
	public abstract List<User> selectAll();
	public abstract int deleteById(final int uid);
	public abstract User selectById(final int uid);
	public abstract User selectByUsername(final String username);
	public abstract User selectByEmail(final String email);
	public abstract int update(final User user);
	public abstract User selectByObjet(final String username,final String password);
	public abstract List<User> selectAllFollow(final int uid);
	public abstract List<User> selectAllFollower(final int uid);
	public abstract List<User> selectAllNotFollow(final int uid);
	public abstract List<User> selectByObject(final String obj);
	public abstract int selectPraiseCount(final int uid);
	public abstract int selectCollectCount(final int uid);
}
