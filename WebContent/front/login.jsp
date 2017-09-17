<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<title>前台登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/front_login.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
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
		<div class="login">

			<form id="checkForm" role="form" action="${pageContext.request.contextPath }/front/userServlet?method=login" method="post">
				<div class="form-group">
					<input id="username" required type="text" class="form-control" name="username" style="height: 45px; margin-top: 20px;" placeholder="请输入用户名">			
				</div>
				<div class="form-group">
					<input type="password" required class="form-control" name="password" style="height: 45px; margin-top: 20px;" placeholder="请输入密码">
				</div>
				<div class="form-group">		
					<div class="col-md-6" style="height: 40px; margin-top: 10px;">	
						<input id="codeCheck" type="text" class="form-control" name="codeCheck" placeholder="请输入验证码" > 
					</div>
				</div>
				<div class="form-group">	
					<div class="col-md-6" style="height: 40px; margin-top: 10px;">
						<input type="text" onclick="createCode()" readOnly="true" id="checkCode" class="unchanged" style="width: 80px" />	
					</div>
				</div>
				<div style="height: 100px; width: 100%; margin-top: 30px;">
					<div style="float: left; width: 50%; padding: 20px;">
						<input type="submit" class="btn btn-primary" value="登陆" style="margin: auto; width: 80%; height: 50px; padding: 13px;"></input>
					</div>
					<div style="float: right; width: 50%; padding: 20px;">
						<a href="regist.jsp" type="button" class="btn btn-primary" style="margin: auto; width: 80%; height: 50px; padding: 13px;">注册</a>
					</div>
				</div>
				<div class="form-group"></div>
			<div class="row-fluid">
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-danger" role="alert">用户名或密码错误</div>
				</c:if>
			</div>
		</form>
		</div>
		<div class="bottom" style="position: absolute; bottom: 0px; margin-top: 20px; background-color: rgba(0, 0, 0, 0.8); width: 100%; height: 75px; color: darkgray">
			<div style="width: 400px; padding-top: 15px; padding-left: 40px; padding-right: 40px; margin: auto;">
				<div align="center">
				<span>版本1.0</span>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  
				<span><a href="#">关于</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
				<span><a href="#">联系</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
				<span><a href="#">反馈</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
				<span><a href="#">帮助</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<p>Copyright © 2017 LitterBBS All rights reserved.</p>
				</div>				
			</div>
		</div>
	</body>
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/myValidate.js" type="text/javascript"></script>
	</body>

</html>