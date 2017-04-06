package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IAuctionDao;
import com.kc.inspiration.dao.IBidDao;
import com.kc.inspiration.dao.ITradeDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Auction;
import com.kc.inspiration.po.Bid;
import com.kc.inspiration.po.Trade;

/**
 * 名称：TradeDaoImpl 功能：ITradeDao实现类 作者：谢贵阳 时间：2014-08-04 14:53
 */
public class TradeDaoImpl implements ITradeDao {
	// 步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;

	public TradeDaoImpl() {
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Trade trade) {
		// TODO Auto-generated method stub
		// 准备1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();

		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();

		// 步骤3：拆分对象的属性
		int bid = trade.getBid();

		// 步骤4：创建SQL语句模板
		String strSQL = "insert into trade values(null,?)";
		Object[] params = new Object[] { bid };

		// 步骤5：创建dbUtils对象并调用execOthers方法实现数据库的添加操作
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);

		// 步骤6：根据步骤5的执行结果完成相对应的事务处理
		if (affectedRows > 0) {
			TransactionManager.commit();// 提交事务
		} else {
			TransactionManager.rollback();// 回滚事务
		}
		return affectedRows;
	}

	@Override
	public List<Trade> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Trade> lstTrade = new ArrayList<Trade>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from trade order by tid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Trade trade = new Trade();
				trade.setTid(resultSet.getInt(1));
				trade.setBid(resultSet.getInt(2));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstTrade.add(trade);
			}
			// 返回结果
			return lstTrade;
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
	public int deleteById(int tid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from trade where tid=?";
		Object[] params = new Object[] { tid };
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
	public Trade selectById(int tid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from auction where tid=?";
		Object[] params = new Object[] { tid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Trade象
				Trade trade = new Trade();
				trade.setTid(resultSet.getInt(1));
				trade.setBid(resultSet.getInt(2));
				// 步骤7：返回对象
				return trade;
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
	public int update(Trade trade) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update trade set bid=? where tid=?";
		Object[] params = new Object[] { trade.getBid(), trade.getTid() };
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
	public int check() {
		// TODO Auto-generated method stub
		//获取status=3的auction
		IAuctionDao auctionDao=new AuctionDaoImpl();
		List<Auction> alst=auctionDao.selectByStatus(3);
		if(alst!=null&&alst.size()>=0){
			//
			IBidDao bidDao=new BidDaoImpl();
			ITradeDao tradeDao=new TradeDaoImpl();
			Auction auction;
			for(int i=0;i<alst.size();i++){
				Bid bid=bidDao.selectMax(alst.get(i).getAid());
				if(bid!=null){
					//修改：status=4
					auction=auctionDao.selectById(bid.getAuctionid());
					auction.setStatus(4);
					auctionDao.update(auction);
					//在trade表添加一条记录
					Trade trade=new Trade();
					trade.setBid(bid.getBid());
					tradeDao.insert(trade);
				}
				else{
					//修改：status=5;
					auction=auctionDao.selectById(alst.get(i).getAid());
					auction.setStatus(5);
					auctionDao.update(auction);
				}
			}
			
			return alst.size();
		}else{
			return 0;
		}
	}

}
