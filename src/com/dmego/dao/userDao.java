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
 * ��Ա�������
 */
public class userDao {
	
	//����û����Ƿ����
	public boolean checkReg(String username) {
		String sql = "select username from user";
		boolean flag = true;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn(); // �������ݿ�
			state = conn.createStatement(); // ��������������
			rs = state.executeQuery(sql); // ִ�в�ѯ����
			while(rs.next()) { // ����н��������Ϊͨ������֤
				if (username.equals(rs.getString("username"))) {
					flag = false; //�и��û���flag ����false
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, state, conn); // �ر����ݿ������
		}
		return flag;
	}
	
	//��¼����
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
	
	//��ȡȫ����Ա��Ϣ
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
	
	//ǰ̨ע���Ա
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
	
	
	//������Ա
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
	
	//���»�Ա��Ϣ
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
	//���»�Ա��Ϣ������ͷ��
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
	
	//ɾ����Ա
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
	//ͨ����Աid��ȡ��Ա��Ϣ��
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
	//�����û�������ȡ�û���Ϣ
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
	//�����ǳ�����ȡ�û���Ϣ
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
	
	//��ȡ��Ա����
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
	
	//��ȡ��ҳ���ݣ�start�ǿ�ʼ��ţ���һλ��0��size �ǽ�ȡ���ݳ��ȣ�
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
