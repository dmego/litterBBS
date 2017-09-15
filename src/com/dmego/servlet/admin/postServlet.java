package com.dmego.servlet.admin;


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
import com.dmego.bean.postBean;
import com.dmego.bean.typeBean;
import com.dmego.bean.typeChildBean;
import com.dmego.bean.userBean;
import com.dmego.dao.noticeDao;
import com.dmego.dao.postDao;
import com.dmego.dao.userDao;
import com.dmego.dao.typeChildDao;
import com.dmego.dao.typeDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;


/**
 * ���ӹ��� Servlet
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class postServlet extends HttpServlet {
	/**
	 * service �жϷ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		if("addPost".equals(method)) {
			addPost(request,response);
		}else if("updPost".equals(method)) {
			updPost(request,response);
		}else if("delPost".equals(method)) {
			delPost(request,response);
		}else if("listPost".equals(method)) {
			listPost(request,response);
		}else if("addPostInit".equals(method)) {
			addPostInit(request,response);
		}else if("getTypeChild".equals(method)) {
			getTypeChild(request,response);
		}
	}

	

	
	/**
	 * �鿴���Ӳ���
	 * @param request
	 * @param response
	 */
	private void listPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");		
		postDao postdao = new postDao();
		String status = request.getParameter("status");
		//��ʼ����ǰҳ��
		int currentPage =StringUtil.StrToInt(request.getParameter("currentPage")) ;
		//��ȡ������
		int countSize = postdao.getCount();
		//��ʼ��һ����ҳbean
		pagingBean pagingBean = new pagingBean(currentPage, countSize,Constants.PAGE_SIZE_2);		
		List<postBean> postList = postdao.getListPage(currentPage*Constants.PAGE_SIZE_2, Constants.PAGE_SIZE_2);
		//ͨ��for ѭ��Ϊ ������ �� �������� ��ֵ
		userDao userdao = new userDao();
		typeChildDao tychdao = new typeChildDao(); 
		for(int i = 0; i < postList.size();i++) {
			int userid = postList.get(i).getUserid();
			int tychid = postList.get(i).getTychid();
			String username = userdao.getUserById(userid).getUsername();
			String tychname = tychdao.getTypeChildById(tychid).getName();
			postList.get(i).setUsername(username);
			postList.get(i).setTychname(tychname);			
		}
		
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/post/postServlet?method=listPost");
		pagingBean.setAnd(true);
		request.setAttribute(Constants.POST_LIST,postList);
		request.setAttribute("pagingBean", pagingBean);
		if(status != null){ //����ת����listPostҳ��
			request.getRequestDispatcher("listPost.jsp?status="+status).forward(request, response);
		}else{
			request.getRequestDispatcher("listPost.jsp").forward(request, response);
		}
		
	}

	/**
	 * ɾ�����Ӳ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		postdao.delPost(postid);
		response.sendRedirect(request.getContextPath()+"/admin/post/postServlet?method=listPost&status=3");				
	}

	/**
	 * �������Ӳ���(��ת�����ҳ��ͬʱ����һ��updateBean session ����)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		System.out.println("postid--"+postid);
		postBean postBean = postdao.getPostById(postid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, postBean);
		request.getRequestDispatcher("addPost.jsp").forward(request, response);		

	}
	
	/**
	 * ������Ӳ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		String updateId = request.getParameter("updateId");
		int tychid =StringUtil.StrToInt(request.getParameter("tychid")) ;
		HttpSession session=request.getSession();
		userBean user=(userBean)session.getAttribute("userBean");
		int userid = user.getUserid();
		String title = request.getParameter("title");
		String content = request.getParameter("content"); //��������
		SimpleDateFormat postTime1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String postTime=postTime1.format(new Date());
		postBean postBean = new postBean(userid, tychid, title, content, postTime);
		
		/**
		 * updateId Ϊ��ʱ˵������ӣ���Ϊ��ʱ˵�����޸Ĳ���
		 */
		if( !updateId.equals("")) {//�޸Ĳ���
			int id = StringUtil.StrToInt(updateId);
			postBean.setPostid(id);
			postdao.updPostByAdmin1(postBean);
			response.sendRedirect("postServlet?method=listPost&status=2");
		}else {//��������
			postdao.addPost(postBean);//���뵽���ݿ���
			response.sendRedirect("addPost.jsp?status=1");
		}			   
	}
	

//-----------------------���������ǰ��Ҫ���ذ�������--------------------------------------
	private void addPostInit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeDao typedao = new typeDao();
		List<typeBean> typeList = typedao.getAllType();
		request.setAttribute("typeList", typeList);
		request.getRequestDispatcher("addPost.jsp").forward(request, response);
	}
	//ͨ��ajax�첽�����ȡ�Ӱ�������
	private void getTypeChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		int parentid = StringUtil.StrToInt(request.getParameter("id"));
		System.out.println("��ȡ��������ID:"+parentid);
		List<typeChildBean> tychList = tychdao.getTypeChildByParentId(parentid);
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(tychList));
		out.flush();
		out.close();		
	}
//----------------------------------------------------------------------------------------
}
