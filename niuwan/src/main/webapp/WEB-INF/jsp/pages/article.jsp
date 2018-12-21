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
</head>

<body style="background-color: #efefef;">
	<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12"
		style="height: 50px; position: fixed; top: 0; color: #fff; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900">
		<spring:message code="information"></spring:message></div>
	<div style="padding: 10px;">
		<c:forEach items="${articles }" var="article">
			<div
				style="border-radius: 10px; border: 1px solid #DDD; font-size: 16px; padding: 0px; margin: 10px 0px; background-color: #FFF; padding: 10px;"
				class="col-xs-12">
				<img src="${article.articleCoverPic }" class="picimg" alt=""
					style="width: 100%; border-radius: 10px;"
					onclick="javascript:window.location.href='pages/articledtl?id=${article.id }'" />
				<div class="col-xs-12"
					style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; padding-bottom: 10px; padding: 0px;">
					<div class="col-xs-7"
						style="overflow: hidden; padding-left: 0px; text-overflow: ellipsis; white-space: nowrap; padding-right: 0px;"
						onclick="javascript:window.location.href='pages/articledtl?id=${article.id }'">
						<span style="font-size: 20px">${article.articleTitle }</span>
					</div>
					<div class="col-xs-5" style="padding: 0px; text-align: right;">
						<span
							style="text-align: right; font-size: 14px; padding-top: 2px; color: #adadad">${article.publishTime }</span>
					</div>
				</div>
				<p style="font-size: 14px; color: #adadad"
					onclick="javascript:window.location.href='pages/articledtl?id=${article.id }'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${article.articleAbstract }</p>
				<div
					style="overflow: hidden; width: 100%; text-overflow: ellipsis; white-space: nowrap; padding: 0px; float: left">
					<%-- <div 
						style="width:79%; hidden; text-overflow: ellipsis; white-space: nowrap; height: 31px;"
						onclick="javascript:window.location.href='pages/articledtl?id=${article.id }'">
					</div> --%>
					<div
						style="padding: 0px; text-align: left; width: 18%; float: right;">

						<span
							style="font-size: 20px;<c:if test="${article.collertMode=='1' }">color: #FFCC00;</c:if>"
							class="glyphicon <c:if test="${article.collertMode=='0' }">glyphicon-star-empty</c:if><c:if test="${article.collertMode=='1' }">glyphicon-star</c:if>"
							onclick="dz(${article.id},this)"></span><span
							style="font-size: 17px; float: right;"><spring:message code="collection"></spring:message></span>
					</div>

				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12"
		style="height: 60px; position: fixed; bottom: 0; line-height: 25px; font-size: 18px; width: 100%; background-color: #FFFFFF; padding: 5px; box-shadow: 0 0 2px #661900">
		<div class="col-xs-4" style="text-align: center;"
			onclick="javascript:window.location.href='pages/index'">
			<img name="syImg"
				src="<%=basePath%>/resources/images/sysimg/shouye1@2x.png"
				style="width: 25px; height: 25px;"><br> <span
				style="font-size: 16px; "><spring:message code="homepage"></spring:message></span>
		</div>
		<div class="col-xs-4" style="text-align: center;"
			onclick="javascript:window.location.href='pages/article'">
			<img src="<%=basePath%>/resources/images/sysimg/zixun4.png"
				style="width: 25px; height: 25px;"><br> <span
				style="font-size: 16px;color: #e01515"><spring:message code="information"></spring:message></span>
		</div>
		<div class="col-xs-4" style="text-align: center;"
			onclick="javascript:window.location.href='pages/personal'">
			<img src="<%=basePath%>/resources/images/sysimg/我的(1)@2x.png"
				style="width: 25px; height: 25px;"><br> <span
				style="font-size: 16px"><spring:message code="my"></spring:message></span>
		</div>
	</div>
	<script type="text/javascript">
	$('.picimg').css({'height':$(".picimg").width()/16*9});
	function dz(id,obj) {
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
					$(obj).css({ color: "#FFCC00"});
					$(obj).removeClass("glyphicon-star-empty");
					$(obj).addClass("glyphicon-star");
				}else if(data=="success2"){
					var tmp = obj;
					$(obj).css({ color: "rgb(102,102,102)"});
					$(obj).removeClass("glyphicon-star");
					$(obj).addClass("glyphicon-star-empty");
				}else{
					alert("<spring:message code='modificationfailed'></spring:message>");
				}
			}
		});
	}
	</script>
</body>
</html>
