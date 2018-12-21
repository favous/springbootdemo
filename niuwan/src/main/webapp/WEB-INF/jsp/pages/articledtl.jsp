<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	#ad img{max-width: 100%;}
</style>
</head>

<body style="background-color: #efefef;">
	<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12" style="height: 50px;color:#fff; position: fixed;top: 0; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900">
		<div class="col-xs-2" style="text-align: left;padding-left: 0px;height:20px" >
			<img height="20px" src="<%=basePath%>/resources/images/sysimg/fanhui.png"onclick="javascript:window.location.href =document.referrer;">
		</div>
		<div class="col-xs-8" style="text-align: center;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">
			${article.articleTitle }</div>
	</div>
	<div style="width: 100%;padding: 10px;" class="col-xs-12">
	<div style="background-color: #FFF;" class="col-xs-12">
	<div style="width: 100%;padding: 10px;" class="col-xs-12">
	<img src="${article.articleCoverPic }" class="picimg" alt="" style="width: 100%;border-radius:10px;"/>
	</div>
	<div style="width: 100%;padding: 0px;line-height: 30px;" class="col-xs-12">
		<div class="col-xs-4" style="padding: 0px; font-size: 20px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">
			${article.articleTitle }
		</div>
		<div class="col-xs-5" style="text-align:left; padding: 0px;color: rgb(153,153,153);font-size: 14px;">
			${article.publishTime }
		</div>
		<div class="col-xs-3" onclick="dz(${article.id})" style="padding: 0px;font-size: 20px;">
			<span class="glyphicon <c:if test="${colourMode=='0' }">glyphicon-star-empty</c:if><c:if test="${colourMode=='1' }">glyphicon-star</c:if>" id="colourMode"  <c:if test="${colourMode=='1' }">style="color: #FFCC00"</c:if>></span>
		<span style="font-size: 16px">收藏</span>
		</div>
	</div>
	<div id="ad" style="padding: 0px;margin-top: 20px;font-size: 14px;" class="col-xs-12">
		${article.articleContent }
	</div>
	</div>
	</div>
	<script type="text/javascript">
	$('.picimg').css({'height':$(".picimg").width()/16*9});
	function dz(id) {
		$.ajax({
			type:"post",
			dataType:"text",
			data:{
				"id":id,
				"type":1
			},
			url:"collect/chickcollect",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data=="success1"){
					$("#colourMode").css({ color: "#FFCC00"});
					$("#colourMode").removeClass("glyphicon-star-empty");
					$("#colourMode").addClass("glyphicon-star");
				}else if(data=="success2"){
					$("#colourMode").css({ color: "rgb(153,153,153)"});
					$("#colourMode").removeClass("glyphicon-star");
					$("#colourMode").addClass("glyphicon-star-empty");
				}else{
					alert("<spring:message code='modificationfailed'></spring:message>");
				}
			}
		});
	}
	</script>
</body>
</html>
