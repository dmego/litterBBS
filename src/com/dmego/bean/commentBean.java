package com.dmego.bean;
/**
 * 
 * @author dmego
 *	����ʵ����
 */
public class commentBean {
	private int commentid;
	private int postid; //���۵�����id
	private int userid; //������id
	private String content; //��������
	private String comtime; //����ʱ��
	private int flood; //�ڼ�¥
	private String username;	
	private String sex;
	private String usericon; //�û�ͷ��
	private int level; //�û�����
	public commentBean(int postid,int userid,String content,String comtime,int flood) {
		this.postid = postid;
		this.userid = userid;
		this.content = content;
		this.comtime = comtime;
		this.flood = flood;
	}

	
	public commentBean() {}
	
	
	
	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public String getSex() {
		return sex;
	}






	public void setSex(String sex) {
		this.sex = sex;
	}






	public String getUsericon() {
		return usericon;
	}






	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}






	public int getLevel() {
		return level;
	}






	public void setLevel(int level) {
		this.level = level;
	}






	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComtime() {
		return comtime;
	}

	public void setComtime(String comtime) {
		this.comtime = comtime;
	}

	public int getFlood() {
		return flood;
	}

	public void setFlood(int flood) {
		this.flood = flood;
	}

	@Override
	public String toString() {
		return "commentBean [commentid=" + commentid + ", postid=" + postid + ", userid=" + userid + ", content="
				+ content + ", comtime=" + comtime + ", flood=" + flood + "]";
	}

	

	
	
	
}
