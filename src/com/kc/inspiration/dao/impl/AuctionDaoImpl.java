package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kc.inspiration.dao.IAuctionDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Auction;

/**
 * 名称：ActionDaoImpl 功能：IStudentsDao实现类 作者：谢贵阳 时间：2014-08-04 14:34
 */
public class AuctionDaoImpl implements IAuctionDao {
	// 步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;

	// 步骤2：为该实现类创建一个构造方法用于实例化步骤1的两个对象
	public AuctionDaoImpl() {
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Auction auction) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from auction where uid=? and wid=?";
		// praise的wid和uid构成侯选键
		int uid =auction.getUid();
		int wid = auction.getWid();
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
		// 准备1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();

		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();

		double baseprice = auction.getBaseprice();
		//Date applicationtime = auction.getApplicationtime();
		Date begintime = auction.getBegintime();
		Date endtime = auction.getEndtime();
		int status = auction.getStatus();
		double topprice=auction.getTopprice();
		String mark=auction.getMark();

		// 步骤4：创建SQL语句模板
		strSQL = "insert into auction values(null,?,?,?,now(),?,?,?,?,?)";
		Object[] params1 = new Object[] { wid, uid, baseprice,
				begintime, endtime, status,topprice,mark};

		// 步骤5：创建dbUtils对象并调用execOthers方法实现数据库的添加操作
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params1);

		// 步骤6：根据步骤5的执行结果完成相对应的事务处理
		if (affectedRows > 0) {
			TransactionManager.commit();// 提交事务
		} else {
			TransactionManager.rollback();// 回滚事务
		}
		return affectedRows;
	}

	@Override
	public List<Auction> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Auction> lstAuction = new ArrayList<Auction>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from auction order by deptno";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Auction对象
				Auction auction = new Auction();
				auction.setAid(resultSet.getInt(1));
				auction.setWid(resultSet.getInt(2));
				auction.setUid(resultSet.getInt(3));
				auction.setBaseprice(resultSet.getDouble(4));
				auction.setApplicationtime(resultSet.getDate(5));
				auction.setBegintime(resultSet.getDate(6));
				auction.setEndtime(resultSet.getDate(7));
				auction.setStatus(resultSet.getInt(8));
				auction.setTopprice(resultSet.getDouble(9));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstAuction.add(auction);
			}
			// 返回结果
			return lstAuction;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public int deleteById(int aid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from auction where aid=?";
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
	public Auction selectById(int aid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from auction where aid=?";
		Object[] params = new Object[] { aid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Auction象
				Auction auction = new Auction();
				auction.setAid(resultSet.getInt(1));
				auction.setWid(resultSet.getInt(2));
				auction.setUid(resultSet.getInt(3));
				auction.setBaseprice(resultSet.getDouble(4));
				auction.setApplicationtime(resultSet.getDate(5));
				auction.setBegintime(resultSet.getDate(6));
				auction.setEndtime(resultSet.getDate(7));
				auction.setStatus(resultSet.getInt(8));
				auction.setTopprice(resultSet.getDouble(9));
				// 步骤7：返回对象
				return auction;
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
	public int update(Auction auction) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update auction set wid=?, uid=?, baseprice=?,applicationtime=?,begintime=?,endtime=?,status=?,topprice=? where aid=?";
		Object[] params = new Object[] { auction.getWid(), auction.getUid(),
				auction.getBaseprice(), auction.getApplicationtime(),
				auction.getBegintime(), auction.getEndtime(),
				auction.getStatus(), auction.getTopprice(),auction.getAid() };
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
	public List<Auction> selectByStatus(int status) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<Auction> lstAuction = new ArrayList<Auction>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from auction where auction.status=? order by aid desc";
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						new Object[] {status});
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个Auction对象
						Auction auction = new Auction();
						auction.setAid(resultSet.getInt(1));
						auction.setWid(resultSet.getInt(2));
						auction.setUid(resultSet.getInt(3));
						auction.setBaseprice(resultSet.getDouble(4));
						auction.setApplicationtime(resultSet.getDate(5));
						auction.setBegintime(resultSet.getDate(6));
						auction.setEndtime(resultSet.getDate(7));
						auction.setStatus(resultSet.getInt(8));
						auction.setTopprice(resultSet.getDouble(9));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstAuction.add(auction);
					}
					// 返回结果
					return lstAuction;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally {
					// 步骤6：关闭数据库连接
					this.connectionManager.closeConnection(conn);
				}
	}
	@Override
	public List<Auction> selectAllByUid(int uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		List<Auction> lstAuction = new ArrayList<Auction>();
		String strSQL = "select * from auction where uid=?";
		Object[] params = new Object[]{uid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAid(resultSet.getInt(1));
				auction.setWid(resultSet.getInt(2));
				auction.setUid(resultSet.getInt(3));
				auction.setBaseprice(resultSet.getDouble(4));
				auction.setApplicationtime(resultSet.getDate(5));
				auction.setBegintime(resultSet.getDate(6));
				auction.setEndtime(resultSet.getDate(7));
				auction.setStatus(resultSet.getInt(8));
				auction.setTopprice(resultSet.getDouble(9));
				auction.setMark(resultSet.getString(10));
				lstAuction.add(auction);
			}
			return lstAuction;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			
			this.connectionManager.closeConnection(conn);
		}
	}
	@Override
	public Auction selectByUidWid(int uid, int wid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL="select * from auction where uid=? and wid=?";
		Object[] params = new Object[]{uid,wid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if (resultSet.next()) {
				
				Auction auction = new Auction();
				auction.setAid(resultSet.getInt(1));
				auction.setWid(resultSet.getInt(2));
				auction.setUid(resultSet.getInt(3));
				auction.setBaseprice(resultSet.getDouble(4));
				auction.setApplicationtime(resultSet.getDate(5));
				auction.setBegintime(resultSet.getDate(6));
				auction.setEndtime(resultSet.getDate(7));
				auction.setStatus(resultSet.getInt(8));
				auction.setTopprice(resultSet.getDouble(9));
				auction.setMark(resultSet.getString(10));
				return auction;
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
	public int update() {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "UPDATE auction a SET a.status=2 where a.status=1 and a.begintime<now();";
		Object[] params = new Object[] {};
	    // 步骤4：调用dbutils中的方法完成对数据库的更新操作
		int affectedRows1 = this.dbUtils.execOthers(conn, strSQL, params);
		//
		strSQL = "UPDATE auction a SET a.status=3 where a.status=2 and a.endtime>now();";
		int affectedRows2=this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRows1 >= 0 && affectedRows2>=0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return affectedRows1+affectedRows2;
	}

	@Override
	public boolean updateStatus(int aid, int status) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update auction set wid=?, uid=?, baseprice=?,applicationtime=?,begintime=?,endtime=?,status=?,topprice=? where aid=?";
		Object[] params = new Object[] { aid,status};
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRwos > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return false;
	}

	@Override
	public List<Auction> selectByStatusUid(int uid, int status) {
		// TODO Auto-generated method stub
		List<Auction> lstAuction = new ArrayList<Auction>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from auction where uid=? and status=? order by applicationtime desc";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {uid,status});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Auction对象
				Auction auction = new Auction();
				auction.setAid(resultSet.getInt(1));
				auction.setWid(resultSet.getInt(2));
				auction.setUid(resultSet.getInt(3));
				auction.setBaseprice(resultSet.getDouble(4));
				auction.setApplicationtime(resultSet.getDate(5));
				auction.setBegintime(resultSet.getDate(6));
				auction.setEndtime(resultSet.getDate(7));
				auction.setStatus(resultSet.getInt(8));
				auction.setTopprice(resultSet.getDouble(9));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstAuction.add(auction);
			}
			// 返回结果
			return lstAuction;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}

}
