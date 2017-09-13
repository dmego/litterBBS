package com.dmego.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author dmego
 *	���ݿ����ӹ�����
 */
public class DBUtils {
	//���ݿ����ƣ�litterBBS
	public static String db_driver = "com.mysql.jdbc.Driver"; //Mysql ���ݿ������
	public static String db_url = "jdbc:mysql://localhost:3306/litterbbs?useUnicode=true&characterEncoding=UTF-8"; //���ݿ��ַ
	public static String db_username = "root";
	public static String db_password = "root";
	
	/**
	 * �������ݿ�
	 * @return Connection ����
	 */
	public static Connection getConn() {
		Connection conn = null;		
		try {
			//�������ݿ�����
			Class.forName(db_driver);
			//�������ݿ�����
			conn = DriverManager.getConnection(db_url, db_username, db_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ر����ݿ�
	 * @param rs ���������
	 * @param state ����������
	 * @param conn ���Ӷ���
	 */
	public static void close(ResultSet rs, Statement state,Connection conn) {
		if(rs != null) { //����������Ϊ��
			try {
				rs.close(); //�رս����
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rs = null; //�� rs ��Ϊ�գ���JVM�Զ����տռ�
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
	 * �ر����ݿ�
	 * @param rs ���������
	 * @param state ����������
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
