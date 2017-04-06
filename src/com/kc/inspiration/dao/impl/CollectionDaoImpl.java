package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.ICollectionDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Collection;

public class CollectionDaoImpl implements ICollectionDao {

	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;
	
	

	public CollectionDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Collection collection) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
				
				String strSQL="select * from collection where wid=? and uid=?";
				// 步骤2：拆分对向属性
				// Collection的uid与wid构成侯选建
				int wid = collection.getWid();
				int uid = collection.getUid();
				Object[] params = new Object[]{wid, uid};
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
				try {
					if(resultSet.next()){
						System.out.println("[CollectionDaoImpl]: > uid="+uid+" wid="+wid+"的行已存在！");
						return -1;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
				// 步骤3：开启一个事务
				TransactionManager.conn = this.conn;
				TransactionManager.beginTransaction();
				// 步骤4：设置添加SQL语句模板
				strSQL = "insert into collection values(null,?,?)";
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

	@Override
	public List<Collection> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Collection> lstCollection = new ArrayList<Collection>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from Collection order by cid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个Customers对象
				Collection collection = new Collection();
				collection.setCid(resultSet.getInt(1));
				collection.setWid(resultSet.getInt(2));
				collection.setUid(resultSet.getInt(3));
				
				
				// 步骤5-2：将封装好的对象添加到List集合中
				lstCollection.add(collection);				
			}
			// 返回结果
			return lstCollection;
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
	public int deleteById(int cid){
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from Collection where cid=?";
		Object[] params = new Object[] { cid};
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
	public Collection selectById(int cid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from collection where cid=?";
		Object[] params = new Object[] {cid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Collection collection = new Collection();
				collection.setCid(resultSet.getInt(1));
				collection.setWid(resultSet.getInt(2));
				collection.setUid(resultSet.getInt(3));
				
				
				// 步骤7：返回对象
				return collection;
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
	public int update(Collection collection) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update collection set wid=?,uid=?,where cid=?";
		Object[] params = new Object[] {  collection.getWid(), collection.getUid(),collection.getCid()};
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