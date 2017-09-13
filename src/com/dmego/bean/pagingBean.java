package com.dmego.bean;
/**
 * ��ҳ��ǩ Bean
 * @author dmego
 *
 */
public class pagingBean {
	private int totalCount; //������
	private int pageCount; //��ҳ��
	private int currentPage; //��ǰҳ�ţ���ҳҳ��Ϊ��ʾΪ1��ʵ��Ϊ0
	private int countPerPage; //һҳ����������
	private String prefixUrl; //��ʼ����ַ��������ǰҳ�ŵĲ���
	private boolean isAnd; //���������Ƿ��ҳ�Ų�����������Ԫ���ʽ���㣬trueʱ��"&" ����Ϊ "?"��
	
	public pagingBean() {}
	//���캯�������������ֱ��ǣ���ǰҳ�ţ���������һҳ��������
	public pagingBean(int currentPage,int totalCount,int countPerPage) {
		//����ȷ��һҳ����������
		this.countPerPage = countPerPage;
		if(totalCount % countPerPage  == 0) {
			pageCount = totalCount / countPerPage;
		}else {
			pageCount = (totalCount / countPerPage)+1;
		}
		
		//�����ǰҳ�Ŵ��ڻ������ҳ��
		if(currentPage >= pageCount) {
			currentPage = pageCount - 1;
		}
		//�����ǰҳ��С��0��˵����ǰҳΪ��ҳ
		if(currentPage < 0) {
			currentPage = 0;
		}
		this.currentPage = currentPage;
		this.totalCount = totalCount;
	}
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public String getPrefixUrl() {
		return prefixUrl;
	}
	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}
	public boolean isAnd() {
		return isAnd;
	}
	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}
	@Override
	public String toString() {
		return "pagingBean [totalCount=" + totalCount + ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", countPerPage=" + countPerPage + ", prefixUrl=" + prefixUrl + ", isAnd=" + isAnd + "]";
	}
}
