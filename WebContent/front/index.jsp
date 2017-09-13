<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台首页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/zzsc-demo.css">
<link href="${pageContext.request.contextPath}/static/css/index.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/pages/header.jsp" />
	<div style="width: 100%;">
		<div class="container user-info">
			<div class="row">
				<div class="col-xs-12">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="../img/2.jpg" alt="...">
								<div class="carousel-caption"></div>
							</div>
							<div class="item">
								<img src="../img/1.jpg" alt="...">
								<div class="carousel-caption"></div>
							</div>
							<div class="item">
								<img src="../img/2.jpg" alt="...">
								<div class="carousel-caption"></div>
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
			
			<div class="container" style="margin-top: 30px;">
				<div class="row">
					<div class="col-md-9">
						<ul class="list-group">
							<div class="list-group-item active">
								论坛新帖
								<a href="" style="float: right; color: white">更多>></a>
							</div>							
							<a href="" class="list-group-item">
								<h4 class="list-group-item-heading">[]</h4> <span class="badge">新</span>
								<p class="text-right" style="float: right; margin-right: 20px">浏览量:&nbsp;评论量:&nbsp;发表日期:</p>
							</a>
						</ul>
					</div>
					
					
					
					<div class="col-md-3">
						<ul class="list-group">
							<div class="list-group-item active">论坛公告</div>
							<a href="" class="list-group-item">XXXXXXXXXXXXXX</a>
							<a href="" class="list-group-item">XXXXXXXXXXXXXX</a>
							<a href="" class="list-group-item">XXXXXXXXXXXXXX</a>
							<a href="" class="list-group-item">XXXXXXXXXXXXXX</a>
							
						</ul>
						<a href=""><button type="button" class="btn btn-primary"
								style="width: 200px; height: 50px; margin-left: 30px">我要发帖</button></a>
					</div>
					<div class="row">
						<div class="col-md-9" style="margin-left: 15px">
							<ul class="list-group">
								<div class="list-group-item active">
									精华帖 <a href="" style="float: right; color: white">更多>></a>
								</div>
								<a href="" class="list-group-item">
									<h4 class="list-group-item-heading">[]</h4> <span class="badge">热</span>
									<p class="text-right" style="float: right; margin-right: 20px">浏览量:&nbsp;评论量:&nbsp;发表日期:</p>
								</a>
							</ul>
						</div>
					</div>
					<h3 style="margin-top: 20px; margin-left: 15px">板块分类</h3>
					<hr />
					<div class="container">
						<div class="row">
							<div class="col-md-3 col-sm-12">
								<a href="">
									<div class="main-forum">
										<h3></h3>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
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