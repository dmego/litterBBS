package com.dmego.bean;
/**
 * 版区实体类
 * @author dmego
 *
 */
public class typeBean {
	private int typeid;
	private String name;
	private String info;
	
	public typeBean() {}
	
	public typeBean(int typeid, String name, String info) {
		this.typeid = typeid;
		this.name = name;
		this.info = info;
	}
	public typeBean(String name, String info) {
		this.name = name;
		this.info = info;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
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
