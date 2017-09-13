<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/static/css/titlebar.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">LitterBBS论坛</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li>
						<a href="index.jsp">首页</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 精选板块 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li class="divider"></li>
							<li>
								<a href=""></a>
							</li>
						</ul>
					</li>
					<li>
						<a href="">论坛热帖</a>
					</li>
					<li>
						<a href="">论坛新帖</a>
					</li>
					<li>
						<a href="">精华帖</a>
					</li>
				</ul>
			</div>
			
			<c:if test="${sessionScope.userBean == null}">
				<ul class="nav navbar-nav navbar-right user">
					<li>
						<a href="login.jsp">登陆</a>
					</li>
					<li>
						<a href="regist.jsp">注册</a>
					</li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的游客您好！</p>
			</c:if>
			
			<c:if test="${(sessionScope.userBean != null) &&(sessionScope.userBean.level < 2) }">
				<ul class="nav navbar-nav navbar-right user">
	                    <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                            ${userBean.username}<b class="caret"></b>
	                        </a>
	                        <ul class="dropdown-menu">
	                            <li><a href="pages/change-info.jsp">个人设置</a></li>
	                            <li><a href="pages/mypost.jsp?page=1">我的帖子</a></li>
	                            <li><a href="/publish_post.jsp">我要发帖</a></li>
	                            <li class="divider"></li>
	                            <li><a href="${pageContext.request.contextPath}/front/userServlet?method=logout&&status=1">退出登陆</a></li>
	                        </ul>
	                    </li>
	               </ul>
	              <p class="navbar-text navbar-right">尊敬的用户您好！</p>
			</c:if>
			<c:if test="${(sessionScope.userBean != null) &&(sessionScope.userBean.level >= 2) }">
				<ul class="nav navbar-nav navbar-right user">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            ${userBean.username}<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li ><a href="/manage/notice.jsp">发布公告</a></li>
			                <li  ><a href="/manage/change-admin.jsp">资料修改</a></li>
			                <li ><a href="/manage/newpost.jsp">查看新帖</a></li>
			                <li><a href="manage/bestpost.jsp">精华帖请求</a></li>
			                <li><a href="/manage/limit.jsp">封锁用户</a></li>
			                <li><a href="/manage/create_discuss.jsp">创建讨论区</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/front/userServlet?method=logout&&status=1">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
                 <p class="navbar-text navbar-right">尊敬的管理员您好！</p>
			</c:if>
			
			<form class="navbar-form navbar-right" role="search" action="">
				<div class="input-group">
					<input type="text" class="form-control" name="keywords" placeholder="search"> 
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-search"></span> 
					</span>
				</div>
			</form>
		</nav>
	</body>
</html>