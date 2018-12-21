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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/font-awesome.min.css">
<script type="text/javascript"
	src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>

</head>

<body>
	<form id="signinForm" class="form-horizontal" method="post" autocomplete="off">
		<div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			   <!--  <label for="name">1.メールアドレス</label> -->
			    <input type="email"  style="border:0;border-bottom:1 solid black;background:;" class="form-control" id="account" name="account" placeholder="请输入邮箱">
			  </div>
			  <div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			 <!--  <input style="border:0;border-bottom:1 solid black;background:;"> -->
			  <!--   <label for="name">2.密码</label> -->
			  <img src="<%=basePath%>/resources/images/sysimg/密码@2x.png" style="float: left;width: 5%;padding-top: 8px">
			    <input  style="width:92%;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-width:1px;border-bottom-color:black;padding-top: 7px;margin-left: 3%" type="password"  id="pwd" name="pwd" placeholder="请输入密码">
			
			  </div>
			  <div class="form-group" style="margin-left: 15px;margin-right: 15px;">
			  	<a href="<%=basePath%>pages/signup">没有账户，前往注册</a>
			  </div>
			  <div class="col-sm-offset-2 col-sm-10" style="margin: 10px;">
      <button type="button" class="btn btn-primary btn-lg btn-block" style="color: #337ab7;background-color: #FFF;border: 1px solid #337ab7;" onclick="signin()">登录</button>
    </div>
	
	
	</form>
	<script type="text/javascript">
		//登录
		function signin() {
			var account=$.trim($("#account").val());
			var pwd=$.trim($("#pwd").val());
			if(account==""){
				alert("请填写邮箱");
				return false;
			}
			if(pwd==""){
				alert("请填写密码");
				return false;
			}
			$.ajax({
				type:"post",
				dataType:"text",
				data:$("#signinForm").serialize(),
				url:"<%=basePath%>user/signin",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				cache : false,
				success:function(data){
					if(data=="success"){
						window.location.href="pages/index";
					}else if(data=="error"){
						alert("登录失败！邮箱或密码输入错误");
					}else if(data=="error2"){
						alert("登录失败！");
					}
				},
				error:function(){
					alert("登录失败！");
				}
			});
			
		}
	</script>
</body>
</html>
