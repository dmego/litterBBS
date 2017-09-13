package com.dmego.bean;
/**
 * 分页标签 Bean
 * @author dmego
 *
 */
public class pagingBean {
	private int totalCount; //总数量
	private int pageCount; //总页数
	private int currentPage; //当前页号，首页页号为显示为1，实际为0
	private int countPerPage; //一页多少条数据
	private String prefixUrl; //初始化地址，不带当前页号的参数
	private boolean isAnd; //用来处理是否带页号参数（根据三元表达式计算，true时是"&" 否则为 "?"）
	
	public pagingBean() {}
	//构造函数，三个参数分别是：当前页号，总数量，一页多少数据
	public pagingBean(int currentPage,int totalCount,int countPerPage) {
		//首先确定一页多少条数量
		this.countPerPage = countPerPage;
		if(totalCount % countPerPage  == 0) {
			pageCount = totalCount / countPerPage;
		}else {
			pageCount = (totalCount / countPerPage)+1;
		}
		
		//如果当前页号大于或等于总页数
		if(currentPage >= pageCount) {
			currentPage = pageCount - 1;
		}
		//如果当前页号小于0，说明当前页为首页
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
