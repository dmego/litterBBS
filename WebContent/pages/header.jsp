<%@ page import="com.dmego.servlet.admin.typeServlet" %>
<%@ page import="com.dmego.dao.typeDao" %>
<%@ page import="com.dmego.bean.typeBean" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/static/css/titlebar.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
	</head>
	<script type="text/javascript">
	function getType(){		
			//ajax请求
			$.post("admin/type/typeServlet",{
				method: "headGetType"
			},
				function(data){
					if(data != null && data.length > 0){
						var str="";
	        			for(var type in data){
	        			str+="<li><a href='checkPostByUrl?bid="+data[type].typeid+"'>"+data[type].name+"</a></li>";
	        			str+="<li class='divider'></li>";
	        			}
	        			$("#boardlist").append(str);
					}
				},"json");
		   }
				
</script>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">LitterBBS论坛</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li>
						<a href="index.jsp">首页</a>
					</li>
					<li class="dropdown" >
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" > 精选板块 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu" id="boardlist" >
							<%
								typeDao typedao = new typeDao();
								List<typeBean> typeList = typedao.getAllType();
								for(int i = 0; i < typeList.size();i++){
							%>
							<li><a href="checkPostByUrl?bid=<%=typeList.get(i).getTypeid()%>"><%=typeList.get(i).getName()%></a></li>
							<li class='divider'></li>
							<%		
								}
							%>						
						</ul>
						
					</li>
					<li>
						<a href="">论坛热帖</a>
					</li>
					<li>
						<a href="">论坛新帖</a>
					</li>
					<li>
						<a href="">精华帖</a>
					</li>
				</ul>
			</div>
			
			<c:if test="${sessionScope.userBean == null}">
				<ul class="nav navbar-nav navbar-right user">
					<li>
						<a href="${pageContext.request.contextPath }/front/login.jsp">登陆</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/front/regist.jsp">注册</a>
					</li>
				</ul>
				<p class="navbar-text navbar-right">尊敬的游客您好！</p>
			</c:if>
			
			<c:if test="${(sessionScope.userBean != null) &&(sessionScope.userBean.level < 2) }">
				<ul class="nav navbar-nav navbar-right user">
	                    <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                            ${userBean.username}<b class="caret"></b>
	                        </a>
	                        <ul class="dropdown-menu">	                            
	                            <li><a href="front/personal.jsp">我的信息</a></li>
	                            <li><a href="">消息中心</a></li>
	                            <li class="divider"></li>
	                            <li><a href="${pageContext.request.contextPath}/front/userServlet?method=logout&&status=1">退出登陆</a></li>
	                        </ul>
	                    </li>
	               </ul>
	              <p class="navbar-text navbar-right">尊敬的用户您好！</p>
			</c:if>
			<c:if test="${(sessionScope.userBean != null) &&(sessionScope.userBean.level >= 2) }">
				<ul class="nav navbar-nav navbar-right user">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            ${userBean.username}<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="">发布公告</a></li>
			                <li><a href="front/personal.jsp">我的信息</a></li>
			                <li><a href="">消息中心</a></li>
			                <li><a href="">精华帖请求</a></li>
			                <li><a href="">封锁用户</a></li>
			                <li><a href="">后台管理</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/front/userServlet?method=logout&&status=1">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
                 <p class="navbar-text navbar-right">尊敬的管理员您好！</p>
			</c:if>
		</nav>
	</body>
</html>