package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IBidDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Bid;

public class BidDaoImpl implements IBidDao {

	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;
	
	

	public BidDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Bid bid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
				// 步骤2：开启一个事务
				TransactionManager.conn = this.conn;
				TransactionManager.beginTransaction();
				
				// 步骤3：拆分对向属性
				int uid = bid.getUid();
				int auctionid = bid.getAuctionid();
				double price=bid.getPrice();
		
				// 步骤4：设置添加SQL语句模板
				String strSQL = "insert into bid values(null,?,?,?,now())";
				Object[] params = new Object[]{uid, auctionid,price};
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
	public List<Bid> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Bid> lstBid = new ArrayList<Bid>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from bid order by bidn";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个Customers对象
				Bid bid = new Bid();
				bid.setBid(resultSet.getInt(1));
				bid.setUid(resultSet.getInt(2));
				bid.setAuctionid(resultSet.getInt(3));
				bid.setPrice(resultSet.getDouble(4));
				bid.setBidtime(resultSet.getDate(5));
				
				// 步骤5-2：将封装好的对象添加到List集合中
				lstBid.add(bid);				
			}
			// 返回结果
			return lstBid;
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
	public int deleteById(int bidn){
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from bid where bidn=?";
		Object[] params = new Object[] { bidn };
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
	public Bid selectById(int bidn) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from bid where bidn=?";
		Object[] params = new Object[] { bidn };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Bid bid = new Bid();
				bid.setBid(resultSet.getInt(1));
				bid.setUid(resultSet.getInt(2));
				bid.setAuctionid(resultSet.getInt(3));
				bid.setPrice(resultSet.getDouble(4));
				bid.setBidtime(resultSet.getDate(5));
				
				// 步骤7：返回对象
				return bid;
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
	public int update(Bid bid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update attention set uid=?, autionid=?,price=?where bidn=?";
		Object[] params = new Object[] {  bid.getUid(), bid.getAuctionid(),bid.getPrice(), bid.getBid()};
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
	public List<Bid> selectByAid(int aid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bid selectMax(int aid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
				this.conn = this.connectionManager.openConnection();
				// 步骤2：创建SQL语句模板
				String strSQL = "select * from bid where bid.price=(select max(price) from bid where bid.aid=?)";
				Object[] params = new Object[] { aid };
				// 步骤4：调用dbutils中的方法完成对数据库的查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
				// 步骤5：获取结果集合并封装成一个对象
				try {
					if (resultSet.next()) {
						// 步骤6：创建一个Customers对象
						Bid bid = new Bid();
						bid.setBid(resultSet.getInt(1));
						bid.setUid(resultSet.getInt(2));
						bid.setAuctionid(resultSet.getInt(3));
						bid.setPrice(resultSet.getDouble(4));
						bid.setBidtime(resultSet.getDate(5));
						// 步骤7：返回对象
						return bid;
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
}