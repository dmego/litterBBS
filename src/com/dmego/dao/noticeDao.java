package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.noticeBean;
import com.dmego.bean.userBean;
import com.dmego.util.DBUtils;

/**
 * 公告表操作类
 * 
 * @author dmego
 *
 */
public class noticeDao {

	// 获取全部公告信息
	public List<noticeBean> ListNotice() {
		String sql = "select * from notice";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<noticeBean> noticeList = new ArrayList<noticeBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int noticeid = rs.getInt("noticeid");
				int userid = rs.getInt("userid");
				String content = rs.getString("content");
				String title = rs.getString("title");
				String noticetime = rs.getString("noticetime");
				noticeBean notice = new noticeBean(noticeid, userid, content, title, noticetime);
				noticeList.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return noticeList;
	}

	// 新增公告
	public void addNotice(noticeBean notice) {
		String sql = "insert into notice(userid,content,title,noticetime) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getUserid());
			ps.setString(2, notice.getContent());
			ps.setString(3, notice.getTitle());
			ps.setString(4, notice.getNoticetime());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}

	// 更新公告
	public void updNotice(noticeBean notice) {
		String sql = "update notice set content=?,title=? where noticeid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getContent());
			ps.setString(2, notice.getTitle());
			ps.setInt(3, notice.getNoticeid());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}

	// 删除公告
	public void delNotice(int noticeid) {
		String sql = "delete from notice where noticeid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}

	// 通过Id获取公告
	public noticeBean getNoticeById(int noticeId) {
		String sql = "select * from notice where noticeid="+noticeId;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		noticeBean notice = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				int noticeid = rs.getInt("noticeid");
				int userid = rs.getInt("userid");
				String content = rs.getString("content");
				String title = rs.getString("title");
				String noticetime = rs.getString("noticetime");
				notice = new noticeBean(noticeid, userid, content, title, noticetime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return notice;
	}

	// 获取公告数量
	public int getCount() {
		String sql = "select count(*) count from notice";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int size = 0;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				size = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return size;
	}

	// 获取分页数据（start是开始序号，第一位是0，size 是截取数据长度）
	public List<noticeBean> getListPage(int start, int size) {
		String sql = "select * from notice limit " + start + " , " + size;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<noticeBean> noticeList = new ArrayList<noticeBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int noticeid = rs.getInt("noticeid");
				int userid = rs.getInt("userid");
				String content = rs.getString("content");
				String title = rs.getString("title");
				String noticetime = rs.getString("noticetime");
				noticeBean notice = new noticeBean(noticeid, userid, content, title, noticetime);
				noticeList.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return noticeList;
	}
}
