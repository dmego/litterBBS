package com.dmego.bean;
/**
 * 
 * @author dmego
 *	����ʵ����
 */
public class commentBean {
	private int commitid;
	private int postid; //���۵�����id
	private int userid; //������id
	private String content; //��������
	private String comtime; //����ʱ��
	private int agree; //�޸���
	
	public commentBean() {}

	public int getCommitid() {
		return commitid;
	}

	public void setCommitid(int commitid) {
		this.commitid = commitid;
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
		return "commitBean [commitid=" + commitid + ", postid=" + postid + ", userid=" + userid + ", content=" + content
				+ ", comtime=" + comtime + ", agree=" + agree + "]";
	}
	
	
}
