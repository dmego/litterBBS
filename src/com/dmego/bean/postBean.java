package com.dmego.bean;
/**
 * 
 * @author dmego
 * ����ʵ��Bean
 */
public class postBean {
	private int postid;
	private int userid;
	private String username;//����������
	private int tychid;//�����Ӱ��ID
	private String tychname; //��������
	private String title;
	private String content;//��������
	private int replynum; //������
	private String posttime;
	
	
	public postBean() {}
	public postBean(int postid,int userid,int tychid,String title,String content,int replynum,String posttime) {
		this.postid = postid;
		this.userid = userid;
		this.tychid = tychid;
		this.title = title;
		this.content = content;
		this.replynum = replynum;
		this.posttime = posttime;		
	}
	
	public postBean(int userid,int tychid,String title,String content,String posttime) {
		this.userid = userid;
		this.tychid = tychid;
		this.title = title;
		this.content = content;
		this.posttime = posttime;		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTychid() {
		return tychid;
	}

	public void setTychid(int tychid) {
		this.tychid = tychid;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getTychname() {
		return tychname;
	}
	public void setTychname(String tychname) {
		this.tychname = tychname;
	}
	@Override
	public String toString() {
		return "postBean [postid=" + postid + ", userid=" + userid + ", tychid=" + tychid + ", title=" + title
				+ ", content=" + content + ", replynum=" + replynum + ", posttime=" + posttime + "]";
	}

	
	

	
	
}
