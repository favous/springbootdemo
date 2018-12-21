<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>gyumaru后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Fonts -->
<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/animate.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/checkbox3.min.css">
<link href="http://cdn.bootcss.com/datatables/1.10.11/css/dataTables.bootstrap.min.css" rel="stylesheet" media="screen">
<link href="http://cdn.bootcss.com/datatables/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/waitMe.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/themes/flat-blue.css">
<!-- 编辑器css -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.css" rel="stylesheet">
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
							<li>用户管理</li>
						</ol>
						<button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-times icon"></i>
						</button>
						<li class="dropdown profile">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${admin.card}<span class="caret"></span>
							</a>
							<ul class="dropdown-menu animated fadeInDown">
								<li>
									<div class="profile-info">
										<h4 class="username">${admin.card}</h4>
										<div class="btn-group margin-bottom-2x" role="group">
											<button type="button" class="btn btn-default" onclick="modifyPwd()">
												<i class="fa fa-key"></i>修改密码
											</button>
											<button type="button" class="btn btn-default" onclick="javascript:location.href='<%=basePath%>admin/logout'">
												<i class="fa fa-sign-out"></i>登出
											</button>
										</div>
									</div>
								</li>
							</ul>
						</li>
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
							<button type="button" class="navbar-expand-toggle pull-right visible-xs">
								<i class="fa fa-times icon"></i>
							</button>
						</div>
						<!-- 一级菜单，无二级菜单 -->
						<ul class="nav navbar-nav">
							<c:forEach items="${menus}" var="menu1">
								<c:if test="${menu1.menus==null||fn:length(menu1.menus) == 0}">
									<li>
										<a href="<%=basePath%>${menu1.menu_url}">
											<span class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
										</a>
									</li>
								</c:if>
								<!-- 有二级菜单 -->
								<c:if test="${menu1.menus!=null&&fn:length(menu1.menus) > 0}">
									<li class="panel panel-default dropdown" id="panel${menu1.id}">
										<c:if test="${menu1.menu_url==null||menu1.menu_url==''}">
											<a onclick="return false" data-toggle="collapse" href="#dropdown-element${menu1.id}" data-parent="#panel${menu1.id}">
												<span class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
											</a>
										</c:if>
										<c:if test="${menu1.menu_url!=null&&menu1.menu_url!=''}">
											<a data-toggle="collapse" href="<%=basePath%>${menu1.menu_url}">
												<span class="${menu1.icon}"></span> <span class="title">${menu1.name}</span>
											</a>
										</c:if>
										<!-- 二级菜单 -->
										<div id="dropdown-element${menu1.id}" class="panel-collapse collapse">
											<div class="panel-body">
												<ul class="nav navbar-nav">
													<c:forEach items="${menu1.menus}" var="menu2">
														<li>
															<a href="<%=basePath %>${menu2.menu_url}">${menu2.name}</a>
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
						<span class="title">用户管理</span>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<div class="row">
											<form role="form" class="form-horizontal" id="searchForm" action="<%=basePath%>user/userManage" method="post">
												<div class="col-xs-3">
													<div class="input-group" style="margin-top: 5px;">
														<span class="input-group-addon">姓名</span> <input type="text" class="form-control" name="name" id="searchName" value="${name}" autocomplete="off">
													</div>
												</div>
												<div class="col-xs-1">
													<button type="button" class="btn btn-info btn-block" onclick="$('#searchForm').submit()">查询</button>
												</div>
											</form>
										</div>
									</div>
								</div>
								<div class="card-body">
									<table id="dataTable" class="table" cellspacing="0" width="100%">
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- /.row -->
				</div>
			</div>
			<!-- /主要内容区域 -->
		</div>
		<!-- 底部文字区域 -->
		<footer class="app-footer">
			<div class="wrapper">
				<span class="pull-right"><a href="http://www.njdgsoft.com" target="_blank">南京道格软件有限公司</a></span>
			</div>
		</footer>
		<!-- /底部文字区域 -->
	</div>


	<!-- 模态框区域 -->
	<!-- 修改用户模态框 -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modifyTitle">修改权限</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="modifyForm">
						<input type="hidden" class="form-control" id="m_id" name="id">
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">所属企业</label>
							<div class="col-sm-10">
								<select name="pl" id="m_pl" style="width: 10%">
									<c:forEach items="${plDict}" var="item">
										<option value="${item.key}" >${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" onclick="modify()">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 修改用户模态框  -->
	<!-- 修改密码模态框 -->
	<div class="modal fade" id="modifyPwdModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modifyPwdLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<form id="modifyPWD">
						<div class="form-group">
							<label for="name">原始密码</label> <input type="password" class="form-control" name="pwd" id="old_pwd" placeholder="请输入原始密码" onkeyup="keyUp(this)">
						</div>
						<div class="form-group">
							<label for="name">新的密码</label> <input type="password" class="form-control" name="pwd1" id="new_pwd1" placeholder="请输入新的密码" onkeyup="keyUp(this)">
						</div>
						<div class="form-group">
							<label for="name">确认密码</label> <input type="password" class="form-control" name="pwd2" id="new_pwd2" placeholder="请再次输入密码" onkeyup="keyUp(this)">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" onclick="toModifyPwd()">确认修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- /.modal 修改密码模态框  -->
	<!-- 修改用户模态框 -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modifyTitle">修改用户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="modifyForm">
						<input type="hidden" class="form-control" id="m_id" name="id">
						<div class="form-group">
							<label for="m_title" class="col-sm-2 control-label">帐号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="m_title" name="title" placeholder="请输入帐号">
							</div>
						</div>
						<div class="form-group">
							<label for="m_pwd" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="m_pwd" name="pwd" placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="m_name" class="col-sm-2 control-label">昵称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="m_name" name="name" placeholder="请输入昵称">
							</div>
						</div>
						<div class="form-group">
							<label for="m_sex" class="col-sm-2 control-label">用户性别</label>
							<div class="col-sm-10">
								<select class="form-control" id="m_sex" name="sex">
									<option value="1">男</option>
									<option value="0">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="m_region" class="col-sm-2 control-label">地区</label>
							<div class="col-sm-10">
								<select class="form-control" id="m_region" name="region.id" onchange="regionChange(null)">
									<c:forEach items="${regions }" var="region">
										<option value="${ region.id}">${ region.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="m_county" class="col-sm-2 control-label">县级</label>
							<div class="col-sm-10">
								<select class="form-control" id="m_county" name="county.id" onchange="countyChange()">
									<c:forEach items="${countys }" var="county">
										<option>${ county.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="m_city" class="col-sm-2 control-label">市级</label>
							<div class="col-sm-10">
								<select class="form-control" id="m_city" name="city.id">
									<c:forEach items="${citys }" var="city">
										<option>${ city.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="m_profession_1" class="col-sm-2 control-label">职业</label>
							<div class="col-sm-10">
								<select class="form-control" id="m_profession_1" name="profession">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" onclick="modify()">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 修改用户模态框  -->

	<!-- /模态框区域 -->
	<!-- Javascript Libs -->
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/Chart.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap-switch.min.js"></script>

	<script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.matchHeight-min.js"></script>
	<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
	<script src="http://cdn.bootcss.com/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/select2.full.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/ace.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/mode-html.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/ace/theme-github.js"></script>
	<!-- Javascript -->
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/app.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/waitMe.js"></script>
	<!-- 编辑器JS -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
	<!-- 编辑器中文库 -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/lang/summernote-zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/admin/userManage.js"></script>
</body>
</html>
