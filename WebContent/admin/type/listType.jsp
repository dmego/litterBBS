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
<title>查看版区</title>
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
			<span class="crumb-step">&gt;</span><span class="crumb-name">版区管理</span>
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
				<a href="addType.jsp"><i class="icon-font"></i>新增版区</a> <a id="batchDel"
					href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
			</div>
		</div>
		<div class="result-content">
			<table class="result-tab" width="100%">
				<tr>
					<th class="tc" width="5%"><input class="allChoose" name=""
						type="checkbox"></th>
					<th>版区ID</th>
					<th>版区名称</th>
					<th>版区简介</th>
					<th>子版区</th>
					<th>操作</th>
				</tr>
				<!-- forEach 遍历出 typeList -->
				<c:forEach items="${typeList }" var="item" varStatus="status">
					<tr>
						<td class="tc"><input name="id[]" value="59" type="checkbox"></td>
						<td>${item.typeid}</td>
						<td>${item.name}</td>						
						<td>${item.info}</td>						
						<td><a target="mainAction" href="${pageContext.request.contextPath }/admin/type/typeServlet?method=listTypeChild&parentid=${item.typeid}" >查看</a></td>
						<td><a class="link-update" href="${pageContext.request.contextPath }/admin/type/typeServlet?method=updType&typeid=${item.typeid}">修改</a> <a
							class="link-del" href="${pageContext.request.contextPath }/admin/type/typeServlet?method=delType&typeid=${item.typeid}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${param.status.equals('2') }">
				<div class="alert alert-success" role="alert">修改成功</div>
			</c:if>
			<c:if test="${param.status.equals('3') }">
				<div class="alert alert-success" role="alert">删除成功</div>
			</c:if>
			<c:if test="${param.status.equals('1') }">
				<div class="alert alert-success" role="alert">请先删除该版区下的子版区</div>
			</c:if>
		</div>
	</div>
</body>
</html>
