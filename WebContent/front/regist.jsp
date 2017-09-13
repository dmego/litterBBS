<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<link href="${pageContext.request.contextPath}/static/css/regist.css"
	rel="stylesheet">
		<style type="text/css">
			.code {
				background-image: url(code.jpg);
				font-family: Arial;
				font-style: italic;
				color: Red;
				border: 0;
				padding: 2px 3px;
				letter-spacing: 3px;
				font-weight: bolder;
			}
			
			.unchanged {
				border: 0;
			}
		</style>
		<script type="text/javascript">
			var code; //在全局 定义验证码   
			function createCode() {
				code = "";
				var codeLength = 6; //验证码的长度   
				var checkCode = document.getElementById("checkCode");
				var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的        
				for(var i = 0; i < codeLength; i++) {
					var charIndex = Math.floor(Math.random() * 36);
					code += selectChar[charIndex];
				}
				console.log(code);  
				if(checkCode) {
					checkCode.className = "code";
					checkCode.value = code;
				}
			}
		</script>
</head>
<body onload="createCode()">
	<jsp:include page="/pages/header.jsp" />
	<div class="regist">
		<form  role="form" action="${pageContext.request.contextPath }/front/userServlet?method=regist" method="post" id="checkForm">
		<div class="form-group">	
			<input id="username" type="text" class="form-control" name="username"
				style="height: 40px; margin-top: 20px;" placeholder="请输入用户名">
		</div>
		<div class="form-group">			
			<input id="nickname" type="text" class="form-control" name="nickname"
				style="height: 40px; margin-top: 20px;" placeholder="请输入昵称">
		</div>
		<div class="form-group">			
			<input id="inputPassword" type="password" class="form-control" name="password" 
				style="height: 40px; margin-top: 20px;" placeholder="请输入密码"> 
		</div>
		<div class="form-group">		
			<input id="password2" type="password" class="form-control" name="password2"
				style="height: 40px; margin-top: 20px;" placeholder="请重复密码">
		</div>
		<div class="form-group">		
			<div class="col-md-6" style="height: 40px; margin-top: 20px;">	
				<input id="codeCheck" type="text" class="form-control" name="codeCheck" placeholder="请输入验证码" > 
			</div>
		</div>
		<div class="form-group">	
			<div class="col-md-6" style="height: 40px; margin-top: 20px;">
				<input type="text" onclick="createCode()" readOnly="true" id="checkCode" class="unchanged" style="width: 80px" />	
			</div>
		</div>
			
			<div style="height: 40px; width: 100%; margin-top: 20px; margin-left: 30px;">				
				<input type="submit" class="btn btn-primary" value="注册"
					style="margin: auto; width: 80%; height: 40px; padding: 10px;"></input>
			</div>
			<div class="form-group"></div>
			<div class="row-fluid">
				<div class="col-md-12">
					<c:if test="${param.status.equals('1')}">
						<div class="alert alert-danger" role="alert">该用户名已存在，请重新注册</div>
					</c:if>
				</div>					
			</div>		
		</form>
	</div>

	<div class="bottom"
		style="position: absolute; bottom: 0px; margin-top: 20px; background-color: rgba(0, 0, 0, 0.8); width: 100%; height: 100px; color: darkgray">
		<div
			style="width: 400px; padding-top: 35px; padding-left: 40px; padding-right: 40px; margin: auto;">
			<div align="center">
				<span>版本1.0</span>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <span><a
					href="#">关于</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><a
					href="#">联系</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><a
					href="#">反馈</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><a
					href="#">帮助</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<p>Copyright © 2017 LitterBBS All rights reserved.</p>
			</div>
		</div>
	</div>

</body>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/myValidate.js" type="text/javascript"></script>
</html>