<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modernizr.min.js"></script>
</head>
<body>
<div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font"></i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a target="mainAction" href="${pageContext.request.contextPath }/admin/post/postServlet?method=listPost"><i class="icon-font"></i>帖子管理</a></li>
                        <li><a target="mainAction" href="${pageContext.request.contextPath }/admin/adminServlet?method=listUser"><i class="icon-font"></i>会员管理</a></li>
                        <li><a target="mainAction" href="${pageContext.request.contextPath }/admin/type/typeServlet?method=listType"><i class="icon-font"></i>版区管理</a></li>                   
                        <li><a target="mainAction" href="${pageContext.request.contextPath }/admin/notice/noticeServlet?method=listNotices"><i class="icon-font"></i>公告管理</a></li>
                        <li><a target="mainAction" href=""><i class="icon-font"></i>友情链接</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font"></i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href=""><i class="icon-font"></i>系统设置</a></li>
                        <li><a href=""><i class="icon-font"></i>清理缓存</a></li>
                        <li><a href=""><i class="icon-font"></i>数据备份</a></li>
                        <li><a href=""><i class="icon-font"></i>数据还原</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

	<div style="text-align: center;"></div>
</body>
</html>