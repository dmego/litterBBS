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
 * ��Ա Servlet
 */


@SuppressWarnings("serial")
public class userServlet extends HttpServlet {
	/**
	 * service �жϷ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����������
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
	
	
	//�ڸ�����Ϣҳ��鿴����������
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



	//�鿴�����˵ĸ�����Ϣ
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
	 * �ǳ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String status = request.getParameter("status");
		if(status != null && "1".equals(status)) {
			request.getSession().invalidate(); //ʹ session ʧЧ��ע���û���¼
			response.sendRedirect(request.getContextPath()+"/front/index.jsp");
		}
	}
	
	/**
	 * ��Աע��
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
		
		boolean flag = userdao.checkReg(username);//�����ڸ��û���ʱ���� false ��������ʱ���� true��ֻ�в����ڸ��û���ʱ��������
		if(flag) {
			// �����û�
			userdao.addFrontUser(userBean);
			HttpSession session = request.getSession();
			session.setAttribute("userBean", userBean);
			request.setAttribute("userBean", userBean);
			response.sendRedirect("index.jsp");
		}else {
			//���ڸ��û����Ļ�Ա������ʧ��
			response.sendRedirect("regist.jsp?status=1");
		}

		
	}

	/**
	 * ��Ա��¼
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		userDao userdao = new userDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userBean userBean = userdao.checkLogin(username, password);
		//--------------------------------------��ʼ������ʱ�İ���ѡ��
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
