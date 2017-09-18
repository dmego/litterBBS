package com.dmego.servlet.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dmego.bean.postBean;
import com.dmego.bean.typeBean;
import com.dmego.bean.userBean;
import com.dmego.dao.postDao;
import com.dmego.dao.typeChildDao;
import com.dmego.dao.typeDao;
import com.dmego.dao.userDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;
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
		}else if("showUser".equals(method)) {
			showUser(request,response);
		}else if("personal".equals(method)) {
			personal(request,response);
		}
	}
	
	
	//在个人信息页面查看发过的帖子
	private void personal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		userDao userdao = new userDao();
		postDao postdao = new postDao();
		typeChildDao tychdao = new typeChildDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		String username = userdao.getUserById(userid).getUsername();
		List<postBean> userPostList = postdao.getPostByUserId(userid);
		for(int i= 0; i< userPostList.size();i++) {
			int tychid = userPostList.get(i).getTychid();
			String tychname = tychdao.getTypeChildById(tychid).getName();
			userPostList.get(i).setTychname(tychname);
			userPostList.get(i).setUsername(username);	
		}
		request.setAttribute("userPostList", userPostList);
		request.getRequestDispatcher("personal.jsp").forward(request, response);
	}



	//查看其它人的个人信息
	private void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		userDao userdao = new userDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		userBean userBean = userdao.getUserById(userid);
		request.setAttribute("userBean", userBean);
		request.getRequestDispatcher("personal.jsp").forward(request, response);
	}




	/**
	 * 登出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
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
		//--------------------------------------初始化发帖时的版区选择
		typeDao typedao = new typeDao();
		List<typeBean> typeList = new ArrayList<typeBean>();
		typeList = typedao.getAllType();
		HttpSession session = request.getSession();
		session.setAttribute("typeList", typeList);
		//---------------------------------------
		if(userBean != null) {			
			session.setAttribute("userBean", userBean);
			request.setAttribute("userBean", userBean);
			
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/front/login.jsp?status=1");
		}
		
	}
	
}
