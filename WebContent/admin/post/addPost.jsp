<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>操作帖子</title>
<meta http-equiv="Content-Type" content="text\html;charset=utf-8">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/main.css" />
<script
	src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/fatie.css">
<link
	href="${pageContext.request.contextPath}/static/ueditor/themes/default/css/ueditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<style>  
.col-center-block {  
    height: 40px; 
    width: 200px; 
    float: none;  
    display: block;  
    margin-left: auto;  
    margin-right: auto;
 }  
</style> 
<script type="text/javascript">
	function addType(obj){
		$(obj).parent().nextAll().remove();
		var id = obj.value;
		var typeChild = document.getElementById("typeChild");
		//只有在是父标签的情况下才会进行异步请求获取数据
		if(id > 0 && typeChild == null){ 
			//ajax请求
			$.post("postServlet",{
				method: "getTypeChild",
				id: id
			},
				function(data){
					if(data != null && data.length > 0){
						var content = "<div style='display: inline;' ><select name='tychid' onchange='addType(this)' style='width: 100px' id='typeChild'><option value='0'>请选择子版区</option>";
						for(var tych in data){
							content += "<option value='" + data[tych].tychid + "'>" + data[tych].name + "</option>";
						}
						content += "</select></div>";
						$("#types").append(content);
					}
				},"json");
		}
	}
</script>
</head>
<body>
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font-1"></i><a href="../mainIndex.jsp">首页</a> <span
				class="crumb-step">&gt;</span><span class="crumb-name">帖子信息操作</span>
		</div>
	</div>
	<form action="${pageContext.request.contextPath }/admin/post/postServlet?method=addPost&updateId=${updateBean.postid}" method="post">			
		<div id="content" class="center">
			<div class="title">
				<div class="title_tab"><p><b>发帖</b></p></div>
				
				<div class="theme">				
						<div  style="display: inline;" id="types">	
							<span>选择版区：</span>						
							<div style="display: inline;">								
								<select id="type" name="parentId" onchange="addType(this)" style="width: 100px" >
									<option value="0">请选择版区</option>
									<c:forEach items="${typeList}" var="item">
										<option value="${item.typeid}">${item.name}</option>
									</c:forEach>
							   	</select>
						 	</div>
						 </div>	
				   <div  style="display: inline;">				   				   		
						<span>标题：</span> 
						<input id="postTitle" type="text" name="title" value="${updateBean.title}"> 
						<span id="counter">还可以输入40个字</span>
					</div>
				</div>
			</div>
			<textarea name="content" id="myEditor" style="width:1000px;height:360px;">${updateBean.content }</textarea>			       
		</div>
		<div class="form-group"></div>
		<div class="col-center-block">
			<button type="submit" id="sub" class="btn btn-primary btn-block">提交</button>
		</div>
	</form>
	<c:if test="${param.status.equals('1')}">
			<div class="alert alert-danger" role="alert">添加成功</div>
	</c:if>
														
	<script type="text/javascript">
			var editor = new UE.ui.Editor();
			editor.render("myEditor");
	</script>	
			
	
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/static/js/post.js"
		charset="utf-8"></script>


</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
</html>