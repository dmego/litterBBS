<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>LitterBBS</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
	</head>
		<frameset frameborder="no" rows="50px,*，115px" >
			<frame src="head.jsp" >
			<frameset cols="850px,*">
				<frame src="content.jsp" >
				<frameset rows="200px,250px,600px">
					<frame src="user.jsp" >
					<frame src="notice.jsp" >
					<frame src="today.jsp" >
				</frameset>
			</frameset>
			<frame src="foot.jsp" >			
		</frameset>
</html>