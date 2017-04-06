package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.INworkDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.vo.Nwork;

public class NworkDaoImpl implements INworkDao {
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;
	
	public NworkDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public List<Nwork> selectByUid(int uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL="select * from nwork where uid=? order by publishtime desc";
		List<Nwork> lstNwork = new ArrayList<Nwork>();
		Object[] params = new Object[]{uid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			while (resultSet.next()) {
				Nwork nwork = new Nwork();
				nwork.setWid(resultSet.getInt(1));
				nwork.setUid(resultSet.getInt(2));
				nwork.setWorkname(resultSet.getString(3));
				nwork.setWorkdescription(resultSet.getString(4));
				nwork.setPublishtime(resultSet.getDate(5));
				nwork.setSharecount(resultSet.getInt(6));
				nwork.setVisitcount(resultSet.getInt(7));
				nwork.setReplycount(resultSet.getInt(8));
				nwork.setPraisecount(resultSet.getInt(9));
				nwork.setCollectcount(resultSet.getInt(10));
				nwork.setRid(resultSet.getInt(11));
				nwork.setCategory(resultSet.getString(12));
				nwork.setPath(resultSet.getString(13));
				lstNwork.add(nwork);
			}
			// 返回结果
			return lstNwork;
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
	public Nwork selectById(int wid) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		String strSQL = "select * from nwork where wid=?";
		Object[] params = new Object[] { wid };
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if (resultSet.next()) {
				Nwork nwork = new Nwork();
				nwork.setWid(resultSet.getInt(1));
				nwork.setUid(resultSet.getInt(2));
				nwork.setWorkname(resultSet.getString(3));
				nwork.setWorkdescription(resultSet.getString(4));
				nwork.setPublishtime(resultSet.getDate(5));
				nwork.setSharecount(resultSet.getInt(6));
				nwork.setVisitcount(resultSet.getInt(7));
				nwork.setReplycount(resultSet.getInt(8));
				nwork.setPraisecount(resultSet.getInt(9));
				nwork.setCollectcount(resultSet.getInt(10));
				nwork.setRid(resultSet.getInt(11));
				nwork.setCategory(resultSet.getString(12));
				nwork.setPath(resultSet.getString(13));
				
				return nwork;
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
