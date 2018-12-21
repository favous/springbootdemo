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
<style type="text/css">
.badge {
	cursor: not-allowed;
	background-color: #fff;
	color: #000;
	margin-left: 3px;
	border: solid 1px #000;
}

.badge.used {
	cursor: not-allowed;
	background-color: #777;
	color: #fff;
	border: none;
	border: solid 1px #777;
}

.badge.go {
	cursor: not-allowed;
	background-color: #000;
	color: #fff;
	border: none;
	border: solid 1px #000;
}

.badge.checked {
	cursor: not-allowed;
	background-color: #5cb85c;
	color: #fff;
	border: none;
	border: solid 1px #5cb85c;
}

.badge.error {
	cursor: not-allowed;
	background-color: #d9534f;
	color: #fff;
	border: none;
	border: solid 1px #d9534f;
}

.badge.used.checked {
	cursor: not-allowed;
	background-color: #d9534f;
	color: #fff;
	border: none;
	border: solid 1px #d9534f;
}
</style>
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
							<li>广告管理</li>
							<li>广告详情</li>
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
						<span class="title">广告计划</span>
					</div>
					<div class="col-xs-12">
						<div class="card">
							<form class="form-horizontal" role="form" id="addAdv">
								<input type="hidden" id="imgType" name="imgType" value=""> <input type="hidden" id="hiddenUrl" name="hiddenUrl" value="<%=basePath%>"> <input type="hidden" id="id" name="id" value="${advPlan.id }">

								<div style="height: 20px"></div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">计划名称</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.name }</p>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">投放日期</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.startdate }~${advPlan.enddate }</p>
										<input type="hidden" name="startdate" id="startTime" class="form-control" value="${advPlan.startdate}"> <input type="hidden" name="enddate" id="endTime" class="form-control" value="${advPlan.enddate}">
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">投放时段</label>
								</div>
								<div id="timeDiv"></div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">期望展示数</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.expectedNum }次</p>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">总展示数</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.totaldisNum }次</p>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">总点击数</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.totalNum }次</p>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">频次控制</label>
									<div class="col-sm-5">
										<span class="col-sm-5" style="padding-top: 7px; padding-left: 0px;">同一素材，对同一用户，曝光次数</span>
										<div class="col-sm-7" style="padding: 0px">
											<p class="form-control-static">${advPlan.dispalyNum }次</p>
										</div>
									</div>
									<div class="col-sm-5">
										<span class="col-sm-3" style="padding-top: 7px; padding-left: 0px;">同一用户，点击次数</span>
										<div class="col-sm-7" style="padding: 0px">
											<p class="form-control-static">${advPlan.clickNum }次</p>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">投放页面</label> <input type="hidden" id="advtype" value="${advPlan.type}" />
									<div class="col-sm-11">
										<c:if test="${advPlan.type ==1 }">
											<p class="form-control-static">开屏页</p>
										</c:if>
										<c:if test="${advPlan.type ==2 }">
											<p class="form-control-static">banner</p>
										</c:if>
										<c:if test="${advPlan.type ==3 }">
											<p class="form-control-static">插屏</p>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">页面预览</label>
									<div class="col-sm-10">
										<c:if test="${advPlan.type ==2 }">
											<div id="bannerImg" class="col-sm-1">
												<span>banner：</span>
											</div>
											<div id="bannerImg" class="col-sm-5">
												<img style="width: 162px; height: 328px" src="<%=basePath%>resources/images/sysimg/banner.jpg">
												 <img src='${outImgPreFixUrl }${advPlanImg.url }' width='83px' height='50px' style='position: absolute; top: 71px; left: 55px'>
											</div>
										</c:if>
										<c:if test="${advPlan.type == 1 }">
											<div id="bannerImg" class="col-sm-2">
												<span style="">开屏(750*1334)：</span>
											</div>
											<div id="kp1Img" class="col-sm-4">
												<img style="width: 162px; height: 328px" src="<%=basePath%>resources/images/sysimg/kp.jpg">
												 <img src='${outImgPreFixUrl }${advPlanImg1.url }' width='141px' height='247px' style='position: absolute; top: 42px; left: 26px'>
											</div>
											<div id="bannerImg" class="col-sm-2">
												<span style="">开屏(1125*2436)：</span>
											</div>
											<div id="kp2Img" class="col-sm-4">
												<img style="width: 162px; height: 328px" src="<%=basePath%>resources/images/sysimg/kp.jpg"> 
												<img src='${outImgPreFixUrl }${advPlanImg2.url }' width='141px' height='247px' style='position: absolute; top: 42px; left: 26px'>
											</div>
										</c:if>
										<c:if test="${advPlan.type ==3 }">
											<div id="cpImg" class="col-sm-5">
												<span>插屏：</span> <img style="width: 162px; height: 328px" src="<%=basePath%>resources/images/sysimg/cp.jpg">
												 <img src='${outImgPreFixUrl }${advPlanImg.url }' width='140px' height='48px' style='position: absolute; top: 205px; left: 70px'>
											</div>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">落地链接</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.link }</p>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="col-sm-1 control-label">简单介绍</label>
									<div class="col-sm-10">
										<p class="form-control-static">${advPlan.introduction }</p>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-2">
										<button type="button" class="btn btn-info" onclick="javascript:history.go(-1)">返回</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- 底部文字区域 -->
					<footer class="app-footer">
						<div class="wrapper">
							<span class="pull-right"><a href="http://www.njdgsoft.com" target="_blank">南京道格软件有限公司</a></span>
						</div>
					</footer>
					<!-- /底部文字区域 -->
				</div>
			</div>


			<!-- 模态框区域 -->
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
			<!-- /用户审核模态框 -->
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
			<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
			<!-- 编辑器JS -->
			<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
			<!-- 编辑器中文库 -->
			<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/lang/summernote-zh-CN.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/admin/advsaveDtl.js"></script>
</body>
</html>
