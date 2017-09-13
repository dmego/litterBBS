<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modernizr.min.js"></script>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="mainIndex.jsp">首页</a></li>
                <li><a href="" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
	            <c:if test="${sessionScope.userBean!=null}">
					<li><a href="${pageContext.request.contextPath }">${sessionScope.userBean.username}&nbsp;管理员</a></li>
					<li><a target="_parent" href="${pageContext.request.contextPath}">修改密码</a></li>
					<li><a target="_parent" href="${pageContext.request.contextPath}/admin/adminServlet?method=end&status=1">退出</a></li>
				</c:if>
				<c:if test="${sessionScope.userBean==null}">
					<li><a target="_parent"
						href="${pageContext.request.contextPath}/admin/login.jsp">立即登录</a></li>
				</c:if>
            </ul>
        </div>
    </div>
</div>