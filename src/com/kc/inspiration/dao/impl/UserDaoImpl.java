package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IUserDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.User;

/**
 * 名称：UserDaoImpl 功能：IUserDao实现类 作者：谢贵阳 时间：2014-08-04 15:05
 */
public class UserDaoImpl implements IUserDao {
	// 步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		// 准备1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();

		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();

		// 步骤3：拆分对象的属性
		int account = user.getAccount();
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();

		int level = user.getLevel();
		int honestylevel = user.getHonestylevel();
		int status = user.getStatus();
		int accstatus = user.getAccstatus();
		int fanscount = user.getFanscount();
		int attentioncount = user.getAttentioncount();
		String mark = user.getMark();
		String photo = user.getPhoto();

		// 步骤4：创建SQL语句模板
		String strSQL = "insert into user values(null,?,?,?,?,now(),?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { account, username, password, email,
				level, honestylevel, status, accstatus, fanscount,
				attentioncount, mark, photo };

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
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<User> lstUser = new ArrayList<User>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from user order by uid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个User对象
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstUser.add(user);
			}
			// 返回结果
			return lstUser;
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
	public int deleteById(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from user where uid=?";
		Object[] params = new Object[] { uid };
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
	public User selectById(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from user where uid=?";
		Object[] params = new Object[] { uid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个User象
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				// 步骤7：返回对象
				return user;
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
	public int update(User user) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update user set account=?,username=?,password=?,email=?,level=?,honestylevel=?,status=?,accstatus=?,fanscount=?,attentioncount=?,mark=?,photo=? where uid=?";
		Object[] params = new Object[] { user.getAccount(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getLevel(),
				user.getHonestylevel(), user.getStatus(), user.getAccstatus(),
				user.getFanscount(), user.getAttentioncount(), user.getMark(),
				user.getPhoto(), user.getUid() };
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
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from user where username=?";
		Object[] params = new Object[] { username };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个User象
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				System.out.println("[UserDaoImpl.SelectByUserName]"
						+ user.toString());
				// 步骤7：返回对象
				return user;
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
	public User selectByEmail(String email) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from user where email=?";
		Object[] params = new Object[] { email };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个User象
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				// 步骤7：返回对象
				return user;
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
	public User selectByObjet(String username, String password) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL = "select * from user where username=? and password=?";
		Object[] params = new Object[] { username, password };

		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if (resultSet.next()) {
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
		return null;
	}

	@Override
	public List<User> selectAllFollow(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<User> lstUser = new ArrayList<User>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from user where user.uid in (select attention.buid from attention where attention.uid=?) order by uid";
		System.out.println("SQL:>"+strSQL);
		Object[] params=new Object[]{uid};
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个User对象
				User user = new User();
				user.setUid(resultSet.getInt(1));
				user.setAccount(resultSet.getInt(2));
				user.setUsername(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setRegistertime(resultSet.getDate(6));
				user.setLevel(resultSet.getInt(7));
				user.setHonestylevel(resultSet.getInt(8));
				user.setStatus(resultSet.getInt(9));
				user.setAccstatus(resultSet.getInt(10));
				user.setFanscount(resultSet.getInt(11));
				user.setAttentioncount(resultSet.getInt(12));
				user.setMark(resultSet.getString(13));
				user.setPhoto(resultSet.getString(14));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstUser.add(user);
			}
			// 返回结果
			return lstUser;
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
	public List<User> selectAllFollower(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<User> lstUser = new ArrayList<User>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from user where user.uid in (select attention.uid from attention where attention.buid=?) order by uid";
				System.out.println("SQL:>"+strSQL);
				Object[] params=new Object[]{uid};
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						params);
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个User对象
						User user = new User();
						user.setUid(resultSet.getInt(1));
						user.setAccount(resultSet.getInt(2));
						user.setUsername(resultSet.getString(3));
						user.setPassword(resultSet.getString(4));
						user.setEmail(resultSet.getString(5));
						user.setRegistertime(resultSet.getDate(6));
						user.setLevel(resultSet.getInt(7));
						user.setHonestylevel(resultSet.getInt(8));
						user.setStatus(resultSet.getInt(9));
						user.setAccstatus(resultSet.getInt(10));
						user.setFanscount(resultSet.getInt(11));
						user.setAttentioncount(resultSet.getInt(12));
						user.setMark(resultSet.getString(13));
						user.setPhoto(resultSet.getString(14));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstUser.add(user);
					}
					// 返回结果
					return lstUser;
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
	public List<User> selectAllNotFollow(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<User> lstUser = new ArrayList<User>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from user where user.uid not in (select attention.buid from attention where attention.uid=?) and user.uid !=? order by uid";
				System.out.println("SQL:>"+strSQL);
				Object[] params=new Object[]{uid,uid};
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						params);
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个User对象
						User user = new User();
						user.setUid(resultSet.getInt(1));
						user.setAccount(resultSet.getInt(2));
						user.setUsername(resultSet.getString(3));
						user.setPassword(resultSet.getString(4));
						user.setEmail(resultSet.getString(5));
						user.setRegistertime(resultSet.getDate(6));
						user.setLevel(resultSet.getInt(7));
						user.setHonestylevel(resultSet.getInt(8));
						user.setStatus(resultSet.getInt(9));
						user.setAccstatus(resultSet.getInt(10));
						user.setFanscount(resultSet.getInt(11));
						user.setAttentioncount(resultSet.getInt(12));
						user.setMark(resultSet.getString(13));
						user.setPhoto(resultSet.getString(14));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstUser.add(user);
					}
					// 返回结果
					return lstUser;
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
	public List<User> selectByObject(String obj) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<User> lstUser = new ArrayList<User>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from user where username like ? ";
				System.out.println("SQL:>"+strSQL);
				Object[] params=new Object[]{"%"+obj+"%"};
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						params);
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个User对象
						User user = new User();
						user.setUid(resultSet.getInt(1));
						user.setAccount(resultSet.getInt(2));
						user.setUsername(resultSet.getString(3));
						user.setPassword(resultSet.getString(4));
						user.setEmail(resultSet.getString(5));
						user.setRegistertime(resultSet.getDate(6));
						user.setLevel(resultSet.getInt(7));
						user.setHonestylevel(resultSet.getInt(8));
						user.setStatus(resultSet.getInt(9));
						user.setAccstatus(resultSet.getInt(10));
						user.setFanscount(resultSet.getInt(11));
						user.setAttentioncount(resultSet.getInt(12));
						user.setMark(resultSet.getString(13));
						user.setPhoto(resultSet.getString(14));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstUser.add(user);
					}
					// 返回结果
					return lstUser;
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
	public int selectPraiseCount(int uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL = "select count(*) from praise where uid=?";
		Object[] params = new Object[] { uid };
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if (resultSet.next()) {
				int praiseCount=resultSet.getInt(1);
				return praiseCount;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
		return 0;
	}

	@Override
	public int selectCollectCount(int uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL = "select count(*) from collection where uid=?";
		Object[] params = new Object[] { uid };
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if (resultSet.next()) {
				int collectCount=resultSet.getInt(1);
				return collectCount;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
		return 0;
	}

}
