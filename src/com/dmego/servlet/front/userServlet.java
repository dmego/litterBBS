package com.dmego.servlet.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dmego.bean.userBean;
import com.dmego.dao.userDao;
/**
 * 
 * @author dmego
 * 会员 Servlet
 */


@SuppressWarnings("serial")
public class userServlet extends HttpServlet {
	/**
	 * service 判断方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		if("login".equals(method)) {
			login(request,response);
		}else if("regist".equals(method)) {
			regist(request,response);
		}else if("logout".equals(method)) {
			logout(request,response);
		}
	}

	/**
	 * 登出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String status = request.getParameter("status");
		if(status != null && "1".equals(status)) {
			request.getSession().invalidate(); //使 session 失效，注销用户登录
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		}
	}
	
	/**
	 * 会员注册
	 * @param request
	 * @param response
	 */
	private void regist(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		userDao userdao = new userDao();
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		SimpleDateFormat regtime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String regtime = regtime1.format(new Date());
		userBean userBean = new userBean(username, password, nickname,regtime);
		
		boolean flag = userdao.checkReg(username);//当存在该用户名时返回 false ，不存在时返回 true，只有不存在该用户名时才能新增
		if(flag) {
			// 新增用户
			userdao.addFrontUser(userBean);
			HttpSession session = request.getSession();
			session.setAttribute("userBean", userBean);
			request.setAttribute("userBean", userBean);
			response.sendRedirect("index.jsp");
		}else {
			//存在该用户名的会员，新增失败
			response.sendRedirect("regist.jsp?status=1");
		}

		
	}

	/**
	 * 会员登录
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		userDao userdao = new userDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userBean userBean = userdao.checkLogin(username, password);
		if(userBean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userBean", userBean);
			request.setAttribute("userBean", userBean);
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/front/login.jsp?status=1");
		}
		
	}
	
}
