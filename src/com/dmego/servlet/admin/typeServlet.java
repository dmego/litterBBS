package com.dmego.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.dmego.bean.noticeBean;
import com.dmego.bean.typeBean;
import com.dmego.bean.typeChildBean;
import com.dmego.bean.userBean;
import com.dmego.dao.noticeDao;
import com.dmego.dao.typeChildDao;
import com.dmego.dao.typeDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;

/**
 * 版区管理 Servlet
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class typeServlet extends HttpServlet {
	/**
	 * service 判断方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
//--------------------父板块----------------------
		if("addType".equals(method)) {
			addType(request,response);
		}else if("updType".equals(method)) {
			updType(request,response);
		}else if("delType".equals(method)) {
			delType(request,response);
		}else if("listType".equals(method)) {
			listType(request,response);
		}else if("headGetType".equals(method)) {
			headGetType(request,response);
		}
//--------------子版块-----------------------------		
		else if("addTypeChild".equals(method)) {
			addTypeChild(request,response);
		}else if("updTypeChild".equals(method)) {
			updTypeChild(request,response);
		}else if("delTypeChild".equals(method)) {
			delTypeChild(request,response);
		}else if("listTypeChild".equals(method)) {
			listTypeChild(request,response);
		}
	}

//----------------------------------------------------------------
	private void headGetType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeDao typedao = new typeDao();
		List<typeBean> typeList = typedao.getAllType();
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(typeList));
		out.flush();
		out.close();	
	}		
//----------------------------------------------------------------
	/**
	 * 查看父板块操作
	 * @param request
	 * @param response
	 */
	private void listType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");
		typeDao typedao = new typeDao();
		List<typeBean> typeList = typedao.getAllType();
		request.setAttribute("typeList", typeList);
		HttpSession session = request.getSession();
		session.setAttribute("typeList", typeList);
		request.getRequestDispatcher("listType.jsp").forward(request, response);

	}

	/**
	 * 删除父板块操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeDao typedao = new typeDao();
		typeChildDao tychdao = new typeChildDao();
		int typeid = StringUtil.StrToInt(request.getParameter("typeid"));
		boolean flag = tychdao.hasChild(typeid);
		if(flag) {
			response.sendRedirect(request.getContextPath()+"/admin/type/typeServlet?method=listType&status=1");	
		}else {
			typedao.delType(typeid);
			response.sendRedirect(request.getContextPath()+"/admin/type/typeServlet?method=listType&status=3");		
		}
			
		
	}

	/**
	 * 更新父板块操作(跳转到添加页面同时设置一个updateBean session 对象)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updType(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeDao typedao = new typeDao();		
		int typeid = StringUtil.StrToInt(request.getParameter("typeid"));
		typeBean typeBean = typedao.getTypeById(typeid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, typeBean);
		request.getRequestDispatcher("addType.jsp").forward(request, response);		

	}
	
	/**
	 * 添加父板块操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		typeDao typedao = new typeDao();
		String updateId = request.getParameter("updateId");		
		String name = request.getParameter("name");
		String info = request.getParameter("info");					
		typeBean type = new typeBean(name, info);
		/**
		 * updateId 为空时说明是添加，不为空时说明是修改操作
		 */
		if( !updateId.equals("")) {//修改操作
			int id = StringUtil.StrToInt(updateId);
			type.setTypeid(id);
			typedao.updType(type);
			response.sendRedirect("typeServlet?method=listType&status=2");
		}else {//新增操作
			typedao.addType(type);
			response.sendRedirect("addType.jsp?status=1");

		}	
	}	
	
//----------------------------子板块操作------------------------------------------------------------
	/**
	 * 查看子板块操作
	 * @param request
	 * @param response
	 */
	private void listTypeChild(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");
		typeDao typedao = new typeDao();
		typeChildDao tychdao = new typeChildDao();
		int parentid =StringUtil.StrToInt(request.getParameter("parentid")) ;
		List<typeChildBean> tychList = tychdao.getTypeChildByParentId(parentid);
		request.setAttribute("tychList", tychList);
		typeBean typeBean = typedao.getTypeById(parentid);
		HttpSession session = request.getSession();
		session.setAttribute("tychList", tychList);
		session.setAttribute("typeBean", typeBean);
		request.getRequestDispatcher("listTypeChild.jsp").forward(request, response);
	}

	/**
	 * 删除子板块操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delTypeChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		//通过typeBean Seesion 对象获取 typeBean
		HttpSession session=request.getSession();
		typeBean typeBean = (typeBean)session.getAttribute("typeBean");
		int parentid = typeBean.getTypeid();
		int tychid = StringUtil.StrToInt(request.getParameter("tychid"));
		System.out.println(tychid);
		tychdao.delTypeChild(tychid);
		response.sendRedirect(request.getContextPath()+"/admin/type/typeServlet?method=listTypeChild&parentid="+parentid+"&status=3");				
	}

	/**
	 * 更新子板块操作(跳转到添加页面同时设置一个updateBean session 对象)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updTypeChild(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		int tychid = StringUtil.StrToInt(request.getParameter("tychid"));		
		typeChildBean tychBean = tychdao.getTypeChildById(tychid);		
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, tychBean);
		request.getRequestDispatcher("addTypeChild.jsp").forward(request, response);		

	}
	
	/**
	 * 添加子板块操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addTypeChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//通过typeBean Seesion 对象获取 typeBean
		HttpSession session=request.getSession();
		typeBean typeBean = (typeBean)session.getAttribute("typeBean");
		int parentid = typeBean.getTypeid();
		
		typeChildDao tychdao = new typeChildDao();
		String updateId = request.getParameter("updateId");
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		typeChildBean tychBean = new typeChildBean(parentid, name, info);
		
		/**
		 * updateId 为空时说明是添加，不为空时说明是修改操作
		 */
		if( !updateId.equals("")) {//修改操作
			int id = StringUtil.StrToInt(updateId);
			tychBean.setTychid(id);
			tychdao.updTypeChild(tychBean);
			response.sendRedirect("typeServlet?method=listTypeChild&parentid="+parentid+"&status=2");
		}else {//新增操作
			tychdao.addTypeChild(tychBean);
			response.sendRedirect("addTypeChild.jsp?status=1");

		}	
	}		


 
}
