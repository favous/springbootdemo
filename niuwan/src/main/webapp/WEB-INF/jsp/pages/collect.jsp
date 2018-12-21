<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>gyumaru</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Fonts -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
<style type="text/css">
li {
	list-style: none;
}

i {
	font-style: normal;
}

a {
	color: #393939;
	text-decoration: none;
}

.list {
	overflow: hidden;
	border-top: 1px solid #ddd;
}

.list li {
	overflow: hidden;
	width: 120%;
	border-bottom: 1px solid #ddd;
}

.list li a {
	display: block;
	-webkit-transition: all 0.3s linear;
	transition: all 0.3s linear;
}

.list li i {
	float: right;
	width: 20%;
	text-align: center;
	background: #E2421B;
	color: #fff;
}

.swipeleft {
	transform: translateX(-20%);
	-webkit-transform: translateX(-20%);
}

#myTab .active {
	border-bottom: 2px solid #dc5858;
}
</style>
</head>

<body style="background-color: #efefef;">
	<div class="col-xs-12"
		style="height: 50px; color: #fff; fixed; top: 0; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900">
		<div class="col-xs-2"
			style="text-align: left; padding-left: 0px; font-size: 23px;">
			<img height="20px"
				src="<%=basePath%>/resources/images/sysimg/fanhui.png"
				onclick="javascript:window.location.href ='pages/personal'">
		</div>
		<div class="col-xs-8"
			style="text-align: center; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
			<spring:message code="mycollection"></spring:message></div>
	</div>
	<ul id="myTab" class="nav nav-tabs" style="background-color: #FFF;">
		<li style="width: 33.33%; text-align: center;" class="active"><a
			href="#h5" style="color: #000;" data-toggle="tab"><spring:message code="H5game"></spring:message></a></li>
		<li style="width: 33.33%; text-align: center;"><a href="#khd"
			style="color: #000;" data-toggle="tab"><spring:message code="clientgames"></spring:message></a></li>
		<li style="width: 33.33%; text-align: center;"><a href="#zx"
			style="color: #000;" data-toggle="tab"><spring:message code="information"></spring:message></a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="h5">
			<div class="list" style="margin: 10px; background-color: #FFF;">
				<ul
					style="padding: 0px; font-size: 14px; line-height: 50px; margin-bottom: 0px;">
					<c:forEach items="${collects }" var="collects" varStatus="status">
						<c:if test="${collects.type==2 }">
							<li style="height: 50px;" id="li${status.index}"><a
								rel="external nofollow"
								style="height: 50px; padding-left: 10px; text-decoration: none; color: #000; margin-right: -20px;"
								rel="external nofollow" rel="external nofollow">
									<div style="height: 50px; width: 50%; float: left; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"
										onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'">${collects.title }</div>
									<i
									onclick="dz(${collects.type },${collects.modeId },${status.index})"
									style="height: 50px; float: right;">取消收藏</i> <span
									onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'"
									style="float: right; margin-right: 20px; height: 50px;">${collects.time }</span>
							</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="tab-pane fade" id="khd">
			<div class="list" style="margin: 10px; background-color: #FFF;">
				<ul
					style="padding: 0px; font-size: 14px; line-height: 50px; margin-bottom: 0px;">
					<c:forEach items="${collects }" var="collects" varStatus="status">
						<c:if test="${collects.type==3 }">
							<li style="height: 50px;" id="li${status.index}"><a
								rel="external nofollow"
								style="height: 50px; padding-left: 10px; text-decoration: none; color: #000; margin-right: -20px;"
								rel="external nofollow" rel="external nofollow">
									<div
										style="height: 50px; width: 50%; float: left; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"
										onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'">${collects.title }</div>
									<i
									onclick="dz(${collects.type },${collects.modeId },${status.index})"
									style="height: 50px; float: right;">取消收藏</i> <span
									onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'"
									style="float: right; margin-right: 20px; height: 50px;">${collects.time }</span>
							</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="tab-pane fade" id="zx">
			<div class="list" style="margin: 10px; background-color: #FFF;">
				<ul
					style="padding: 0px; font-size: 14px; line-height: 50px; margin-bottom: 0px;">
					<c:forEach items="${collects }" var="collects" varStatus="status">
						<c:if test="${collects.type==1 }">
							<li style="height: 50px;" id="li${status.index}"><a
								rel="external nofollow"
								style="height: 50px; padding-left: 10px; text-decoration: none; color: #000; margin-right: -20px;"
								rel="external nofollow" rel="external nofollow">
									<div
										style="height: 50px; width: 50%; float: left; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"
										onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'">${collects.title }</div>
									<i
									onclick="dz(${collects.type },${collects.modeId },${status.index})"
									style="height: 50px; float: right;">取消收藏</i> <span
									onclick="<c:if test="${collects.type==1 }">javascript:window.location.href='pages/articledtl?id=</c:if><c:if test="${collects.type!=1 }">javascript:window.location.href='pages/gamedtl?id=</c:if>${collects.modeId }'"
									style="float: right; margin-right: 20px; height: 50px;">${collects.time }</span>
							</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('.picimg').css({'height':($(window).width()-42)/16*9});
	//侧滑显示删除按钮
	var expansion = null; //是否存在展开的list
	var container = document.querySelectorAll('.list li a');
	for(var i = 0; i < container.length; i++){ 
	 var x, y, X, Y, swipeX, swipeY;
	 container[i].addEventListener('touchstart', function(event) {
	  x = event.changedTouches[0].pageX;
	  y = event.changedTouches[0].pageY;
	  swipeX = true;
	  swipeY = true ;
	  if(expansion){ //判断是否展开，如果展开则收起
	   expansion.className = "";
	  }  
	 });
	 container[i].addEventListener('touchmove', function(event){
	   
	  X = event.changedTouches[0].pageX;
	  Y = event.changedTouches[0].pageY;  
	  // 左右滑动
	  if(swipeX && Math.abs(X - x) - Math.abs(Y - y) > 0){
	   // 阻止事件冒泡
	   event.stopPropagation();
	   if(X - x > 10){ //右滑
	    event.preventDefault();
	    this.className = ""; //右滑收起
	   }
	   if(x - X > 10){ //左滑
	    event.preventDefault();
	    this.className = "swipeleft"; //左滑展开
	    expansion = this;
	   }
	   swipeY = false;
	  }
	  // 上下滑动
	  if(swipeY && Math.abs(X - x) - Math.abs(Y - y) < 0) {
	   swipeX = false;
	  }  
	 });
	}
	function dz(id,modeid,obj) {
		var type=1
		if(id!=1){
			type=2	
		}
		$.ajax({
			type:"post",
			dataType:"text",
			data:{
				"id":modeid,
				"type":type
			},
			url:"collect/chickcollect",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data=="success1"){
					$("#li"+obj).hide();
				}else if(data=="success2"){
					$("#li"+obj).hide();
				}else{
					alert("<spring:message code='modificationfailed'></spring:message>");
				}
			}
		});
	}
	
	
	function fh() {
		
	}
	</script>
</body>
</html>
