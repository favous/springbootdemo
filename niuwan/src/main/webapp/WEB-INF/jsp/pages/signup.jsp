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
<script
	src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/font-awesome.min.css">
</head>

<body>
	<form id="addForm" class="form-horizontal" action="#" method="post" autocomplete="on">
			<div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			    <label for="name">1.メールアドレス（アカウント回复用）</label>
			    <input type="email" class="form-control" id="account" name="account" placeholder="请输入邮箱">
			  </div>
			  <div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			    <label for="name">2.昵称</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="请输入昵称">
			  </div>
			  <div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			    <label for="name">3.密码</label>
			    <input type="password" class="form-control" id="pwd1" name="pwd" placeholder="请输入密码">
			  </div>
			  <div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			    <label for="name">4.再次输入密码</label>
			    <input type="password" class="form-control" id="pwd2" name="pwd2" placeholder="请再次输入密码">
			  </div>
			  <div class="form-group" style="margin-top: 20px;margin-left: 15px;margin-right: 15px;">
			    <label for="name">5.获取邮箱验证码</label>
			    </div>
			  <div class="form-group" style="margin-left: 15px;margin-right: 15px;">
			    <input type="text" class="form-control" style="width: 70%;float: left;" id="recode" name="recode" placeholder="请输入验证码">
			    <input type="button" style="width: 30%;float: left;color: #337ab7;background-color: #FFF;border: 1px solid #337ab7;" onclick="getReCodes()" class="testButton btn btn-default" value="获取" />
			  </div>
			  <div class="form-group" style="margin-left: 15px;margin-right: 15px;">
			  	<a href="pages/signin">已有账户，前往登录</a>
			  </div>	
			  <div class="col-sm-offset-2 col-sm-10" style="margin: 10px;">
      <input type="button" class="btn btn-primary btn-lg btn-block" 
      style="color: #337ab7;background-color: #FFF;border: 1px solid #337ab7;" onclick="signup()" value="注册"/>
  
    </div>
	
	
	</form>
	<script type="text/javascript">
	function getReCodes() {
		if($.trim($("#account").val()).indexOf("@")<0){
			alert("请输入正确的邮箱");
			return false;
		}
		if($.trim($("#account").val()).length>0){
			updateTimeLabel("10");
			$.ajax({
				type:"post",
				dataType:"text",
				url:"<%=basePath%>	user/getSignupCodes",
							data : {
								"account" : $.trim($("#account").val())
							},
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							cache : false,
							success : function(data) {
							if (data == "success") {
									alert("发送成功");
								} else {
									alert("发送失败");
								}
							},
							error : function() {
								alert("系统错误");
							}
						});
			} else {
				alert("邮箱不能为空");
			}
		}
		function updateTimeLabel(time) {
			var btn = $(".testButton");
			btn.attr({
				"disabled" : "disabled"
			});
			btn.fadeIn(1000);
			btn.val(time <= 0 ? "启用" : ("" + (time) + "s后启用"));
			time--;
			var hander = setInterval(function() {
				if (time <= 0) {
					clearInterval(hander);
					btn.val("获取");
					btn.removeAttr("disabled");
				} else {
					btn.val("" + (time--) + "s后启用");
				}
			}, 1000);
		}
		//注册
		function signup() {
			var account=$.trim($("#account").val());
			var name=$.trim($("#name").val());
			var pwd1=$.trim($("#pwd1").val());
			var pwd2=$.trim($("#pwd2").val());
			var recode=$.trim($("#recode").val());
			if(account==""){
				alert("请填写邮箱");
				return false;
			}
			if(name==""){
				alert("请填写昵称");
				return false;
			}
			if(pwd1==""){
				alert("请填写密码");
				return false;
			}
			if(recode==""){
				alert("请填写激活码");
				return false;
			}
			if(pwd1!=pwd2){
				alert("两次密码不一致");
				return false;
			}
			$.ajax({
				type:"post",
				dataType:"text",
				data:$("#addForm").serialize(),
				url:"<%=basePath%>user/signup",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				cache : false,
				success:function(data){
					if (data == "noRecodes") {
						alert("请获取正确的验证码");
					}else if (data == "errorRecodes") {
						alert("验证码错误");
					}else if (data == "sameAccount") {
						alert("账号已存在");
					}else if(data=="success"){
						alert("注册成功！");
						window.location.href="<%=basePath%>pages/index";
					}else if(data=="error"){
						alert("注册失败！");
					}
				},
				error:function(){
					alert("新增失败！");
				}
			});
			
		}
	</script>
</body>
</html>
