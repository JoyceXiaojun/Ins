package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IMessageDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Message;

public class MessageDaoImpl implements IMessageDao {
	private DBUtils dbUtils;
	private ConnectionManager connectionManager;
	

	public MessageDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils = new DBUtils();
		this.connectionManager = new ConnectionManager();
	}

	@Override
	public int insert(Message message) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="insert into message values(null,?,?,?,now(),?)";
		int wid = message.getSid();
		int rid = message.getRid();
		String msgcontent = message.getMsgcontent();
		int msgstatus = message.getMsgstatus();
		Object[] params=new Object[]{wid,rid,msgcontent,msgstatus};
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Message> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		List<Message> lstMessage = new ArrayList<Message>();
		String strSQL = "select * from message";
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next()){
				Message message = new Message();
				message.setMid(resultSet.getInt(1));
				message.setSid(resultSet.getInt(2));
				message.setRid(resultSet.getInt(3));
				message.setMsgcontent(resultSet.getString(4));
				message.setMsgtime(resultSet.getDate(5));
				message.setMsgstatus(resultSet.getInt(6));
				lstMessage.add(message);
			}
			return lstMessage;
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
	public int deleteById(int mid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="delete from message where mid=?";
		Object[] params = new Object[]{mid};
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
	public Message selectById(int mid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from message where mid=?";
		Object[] params = new Object[]{mid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Message message = new Message();
				message.setMid(resultSet.getInt(1));
				message.setSid(resultSet.getInt(2));
				message.setRid(resultSet.getInt(3));
				message.setMsgcontent(resultSet.getString(4));
				message.setMsgtime(resultSet.getDate(5));
				message.setMsgstatus(resultSet.getInt(6));
				return message;
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
	public int update(Message message) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="update message set sid=?,rid=?,msgcontent=?,msgstatus=? where mid=?";
		Object[] params = new Object[]{message.getSid(),message.getRid(),message.getMsgcontent(),message.getMsgstatus(),message.getMid()};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Message> selectChatMessage(int rid, int sid) {
		// TODO Auto-generated method stub
				Connection conn = this.connectionManager.openConnection();
				String strSQL = "select * from message where (rid=? and sid=?) or (rid=? and sid=?) order by msgtime";
				Object[] params = new Object[]{rid,sid,sid,rid};
				List<Message> lstMessage = new ArrayList<Message>();
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
				try {
					while(resultSet.next()){
						Message message = new Message();
						message.setMid(resultSet.getInt(1));
						message.setSid(resultSet.getInt(2));
						message.setRid(resultSet.getInt(3));
						message.setMsgcontent(resultSet.getString(4));
						message.setMsgtime(resultSet.getDate(5));
						message.setMsgstatus(resultSet.getInt(6));
						lstMessage.add(message);
					}
					return lstMessage;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				finally{
					this.connectionManager.closeConnection(conn);
				}
	}

}
