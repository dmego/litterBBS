package com.dmego.bean;
/**
 * 子版区实体类
 * @author dmego
 *
 */
public class typeChildBean {	
	private int tychid; //子版区ID
	private int parentid; //父版区ID
	private String name;
	private String info;
	
	public typeChildBean() {}
	
	public typeChildBean(int tychid,int parentid,String name,String info) {
		this.tychid = tychid;
		this.parentid = parentid;
		this.name = name;
		this.info = info;
	}
	public typeChildBean(int parentid,String name,String info) {
		this.parentid = parentid;
		this.name = name;
		this.info = info;
	}
	
	public int getTychid() {
		return tychid;
	}

	public void setTychid(int tychid) {
		this.tychid = tychid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
