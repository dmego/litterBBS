<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告详情</title>
<link
	href="${pageContext.request.contextPath}/static/css/post-detail.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/ueditor/themes/default/css/ueditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body>
	<jsp:include page="../pages/header.jsp" />
	<div class="container" style="margin-top: 50px">
		<div class="row">
			<div class="col-md-1 post-border"></div>

			<div class="col-md-2 post-head" id="left">
				<img alt="" class="img-responsive img-circle"
					src="${noticeuser.usericon}"
					style="margin: 1px 1px; width: 120px; height: 120px; margin: 30px auto;" />
				<table align="center">
					<tr>
						<span class="user-info">
							<td><span class="badge"
								style="background: #f1c40f; margin-top: 5px">作者</span></td>
							<td><span class="badge"
								style="background: #f1c40f; margin-top: 5px">${noticeuser.username}</span></td>
						</span>
					</tr>
					<tr>
						<span class="user-info">
							<td><span class="badge"
								style="background: #2ecc71; margin-top: 5px">性别</span></td>
							<td><span class="badge"
								style="background: #2ecc71; margin-top: 5px">${noticeuser.sex}</span>
						</td>
						</span>
					</tr>
					<tr>
						<span class="user-info">
							<td><span class="badge"
								style="background: #ff6927; margin-top: 5px">等级</span></td>
							<td><span class="badge"
								style="background: #ff6927; margin-top: 5px">LV
									${noticeuser.level}</span></td>
						</span>
					</tr>
				</table>
			</div>
			<div class="col-md-8 post-content" id="right">
				<div class="post-title">
					<h2 style="margin-left: 20px; color: black">${noticeBean.title}</h2>
					<div style="margin-left: 20px">
					<span>发表于:${noticeBean.noticetime}</span>						
					</div>
					<strong style="float: right; margin-right: 10px; margin-top: 10px">
						<span class="badge" style="background: #ff6927; width: 50px;">管理员</span>
					</strong>
				</div>
				<div style="margin: 20px">${noticeBean.content}</div>
			</div>
			<div class="col-md-1 post-border"></div>
		</div>
	</div>
	<jsp:include page="/pages/foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$("#right").outerHeight()
	console.log($("#right").outerHeight())
	$("#left").css("height", $("#right").outerHeight())
</script>

</html>
