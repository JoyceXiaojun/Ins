package com.kc.inspiration.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IAttentionDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Attention;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttentionDaoImpl implements IAttentionDao {
	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;
	
	

	public AttentionDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Attention attention) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
				
		// 步骤3：拆分对向属性
		int uid = attention.getUid();
		int buid = attention.getBuid();
		// 步骤4：设置添加SQL语句模板
		//扩展：一个用户只能关注另一个用户一次，在此做一下验证
		String strSQL = "select * from attention where uid=? and buid=?";
		Object[] params = new Object[]{uid, buid};
		ResultSet resultSet=this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				//已关注
				return -1;
			}else{
				strSQL = "insert into attention values(null,?,?)";
				// 步骤5：使用dbutils方法实现添加操作
				int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
				// 步骤6：提交事务
				if(affectedRows > 0){
					// 提交事务
					TransactionManager.commit();
				}else{
					// 回滚事务
					TransactionManager.rollback();
				}
				return affectedRows;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public List<Attention> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Attention> lstAttention = new ArrayList<Attention>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from attention order by aid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个Customers对象
				Attention attention = new Attention();
				attention.setAid(resultSet.getInt(1));
				attention.setUid(resultSet.getInt(2));
				attention.setBuid(resultSet.getInt(3));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstAttention.add(attention);				
			}
			// 返回结果
			return lstAttention;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
		
		

	}
	@Override
	public int deleteById(int aid){
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from attention where aid=?";
		Object[] params = new Object[] { aid };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRwos > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return affectedRwos;
	}

	@Override
	public Attention selectById(int aid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from aid where aid=?";
		Object[] params = new Object[] { aid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Attention attention = new Attention();
				attention.setAid(resultSet.getInt(1));
				attention.setUid(resultSet.getInt(2));
				attention.setBuid(resultSet.getInt(3));
				// 步骤7：返回对象
				return attention;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}

	}

	@Override
	public int update(Attention attention) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update attention set uid=?, buid=?where aid=?";
		Object[] params = new Object[] {  attention.getUid(), attention.getBuid(),attention.getAid() };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRwos > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return affectedRwos;
	}

	@Override
	public int delete(Attention attention) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
				this.conn = this.connectionManager.openConnection();
				// 步骤2：开启事务
				TransactionManager.conn = this.conn;
				TransactionManager.beginTransaction();
				// 步骤3：创建SQL语句模板
				String strSQL = "delete from attention where uid=? and buid=?";
				Object[] params = new Object[] {  attention.getUid(), attention.getBuid()};
				// 步骤4：调用dbutils中的方法完成对数据库的删除操作
				int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
				// 步骤5：根据步骤4的操作结果提交或回滚事务
				if (affectedRwos > 0) {
					TransactionManager.commit(); // 事务提交
				} else {
					TransactionManager.rollback(); // 事务的回滚
				}
				// 步骤6：返回影响行数
				return affectedRwos;
	}

	
	

	

}
