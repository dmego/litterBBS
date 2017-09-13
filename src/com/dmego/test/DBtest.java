package com.dmego.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dmego.util.DBUtils;

public class DBtest {
	public static void main(String[] args) {
		String sql = "select * from user";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()) {
				int userid = rs.getInt("userid");
				String username = rs.getString("username");
						
				System.out.println(userid+"--"+username);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		
		
	}
}
