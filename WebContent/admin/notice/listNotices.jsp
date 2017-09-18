<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看公告</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery-1.12.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/modernizr.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/main.css" />
</head>

<body>

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="../mainIndex.jsp">首页</a>
			<span class="crumb-step">&gt;</span><span class="crumb-name">公告管理</span>
		</div>
	</div>
	<div class="search-wrap">
		<div class="search-content">
			<form action="#" method="post">
				<table class="search-tab">
					<tr>
						<th width="120">选择分类:</th>
						<td><select name="search-sort" id="">
								<option value="">全部</option>
								<option value="19">普通会员</option>
								<option value="20">版主管理员</option>
								<option value="21">管理员</option>
						</select></td>
						<th width="70">关键字:</th>
						<td><input class="common-text" placeholder="关键字"
							name="keywords" value="" id="" type="text"></td>
						<td><input class="btn btn-primary btn2" name="sub" value="查询"
							type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-title">
			<div class="result-list">
				<a href="addNotice.jsp"><i class="icon-font"></i>新增公告</a> <a id="batchDel"
					href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
			</div>
		</div>
		<div class="result-content">
			<table class="result-tab" width="100%">
				<tr>
					<th class="tc" width="5%"><input class="allChoose" name=""
						type="checkbox"></th>
					<th>公告ID</th>
					<th>发布人ID</th>
					<th>内容摘要</th>
					<th>公告标题</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<!-- forEach 遍历出 noticeList -->
				<c:forEach items="${noticeList }" var="item" varStatus="status">
					<tr>
						<td class="tc"><input name="id[]" value="59" type="checkbox"></td>
						<td>${item.noticeid}</td>
						<td>${item.userid}</td>
						<c:if test="${item.content.length() > 15 }">
							<td>${fn:substring(item.content, 0, 15)}.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</td>
						</c:if>
						<c:if test="${item.content.length() <= 15 }">
							<td>${item.content}</td>
						</c:if>
						<td><a target="_blank" href="#" >${item.title}</a></td>
						<td>${item.noticetime}</td>
						<td><a class="link-update" href="${pageContext.request.contextPath }/admin/notice/noticeServlet?method=updNotice&noticeid=${item.noticeid}">修改</a> <a
							class="link-del" href="${pageContext.request.contextPath }/admin/notice/noticeServlet?method=delNotice&noticeid=${item.noticeid}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${param.status.equals('2') }">
				<div class="alert alert-success" role="alert">修改成功</div>
			</c:if>
			<c:if test="${param.status.equals('3') }">
				<div class="alert alert-success" role="alert">删除成功</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div>
					<util:page pagingBean="${pagingBean }"/>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>
