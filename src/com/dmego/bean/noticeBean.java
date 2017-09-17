package com.dmego.bean;
/**
 * 
 * @author dmego
 * ����ʵ��Bean
 */
public class noticeBean {
	private int noticeid;
	private int userid; //���𹫸�Ĺ���Աid
	private String content; //��������
	private String title; //�������
	private String noticetime; //���𹫸��ʱ��
	private String usericon; //�������Ա��ͷ��
	private String username; //�������Ա������
	
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
