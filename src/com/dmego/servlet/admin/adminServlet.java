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
 * admin 管理员级别会员 Servlet
 */


@SuppressWarnings("serial")
public class adminServlet extends HttpServlet {

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
	 * 查看会员操作
	 * @param request
	 * @param response
	 */
	private void listUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");
		
		userDao userdao = new userDao();
		String status = request.getParameter("status");
		//初始化当前页数
		int currentPage =StringUtil.StrToInt(request.getParameter("currentPage")) ;
		//获取总数量
		int countSize = userdao.getCount();
		//初始化一个分页bean
		pagingBean pagingBean = new pagingBean(currentPage, countSize,Constants.PAGE_SIZE_5);
		List<userBean> userList = userdao.getListPage(currentPage*Constants.PAGE_SIZE_5, Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/adminServlet?method=listUser");
		pagingBean.setAnd(true);
		request.setAttribute(Constants.USER_LIST,userList);
		request.setAttribute("pagingBean", pagingBean);
		if(status != null){ //请求转发到listUsers页面
			request.getRequestDispatcher("listUsers.jsp?status="+status).forward(request, response);
		}else{
			request.getRequestDispatcher("listUsers.jsp").forward(request, response);
		}
		
	}

	/**
	 * 登出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String status = request.getParameter("status");
		if(status != null && "1".equals(status)) {
			request.getSession().invalidate(); //使 session 失效，注销用户登录
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

	/**
	 * 删除会员操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		userDao userdao = new userDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		int level = StringUtil.StrToInt(request.getParameter("level"));
		if(level > 2) {//级别太高，没有权限进行修改操作
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=1");	
		}else {
			userdao.delUser(userid);
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=3");		
		}
	}

	/**
	 * 更新会员操作(跳转到添加页面同时设置一个updateBean session 对象)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		userDao userdao = new userDao();
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		int level = StringUtil.StrToInt(request.getParameter("level"));
		System.out.println("level="+level);
		if(level > 2) {//级别太高，没有权限进行修改操作
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=listUser&status=1");	
		}else {
			userBean userBean = userdao.getUserById(userid);
			request.setAttribute(Constants.SESSION_UPDATE_BEAN, userBean);
			request.getRequestDispatcher("addUser.jsp").forward(request, response);		
		}
	}
	
	/**
	 * 添加会员操作
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
		 * updateId 为空时说明是添加，不为空时说明是修改操作
		 */
		if( !updateId.equals("")) {//修改操作
			int id = StringUtil.StrToInt(updateId);
			userBean.setUserid(id);
			boolean flag = true;
			//如果修改了用户名，就要检查用户名修改后的用户名是否存在
			if( !(username.equals(userdao.getUserById(id).getUsername()))) {
				flag = userdao.checkReg(username);
			}
			if(flag) {//flag 为 true 说明不存在相同的用户名
				//进行修改操作
				userdao.updUserNoico(userBean);
				response.sendRedirect("adminServlet?method=listUser&status=2");
			}else {
				//修改失败，跳回
				response.sendRedirect("addUser.jsp?status=2");
			}
			
		}else {//新增操作
			boolean flag = userdao.checkReg(username);//当存在该用户名时返回 false ，不存在时返回 true，只有不存在该用户名时才能新增
			if(flag) {
				// 新增用户
				userdao.addUser(userBean);
				response.sendRedirect("addUser.jsp?status=1");
			}else {
				//存在该用户名的会员，新增失败
				response.sendRedirect("addUser.jsp?status=2");
			}
		}	
	}

	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
			System.out.println("登录成功");
			response.sendRedirect(request.getContextPath()+"/admin/main.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp?status=1");
		}
				
		
	}
	
       
   

}
