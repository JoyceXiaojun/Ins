package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.ICommentDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Comment;

public class CommentDaoImpl implements ICommentDao {
	private DBUtils dbUtils;
	private ConnectionManager connectionManager;
	

	public CommentDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils=new DBUtils();
		this.connectionManager=new ConnectionManager();
	}

	@Override
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="insert into comment values(null,?,?,?,now(),?)";
		int wid=comment.getWid();
		int uid=comment.getUid();
		String content=comment.getContent();
		int replycid=comment.getReplycid();
		Object[] params=new Object[]{wid,uid,content,replycid};
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Comment> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		List<Comment> lstComment = new ArrayList<Comment>();
		String strSQL = "select * from comment";
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next()){
				Comment comment = new Comment();
				comment.setCid(resultSet.getInt(1));
				comment.setUid(resultSet.getInt(2));
				comment.setWid(resultSet.getInt(3));
				comment.setContent(resultSet.getString(4));
				comment.setTime(resultSet.getDate(5));
				comment.setReplycid(resultSet.getInt(6));
				lstComment.add(comment);
			}
			return lstComment;
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
	public int deleteById(int cid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="delete from comment where cid=?";
		Object[] params = new Object[]{cid};
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
	public Comment selectById(int cid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from comment where cid=?";
		Object[] params = new Object[]{cid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Comment comment = new Comment();
				comment.setCid(resultSet.getInt(1));
				comment.setWid(resultSet.getInt(2));
				comment.setUid(resultSet.getInt(3));
				comment.setContent(resultSet.getString(4));
				comment.setTime(resultSet.getDate(5));
				comment.setReplycid(resultSet.getInt(6));
				return comment;
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
	public int update(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="update comment set wid=?,uid=?,content=?,replycid=? where cid=?";
		Object[] params = new Object[]{comment.getWid(),comment.getUid(),comment.getContent(),comment.getReplycid(),comment.getCid()};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Comment> selectByReplyId(int replycid,int wid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		List<Comment> lstComment = new ArrayList<Comment>();
		String strSQL = "select * from comment where replycid=? and wid=? order by time desc";
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{replycid,wid});
		try {
			while(resultSet.next()){
				Comment comment = new Comment();
				comment.setCid(resultSet.getInt(1));
				comment.setWid(resultSet.getInt(2));
				comment.setUid(resultSet.getInt(3));
				comment.setContent(resultSet.getString(4));
				comment.setTime(resultSet.getDate(5));
				comment.setReplycid(resultSet.getInt(6));
				lstComment.add(comment);
				System.out.println(comment);
			}
			return lstComment;
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
