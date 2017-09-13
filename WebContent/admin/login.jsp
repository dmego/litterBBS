<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<title>后台登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		<link href="${pageContext.request.contextPath}/static/css/admin_login.css" rel="stylesheet" type="text/css" />
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
		<div class="admin_login_wrap">
	    <h1 align="center">后台管理</h1>
	    <div class="adming_login_border">
	        <div class="admin_input">
	            <form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/admin/adminServlet?method=login" method="post" id="checkForm">
	            <div class="form-group">
					<label class="col-md-3 control-label" for="username">用户名</label>
					<div class="col-md-9">
						<input class="form-control" name="username" type="text"
							id="username" placeholder="Username" value="" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="inputPassword">密码</label>
					<div class="col-md-9">
						<input type="password" name="password" class="form-control"
							id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="checkCode">验证码</label>
					<div class="col-md-4">
						<input type="text" name="codeCheck" class="form-control"
							id="codeCheck" placeholder="Code">
					</div>
					<div class="col-md-4">
						<input type="text" onclick="createCode()" readOnly="true"
							id="checkCode" class="unchanged" style="width: 80px" />
					</div>
					<div class="col-md-1"></div>

				</div>
				<div class="form-group"></div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary btn-block">
							登录</button>
					</div>
				</div>    
	                
	            </form>
	        </div>
	    </div>
	    <p class="admin_copyright"><a tabindex="5" href="" target="_blank">返回首页</a> &copy; 2017 Powered by <a href="" target="_blank">木青子日</a></p>
	</div>

		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/js/myValidate.js" type="text/javascript"></script>
	</body>

</html>