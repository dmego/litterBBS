<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>操作会员</title>
<meta http-equiv="Content-Type" content="text\html;charset=utf-8">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/main.css"/>
<script
	src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<style>  
.col-center-block {  
    float: none;  
    display: block;  
    margin-left: auto;  
    margin-right: auto;  
}  
</style> 
</head>
<body>
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font-1"></i><a href="../mainIndex.jsp">首页</a> <span
				class="crumb-step">&gt;</span><span class="crumb-name">公告信息操作</span>
		</div>
	</div>
	<div class="container" style="margin-top: 80px">
		<div class="row">
			<div class="col-md-9 col-center-block">
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:if test="${updateBean!=null}">
							<h3 class="panel-title">修改公告</h3>
						</c:if>
						<c:if test="${updateBean==null}">
							<h3 class="panel-title">发布公告</h3>
						</c:if>
						
					</div>
					<div class="panel-body">
						<div class="column">
							<form action="${pageContext.request.contextPath }/admin/notice/noticeServlet?method=addNotice&updateId=${updateBean.noticeid}" method="post">
								<div class="form-group">
									<label for="name">公告标题</label> 
									<input required type="text"
										class="form-control" name="title" id="name" width="200px"
										height="80px" placeholder="请输入标题" value="${updateBean.title}">
								</div>
								<dl class="form-group">
									<dt>
										<label>公告内容</label>
									</dt>
									<dd>
										<textarea id="TextArea1" cols="20" rows="5" name="content"
											class="ckeditor" >${updateBean.content}</textarea>
									</dd>
								</dl>
								<p>
									<button type="submit" class="btn btn-primary">发布公告</button>
								</p>
								<c:if test="${param.status.equals('1')}">
									<div class="alert alert-danger" role="alert">添加成功</div>
								</c:if>
								
								<c:if test="${param.status.equals('3')}">
									<div class="alert alert-danger" role="alert">成功</div>
								</c:if>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
</html>