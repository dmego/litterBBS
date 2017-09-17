<%@ page import="com.dmego.servlet.front.postFServlet" %>
<%@ page import="com.dmego.dao.postDao" %>
<%@ page import="com.dmego.dao.userDao" %>
<%@ page import="com.dmego.dao.typeChildDao" %>
<%@ page import="com.dmego.bean.postBean" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.min.css">
		<title>欢迎进入！</title>
	</head>
	<style type="text/css">
		* {
			margin: 0;
		}		
		html,
		body {
			height: 100%;
		}		
		.wrapper {
			min-height: 100%;
			height: auto !important;
			height: 100%;
			margin: 0 auto -4em;
		}		
		.footer,
		.push {
			height: 75px;
		}
	</style>
	<script type="text/javascript">
		function init() {
			//ajax请求获取公告信息
			$.post(
					"admin/notice/noticeServlet", {
						method: "headGetNotice"
					},
					function(data) {
						if(data != null && data.length > 0) {
							var str = "";
							for(var notice in data) {
								str += "<a class='list-group-item' href='" +
									data[notice].noticeid +
									"'>" +
									data[notice].title + "</a>";
							}
							$("#noticelist").append(str);
						}
					}, "json");
			
		}
	</script>

	<body onload="init()">
		<div style="position: relative;">
			<jsp:include page="/pages/header.jsp"></jsp:include>
			<jsp:include page="/pages/search.jsp"></jsp:include>
			<div style="height: 30px;"></div>
			<div class="container" style="margin-top: 30px; min-height: 100%; height: auto !important; height: 100%; margin: 0 auto -4em;">
				<div class="row">
					<div class="col-md-9">
						<ul class="list-group">
							<div class="list-group-item active">
								论坛新帖
								<a href="${pageContext.request.contextPath}/front/" style="float: right; color: white">更多>></a>
							</div>
							<div class="tab-pane active" id="home">
								<section id="LastestPostList" class="widget bg-white post-comments">
								<%
									postDao postdao = new postDao();
									List<postBean> headPostList = postdao.headInitPost();
									//通过for 循环为 发布人 和 所属版区 赋值
									userDao userdao = new userDao();
									typeChildDao tychdao = new typeChildDao(); 
									for(int i = 0; i < headPostList.size();i++) {
										int userid = headPostList.get(i).getUserid();
										int tychid = headPostList.get(i).getTychid();
										String username = userdao.getUserById(userid).getUsername();
										String tychname = tychdao.getTypeChildById(tychid).getName();
										String usericon = userdao.getUserById(userid).getUsericon();
										headPostList.get(i).setUsername(username);
										headPostList.get(i).setTychname(tychname);	
										headPostList.get(i).setUsericon(usericon);
									}
									session.setAttribute("headPostList", headPostList);										
								%>
								<c:forEach items="${headPostList }" var="item" varStatus="status">
									<div class="media">
										<a class="pull-left" href="${pageContext.request.contextPath}/front/userServlet?userid=${item.userid}"> 
											<img class="media-object avatar avatar-sm" src="${item.usericon }" >
										</a>
										<div class="comment">
											<div class="comment-author h6 no-margin">
												<div class="comment-meta small">
													<a class="badge-comment">${item.replynum }</a>
												</div>
												<a href="${pageContext.request.contextPath}/front/postFServlet?method=comment&postid=${item.postid}">${item.title}</a>
											</div>
											<div class="comment-bt">
												<span class="label" style="background-color: #afafaf;"><a
											href="checkZiPostByUrl?cid=7">${item.tychname}</a></span> • <strong><a
											href="${pageContext.request.contextPath}/front/userServlet?userid=${item.userid}">${item.username}</a></strong> • <span>${item.posttime}</span>
											</div>
										</div>
									</div>
									<hr>
									</c:forEach>									
								</section>
							</div>
						</ul>
					</div>
					<div class="col-md-3">
						<ul class="list-group" id="noticelist">
							<div class="list-group-item active">
								论坛公告
								<a href="${pageContext.request.contextPath}/front/" style="float: right; color: white">更多>></a>
							</div>
						</ul>
						<c:if test="${sessionScope.userBean != null}">
							<ul class="list-group">
								<div class="list-group-item active">个人信息</div>
							</ul>
							<div class="panel-body">
								<div class="row">
									<div class="profile-sidebar">
										<div class="profile-sidebar-item profile-avatar">
											<a href="front/personal.jsp"> 
												<img src="${userBean.usericon }" alt="个人头像" class="avatar avatar-lg img-circle">
											</a>
										</div>
										<div class="profile-sidebar-item profile-info">
											<span class="h5 bold"></span>
											<p></p>
											<div class="w150 center-block mt10">
												<a class="btn btn-success btn-outline btn-block" href="front/post.jsp">
													<span>发布新帖子</span>
												</a>
											</div>
										</div>
										<hr>
										<ul class="profile-sidebar-item profile-numbers-count">
											<li>
												<a href="#"><span class="bold">0</span>帖子收藏</a>
											</li>
											<li>
												<a href="#"><span class="bold">0</span>节点收藏</a>
											</li>
											<li>
												<a href="#"><span class="bold">0</span>特别关注</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel-footer box">
								<a href="#"><span id="notice_count">0</span>条未读提醒</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div style="height: 150px;"></div>
			<jsp:include page="/pages/foot.jsp"></jsp:include>
		</div>
	</body>
</html>