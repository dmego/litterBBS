package com.dmego.servlet.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmego.bean.noticeBean;
import com.dmego.bean.pagingBean;
import com.dmego.dao.noticeDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;

/**
 * 公告管理Servlet
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class noticeServlet extends HttpServlet {
	/**
	 * service 判断方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		if("addNotice".equals(method)) {
			addNotice(request,response);
		}else if("updNotice".equals(method)) {
			updNotice(request,response);
		}else if("delNotice".equals(method)) {
			delNotice(request,response);
		}else if("listNotices".equals(method)) {
			listNotices(request,response);
		}
	}

	/**
	 * 查看公告操作
	 * @param request
	 * @param response
	 */
	private void listNotices(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");		
		noticeDao noticedao = new noticeDao();
		String status = request.getParameter("status");
		//初始化当前页数
		int currentPage =StringUtil.StrToInt(request.getParameter("currentPage")) ;
		//获取总数量
		int countSize = noticedao.getCount();
		//初始化一个分页bean
		pagingBean pagingBean = new pagingBean(currentPage, countSize,Constants.PAGE_SIZE_5);
		List<noticeBean> noticeList = noticedao.getListPage(currentPage*Constants.PAGE_SIZE_5, Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/notice/noticeServlet?method=listNotices");
		pagingBean.setAnd(true);
		request.setAttribute(Constants.NOTICE_LIST,noticeList);
		request.setAttribute("pagingBean", pagingBean);
		if(status != null){ //请求转发到listNotices页面
			request.getRequestDispatcher("listNotices.jsp?status="+status).forward(request, response);
		}else{
			request.getRequestDispatcher("listNotices.jsp").forward(request, response);
		}
		
	}

	/**
	 * 删除公告操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		noticeDao noticedao = new noticeDao();
		int noticeid = StringUtil.StrToInt(request.getParameter("noticeid"));
		noticedao.delNotice(noticeid);
		response.sendRedirect(request.getContextPath()+"/admin/notice/noticeServlet?method=listNotices&status=3");		
		
	}

	/**
	 * 更新公告操作(跳转到添加页面同时设置一个updateBean session 对象)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updNotice(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		noticeDao noticedao = new noticeDao();
		int noticeid = StringUtil.StrToInt(request.getParameter("noticeid"));
		noticeBean noticeBean = noticedao.getNoticeById(noticeid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, noticeBean);
		request.getRequestDispatcher("addNotice.jsp").forward(request, response);		

	}
	
	/**
	 * 添加公告操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		noticeDao noticedao = new noticeDao();
		String updateId = request.getParameter("updateId");
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		SimpleDateFormat noticetime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String noticetime = noticetime1.format(new Date());
		noticeBean noticeBean = new noticeBean(userid, content, title, noticetime);
		/**
		 * updateId 为空时说明是添加，不为空时说明是修改操作
		 */
		if( !updateId.equals("")) {//修改操作
			int id = StringUtil.StrToInt(updateId);
			noticeBean.setNoticeid(id);
			noticedao.updNotice(noticeBean);
			response.sendRedirect("noticeServlet?method=listNotices&status=2");
		}else {//新增操作
			noticedao.addNotice(noticeBean);
			response.sendRedirect("addNotice.jsp?status=1");

		}	
	}		

}
