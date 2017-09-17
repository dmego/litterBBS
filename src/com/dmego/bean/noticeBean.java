package com.dmego.bean;
/**
 * 
 * @author dmego
 * 公告实体Bean
 */
public class noticeBean {
	private int noticeid;
	private int userid; //发起公告的管理员id
	private String content; //公告内容
	private String title; //公告标题
	private String noticetime; //发起公告的时间
	private String usericon; //发起管理员的头像
	private String username; //发起管理员的姓名
	
	public noticeBean(int noticeid,int userid,String content,String title,String noticetime) {
		this.noticeid = noticeid;
		this.userid = userid;
		this.content = content;
		this.title = title;
		this.noticetime = noticetime;
	}
	
	public noticeBean(int userid,String content,String title,String noticetime) {		
		this.userid = userid;
		this.content = content;
		this.title = title;
		this.noticetime = noticetime;
	}

	public noticeBean() {
		
	}
		
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsericon() {
		return usericon;
	}

	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}

	public int getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoticetime() {
		return noticetime;
	}

	public void setNoticetime(String noticetime) {
		this.noticetime = noticetime;
	}

	@Override
	public String toString() {
		return "noticeBean [noticeid=" + noticeid + ", userid=" + userid + ", content=" + content + ", title=" + title
				+ ", noticetime=" + noticetime + "]";
	}
	
	
}
