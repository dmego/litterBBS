<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/frontmain.css">
<title>Insert title here</title>
<div class="user">
				<p class="content_right_p">当前用户</p>
				<div class="user_info">
				 <a href="/bbs/jsp/userinfo.jsp" class="user_icon"><img width="80px" height="80px" src="../images/${sessionScope['user'].getUser_icon()}"></a>
				 <div class="user_info_item">
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}" title="${sessionScope['user'].getNickname()}" class="user_nickname">${sessionScope['user'].getNickname()}</a></p>
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}">收藏</a></p>
				 	<p><a href="/bbs/jsp/userdetail.jsp?uid=${sessionScope['user'].getUser_id()}">帖子</a></p>
				 	<p><a href="/bbs/jsp/userinfo.jsp">修改用户信息</a></p>
				 </div>
				</div>
				<a href="/bbs/jsp/fatie.jsp" class="fatie">发帖</a>
			</div>