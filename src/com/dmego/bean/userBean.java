package com.dmego.bean;
/**
 * 
 * @author dmego
 * 会员实体Bean
 *
 */
public class userBean {
	private int userid;
	private String username;
	private String password;
	private String nickname;
	private String sex;
	private String birthday;
	private String usericon; //用户头像
	private String regtime; //注册时间
	private int level; //用户级别
	
	public userBean() {}
	public userBean(int userid,String username,String password,String nickname,String sex,String birthday,String usericon,String regtime,int level) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.usericon = usericon;
		this.regtime = regtime;
		this.level = level;
	}
	public userBean(String username,String password,String nickname,String sex,String birthday,String regtime) {	
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;		
		this.regtime = regtime;
	}	
	public userBean(String username, String password, String nickname, String regtime) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;	
		this.regtime = regtime;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUsericon() {
		return usericon;
	}
	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "userBean [userid=" + userid + ", username=" + username + ", password=" + password + ", nickname="
				+ nickname + ", sex=" + sex + ", birthday=" + birthday + ", usericon=" + usericon + ", regtime="
				+ regtime + ", level=" + level + "]";
	}
	
	
}
