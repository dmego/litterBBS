package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.typeBean;
import com.dmego.bean.typeChildBean;
import com.dmego.util.DBUtils;

/**
 * �Ӱ����������
 * 
 * @author dmego
 *
 */
public class typeChildDao {

	public void addTypeChild(typeChildBean typeChild) {
		String sql = "insert into typechild(parentid,name,info) values(?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeChild.getParentid());
			ps.setString(2, typeChild.getName());
			ps.setString(3, typeChild.getInfo());
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}

	public void updTypeChild(typeChildBean typeChild) {
		String sql = "update typechild set parentid=?,name=?,info=? where tychid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeChild.getParentid());
			ps.setString(2, typeChild.getName());
			ps.setString(3, typeChild.getInfo());
			ps.setInt(4, typeChild.getTychid());
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}

	public void delTypeChild(int tychid) {
		String sql = "delete from typechild where tychid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tychid);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}

	// ͨ���Ӱ��� ID ��ȡ�Ӱ���
	public typeChildBean getTypeChildById(int tychid) {
		String sql = "select * from typechild where tychid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		typeChildBean tychBean = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,tychid);
			rs = ps.executeQuery();
			if(rs.next()) {
				int parentid = rs.getInt("parentid");
				String name = rs.getString("name");
				String info = rs.getString("info");
				tychBean = new typeChildBean(tychid, parentid, name, info);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
		return tychBean;
	}

	// ��ȡȫ���Ӱ���
	public List<typeChildBean> getAllTypeChild() {
		String sql = "select * from typechild";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<typeChildBean> tychList = new ArrayList<typeChildBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				int tychid = rs.getInt("tychid");
				int parentid = rs.getInt("parentid");
				String name = rs.getString("name");
				String info = rs.getString("info");
				typeChildBean tychBean = new typeChildBean(tychid, parentid, name, info);
				tychList.add(tychBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat,conn);
		}
		return tychList;
	}

	// ͨ��������ID ��ȡȫ��������� �Ӱ���
	public List<typeChildBean> getTypeChildByParentId(int parentid) {
		String sql = "select * from typechild where parentid="+parentid;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<typeChildBean> tychList = new ArrayList<typeChildBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				int tychid = rs.getInt("tychid");
				String name = rs.getString("name");
				String info = rs.getString("info");
				typeChildBean tychBean = new typeChildBean(tychid, parentid, name, info);
				tychList.add(tychBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat,conn);
		}
		return tychList;
	}
	
	//�ж�ĳ�����������Ƿ����Ӱ���
	public boolean hasChild(int parentid) {
		String sql = "select * from typechild where parentid="+parentid;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat,conn);
		}
		return flag;
	}
}
