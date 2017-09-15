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
 * �������� Servlet
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class typeServlet extends HttpServlet {
	/**
	 * service �жϷ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
//--------------------�����----------------------
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
//--------------�Ӱ��-----------------------------		
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
	 * �鿴��������
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
	 * ɾ����������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
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
	 * ���¸�������(��ת�����ҳ��ͬʱ����һ��updateBean session ����)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updType(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeDao typedao = new typeDao();		
		int typeid = StringUtil.StrToInt(request.getParameter("typeid"));
		typeBean typeBean = typedao.getTypeById(typeid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, typeBean);
		request.getRequestDispatcher("addType.jsp").forward(request, response);		

	}
	
	/**
	 * ��Ӹ�������
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
		 * updateId Ϊ��ʱ˵������ӣ���Ϊ��ʱ˵�����޸Ĳ���
		 */
		if( !updateId.equals("")) {//�޸Ĳ���
			int id = StringUtil.StrToInt(updateId);
			type.setTypeid(id);
			typedao.updType(type);
			response.sendRedirect("typeServlet?method=listType&status=2");
		}else {//��������
			typedao.addType(type);
			response.sendRedirect("addType.jsp?status=1");

		}	
	}	
	
//----------------------------�Ӱ�����------------------------------------------------------------
	/**
	 * �鿴�Ӱ�����
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
	 * ɾ���Ӱ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delTypeChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		//ͨ��typeBean Seesion �����ȡ typeBean
		HttpSession session=request.getSession();
		typeBean typeBean = (typeBean)session.getAttribute("typeBean");
		int parentid = typeBean.getTypeid();
		int tychid = StringUtil.StrToInt(request.getParameter("tychid"));
		System.out.println(tychid);
		tychdao.delTypeChild(tychid);
		response.sendRedirect(request.getContextPath()+"/admin/type/typeServlet?method=listTypeChild&parentid="+parentid+"&status=3");				
	}

	/**
	 * �����Ӱ�����(��ת�����ҳ��ͬʱ����һ��updateBean session ����)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updTypeChild(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		int tychid = StringUtil.StrToInt(request.getParameter("tychid"));		
		typeChildBean tychBean = tychdao.getTypeChildById(tychid);		
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, tychBean);
		request.getRequestDispatcher("addTypeChild.jsp").forward(request, response);		

	}
	
	/**
	 * ����Ӱ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addTypeChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//ͨ��typeBean Seesion �����ȡ typeBean
		HttpSession session=request.getSession();
		typeBean typeBean = (typeBean)session.getAttribute("typeBean");
		int parentid = typeBean.getTypeid();
		
		typeChildDao tychdao = new typeChildDao();
		String updateId = request.getParameter("updateId");
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		typeChildBean tychBean = new typeChildBean(parentid, name, info);
		
		/**
		 * updateId Ϊ��ʱ˵������ӣ���Ϊ��ʱ˵�����޸Ĳ���
		 */
		if( !updateId.equals("")) {//�޸Ĳ���
			int id = StringUtil.StrToInt(updateId);
			tychBean.setTychid(id);
			tychdao.updTypeChild(tychBean);
			response.sendRedirect("typeServlet?method=listTypeChild&parentid="+parentid+"&status=2");
		}else {//��������
			tychdao.addTypeChild(tychBean);
			response.sendRedirect("addTypeChild.jsp?status=1");

		}	
	}		


 
}
