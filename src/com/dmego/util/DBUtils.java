package com.dmego.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author dmego
 *	数据库连接工具类
 */
public class DBUtils {
	//数据库名称：litterBBS
	public static String db_driver = "com.mysql.jdbc.Driver"; //Mysql 数据库的驱动
	public static String db_url = "jdbc:mysql://localhost:3306/litterbbs?useUnicode=true&characterEncoding=UTF-8"; //数据库地址
	public static String db_username = "root";
	public static String db_password = "root";
	
	/**
	 * 连接数据库
	 * @return Connection 对象
	 */
	public static Connection getConn() {
		Connection conn = null;		
		try {
			//加载数据库驱动
			Class.forName(db_driver);
			//建立数据库连接
			conn = DriverManager.getConnection(db_url, db_username, db_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库
	 * @param rs 结果集对象
	 * @param state 传输器对象
	 * @param conn 连接对象
	 */
	public static void close(ResultSet rs, Statement state,Connection conn) {
		if(rs != null) { //如果结果集不为空
			try {
				rs.close(); //关闭结果集
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rs = null; //将 rs 置为空，让JVM自动回收空间
			}
		}
		if(state != null) {
			try {
				state.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				 state = null;
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
	}
	
	/**
	 * 关闭数据库
	 * @param rs 结果集对象
	 * @param state 传输器对象
	 */
	public static void close(Statement state,Connection conn) {
		if(state != null) {
			try {
				state.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				 state = null;
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
		
	}
}
