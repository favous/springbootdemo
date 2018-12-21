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
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
	rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/checkbox3.min.css">
<link
	href="http://cdn.bootcss.com/datatables/1.10.11/css/dataTables.bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="http://cdn.bootcss.com/datatables/1.10.11/css/jquery.dataTables.min.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/waitMe.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/themes/flat-blue.css">
	<!-- Javascript Libs -->
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/Chart.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/bootstrap-switch.min.js"></script>

	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/jquery.matchHeight-min.js"></script>
	<script
		src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
	<script
		src="http://cdn.bootcss.com/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/select2.full.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/ace/ace.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/ace/mode-html.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/ace/theme-github.js"></script>
	<!-- Javascript -->
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/app.js"></script>
</head>
<body style="background-color: #efefef;">
<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12" style="height: 50px;color:#fff; position: fixed;top: 0; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900">
	<div class="col-xs-2" style="text-align: left;font-size: 23px;padding-left: 0px;">
			<img height="20px" src="<%=basePath%>/resources/images/sysimg/fanhui.png"onclick="javascript:window.location.href =document.referrer;">
		</div>
		<div class="col-xs-8" style="text-align: center;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">
			${game.title }</div>
	</div>
	
	<div class="col-xs-12">
		<img src="<%=basePath%>${game.picture }" style="width: 100%" class="picimg">
	</div>
	<div class="col-xs-12" id="gd" style="background-color: #FFF;margin: 10px 0px;padding: 0px;">
		<div class="col-xs-12" style="font-size: 16px;">
		<img src="<%=basePath%>${game.icon }" style="width: 33.33%;float: left;">
			<span style="font-size: 16px;">${game.title }</span> <p style="font-size: 14px;" id="">${game.jdjs1 }
			<span style="float: right;" onclick="gd()">[<spring:message code="more"></spring:message>]</span></p>
		</div>
		<div class="col-xs-12" style="font-size: 16px;margin-top: -20px;text-align: right;">
			<span style="font-size: 16px;"></span> <p style="font-size: 14px;" id=""></p>
		</div>
	</div>
	<div class="col-xs-12" id="sq" style="background-color: #FFF;margin: 10px 0px;padding: 0px;display: none;">
			
		<div class="col-xs-12" style="font-size: 16px;">
		<img src="<%=basePath%>${game.icon }" style="width: 33.33%;float: left;">
			<span style="font-size: 16px;">${game.title }</span> <p style="font-size: 14px;" id="">${game.jdjs }
			<span style="float: right;" onclick="sq()">[<spring:message code="takeback"></spring:message>]</span></p>
		</div>
		<div class="col-xs-12" style="font-size: 16px;margin-top: -20px;text-align: right;">
			<span style="font-size: 16px;"></span> <p style="font-size: 14px;" id=""></p>
		</div>
	</div>
	<div class="col-xs-12" style="background-color: #FFF;padding: 0px;font-size: 16px;padding-left: 15px;line-height: 25px;">
		<spring:message code="information1"></spring:message>
	</div>
	<div class="col-xs-12" style="background-color: #FFF;margin: 0px 0px 2px 0px;padding: 0px;font-size: 14px;padding-left: 15px;padding-right: 15px;color: rgb(102,102,102);line-height: 20px;">
		<c:if test="${game.dj==1 }">
			<div class="col-xs-4">
				<spring:message code="edition"></spring:message>
			</div>
			<div class="col-xs-8" style="text-align: right;">
				${game.size }
			</div>
		</c:if>
		<c:if test="${game.dj==2 }">
			<div class="col-xs-4">
				<spring:message code="size"></spring:message>
			</div>
			<div class="col-xs-8" style="text-align: right;">
				${game.size }
			</div>
		</c:if>
		
	</div>
	<div class="col-xs-12" style="background-color: #FFF;margin: 0px 0px 2px 0px;padding: 0px;font-size: 14px;padding-left: 15px;padding-right: 15px;color: rgb(102,102,102);line-height: 20px;">
		<div class="col-xs-4">
			<spring:message code="category"></spring:message>
		</div>
		<div class="col-xs-8" style="text-align: right;">
			<c:if test="${game.dj==1 }"><spring:message code="H5game"></spring:message>:</c:if>
			<c:if test="${game.dj==2 }"><spring:message code="clientgames"></spring:message>:</c:if>
			${game.type }
		</div>
	</div>
	<div class="col-xs-12" style="background-color: #FFF;margin: 0px 0px 2px 0px;padding: 0px;font-size: 14px;padding-left: 15px;padding-right: 15px;color: rgb(102,102,102);line-height: 20px;">
		<div class="col-xs-4">
			<spring:message code="peopleplaying"></spring:message>
		</div>
		<div class="col-xs-8" style="text-align: right;">
			${game.times } 
		</div>
	</div>
	<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12" style="height: 60px;position: fixed;bottom: 0;line-height: 60px;font-size: 20px;background-color: #FFF;box-shadow: 0 0 2px #661900;padding: 0px;">
		<div class="col-xs-4" style="height: 60px;text-align: center;color: rgb(102,102,102);" onclick="dz(${game.id })">
			<span class="glyphicon <c:if test="${colourMode=='0' }">glyphicon-star-empty</c:if><c:if test="${colourMode=='1' }">glyphicon-star</c:if>" id="colourMode"  <c:if test="${colourMode=='1' }">style="color: #FFCC00"</c:if>></span>收藏
		</div>
		<div class="col-xs-8" style="height: 60px;text-align: center;background-color: #dc5858;color: #FFF;" onclick="javascript:window.location.href='${game.file }'">
			<c:if test="${game.dj==1 }"><span class="glyphicon glyphicon-play-circle"></span><spring:message code="goplay"></spring:message></c:if>
			<c:if test="${game.dj==2 }"><span class="glyphicon glyphicon-download-alt"></span><spring:message code="download"></spring:message></c:if>
		</div>
	</div>

	<!-- /用户审核模态框 -->
	<!-- /模态框区域 -->
	
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/waitMe.js"></script>
		<script type="text/javascript">
		$('.picimg').css({'height':$(".picimg").width()/16*9});
			function qw(a) {
				window.location.href = a;
			}
			
			
			function sq() {
				$("#sq").hide();
				$("#gd").show();
			}
			function gd() {
				$("#gd").hide();
				$("#sq").show();
			}
			function dz(id) {
				$.ajax({
					type:"post",
					dataType:"text",
					data:{
						"id":id,
						"type":2
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
							$("#colourMode").css({ color: "rgb(102,102,102)"});
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
