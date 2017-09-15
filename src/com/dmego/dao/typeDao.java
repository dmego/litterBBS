package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.typeBean;
import com.dmego.util.DBUtils;

/**
 * 版区表操作类
 * 
 * @author dmego
 *
 */
public class typeDao {

	public void addType(typeBean type) {
		String sql = "insert into type(name,info) values(?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getName());
			ps.setString(2, type.getInfo());
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}

	public void updType(typeBean type) {
		String sql = "update type set name=?, info=? where typeid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getName());
			ps.setString(2, type.getInfo());
			ps.setInt(3, type.getTypeid());
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}

	//删除 大版区
	public void delType(int typeid) {
		String sql = "delete from type where typeid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeid);			
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}
	
	//通过版区ID 获取版区
	public typeBean getTypeById(int typeid){
		String sql = "select * from type where typeid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		typeBean type = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeid);			
			rs = ps.executeQuery();			
			if(rs.next()) {				
				String name = rs.getString("name");
				String info = rs.getString("info");
				type = new typeBean(typeid, name, info);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
		return type;
	}
	
	//获取全部大版区
	public List<typeBean> getAllType(){
		String sql = "select * from type";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<typeBean> typeList = new ArrayList<typeBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				int typeid = rs.getInt("typeid");
				String name = rs.getString("name");
				String info = rs.getString("info");
				typeBean type = new typeBean(typeid, name, info);
				typeList.add(type);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs,stat, conn);
		}
		return typeList;
	}
	
	
	

}
