package com.dmego.servlet.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dmego.bean.pagingBean;
import com.dmego.bean.userBean;
import com.dmego.dao.userDao;
import com.dmego.util.StringUtil;
import com.dmego.util.Constants;
/**
 * 
 * @author dmego
 * admin ����Ա�����Ա Servlet
 */


@SuppressWarnings("serial")
public class adminServlet extends HttpServlet {

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
		}else if("addUser".equals(method)) {
			addUser(request,response);
		}else if("updUser".equals(method)) {
			updUser(request,response);
		}else if("delUser".equals(method)) {
			delUser(request,response);
		}else if("end".equals(method)) {
			end(request,response);
		}else if("listUser".equals(method)) {
			listUser(request,response);
		}
	}

	/**
	 * �鿴��Ա����
	 * @param request
	 * @param response
	 */
	private void listUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");
		
		userDao userdao = new userDao();
		String status = request.getParameter("status");
		//��ʼ����ǰҳ��
		int currentPage =StringUtil.StrToInt(request.getParameter("currentPage")) ;
		//��ȡ������
		int countSize = userdao.getCount();
		//��ʼ��һ����ҳbean
		pagingBean pagingBean = new pagingBean(currentPage, countSize,Constants.PAGE_SIZE_5);
		List<userBean> userList = userdao.getListPage(currentPage*Constants.PAGE_SIZE_5, Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/adminServlet?method=listUser");
		pagingBean.setAnd(true);
		request.setAttribute(Constants.USER_LIST,userList);
		request.setAttribute("pagingBean", pagingBean);
		if(status != null){ //����ת����listUsersҳ��
			request.getRequestDispatcher("listUsers.jsp?status="+status).forward(request, response);
		}else{
			request.getRequestDispatcher("listUsers.jsp").forward(request, response);
		}
		
	}

	/**
	 * �ǳ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String status = request.getParameter("status");
		if(status != null && "1".equals(status)) {
			request.getSession().invalidate(); //ʹ session ʧЧ��ע���û���¼
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

	/**
	 * ɾ����Ա����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		userDao userdao = new userDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		int level = StringUtil.StrToInt(request.getParameter("level"));
		if(level > 2) {//����̫�ߣ�û��Ȩ�޽����޸Ĳ���
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=1");	
		}else {
			userdao.delUser(userid);
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=3");		
		}
	}

	/**
	 * ���»�Ա����(��ת�����ҳ��ͬʱ����һ��updateBean session ����)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		userDao userdao = new userDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		int level = StringUtil.StrToInt(request.getParameter("level"));
		System.out.println("level="+level);
		if(level > 2) {//����̫�ߣ�û��Ȩ�޽����޸Ĳ���
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=1");	
		}else {
			userBean userBean = userdao.getUserById(userid);
			request.setAttribute(Constants.SESSION_UPDATE_BEAN, userBean);
			request.getRequestDispatcher("addUser.jsp").forward(request, response);		
		}
	}
	
	/**
	 * ��ӻ�Ա����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		userDao userdao = new userDao();
		String updateId = request.getParameter("updateId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		SimpleDateFormat regtime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String regtime = regtime1.format(new Date());
		userBean userBean = new userBean(username, password, nickname, sex, birthday, regtime);
		/**
		 * updateId Ϊ��ʱ˵������ӣ���Ϊ��ʱ˵�����޸Ĳ���
		 */
		if( !updateId.equals("")) {//�޸Ĳ���
			int id = StringUtil.StrToInt(updateId);
			userBean.setUserid(id);
			boolean flag = true;
			//����޸����û�������Ҫ����û����޸ĺ���û����Ƿ����
			if( !(username.equals(userdao.getUserById(id).getUsername()))) {
				flag = userdao.checkReg(username);
			}
			if(flag) {//flag Ϊ true ˵����������ͬ���û���
				//�����޸Ĳ���
				userdao.updUserNoico(userBean);
				response.sendRedirect("adminServlet?method=listUser&status=2");
			}else {
				//�޸�ʧ�ܣ�����
				response.sendRedirect("addUser.jsp?status=2");
			}
			
		}else {//��������
			boolean flag = userdao.checkReg(username);//�����ڸ��û���ʱ���� false ��������ʱ���� true��ֻ�в����ڸ��û���ʱ��������
			if(flag) {
				// �����û�
				userdao.addUser(userBean);
				response.sendRedirect("addUser.jsp?status=1");
			}else {
				//���ڸ��û����Ļ�Ա������ʧ��
				response.sendRedirect("addUser.jsp?status=2");
			}
		}	
	}

	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
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
			System.out.println("��¼�ɹ�");
			response.sendRedirect(request.getContextPath()+"/admin/main.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp?status=1");
		}
				
		
	}
	
       
   

}
