package com.dmego.bean;
/**
 * 
 * @author dmego
 *	回帖实体类
 */
public class commentBean {
	private int commentid;
	private int postid; //评论的帖子id
	private int userid; //回帖人id
	private String content; //回帖内容
	private String comtime; //回帖时间
	private int agree; //赞个数
	
	public commentBean() {}

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

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	@Override
	public String toString() {
		return "commitBean [commitid=" + commentid + ", postid=" + postid + ", userid=" + userid + ", content=" + content
				+ ", comtime=" + comtime + ", agree=" + agree + "]";
	}
	
	
}
