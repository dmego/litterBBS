<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>操作会员</title>
<meta http-equiv="Content-Type" content="text\html;charset=utf-8">
<meta http-equiv="viewport"
	content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/main.css" />

</head>
<body>
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font-1"></i><a href="mainIndex.jsp">首页</a> <span
				class="crumb-step">&gt;</span><span class="crumb-name">会员信息操作</span>
		</div>
	</div>
	<div class="col-md-8 col-sm-offset-2 text-center">
		<div class="col-md-3"></div>
		<div class="col-md-6 text-center">
			<c:if test="${updateBean!=null}">
				<h1 class="text-center text-danger">修改会员${updateBean.username}</h1>
			</c:if>
			<c:if test="${updateBean==null}">
				<h1 class="text-center text-danger">添加会员</h1>
			</c:if>
			
			<hr>
			<form role="form" class="form-horizontal"
				action="${pageContext.request.contextPath }/admin/adminServlet?method=addUser&updateId=${updateBean.userid}"
				method="post" id="checkForm">
				<div class="form-group">
					<label class="col-md-3 control-label" for="username">用户名</label>
					<div class="col-md-9">
						<input class="form-control" name="username" type="text"
							id="username" placeholder="Username"
							value="${updateBean.username}" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label" for="nickname">昵称</label>
					<div class="col-md-9">
						<input class="form-control" name="nickname" type="text"
							id="nickname" placeholder="Nickname"
							value="${updateBean.nickname}" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label" for="sex">性别</label>
					<div class="col-md-9">
						<label class="radio-inline"> 
							<input class="radio" name="sex" type="radio" id="sex" value="男" ${updateBean.sex == '男' ? 'checked' : ''} />男
						</label> 
						<label class="radio-inline"> 
							<input class="radio" name="sex" type="radio" id="sex" value="女" ${updateBean.sex == '女' ? 'checked' : ''}/>女
						</label>
						
					</div>
				</div>

				<div class="form-group">
					<label for="dtp_input2" class="col-md-3 control-label">生日</label>
					<div class="col-md-9">
						<div class="input-group date form_date"
							data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<input class="form-control" type="text" value="${updateBean.birthday}" id="birthday" name="birthday">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-remove"></span>
							</span> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-md-3 control-label" for="inputPassword">密码</label>
					<div class="col-md-9">
						<input class="form-control" name="password" type="password"
							id="inputPassword" placeholder="Passwrod"
							value="${updateBean.password}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="password2">确认密码</label>
					<div class="col-md-9">
						<input class="form-control" name="password2" type="password"
							id="password2" placeholder="conformPassword" />
					</div>
				</div>
				<div class="form-group"></div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary btn-block">提交</button>
					</div>
				</div>
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-danger" role="alert">添加成功</div>
				</c:if>
				<c:if test="${param.status.equals('2')}">
					<div class="alert alert-danger" role="alert">该会员已存在</div>
				</c:if>
				<c:if test="${param.status.equals('3')}">
					<div class="alert alert-danger" role="alert">成功</div>
				</c:if>
			</form>
		</div>
	</div>

	<div class="col-md-3"></div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/myValidate.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/moment-with-locales.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			autoclose : 1,
			format : 'yyyy-mm-dd',//格式化想要显示的时间格式
			minView : 'month'//日期时间选择器所能够提供的最精确的时间选择视图。
		});
	</script>
</body>
</html>
