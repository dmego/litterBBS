<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
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
<title>更多帖子</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/user.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/font-awesome-4.4.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/main.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>

<body>
	<jsp:include page="../pages/header.jsp"></jsp:include>
	<div class="container">
		<div class="position clearfix">
			<a href="index.jsp">首页</a> / 更多帖子
		</div>
		<div class="col-md-8 user-right " style="margin-left: 200;">
			<div class="user-right-n clearfix">
				<ul id="right-tab" class="nav nav-tabs">
					<li role="presentation" class="active"><a id="home-tab1"
						data-toggle="tab"><i class="fa fa-book"></i>&nbsp;更多帖子</a></li>
				</ul>
				<div class="user-right-n clearfix tab-content">
					<div role="tabpanel" class="tab-pane active" id="myArticle">
						<div class="tab-pane active" id="home">
							<section id="LastestPostList"
								class="widget bg-white post-comments"> <c:forEach
								items="${morePostList }" var="item" varStatus="status">
								<div class="media">
									<a class="pull-left"
										href="${pageContext.request.contextPath}/front/userServlet?method=showUser&userid=${item.userid}">
										<img class="media-object avatar avatar-sm"
										src="${item.usericon }">
									</a>
									<div class="comment">
										<div class="comment-author h6 no-margin">
											<div class="comment-meta small">
												<a class="badge-comment">${item.replynum }</a>
											</div>
											<a
												href="${pageContext.request.contextPath}/front/postFServlet?method=comment&postid=${item.postid}">${item.title}</a>
										</div>
										<div class="comment-bt">
											<span class="label" style="background-color: #afafaf;"><a
												href="checkZiPostByUrl?cid=7">${item.tychname}</a></span> • <strong><a
												href="${pageContext.request.contextPath}/front/userServlet?userid=${item.userid}">${item.username}</a></strong>
											• <span>${item.posttime}</span>
										</div>
									</div>
								</div>
								<hr>
							</c:forEach> </section>
						</div>
					</div>					
					<div class="alert1"></div>
				</div>
				<div class="row-fluid">
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<div>
								<util:page pagingBean="${pagingBean }" />
							</div>
						</div>
						<div class="col-md-3"></div>
					</div>
			</div>

		</div>
	</div>

</body>
</html>