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
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%
}

#bg {
	width:100%;
	height:250px;
	background: url("resources/images/persional/tuceng32.png")  no-repeat;
	overflow:hidden;
	background-size:100% 100%;
	background-color: #DFDFDF;
}

.row {
	margin-right: 0px;
	margin-left: 0px;
}
</style>
</head>

<body>
<div id="bg" class="row">
	<div class="row">
		<div class="col-xs-12" style="margin-top: 40px; text-align: center;">
			<img alt="" src="${user.headimg }" style="border-radius: 50%; border: 1px solid #DDD;" width="100px;" height="100px;" onclick="upfile()">
			<form action="<%=basePath%>pages/upfile" id="upfileform">
				<input type="file" id="upfile" name="upfile" style="display: none">
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12" style="margin-top: 20px; text-align: center; font-size: 20px;margin-bottom: 50px;color: #FFF;">
			${user.name } <span class="glyphicon glyphicon-pencil" onclick="javascript:window.location.href='pages/pinfo'"></span>
		</div>
	</div>
</div>
	
	<div class="row" style="margin-top: 0px;height: 70px; background-color: #DFDFDF;">
	</div>
	<div class="row" style="margin-top: -120px;">
		<div class="col-xs-6" style=" " onclick="javascript:window.location.href='pages/collect'">
			<div  style="background:url(<%=basePath%>/resources/images/sysimg/xing2@3x.png);     border: 1px solid #Dfdfdf; background-color: white; padding-top: 20px; padding-bottom: 20px" class="text-center"
			 onclick="javascript:window.location.href='pages/collect'"
			>
				<img alt="" src="resources/images/persional/xing1.png" style="width: 30px; height: 30px; margin-bottom: 5px"> <br> <span><spring:message code="mycollection"></spring:message></span>
			</div>
		</div>
		<div class="col-xs-6"  onclick="javascript:window.location.href='pages/pinfo'">
			<div style="background:url(<%=basePath%>/resources/images/sysimg/data2@2x.png);    border: 1px solid #Dfdfdf; background-color: white; padding-top: 20px; padding-bottom: 20px" class="text-center" onclick="javascript:window.location.href='pages/pinfo'">
				<img alt="" src="resources/images/persional/data1.png" style="width: 30px; height: 30px; margin-bottom: 5px"> <br> <span><spring:message code="myinformation"></spring:message></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12" style="height: 60px; background-color: white; margin-top: 10px; margin-bottom: 1px; line-height: 60px; position: relative;">
			<img name="syImg" src="<%=basePath%>/resources/images/sysimg/xinxi@2x.png" style="height: 40%; max-width: 50px;"> <span style="font-size: 18px; position: absolute; left: 65px;"><spring:message code="customerservice"></spring:message></span> <img name="syImg" src="<%=basePath%>/resources/images/sysimg/返回拷贝7@3x.png" style="height: 40%; right: 10px; top: 18px; position: absolute;">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12" style="height: 60px; background-color: white; line-height: 60px; position: relative;">
			<img name="syImg" src="<%=basePath%>/resources/images/sysimg/hezuo@2x.png" style="height: 40%; max-width: 50px;"> <span style="font-size: 18px; position: absolute; left: 65px;"><spring:message code="businesscooperation"></spring:message></span> <img name="syImg" src="<%=basePath%>/resources/images/sysimg/返回拷贝7@3x.png" style="height: 40%; right: 10px; top: 18px; position: absolute;">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12" style="height: 60px; position: fixed; bottom: 0; line-height: 25px; font-size: 18px; width: 100%; background-color: #FFFFFF; padding: 5px; box-shadow: 0 0 2px #661900">
			<div class="col-xs-4" style="text-align: center;" onclick="javascript:window.location.href='pages/index'">
				<img name="syImg" src="<%=basePath%>/resources/images/sysimg/shouye1@2x.png" style="width: 25px; height: 25px;"><br> <span style="font-size: 16px;"><spring:message code="homepage"></spring:message></span>
			</div>
			<div class="col-xs-4" style="text-align: center;" onclick="javascript:window.location.href='pages/article'">
				<img src="<%=basePath%>/resources/images/sysimg/资讯-点击@2x.png" style="width: 25px; height: 25px;"><br> <span style="font-size: 16px;"><spring:message code="information"></spring:message></span>
			</div>
			<div class="col-xs-4" style="text-align: center;" onclick="javascript:window.location.href='pages/personal'">
				<img src="<%=basePath%>/resources/images/sysimg/my123.png" style="width: 25px; height: 25px;"><br> <span style="font-size: 16px; color:#e01515"><spring:message code="my"></spring:message></span>
			</div>
		</div>
	</div>





	<%-- --%>
	<script type="text/javascript">
		function upfile() {
			$("#upfile")[0].click();
		}
		$("#upfile")
				.change(
						function() {
							$("#upfileform")
									.ajaxSubmit(
											{
												type : "POST",
												dataType : "text",
												data : $("#upfileform")
														.serialize(),
												url : "pages/upfile",
												contentType : "application/x-www-form-urlencoded; charset=utf-8",
												cache : false,
												success : function(data) {
													if (data == "success") {
														window.location
																.reload();
													}
												}
											});
						});
	</script>
</body>
</html>
