<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>帖子浏览</title>
<link
	href="${pageContext.request.contextPath}/static/css/post-detail.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
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
	<div style="position: relative;">
		<jsp:include page="../pages/header.jsp" />
		<div class="container" style="margin-top: 50px">
			<div class="row">
				<div class="col-md-1 post-border"></div>

				<div class="col-md-2 post-head" id="left">
					<img alt="" class="img-responsive img-circle"
						src="${postuser.usericon}"
						style="margin: 1px 1px; width: 120px; height: 120px; margin: 30px auto;" />
					<table align="center">
						<tr>
							<span class="user-info">
								<td><span class="badge"
									style="background: #f1c40f; margin-top: 5px">作者</span></td>
								<td><span class="badge"
									style="background: #f1c40f; margin-top: 5px">${postuser.username}</span></td>
							</span>
						</tr>
						<tr>
							<span class="user-info">
								<td><span class="badge"
									style="background: #2ecc71; margin-top: 5px">性别</span></td>
								<td><span class="badge"
									style="background: #2ecc71; margin-top: 5px">${postuser.sex}</span>
							</td>
							</span>
						</tr>
						<tr>
							<span class="user-info">
								<td><span class="badge"
									style="background: #ff6927; margin-top: 5px">等级</span></td>
								<td><span class="badge"
									style="background: #ff6927; margin-top: 5px">LV
										${postuser.level}</span></td>
							</span>
						</tr>
					</table>
				</div>
				<div class="col-md-8 post-content" id="right">
					<div class="post-title">
						<h2 style="margin-left: 20px; color: black">[${postBean.tychname}]${postBean.title}</h2>
						<div style="margin-left: 20px">

							<span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;${postBean.replynum}
							&nbsp;|&nbsp;<span>发表于:${postBean.posttime}</span>
							<c:if test="${userBean.username.equals(postuser.username)}">
								<a style="float: right; margin-right: 20px;"
									href="/editpost.action?postId=">编辑</a>
							</c:if>
						</div>
						<strong style="float: right; margin-right: 10px; margin-top: 10px">
							<span class="badge" style="background: #ff6927; width: 50px;">楼主</span>
						</strong>
					</div>
					<div style="margin: 20px">${postBean.content}</div>
				</div>
				<div class="col-md-1 post-border"></div>
			</div>
		</div>

		<!-- 回复内容 -->
		<c:forEach items="${commentList }" var="item" varStatus="status">
			<div class="container">
				<div class="row" style="margin-top: 5px">
					<div class="col-md-1 reply-border"></div>
					<div class="col-md-2 reply-head">
						<img alt="" class="img-responsive img-circle"
							src="${item.usericon}"
							style="margin: 1px 1px; width: 120px; height: 120px; margin: 30px auto;" />
						<table align="center">
							<tr>
								<span class="user-info">
									<td><span class="badge"
										style="background: #f1c40f; margin-top: 5px">作者</span></td>
									<td><span class="badge"
										style="background: #f1c40f; margin-top: 5px">${item.username}</span></td>
								</span>
							</tr>
							<tr>
								<span class="user-info">
									<td><span class="badge"
										style="background: #2ecc71; margin-top: 5px">性别</span></td>
									<td><span class="badge"
										style="background: #2ecc71; margin-top: 5px">${item.sex}</span>
								</td>
								</span>
							</tr>
							<tr>
								<span class="user-info">
									<td><span class="badge"
										style="background: #ff6927; margin-top: 5px">等级</span></td>
									<td><span class="badge"
										style="background: #ff6927; margin-top: 5px">LV
											${item.level}</span></td>
								</span>
							</tr>
						</table>
					</div>
					<div class="col-md-8 reply-content">
						<div class="reply-time">
							<span style="color: gray">回复于:${item.comtime}</span>
						</div>
						<div style="margin: 20px;">${item.content}</div>
						<c:if test="${item.flood == 1}">
							<span class="badge"
								style="float: right; margin-right: 10px; background: #ff6927; width: 50px;">沙发</span>
						</c:if>
						<c:if test="${item.flood == 2}">
							<span class="badge"
								style="float: right; margin-right: 10px; background: #ff5959; width: 50px;">板凳</span>
						</c:if>
						<c:if test="${item.flood == 3}">
							<span class="badge"
								style="float: right; margin-right: 10px; background: #4b9ded; width: 50px;">地板</span>
						</c:if>
						<c:if test="${item.flood > 3}">
							<span class="badge"
								style="float: right; margin-right: 10px; background: #4b9ded; width: 50px;">第${item.flood}楼</span>
						</c:if>
					</div>
					<div class="col-md-1 reply-border"></div>
				</div>
			</div>
			
		</c:forEach>
		<div class="row-fluid" align="center">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div>
					<util:page pagingBean="${pagingBean }" />
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div
			style="height: 200px; margin: 50px auto; width: 800px; clear: both;">
			<form
				action="${pageContext.request.contextPath}/front/postFServlet?method=reply&userid=${userBean.userid }&postid=${postBean.postid}"
				method="post">
				<div class="sendcomment">
					<p>
						<span>发表评论</span><a href="#">回到顶部^</a>
					</p>
				</div>
				<textarea name="content" id="myEditor"
					style="width: 900px; height: 120px;"></textarea>
				<div style="height: 10px;"></div>
				<div class="col-md-2">
					<input class="btn btn-success btn-outline btn-block" type="submit"
						value="跟帖" />
				</div>
			</form>
		</div>
		<div style="height: 120px; clear: both"></div>
		<jsp:include page="/pages/foot.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	$("#right").outerHeight()
	console.log($("#right").outerHeight())
	$("#left").css("height", $("#right").outerHeight())

	var option = {
		toolbars : [ [ 'source', '|', 'undo', 'redo', '|', 'bold', 'italic',
				'underline', 'superscript', 'subscript', 'removeformat', '|',
				'forecolor', 'backcolor', 'insertorderedlist',
				'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'paragraph', 'fontfamily', 'fontsize', '|', 'justifyleft',
				'justifycenter', 'justifyright', 'justifyjustify', '|', 'link',
				'unlink', '|', 'simpleupload', 'emotion' ] ],
		elementPathEnabled : false
	};
	var editor = UE.getEditor('myEditor', option);
</script>

</html>
