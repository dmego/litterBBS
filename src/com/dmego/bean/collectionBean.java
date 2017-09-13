package com.dmego.bean;
/**
 * 
 * @author dmego
 * 连接实体Bean
 */
public class collectionBean {
	private int collid;
	private int userid;
	private int postid;
	private String colltime;
	
	public collectionBean() {}

	public int getCollid() {
		return collid;
	}

	public void setCollid(int collid) {
		this.collid = collid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getColltime() {
		return colltime;
	}

	public void setColltime(String colltime) {
		this.colltime = colltime;
	}

	@Override
	public String toString() {
		return "collectionBean [collid=" + collid + ", userid=" + userid + ", postid=" + postid + ", colltime="
				+ colltime + "]";
	}
	
	

}
