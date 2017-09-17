package com.dmego.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.dmego.bean.noticeBean;
import com.dmego.bean.pagingBean;
import com.dmego.bean.typeBean;
import com.dmego.bean.userBean;
import com.dmego.dao.noticeDao;
import com.dmego.dao.typeDao;
import com.dmego.dao.userDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;

/**
 * �������Servlet
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class noticeFServlet extends HttpServlet {
	/**
	 * service �жϷ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		if("addNotice".equals(method)) {
			addNotice(request,response);
		}else if("updNotice".equals(method)) {
			updNotice(request,response);
		}else if("delNotice".equals(method)) {
			delNotice(request,response);
		}else if("listNotice".equals(method)) {
			listNotice(request,response);
		}else if("showNotice".equals(method)) {
			showNotice(request,response);
		}
	}
		
	
	//������ϸչʾҳ��
	private void showNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");		
		noticeDao noticedao = new noticeDao();
		int noticeid = StringUtil.StrToInt(request.getParameter("noticeid"));
		noticeBean noticeBean = noticedao.getNoticeById(noticeid);
		int userid = noticeBean.getUserid();
		userDao userdao = new userDao();
		userBean noticeuser = userdao.getUserById(userid);
		request.setAttribute("noticeuser", noticeuser);
		request.setAttribute("noticeBean", noticeBean);
		request.getRequestDispatcher("notice.jsp").forward(request, response);
	}



	/**
	 * �鿴�������
	 * @param request
	 * @param response
	 */
	private void listNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");		
		noticeDao noticedao = new noticeDao();						
		List<noticeBean> moreNoticeList = noticedao.ListNotice();
		userDao userdao = new userDao();
		for(int i = 0; i < moreNoticeList.size();i++) {
			int userid = moreNoticeList.get(i).getUserid();
			userBean user = userdao.getUserById(userid);
			String usericon = user.getUsericon();
			String username = user.getUsername();
			moreNoticeList.get(i).setUsername(username);
			moreNoticeList.get(i).setUsericon(usericon);
		}
		
		request.setAttribute("moreNoticeList", moreNoticeList);
		request.getRequestDispatcher("moreNotice.jsp").forward(request, response);		
	}

	/**
	 * ɾ���������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		noticeDao noticedao = new noticeDao();
		int noticeid = StringUtil.StrToInt(request.getParameter("noticeid"));
		noticedao.delNotice(noticeid);
		response.sendRedirect(request.getContextPath()+"/admin/notice/noticeServlet?method=listNotices&status=3");		
		
	}

	/**
	 * ���¹������(��ת�����ҳ��ͬʱ����һ��updateBean session ����)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updNotice(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		noticeDao noticedao = new noticeDao();
		int noticeid = StringUtil.StrToInt(request.getParameter("noticeid"));
		noticeBean noticeBean = noticedao.getNoticeById(noticeid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, noticeBean);
		request.getRequestDispatcher("addNotice.jsp").forward(request, response);		

	}
	
	/**
	 * ��ӹ������
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
		
		//ͨ��user Seesion �����ȡ userid
		HttpSession session=request.getSession();
		userBean user = (userBean)session.getAttribute("userBean");
		int userid = user.getUserid();
		
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		SimpleDateFormat noticetime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String noticetime = noticetime1.format(new Date());
		noticeBean noticeBean = new noticeBean(userid, content, title, noticetime);
		/**
		 * updateId Ϊ��ʱ˵������ӣ���Ϊ��ʱ˵�����޸Ĳ���
		 */
		if( !updateId.equals("")) {//�޸Ĳ���
			int id = StringUtil.StrToInt(updateId);
			noticeBean.setNoticeid(id);
			noticedao.updNotice(noticeBean);
			response.sendRedirect("noticeServlet?method=listNotices&status=2");
		}else {//��������
			noticedao.addNotice(noticeBean);
			response.sendRedirect("addNotice.jsp?status=1");

		}	
	}		

}
