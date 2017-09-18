<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
.greenbutton {
	color: #fff;
	background: #5cb85c;
}

/* Blue Color */
.redbutton {
	color: #fff;
	background: #d9534f;
}

.hoverbutton {
	display: inline-block;
	position: relative;
	margin-top: 3px;
	padding: 0 20px;
	text-align: center;
	text-decoration: none;
	font: bold 12px/25px Arial, sans-serif;
	text-shadow: 1px 1px 1px rgba(255, 255, 255, .22);
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	border-radius: 30px;
	-webkit-box-shadow: 1px 1px 1px rgba(0, 0, 0, .29), inset 1px 1px 1px
		rgba(255, 255, 255, .44);
	-moz-box-shadow: 1px 1px 1px rgba(0, 0, 0, .29), inset 1px 1px 1px
		rgba(255, 255, 255, .44);
	box-shadow: 1px 1px 1px rgba(0, 0, 0, .29), inset 1px 1px 1px
		rgba(255, 255, 255, .44);
	-webkit-transition: all 0.15s ease;
	-moz-transition: all 0.15s ease;
	-o-transition: all 0.15s ease;
	-ms-transition: all 0.15s ease;
	transition: all 0.15s ease;
}

.alert1-false {
	width: 220px;
	color: #FFFFFF;
	background-color: #FF3030;
	border-color: #FFFFFF;
}

.alert1-success {
	width: 150px;
	color: #3c763d;
	background-color: #dff0d8;
	border-color: #d6e9c6;
}

.alert1 {
	text-align: center;
	margin-left: 120px;
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
}

.hoverbutton redbutton {
	
}
</style>
<title>我的信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/user.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/font-awesome-4.4.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/main.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/search.css">
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/member.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/posts.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/search.js"></script>
<body>
	<jsp:include page="../pages/header.jsp"></jsp:include>
	<div class="container">
		<div class="position clearfix">
			<a href="index.jsp">首页</a> / 我的信息
		</div>
		<div class="user-cont clearfix">
			<div class="col-md-4 user-left">
				<div class="user-left-n clearfix">
					<h6>
						<i class="fa fa-address-card"></i>详细信息
					</h6>
					<a class="user-headimg f"> <img src="${userBean.usericon }"></a>
					<div class="user-name f">
						<h4>${userBean.username }</h4>
						<p>${userBean.nickname }</p>
					</div>
				</div>
				<div class="user-left-n clearfix">
					<ul class="list-group">
						<li class="list-group-item" style="text-align: center;"><i
							class="fa fa-user-secret"></i>&nbsp;Level：${userBean.level }</li>
						<li class="list-group-item" style="text-align: center;"><i
							class="fa fa-transgender"></i>&nbsp;性别：${userBean.sex }</li>
						<li class="list-group-item" style="text-align: center;"><i
							class="fa fa-heartbeat"></i>&nbsp;生日：${userBean.birthday }</li>
						<li class="list-group-item" style="text-align: center;"><i
							class="fa fa-clock-o"></i>&nbsp;注册日期：${userBean.regtime }</li>
					</ul>
					<div>
						<a name="" class="btn btn-info"><i class="fa fa-cogs"></i>&nbsp;修改个人资料</a>
					</div>
				</div>
			</div>
			<div class="col-md-8 user-right">
				<div class="user-right-n clearfix">
					<ul id="right-tab" class="nav nav-tabs">
						<li role="presentation" class="active"><a href="#myArticle"
							id="home-tab1" data-toggle="tab"><i class="fa fa-book"></i>&nbsp;我的帖子</a>
						</li>
						<li role="presentation"><a href="#myFriends" id="home-tab3"
							data-toggle="tab"><i class="fa fa-group"></i>&nbsp;我的好友</a></li>
					</ul>

					<div class="user-right-n clearfix tab-content">
						<div role="tabpanel" class="tab-pane active" id="myArticle">
							<c:if test="${userPostList == null }">
								<h4>未发表任何帖子。</h4>
							</c:if>
							<c:if test="${userPostList != null }">
								<c:forEach items="${userPostList }" var="item"
									varStatus="status">
									<div class="art-row">
										<h4>
											<a
												href="${pageContext.request.contextPath}/front/postFServlet?method=comment&postid=${item.postid}"
												class="title">${item.title} </a>
										</h4>
										<span class="label" style="background-color: #afafaf;">
											<a href="">${item.tychname}</a>
										</span> &nbsp;<a class="author"> <i class="fa fa-user"></i> &nbsp;<span>${item.username}</span>
										</a> <a class="time"> <i class="fa fa-clock-o"></i> &nbsp;<span>${item.posttime}</span>
										</a>

									</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="alert1"></div>
						<div role="tabpanel" class="tab-pane" id="myFriends">
							<h4>未添加任何好友。</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>