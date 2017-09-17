package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.commentBean;
import com.dmego.util.DBUtils;

/**
 * 评论表操作类
 * @author zengk
 *
 */
public class commentDao {

	//添加评论	
		public void addComment(commentBean comment) {
			String sql = "insert into comment(postid, userid,content,comtime,flood) values(?,?,?,?,?)";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DBUtils.getConn();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, comment.getPostid());
				ps.setInt(2, comment.getUserid());
				ps.setString(3, comment.getContent());
				ps.setString(4, comment.getComtime());
				ps.setInt(5, comment.getFlood());
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(ps, conn);
			}
		}
	//查询一个帖子下的所有评论数
		public int getCountByPost(int postid) {
			String sql = "select count(*) count from comment where postid=?";
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int size = 0;
			try {
				conn = DBUtils.getConn();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, postid);
				rs = ps.executeQuery();
				if(rs.next()) {
					size = rs.getInt("count");
				}			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(ps, conn);			
			}
			return size;
	
		}
	//获取分页数据
		public List<commentBean> getListPage(int postid,int start,int size){
			String sql = "select * from comment where postid= " + postid + " limit " + start + " , " + size;
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			List<commentBean> commentList = new ArrayList<commentBean>();
			try {
				conn = DBUtils.getConn();
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				while(rs.next()) {
					commentBean comment = null;
					int userid = rs.getInt("userid");
					String content = rs.getString("content");
					String comtime = rs.getString("comtime");
					int flood = rs.getInt("flood");
					comment = new commentBean(postid, userid, content, comtime, flood);
					commentList.add(comment);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(rs, stat, conn);
			}
			return commentList;
		}
}
