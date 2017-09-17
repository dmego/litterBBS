package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.postBean;
import com.dmego.util.DBUtils;

/**
 * 帖子表操作类
 * @author dmego
 *
 */
public class postDao {
	
	//首页初始化帖子
	public List<postBean> headInitPost() {
		String sql = "select * from post";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<postBean> postList = new ArrayList<postBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs =stat.executeQuery(sql);
			int i = 0;
			while(rs.next() && i < 10) {				
					postBean post = null;
					int postid = rs.getInt("postid");
					int userid = rs.getInt("userid");
					int tychid = rs.getInt("tychid");
					String title = rs.getString("title");
					String content = rs.getString("content");
					int replynum = rs.getInt("replynum");
					String posttime = rs.getString("posttime");
					post = new postBean(postid, userid, tychid, title, content, replynum, posttime);
					postList.add(post);
					i++;								
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return postList;
	}

	//新增帖子	
	public void addPost(postBean post) {
		String sql = "insert into post(postid, userid, tychid,title,content,posttime) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPostid());
			ps.setInt(2, post.getUserid());
			ps.setInt(3, post.getTychid());
			ps.setString(4, post.getTitle());
			ps.setString(5, post.getContent());
			ps.setString(6, post.getPosttime());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}
	//用户更新帖子
	public void updPostByUser(postBean post) {
		String sql = "update post set tychid=?,title=?,content=? where postid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getTychid());
			ps.setString(2, post.getTitle());
			ps.setString(3, post.getContent());
			ps.setInt(4, post.getPostid());			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}
	
		//管理员更新帖子
		public void updPostByAdmin1(postBean post) {
			String sql = "update post set userid=?, tychid=?,title=? ,content=?, posttime=? where postid=?";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DBUtils.getConn();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, post.getUserid());
				ps.setInt(2, post.getTychid());
				ps.setString(3, post.getTitle());
				ps.setString(4, post.getContent());			
				ps.setString(5, post.getPosttime());
				ps.setInt(6, post.getPostid());
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(ps, conn);
			}
		}
		
	
	//删除帖子	 
	public void delPost(int postid) {
		String sql = "delete from post where postid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}
	
	//增加回帖数
	public void addReplyNum(int postid) {
		String sql = "update  post set replynum=replynum+1 where postid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
	}
	
	//通过ID获取帖子	
	public postBean getPostById(int postid) {
		String sql = "select * from post where postid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		postBean post = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postid);
			rs = ps.executeQuery();
			if(rs.next()) {
				int userid = rs.getInt("userid");
				int tychid = rs.getInt("tychid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int replynum = rs.getInt("replynum");
				String posttime = rs.getString("posttime");
				post = new postBean(postid, userid, tychid, title, content, replynum, posttime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps, conn);
		}
		return post;
	}	
	 //通过标题获取帖子集	
	public List<postBean> getPostByTitle(String Title) {
		String sql = "select * from post where title like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<postBean> postList = new ArrayList<postBean>();
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Title);
			rs = ps.executeQuery();
			while(rs.next()) {
				postBean post = null;
				int postid = rs.getInt("postid");
				int userid = rs.getInt("userid");
				int tychid = rs.getInt("tychid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int replynum = rs.getInt("replynum");
				String posttime = rs.getString("posttime");
				post = new postBean(postid, userid, tychid, title, content, replynum, posttime);
				postList.add(post);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return postList;
	}
	//获取帖子总数量
	public int getCount() {
		String sql = "select count(*) count from post";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int size = 0;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);			
			if(rs.next()) {
				size = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return size;
	}
	
	// 通过帖子ID 来获取发帖人ID
	public int getUserIdByPostId(int postid){
		String sql = "select userid from post where postid="+postid;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int userid = 0;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);			
			if(rs.next()) {
				userid = rs.getInt("userid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stat, conn);
		}
		return userid;
	}
	
	//获取分页数据
	public List<postBean> getListPage(int start,int size){
		String sql = "select * from post limit " + start + " , " + size;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<postBean> postList = new ArrayList<postBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				postBean post = null;
				int postid = rs.getInt("postid");
				int userid = rs.getInt("userid");
				int tychid = rs.getInt("tychid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int replynum = rs.getInt("replynum");
				String posttime = rs.getString("posttime");
				post = new postBean(postid, userid, tychid, title, content, replynum, posttime);
				postList.add(post);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return postList;
	}
	//获取某个用户的全部帖子数据
	public List<postBean> getPostByUserId(int userid){
		String sql = "select * from post where userid ="+userid;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<postBean> postList = new ArrayList<postBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				postBean post = null;
				int postid = rs.getInt("postid");
				int tychid = rs.getInt("tychid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int replynum = rs.getInt("replynum");
				String posttime = rs.getString("posttime");
				post = new postBean(postid, userid, tychid, title, content, replynum, posttime);
				postList.add(post);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return postList;
	}
	
}
