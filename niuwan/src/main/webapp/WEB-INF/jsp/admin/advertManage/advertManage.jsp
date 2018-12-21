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

<title>gyumaru后台管理系统</title>
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
<!-- 编辑器css -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.css"
	rel="stylesheet">
</head>

<body class="flat-blue">
	<div class="app-container">
		<div class="row content-container">
			<!-- 顶部菜单 -->
			<nav class="navbar navbar-inverse navbar-fixed-top navbar-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle">
							<i class="fa fa-bars icon"></i>
						</button>
						<ol class="breadcrumb navbar-breadcrumb">
							<li>游戏管理</li>
							<li>顶部广播</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-times icon"></i>
						</button>
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${admin.card}<span class="caret"></span>
						</a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${admin.card}</h4>
										<div class="btn-group margin-bottom-2x" role="group">
											<button type="button" class="btn btn-default"
												onclick="modifyPwd()">
												<i class="fa fa-key"></i>修改密码
											</button>
											<button type="button" class="btn btn-default"
												onclick="javascript:location.href='<%=basePath%>admin/logout'">
												<i class="fa fa-sign-out"></i>登出
											</button>
										</div>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
			<!-- 左侧菜单 -->
			<div class="side-menu sidebar-inverse">
				<nav class="navbar navbar-default" role="navigation">
					<div class="side-menu-container">
						<div class="navbar-header">
							<a class="navbar-brand" href="<%=basePath%>admin/gotoAdminMain">
								<div class="icon fa fa-paper-plane"></div>
								<div class="title">gyumaru后台管理</div>
							</a>
							<button type="button"
								class="navbar-expand-toggle pull-right visible-xs">
								<i class="fa fa-times icon"></i>
							</button>
						</div>
						<!-- 一级菜单，无二级菜单 -->
						<ul class="nav navbar-nav">
							<c:forEach items="${menus}" var="menu1">
								<c:if test="${menu1.menus==null||fn:length(menu1.menus) == 0}">
									<li><a href="<%=basePath%>${menu1.menu_url}"> <span
											class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
									</a></li>
								</c:if>
								<!-- 有二级菜单 -->
								<c:if test="${menu1.menus!=null&&fn:length(menu1.menus) > 0}">
									<li class="panel panel-default dropdown" id="panel${menu1.id}">
										<c:if test="${menu1.menu_url==null||menu1.menu_url==''}">
											<a onclick="return false" data-toggle="collapse"
												href="#dropdown-element${menu1.id}"
												data-parent="#panel${menu1.id}"> <span
												class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
											</a>
										</c:if> <c:if test="${menu1.menu_url!=null&&menu1.menu_url!=''}">
											<a data-toggle="collapse"
												href="<%=basePath%>${menu1.menu_url}"> <span
												class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
											</a>
										</c:if> <!-- 二级菜单 -->
										<div id="dropdown-element${menu1.id}"
											class="panel-collapse collapse">
											<div class="panel-body">
												<ul class="nav navbar-nav">
													<c:forEach items="${menu1.menus}" var="menu2">
														<li><a href="<%=basePath %>${menu2.menu_url}">${menu2.name}</a>
														</li>
													</c:forEach>
												</ul>
											</div>
										</div>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</nav>
			</div>
			<!-- 主要内容区域 -->
			<div class="container-fluid">
				<div class="side-body">
					<div class="page-title">
						<span class="title">编辑顶部广告</span>

					</div>

					<div class="col-xs-12">
						<div class="card">
							<form role="form" class="form-horizontal" id="advert"
								method="post">
								<table class="table">
									<tbody>
										<tr>
											<td>游戏链接1</td>
											<td><input type="text" id="gameUrl1" name="gameUrl1"
												value="${advert1.gameUrl}"></td>
										</tr>
										<tr>
											<td>图片1</td>
											<td>
												<div style="float: left; width: 15%">                                                                                                                                           
													<input type="text" id="imgUrl1" name="imgUrl1"
														value="${advert1.imgUrl}" readonly="readonly">
												</div>

												<div id="mNewPic" style="float: left;">
													<label for="name" style="padding-top: 4px; float: left">上传图片
														(640x300)</label> <input type="file" name="a_imgUrl1"
														id="a_imgUrl1" multiple="multiple1" accept="image/*"
														onchange="muploadImg1()" style="margin-left: 60px">
												</div>
												<div style="float: left; width: 15%">
													<a href="${outImgPreFixUrl }${advert1.imgUrl}"
														id="imgrul" target="_blank">预览</a>
												</div> <input type="hidden" name="img1" id="img1" value="1">

											</td>
										</tr>
										<tr>
											<td>游戏链接2</td>
											<td><input type="text" id="gameUrl2" name="gameUrl2"
												value="${advert2.gameUrl}">
										</tr>
										<tr>
											<td>图片2</td>
											<td>
												<div style="float: left; width: 15%">
													<input type="text" id="imgUrl2" name="imgUrl1"
														value="${advert2.imgUrl}"readonly="readonly">
												</div>
												<div id="mNewPic" style="float: left;">
													<label for="name" style="padding-top: 4px; float: left">上传图片
														(640x300)</label> <input type="file" name="a_imgUrl2"
														id="a_imgUrl2" multiple="multiple2" accept="image/*"
														onchange="muploadImg2()" style="margin-left: 60px">
												</div> <input type="hidden" name="img2" id="img2" value="1">
												<div style="float: left; width: 15%">
													<a href="${outImgPreFixUrl }${advert2.imgUrl}"
														id="imgrul" target="_blank">预览</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>游戏链接3</td>
											<td><input type="text" id="gameUrl3" name="gameUrl3"
												value="${advert3.gameUrl}"></td>
										</tr>
										<tr>
											<td>图片3</td>
											<td>
												<div style="float: left; width: 15%">
													<input type="text" id="imgUrl3" name="imgUrl3"
														value="${advert3.imgUrl}"readonly="readonly">
												</div>
												<div id="mNewPic" style="float: left;">
													<label for="name" style="padding-top: 4px; float: left">上传图片
														(640x300)</label> <input type="file" name="a_imgUrl3"
														id="a_imgUrl3" multiple="multiple3" accept="image/*"
														onchange="muploadImg3()" style="margin-left: 60px">
												</div> <input type="hidden" name="img3" id="img3" value="1">
												<div style="float: left; width: 15%">
													<a href="${outImgPreFixUrl }${advert3.imgUrl}"
														id="imgrul" target="_blank">预览</a>
												</div>

											</td>
										</tr>
									</tbody>
								</table>
								<button type="button" class="btn btn-default"
									onclick="saveAdvert()">保存</button>
							</form>
						</div>
					</div>
					<!-- 底部文字区域 -->
					<footer class="app-footer">
						<div class="wrapper">
							<span class="pull-right"><a href="http://www.njdgsoft.com"
								target="_blank">南京道格软件有限公司</a></span>
						</div>
					</footer>
					<!-- /底部文字区域 -->
				</div>
			</div>
			<!-- /主要内容区域 -->
		</div>
		
	</div>


	<!-- 模态框区域 -->
	<!-- 修改密码模态框 -->
	<div class="modal fade" id="modifyPwdModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="modifyPwdLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<form id="modifyPWD">
						<div class="form-group">
							<label for="name">原始密码</label> <input type="password"
								class="form-control" name="pwd" id="old_pwd"
								placeholder="请输入原始密码" onkeyup="keyUp(this)">
						</div>
						<div class="form-group">
							<label for="name">新的密码</label> <input type="password"
								class="form-control" name="pwd1" id="new_pwd1"
								placeholder="请输入新的密码" onkeyup="keyUp(this)">
						</div>
						<div class="form-group">
							<label for="name">确认密码</label> <input type="password"
								class="form-control" name="pwd2" id="new_pwd2"
								placeholder="请再次输入密码" onkeyup="keyUp(this)">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger"
						onclick="toModifyPwd()">确认修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /用户审核模态框 -->
	<!-- /模态框区域 -->
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
	<script type="text/javascript"
		src="<%=basePath%>resources/pc/js/waitMe.js"></script>
	<script
		src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
	<!-- 编辑器JS -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
	<!-- 编辑器中文库 -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/lang/summernote-zh-CN.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/admin/advertManage.js"></script>
</body>
</html>
