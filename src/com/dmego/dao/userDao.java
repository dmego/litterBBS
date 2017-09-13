package com.dmego.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dmego.bean.userBean;
import com.dmego.util.DBUtils;


/**
 * 
 * @author dmego
 * 会员表操作类
 */
public class userDao {
	
	//检查用户名是否存在
	public boolean checkReg(String username) {
		String sql = "select username from user";
		boolean flag = true;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn(); // 连接数据库
			state = conn.createStatement(); // 创建传输器对象
			rs = state.executeQuery(sql); // 执行查询操作
			while(rs.next()) { // 如果有结果，是认为通过了验证
				if (username.equals(rs.getString("username"))) {
					flag = false; //有该用户，flag 返回false
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, state, conn); // 关闭数据库的连接
		}
		return flag;
	}
	
	//登录操作
	public userBean checkLogin(String username, String password) {
		String sql = "select * from user where username= ? and password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		userBean user = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {	
				int userid = rs.getInt("userid");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return user;
	}
	
	//获取全部会员信息
	public List<userBean> listUser(){
		String sql = "select * from user";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<userBean> userList= new ArrayList<userBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				userBean user = null;
				int userid = rs.getInt("userid");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
				userList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return userList;
	}
	
	//前台注册会员
		public void addFrontUser(userBean userbean) {
			String sql = "insert into user(username,password,nickname,regtime) values(?,?,?,?)";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DBUtils.getConn();
				ps = conn.prepareStatement(sql);
				ps.setString(1, userbean.getUsername());
				ps.setString(2, userbean.getPassword());
				ps.setString(3, userbean.getNickname());
				ps.setString(4, userbean.getRegtime());
				ps.execute();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(ps, conn);
			}
		}
	
	
	//新增会员
	public void addUser(userBean userbean) {
		String sql = "insert into user(username,password,nickname,sex,birthday,regtime) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userbean.getUsername());
			ps.setString(2, userbean.getPassword());
			ps.setString(3, userbean.getNickname());
			ps.setString(4, userbean.getSex());
			ps.setString(5, userbean.getBirthday());
			ps.setString(6, userbean.getRegtime());
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}
	
	//更新会员信息
	public void updUser(userBean userbean) {
		String sql = "update user set username=?,password=?,nickname=?,sex=?,birthday=?,usericon=? where userid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userbean.getUsername());
			ps.setString(2, userbean.getPassword());
			ps.setString(3, userbean.getNickname());
			ps.setString(4, userbean.getSex());
			ps.setString(5, userbean.getBirthday());
			ps.setString(6, userbean.getUsericon());
			ps.setInt(7, userbean.getUserid());
			ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}	
	}
	//更新会员信息不包括头像
		public void updUserNoico(userBean userbean) {
			String sql = "update user set username=?,password=?,nickname=?,sex=?,birthday=? where userid = ?";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DBUtils.getConn();
				ps = conn.prepareStatement(sql);
				ps.setString(1, userbean.getUsername());
				ps.setString(2, userbean.getPassword());
				ps.setString(3, userbean.getNickname());
				ps.setString(4, userbean.getSex());
				ps.setString(5, userbean.getBirthday());
				ps.setInt(6, userbean.getUserid());
				ps.execute();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtils.close(ps, conn);
			}	
		}
	
	//删除会员
	public void delUser(int userid) {
		String sql = "delete from user where userid=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(ps, conn);
		}
	}
	//通过会员id获取会员信息·
	public userBean getUserById(int userid) {
		String sql = "select * from user where userid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		userBean user = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			if(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return user;
	}
	//根据用户名来获取用户信息
	public userBean getUserByName(String username) {
		String sql = "select * from user where username = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		userBean user = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int userid = rs.getInt("userid");
				String password = rs.getString("password");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return user;
	}
	//根据昵称来获取用户信息
	public List<userBean> getUserByNick(String nicknames){
		String sql = "select * from user where nickname like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<userBean> userList= new ArrayList<userBean>();
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nicknames);
			rs = ps.executeQuery();
			while(rs.next()) {
				userBean user = null;
				int userid = rs.getInt("userid");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
				userList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return userList;
	}
	
	//获取会员数量
	public int getCount() {
		String sql = "select count(*) count from user";
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
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return size;
	}
	
	//获取分页数据（start是开始序号，第一位是0，size 是截取数据长度）
	public List<userBean> getListPage(int start, int size){
		String sql = "select * from user limit " + start + " , " + size;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<userBean> userList = new ArrayList<userBean>();
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				userBean user = null;
				int userid = rs.getInt("userid");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				String usericon = rs.getString("usericon");
				String regtime = rs.getString("regtime");
				int level = rs.getInt("level");
				user = new userBean(userid, username, password, nickname, sex, birthday, usericon, regtime,level);
				userList.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		return userList;
	}
}
