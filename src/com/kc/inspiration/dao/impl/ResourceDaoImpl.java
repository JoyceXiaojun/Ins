package com.kc.inspiration.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kc.inspiration.dao.IResourceDao;
import com.kc.inspiration.db.ConnectionManager;
import com.kc.inspiration.db.DBUtils;
import com.kc.inspiration.db.TransactionManager;
import com.kc.inspiration.po.Resource;

public class ResourceDaoImpl implements IResourceDao {
	private DBUtils dbUtils;
	private ConnectionManager connectionManager;
	

	public ResourceDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils=new DBUtils();
		this.connectionManager=new ConnectionManager();
	}
	@Override
	public int insert(Resource resource) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="insert into resource values(null,?,?)";
		String type = resource.getType();
		String path=resource.getPath();
		Object[] params=new Object[]{type,path};
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}
 
	@Override
	public List<Resource> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		List<Resource> lstResource = new ArrayList<Resource>();
		String strSQL = "select * from resource";
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next()){
				Resource resource = new Resource();
				resource.setRid(resultSet.getInt(1));
				resource.setType(resultSet.getString(2));
				resource.setPath(resultSet.getString(3));
				lstResource.add(resource);
			}
			return lstResource;
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
	public int deleteById(int rid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="delete from resource where rid=?";
		Object[] params = new Object[]{rid};
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
	public Resource selectById(int rid) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from resource where rid=?";
		Object[] params = new Object[]{rid};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Resource resource = new Resource();
				resource.setRid(resultSet.getInt(1));
				resource.setType(resultSet.getString(2));
				resource.setPath(resultSet.getString(3));
				return resource;
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
	public int update(Resource resource) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		String strSQL="update resource set type=?,path=? where rid=?";
		Object[] params = new Object[]{resource.getType(),resource.getPath()};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows>0){
			TransactionManager.commit();
		}else{
			TransactionManager.rollback();
		}
		return affectedRows;
	}
	@Override
	public Resource selectByPath(String path) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		String strSQL="select * from resource where path=?";
		Object[] params = new Object[]{path};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Resource resource = new Resource();
				resource.setRid(resultSet.getInt(1));
				resource.setType(resultSet.getString(2));
				resource.setPath(resultSet.getString(3));
				return resource;
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

}
