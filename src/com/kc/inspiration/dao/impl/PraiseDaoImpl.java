package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IPraiseDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Praise;

public class PraiseDaoImpl implements IPraiseDao {
	private DBUtils dbUtils;
	private ConnectionManager connectionManager;
	

	public PraiseDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils=new DBUtils();
		this.connectionManager=new ConnectionManager();
	}
	@Override
	public int insert(Praise praise) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		// 先判断praise的wid和uid的唯一性
		String strSQL="select * from praise where uid=? and wid=?";
		// praise的wid和uid构成侯选键
		int uid = praise.getUid();
		int wid = praise.getWid();
		Object[] params=new Object[]{uid,wid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				System.out.println("[PraiseDaoImpl]: > uid="+uid+" wid="+wid+"的行已存在！");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		strSQL="insert into praise values(null,?,?)";
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Praise> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		List<Praise> lstPraise = new ArrayList<Praise>();
		String strSQL = "select * from praise";
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next()){
				Praise praise = new Praise();
				praise.setPid(resultSet.getInt(1));
				praise.setUid(resultSet.getInt(2));
				praise.setWid(resultSet.getInt(3));
				lstPraise.add(praise);
			}
			return lstPraise;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public Praise selectById(int pid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from praise where pid=?";
		Object[] params = new Object[]{pid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Praise praise = new Praise();
				praise.setPid(resultSet.getInt(1));
				praise.setUid(resultSet.getInt(2));
				praise.setWid(resultSet.getInt(3));
				return praise;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public int deleteById(int pid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="delete from praise where pid=?";
		Object[] params = new Object[]{pid};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}
		else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public int update(Praise praise) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="update praise set uid=?,wid=? where pid=?";
		Object[] params = new Object[]{praise.getUid(),praise.getWid(),praise.getPid()};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

}
