package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IWorkUserDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.po.User;
import com.kc.inspiration.po.Work;
import com.kc.inspiration.vo.WorkUser;

public class WorkUserDaoImpl implements IWorkUserDao {

	// 步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;

	public WorkUserDaoImpl() {
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public List<WorkUser> selectByUid(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<WorkUser> lstWorkUser = new ArrayList<WorkUser>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "SELECT work.wid,work.uid,work.workname,"
						+ "work.workdescription,work.publishtime,work.sharecount,"
						+ "work.visitcount,work.replycount,work.praisecount,"
						+ "work.collectcount,work.rid,work.category,user.account,"
						+ "user.username,user.email,user.registertime,user.level,"
						+ "user.honestylevel,user.status,user.accstatus,user.fanscount,"
						+ "user.attentioncount,user.mark,user.photo "
						+ "FROM work,user "
						+ "where (work.uid in (select attention.buid from attention where attention.uid=?) or work.uid=?) and work.uid=user.uid order by publishtime desc";
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						new Object[] {uid,uid});
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个Trade对象
						Work work = new Work();
						User user = new User();
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
						user.setUid(resultSet.getInt(2));
						user.setAccount(resultSet.getInt(13));
						user.setUsername(resultSet.getString(14));
						user.setEmail(resultSet.getString(15));
						user.setRegistertime(resultSet.getDate(16));
						user.setLevel(resultSet.getInt(17));
						user.setHonestylevel(resultSet.getInt(18));
						user.setStatus(resultSet.getInt(19));
						user.setAccstatus(resultSet.getInt(20));
						user.setFanscount(resultSet.getInt(21));
						user.setAttentioncount(resultSet.getInt(22));
						user.setMark(resultSet.getString(23));
						user.setPhoto(resultSet.getString(24));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstWorkUser.add(new WorkUser(work,user));
					}
					// 返回结果
					return lstWorkUser;
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
