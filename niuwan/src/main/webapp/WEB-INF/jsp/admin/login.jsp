<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>gyumaru后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
 	<!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
 	<!-- CSS Libs -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/checkbox3.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/select2.min.css">
    <!-- CSS App -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/themes/flat-blue.css">
  </head>
  
  <SCRIPT language=javascript type=text/javascript>
	
	function loadimage(){
		document.getElementById("randImage").src = "<%=basePath%>/image.jsp?"+Math.random();
	}
</SCRIPT>

  <body class="flat-blue login-page">
    <div class="container">
        <div class="login-box">
            <div>
                <div class="login-form row">
                    <div class="col-sm-12 text-center login-header">
                        <i class="login-logo fa fa-connectdevelop fa-5x"></i>
                        <h4 class="login-title">gyumaru后台管理系统</h4>
                    </div>
                    <div class="col-sm-12">
                        <div class="login-body">
                            <div class="progress hidden" id="login-progress">
                                <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                    Log In...
                                </div>
                            </div>
                            <form role="form" action="<%=basePath %>admin/login" method="post" onsubmit="return checkForm();">
                                <div class="control" id="usernameDiv">
                               	 	<label class="control-label hidden" for="username" id="usernameLabel">账号未填写！</label>
                                    <input type="text" class="form-control" name="card" id="username" value="admin" placeholder="登录账号" onkeyup="usernameKeyUp()"/>
                                </div>
                                <c:if test="${key!=null}">
                               		<div class="control has-error" id="passwordDiv">
                               			<label class="control-label" for="password" id="passwordLabel" >密码错误！</label>
	                                    <input type="password" class="form-control" name="pwd" id="password"  autofocus placeholder="登录密码" onkeyup="passwordKeyUp()"/>
                               		</div>
                               	</c:if>
                               	<c:if test="${key==null}">
	                                <div class="control" id="passwordDiv">
	                               		<label class="control-label hidden"  for="password" id="passwordLabel">密码未填写！</label>
	                                    <input type="password" class="form-control" name="pwd" id="password" value="123456" placeholder="登录密码" onkeyup="passwordKeyUp()"/>
	                                </div>
	                            </c:if>
                               	<c:if test="${code==null}">
	                                <div class="control" id="codeDiv">
	                               		<label class="control-label hidden"  for="code" id="codeLabel">验证码未填写！</label>
	                                    <input  type=text value="${imageCode}" name="imageCode" id="imageCode" placeholder="验证码" onkeyup="codeKeyUp()"> <img
								onclick="javascript:loadimage();" title="换一张试试" name="randImage"id="randImage" src="<%=basePath %>/image.jsp" height="20px" border="1">
	                                </div>
	                            </c:if>
	                            <c:if test="${code!=null}">
                               		<div class="control has-error" id="codeDiv">
                               			<label class="control-label" for="code" id="codeLabel" >验证码错误！</label>
	                                    <input  type=text value="${imageCode}" name="imageCode" id="imageCode" placeholder="验证码" onkeyup="codeKeyUp()"> <img
								onclick="javascript:loadimage();" title="换一张试试" name="randImage"id="randImage" src="<%=basePath %>/image.jsp" height="20px" border="1">
                               		</div>
                               	</c:if>
                                <div class="login-button text-center">
                                    <input type="submit" class="btn btn-primary" value="登录">
                                </div>
                            </form>
                        </div>
                        <%-- <div class="login-footer">
                            <span class="text-right"><a href="#" class="color-white">Forgot password?</a></span>
                        </div> --%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    
    <!-- Javascript Libs -->
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/Chart.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap-switch.min.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.matchHeight-min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/select2.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/ace.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/mode-html.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/theme-github.js"></script>
    <!-- Javascript -->
    <script type="text/javascript" src="<%=basePath%>resources/pc/js/app.js"></script>
     <script type="text/javascript">
	function checkForm(){
		var checkBoolean = true;//是否通过验证
		var username = document.getElementById("username").value;
		var imageCode = document.getElementById("imageCode").value;
		if(username == null||username == ""){
			document.getElementById("usernameDiv").classList.add("has-error");
			document.getElementById("usernameLabel").classList.remove("hidden");
			document.getElementById("usernameLabel").classList.add("show");
			checkBoolean=false;
		}
		if(imageCode == null||imageCode == ""){
			document.getElementById("codeDiv").classList.add("has-error");
			document.getElementById("codeLabel").classList.remove("hidden");
			document.getElementById("codeLabel").classList.add("show");
			checkBoolean=false;
		}
		var password = document.getElementById("password").value;
		if(password == null||password == ""){
			document.getElementById("passwordDiv").classList.add("has-error");
			document.getElementById("passwordLabel").classList.remove("hidden");
			document.getElementById("passwordLabel").classList.add("show");
			checkBoolean=false;
		}
		return checkBoolean;
	}
	function usernameKeyUp(){
		if(document.getElementById("usernameDiv").classList.contains("has-error") == true){
			document.getElementById("usernameDiv").classList.remove("has-error");
			document.getElementById("usernameLabel").classList.remove("show");
			document.getElementById("usernameLabel").classList.add("hidden");
		}
	}
	function passwordKeyUp(){
		if(document.getElementById("passwordDiv").classList.contains("has-error") == true){
			document.getElementById("passwordDiv").classList.remove("has-error");
			document.getElementById("passwordLabel").classList.remove("show");
			document.getElementById("passwordLabel").classList.add("hidden");
		}
	}
	function codeKeyUp(){
		if(document.getElementById("codeDiv").classList.contains("has-error") == true){
			document.getElementById("codeDiv").classList.remove("has-error");
			document.getElementById("codeLabel").classList.remove("show");
			document.getElementById("codeLabel").classList.add("hidden");
		}
	}
	</script>
  </body>
</html>
