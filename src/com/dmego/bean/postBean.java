package com.dmego.bean;
/**
 * 
 * @author dmego
 * Ìû×ÓÊµÌåBean
 */
public class postBean {
	private int postid;
	private int userid;
	private int typeid;
	private int hot;
	private String posttime;
	private String src;
	
	public postBean() {}

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

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "postBean [postid=" + postid + ", userid=" + userid + ", typeid=" + typeid + ", hot=" + hot
				+ ", posttime=" + posttime + ", src=" + src + "]";
	}
	
	
}
