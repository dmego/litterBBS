<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.min.css">
    <title>欢迎进入！</title>  
  </head>
  <script type="text/javascript">
	function init(){		
			//ajax请求获取公告信息
			$.post("admin/notice/noticeServlet",{
				method: "headGetNotice"
			},
				function(data){
					if(data != null && data.length > 0){
						var str="";
	        			for(var notice in data){
	        			str+="<a class='list-group-item' href='checkPostByUrl?bid="+data[notice].noticeid+"'>"+data[notice].title+"</a>";
	        			}
	        			$("#noticelist").append(str);
					}
				},"json");
		   }
			
	
</script>
	  
  <body onload="init()">
	  <jsp:include page="/pages/header.jsp"></jsp:include>
	  <jsp:include page="/pages/search.jsp"></jsp:include>
	<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-md-9">
            <ul class="list-group">
                <div class="list-group-item active">
                   	 论坛新帖
                    <a href="${pageContext.request.contextPath}/front/" style="float: right;color: white">更多>></a>
                </div>
                <div class="tab-pane active" id="home">
					<section id="LastestPostList" class="widget bg-white post-comments">
						<center id="loading"><img src="img/loading.gif"></center>
					</section>								
				</div>			
            </ul>
        </div>
         <div class="col-md-3">
            <ul class="list-group" id="noticelist">
                <div class="list-group-item active">论坛公告</div>                      
            </ul>
            
          <c:if test="${sessionScope.userBean != null}">
	        <ul class="list-group">
	            <div class="list-group-item active">个人信息</div>
	        </ul>
			<div class="panel-body">
				<div class="row">
					<div class="profile-sidebar">
						<div class="profile-sidebar-item profile-avatar">
							<a href="">
								<img src="static/images/face/1.png" alt="" class="avatar avatar-lg img-circle">
							</a>
						</div>
						<div class="profile-sidebar-item profile-info">
							<span class="h5 bold"></span>
							<p></p>
							<div class="w150 center-block mt10">
								<a class="btn btn-success btn-outline btn-block" href="${pageContext.request.contextPath}/publish_post.jsp">
									<span>发布新帖子</span>
								</a>
							</div>
						</div>
						<hr>
						<ul class="profile-sidebar-item profile-numbers-count">
							<li><a href="#"><span class="bold">0</span>帖子收藏</a></li>
							<li><a href="#"><span class="bold">0</span>节点收藏</a></li>
							<li><a href="#"><span class="bold">0</span>特别关注</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-footer box">
				<a href="#"><span id="notice_count">0</span>条未读提醒</a>
			</div>
		</c:if> 
        </div> 
       </div>
       </div>
	<div
		style="margin-top: 80px; background-color: rgba(0, 0, 0, 0.8); height: 100px; color: darkgray; width: 100%">
		<div
			style="width: 400px; padding-top: 35px; padding-left: 40px; padding-right: 40px; margin: auto;">
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
</html>