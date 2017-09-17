<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>帖子浏览</title>
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

		<div class="container">
			<div class="row" style="margin-top: 5px">
				<div class="col-md-1 reply-border"></div>
				<div class="col-md-2 reply-head">
					<img alt="" class="img-responsive img-circle"
						src=" ../static/images/face/1.png"
						style="margin: 1px 1px; width: 120px; height: 120px; margin: 30px auto;" />

					<span class="user-info"> <span class="badge"
						style="background: #f1c40f; margin-top: 5px">姓名</span> :<span
						class="badge" style="background: #f1c40f; margin-top: 5px">XXXX</span>
					</span><br /> <span class="user-info"> <span class="badge"
						style="background: #2ecc71; margin-top: 5px">性别</span> :<span
						class="badge" style="background: #2ecc71; margin-top: 5px">XX</span>
					</span><br /> <span class="user-info"> <span class="badge"
						style="background: #ff6927; margin-top: 5px">论坛等级</span>: <span
						class="badge" style="background: #ff6927; margin-top: 5px">LV
							3</span>
					</span> <br>


				</div>
				<div class="col-md-8 reply-content">
					<div class="reply-time">
						<span style="color: gray">回复于:2017-09-15 15:29:32</span>
					</div>
					<div style="margin: 20px;">回复的内容</div>
					<span class="badge"
						style="float: right; margin-right: 10px; background: #ff6927; width: 50px;">沙发</span>

				</div>
				<div class="col-md-1 reply-border"></div>

			</div>
		</div>
		<br>


		<div style="height: 200px; margin: 50px auto; width: 800px; clear: both;">
			<form action="" method="post">
				<div class="sendcomment">
					<p>
						<span>发表评论</span><a href="#">回到顶部^</a>
					</p>
				</div>
				<textarea name="content" id="myEditor"
					style="width: 900px; height: 120px;">${updateBean.content }</textarea>
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
