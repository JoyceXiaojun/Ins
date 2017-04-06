package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kc.inspiration.dao.IWorkDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Work;

/**
 * 名称：WorkDaoImpl 功能：IWorkDao实现类 作者：谢贵阳 时间：2014-08-04 15:52
 */
public class WorkDaoImpl implements IWorkDao {

	// 步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;

	public WorkDaoImpl() {
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Work work) {
		// TODO Auto-generated method stub
		// 准备1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();

		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();

		// 步骤3：拆分对象的属性
		int uid = work.getUid();
		String workname = work.getWorkname();
		String workdescription = work.getWorkdescription();
		Date publishtime = work.getPublishtime();
		int sharecount = work.getSharecount();
		int visitcount = work.getVisitcount();
		int replycount = work.getReplycount();
		int praisecount = work.getPraisecount();
		int collectcount = work.getCollectcount();
		int rid = work.getRid();
		String category = work.getCatagory();
		// 步骤4：创建SQL语句模板
		String strSQL = "insert into work values(null,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { uid, workname, workdescription,
				publishtime, sharecount, visitcount, replycount, praisecount,
				collectcount, rid, category };

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
	public List<Work> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Work> lstWork = new ArrayList<Work>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from work order by pulishtime desc";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstWork.add(work);
			}
			// 返回结果
			return lstWork;
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
	public int deleteById(int wid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from work where tid=?";
		Object[] params = new Object[] { wid };
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
	public Work selectById(int wid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from work where wid=?";
		Object[] params = new Object[] { wid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Work象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤7：返回对象
				return work;
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
	public int update(Work work) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update work set uid=?,workname=?,workdescription=?,publishtime=?,sharecount=?,visitcount=?,replycount=?,praisecount=?,"
				+ "collectcount=?,rid=?,category=? where wid=?";
		Object[] params = new Object[] { work.getUid(), work.getWorkname(),
				work.getWorkdescription(), work.getPublishtime(),
				work.getSharecount(), work.getVisitcount(),
				work.getReplycount(), work.getPraisecount(),
				work.getCollectcount(), work.getRid(), work.getCatagory(),
				work.getWid() };
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
	public Work selectByObject(int uid, String workname) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from work where uid=? and workname=?";
		Object[] params = new Object[] { uid, workname };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Work象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤7：返回对象
				return work;
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
	public List<Work> selectAll(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Work> lstWork = new ArrayList<Work>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from work where uid=? order by wid desc";
		// 步骤4：使用dbutils方法实现查询操作
		Object[] params = new Object[] { uid };
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstWork.add(work);
			}
			// 返回结果
			return lstWork;
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
	public List<Work> selectAllPraised(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<Work> lstWork = new ArrayList<Work>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from work where work.wid in (select praise.wid from praise where praise.uid=?) order by wid desc";
				// 步骤4：使用dbutils方法实现查询操作
				Object[] params=new Object[]{uid};
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						params);
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个Trade对象
						Work work = new Work();
						work.setWid(resultSet.getInt(1));
						work.setUid(resultSet.getInt(2));
						work.setWorkname(resultSet.getString(3));
						work.setWorkdescription(resultSet.getString(4));
						work.setPublishtime(resultSet.getDate(5));
						work.setSharecount(resultSet.getInt(6));
						work.setVisitcount(resultSet.getInt(7));
						work.setReplycount(resultSet.getInt(8));
						work.setPraisecount(resultSet.getInt(9));
						work.setCollectcount(resultSet.getInt(10));
						work.setRid(resultSet.getInt(11));
						work.setCatagory(resultSet.getString(12));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstWork.add(work);
					}
					// 返回结果
					return lstWork;
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
	public List<Work> selectAllCollected(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Work> lstWork = new ArrayList<Work>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from work where work.wid in (select collection.wid from collection where collection.uid=?) order by wid desc";
		// 步骤4：使用dbutils方法实现查询操作
		Object[] params=new Object[]{uid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstWork.add(work);
			}
			// 返回结果
			return lstWork;
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
	public int getReplyCount(int wid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select count(*) from comment where wid=?";
		Object[] params = new Object[] { wid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		int replycount;
		try {
			if(resultSet.next()){
				replycount = resultSet.getInt(1);
				return replycount;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
		return -1;
	}
	@Override
	public List<Work> selectByUid(int uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection(); 
		String strSQL="select * from work where uid=?";
		List<Work> lstWork = new ArrayList<Work>();
		Object[] params = new Object[]{uid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstWork.add(work);
			}
			// 返回结果
			return lstWork;
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
	public List<Work> selectByObject(String obj) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection(); 
		String strSQL="select * from work where workname like ? ";
		List<Work> lstWork = new ArrayList<Work>();
		Object[] params = new Object[]{"%"+obj+"%"};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Trade对象
				Work work = new Work();
				work.setWid(resultSet.getInt(1));
				work.setUid(resultSet.getInt(2));
				work.setWorkname(resultSet.getString(3));
				work.setWorkdescription(resultSet.getString(4));
				work.setPublishtime(resultSet.getDate(5));
				work.setSharecount(resultSet.getInt(6));
				work.setVisitcount(resultSet.getInt(7));
				work.setReplycount(resultSet.getInt(8));
				work.setPraisecount(resultSet.getInt(9));
				work.setCollectcount(resultSet.getInt(10));
				work.setRid(resultSet.getInt(11));
				work.setCatagory(resultSet.getString(12));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstWork.add(work);
			}
			// 返回结果
			return lstWork;
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
	public List<Work> selectHot(int begin, int end) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection(); 
		String strSQL="select * from work order by  publishtime desc";
		List<Work> lstWork = new ArrayList<Work>();
		Object[] params = new Object[]{};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		int i=1;
		try {
			while (resultSet.next()) {
				if(i>=begin&&i<=end){
					// 步骤5-1：创建一个Trade对象
					Work work = new Work();
					work.setWid(resultSet.getInt(1));
					work.setUid(resultSet.getInt(2));
					work.setWorkname(resultSet.getString(3));
					work.setWorkdescription(resultSet.getString(4));
					work.setPublishtime(resultSet.getDate(5));
					work.setSharecount(resultSet.getInt(6));
					work.setVisitcount(resultSet.getInt(7));
					work.setReplycount(resultSet.getInt(8));
					work.setPraisecount(resultSet.getInt(9));
					work.setCollectcount(resultSet.getInt(10));
					work.setRid(resultSet.getInt(11));
					work.setCatagory(resultSet.getString(12));
					// 步骤5-2：将封装好的对象添加到List集合中
					lstWork.add(work);	
				}
				i++;
				if(i>end){
					break;
				}
			}
			// 返回结果
			return lstWork;
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
