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
import com.dmego.bean.commentBean;
import com.dmego.bean.pagingBean;
import com.dmego.bean.postBean;
import com.dmego.bean.typeBean;
import com.dmego.bean.typeChildBean;
import com.dmego.bean.userBean;
import com.dmego.dao.commentDao;
import com.dmego.dao.postDao;
import com.dmego.dao.userDao;
import com.dmego.dao.typeChildDao;
import com.dmego.dao.typeDao;
import com.dmego.util.Constants;
import com.dmego.util.StringUtil;

/**
 * 帖子管理 Servlet
 * 
 * @author dmego
 *
 */
@SuppressWarnings("serial")
public class postFServlet extends HttpServlet {
	/**
	 * service 判断方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		if ("addPost".equals(method)) {
			addPost(request, response);
		} else if ("updPost".equals(method)) {
			updPost(request, response);
		} else if ("delPost".equals(method)) {
			delPost(request, response);
		} else if ("listPost".equals(method)) {
			listPost(request, response);
		} else if ("addPostInit".equals(method)) {
			addPostInit(request, response);
		} else if ("getTypeChild".equals(method)) {
			getTypeChild(request, response);
		} else if ("headInitPost".equals(method)) {
			headInitPost(request, response);
		} else if ("comment".equals(method)) {
			comment(request, response);
		}else if("reply".equals(method)) {
			reply(request,response);
		}
	}

	
	//回帖操作
	private void reply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		int userid = StringUtil.StrToInt(request.getParameter("userid"));
		String content = request.getParameter("content");
		SimpleDateFormat comtime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String comtime = comtime1.format(new Date());
		commentDao commentdao = new commentDao();
		postDao postdao = new postDao();
		postdao.addReplyNum(postid);
		int replyNum = postdao.getReplyNum(postid);
		commentBean comment = new commentBean(postid, userid, content, comtime, replyNum);
		commentdao.addComment(comment);
		response.sendRedirect(request.getContextPath()+"/front/postFServlet?method=comment&postid="+postid);
		
		
	}



	/**
	 * 查看帖子详情
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void comment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		postBean postBean = postdao.getPostById(postid);
		userDao userdao = new userDao();
		typeChildDao tychdao = new typeChildDao();
		int userid = postBean.getUserid();
		int tychid = postBean.getTychid();
		userBean postuser = userdao.getUserById(userid);		
		String tychname = tychdao.getTypeChildById(tychid).getName();	
		postBean.setTychname(tychname);
		commentDao commentdao = new commentDao();
		//初始化当前页数
		int currentPage =StringUtil.StrToInt(request.getParameter("currentPage")) ;
		int countSize = commentdao.getCountByPost(postid);
		pagingBean pagingBean = new pagingBean(currentPage, countSize,Constants.PAGE_SIZE_3);
		List<commentBean> commentList = commentdao.getListPage(postid,currentPage*Constants.PAGE_SIZE_3, Constants.PAGE_SIZE_3);
		pagingBean.setPrefixUrl(request.getContextPath()+"/front/postFServlet?method=comment&postid="+postid);
		pagingBean.setAnd(true);
		for(int i = 0; i < commentList.size();i++) {
			int id = commentList.get(i).getUserid();
			userBean user = userdao.getUserById(id);
			String username = user.getUsername();
			commentList.get(i).setUsername(username);
			String usericon = user.getUsericon();
			commentList.get(i).setUsericon(usericon);
			String sex = user.getSex();
			commentList.get(i).setSex(sex);
			int level = user.getLevel();
			commentList.get(i).setLevel(level);
		}
		
		request.setAttribute("commentList",commentList);
		request.setAttribute("pagingBean", pagingBean);
		request.setAttribute("postuser", postuser);
		request.setAttribute("postBean", postBean);
		request.getRequestDispatcher("comment.jsp").forward(request, response);
	}

	/**
	 * 首页初始化帖子展示
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void headInitPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		List<postBean> postList = postdao.headInitPost();
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * 查看帖子操作
	 * 
	 * @param request
	 * @param response
	 */
	private void listPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=urf-8");
		postDao postdao = new postDao();
		String status = request.getParameter("status");
		// 初始化当前页数
		int currentPage = StringUtil.StrToInt(request.getParameter("currentPage"));
		// 获取总数量
		int countSize = postdao.getCount();
		// 初始化一个分页bean
		pagingBean pagingBean = new pagingBean(currentPage, countSize, Constants.PAGE_SIZE_5);
		List<postBean> morePostList = postdao.getListPage(currentPage * Constants.PAGE_SIZE_5, Constants.PAGE_SIZE_5);
		// 通过for 循环为 发布人 和 所属版区 赋值
		userDao userdao = new userDao();
		typeChildDao tychdao = new typeChildDao();
		for (int i = 0; i < morePostList.size(); i++) {
			int userid = morePostList.get(i).getUserid();
			int tychid = morePostList.get(i).getTychid();
			String username = userdao.getUserById(userid).getUsername();
			String tychname = tychdao.getTypeChildById(tychid).getName();
			String usericon = userdao.getUserById(userid).getUsericon();
			morePostList.get(i).setUsericon(usericon);
			morePostList.get(i).setUsername(username);
			morePostList.get(i).setTychname(tychname);
		}
		pagingBean.setPrefixUrl(request.getContextPath() + "/front/postFServlet?method=listPost");
		pagingBean.setAnd(true);
		request.setAttribute("morePostList", morePostList);
		request.setAttribute("pagingBean", pagingBean);		
		request.getRequestDispatcher("morePost.jsp").forward(request, response);
	}

	/**
	 * 删除帖子操作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		postdao.delPost(postid);
		response.sendRedirect(request.getContextPath() + "/admin/post/postServlet?method=listPost&status=3");
	}

	/**
	 * 更新帖子操作(跳转到添加页面同时设置一个updateBean session 对象)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		int postid = StringUtil.StrToInt(request.getParameter("postid"));
		System.out.println("postid--" + postid);
		postBean postBean = postdao.getPostById(postid);
		request.setAttribute(Constants.SESSION_UPDATE_BEAN, postBean);
		request.getRequestDispatcher("addPost.jsp").forward(request, response);

	}

	/**
	 * 添加帖子操作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		postDao postdao = new postDao();
		String updateId = request.getParameter("updateId");
		int tychid = StringUtil.StrToInt(request.getParameter("tychid"));
		typeChildDao tychdao = new typeChildDao();
		String tychname = tychdao.getTypeChildById(tychid).getName();
		HttpSession session = request.getSession();
		userBean user = (userBean) session.getAttribute("userBean");
		int userid = user.getUserid();
		String title = request.getParameter("title");
		String content = request.getParameter("content"); // 帖子内容
		SimpleDateFormat postTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String postTime = postTime1.format(new Date());
		postBean postBean = new postBean(userid, tychid, title, content, postTime);
		session.setAttribute("postuser", user);
		postBean.setTychname(tychname);
		session.setAttribute("postBean", postBean);
		/**
		 * updateId 为空时说明是添加，不为空时说明是修改操作
		 */
		if (!updateId.equals("")) {// 修改操作
			int id = StringUtil.StrToInt(updateId);
			postBean.setPostid(id);
			postdao.updPostByAdmin1(postBean);
			response.sendRedirect("comment.jsp");
		} else {// 新增操作
			postdao.addPost(postBean);// 插入到数据库中
			response.sendRedirect("comment.jsp");
		}
	}

	// -----------------------在添加帖子前需要加载版区数据--------------------------------------
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

	// 通过ajax异步请求获取子版区数据
	private void getTypeChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		typeChildDao tychdao = new typeChildDao();
		int parentid = StringUtil.StrToInt(request.getParameter("id"));
		System.out.println("获取到父版区ID:" + parentid);
		List<typeChildBean> tychList = tychdao.getTypeChildByParentId(parentid);
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(tychList));
		out.flush();
		out.close();
	}
	// ----------------------------------------------------------------------------------------
}
