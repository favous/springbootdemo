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
.deductButton {
    font-size: 22px;
    padding: 0px 9px;
    background: lightblue;
    border-radius: 5px;
}
.addButton {
    font-size: 22px;
    padding: 0px 6px;
    background: lightblue;
    border-radius: 5px;
}
</style>


</head>

<body class="flat-blue">
	<div class="app-container">
		<div class="row content-container">
			<!-- 顶部菜单 -->
			<nav class="navbar navbar-inverse navbar-fixed-top navbar-top">
			<input type="hidden" id="hiddenUrl" name="hiddenUrl" value="<%=basePath%>">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle">
							<i class="fa fa-bars icon"></i>
						</button>
						<ol class="breadcrumb navbar-breadcrumb">
							<li>游戏管理</li>
							<li class="active">游戏列表</li>
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
						<span class="title">添加游戏	</span>

					</div>

					<div class="col-xs-12">
						<div class="card">
							<form role="form" class="form-horizontal" id="addgame"
								method="post">
								<table class="table">
									<tbody>
										<tr>
											<td>游戏名称：</td>
											<td><input type="text" id="title" name="title" style="width: 20%" >
												</td>
										</tr>
										<tr>
											<td>游戏类别：</td>
											<td>
												<select name="type" id="type" style="width: 10%">
													<c:forEach items="${gameTypeDict}" var="item">
														<option value="${item.key}">${item.value}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td>游戏大小：</td>
											<td><input type="text" id="size" name="size" style="width: 20%" >
										</tr>
										
										<tr>
											<td>点击次数：</td>
											<td><input type="text" id="times" name="times" style="width: 20%" >
										</tr>
										
										<tr>
											<td>游戏开发商：</td>
											<td><input type="text" id="developer" name="developer" style="width: 20%" >
										</tr>
										
										<tr>
											<td>游戏分类：</td>
											<td>
												<select  name="dj" id="dj" style="width: 10%">
													<option value="1">H5</option>
													<option value="2">客户端</option>
												</select>
												&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
												推荐排序：
												<select name="sort" id="sort" style="width: 10%">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
												</select>
											</td>
										</tr>
										<!-- 
										<tr>
											<td>游戏版本：</td>
											<td><input type="text" id="version" name="version"
												style="width: 20%" ></td>
										</tr>
										<tr>
											<td>简单描述：</td>
											<td><input type="text" id="jdesc" name="jdesc"
												style="width: 20%" >(*不能超过15个字符)</td>
										</tr>
										<tr>
											<td>星级：</td>
											<td>
											
											<select s name="xj" id="xj" style="width: 10%">
											<option value="5">五星</option>
											<option value="4">四星</option>
											<option value="3">三星</option>
											<option value="2">二星</option>
											<option value="1">一星</option>
											</select>

											</td>
										</tr>
										<tr>
											<td>是否显示：</td>
											<td>
											<select name="isshow" id="isshow" style="width: 10%">
											<option value="1">显示</option>
											<option value="0">不显示</option>
											</select>
											</td>
										</tr>
										<tr>
											<td>是否好游戏：</td>
											<td>
											<select s name="hb" id="hb" style="width: 10%">
											<option value="0">否</option>
											<option value="1">是</option>
											</select>
											</td>
										</tr>
										<tr>
											<td>是否火爆热门：</td>
											<td>
											<select s name="rm" id="rm" style="width: 10%">
											<option value="0">否</option>
											<option value="1">是</option>
											</select>
											</td>
										</tr>
										<tr>
											<td>热门排序：</td>
											<td><input type="text" id="rsort" name="rsort"
												style="width: 20%" >	</td>
										</tr>
										<tr>
											<td>是否小编推荐：</td>
											<td>
											<select  name="wy" id="wy" style="width: 10%">
											<option value="0">否</option>
											<option value="1">是</option>
											</select>
											</td>
										</tr>
										-->
											<tr>
											<td>ICON图：</td>
											
											<td>
											
												<div style="float: left;">
													<input type="text" id="icon" name="icon">
												</div>
												
												<div style="float: left;">
													<label for="name" style="padding: 4px; float: left; width: 80px;">推荐比例1:1</label> 
													<input type="file" name="a_icon"
														id="a_icon" multiple="multiple" accept="image/*"
														onchange="muploadImgicon()" style="margin-left: 60px" />
												</div>
												
												<div style="float: left; width: 15%">
													<a id="b_icon"  href="#" target="_blank">预览</a>
												</div> 
										
											</td>
										</tr>
										<tr>
											<td>主图：</td>
											<td>
												<div style="float: left;">
													<input type="text" id="picture" name="picture">
												</div>
												<div style="float: left;">
													<label for="name" style="padding: 4px; float: left; width: 80px;">推荐比例3:2</label> 
													<input type="file" name="a_picture" id="a_picture" multiple="multiple" accept="image/*"
														onchange="muploadImgpicture()" style="margin-left: 60px">
												</div>
												<div style="float: left; width: 15%">
													<a id="b_picture"  href="#" target="_blank">预览</a>
												</div> 

											</td>
										</tr>
										
										<tr class="moreImgVideoTr">
											<td rowspan="1" id="moreTitleTd">更多图片及视频：</td>
											<td>
												<div style="float: left;">
													<input type="text" name="detailImg" readonly>
												</div>
												<div style="float: left;">
													<input type="file" name="a_more_" id="a_more_" multiple="multiple" accept="image/*" onchange="gameDetailImgUpload(this)" style="margin-left: 80px">
												</div>
												<div style="float: left; width: 15%">
													<a class="view" href="#" target="_blank">预览</a>
												</div>
												<a class="addButton" onclick="addRow(this)">+</a>
											</td>
										</tr>
										<tr>
											<td>游戏url：</td>
											<td><input type="text" id="file" name="file"
												style="width: 20%" >	</td>
										</tr>
										<tr>
											<td>参数字符：</td>
											<td><input type="text" id="parameterc" name="parameterc"
												style="width: 20%" >	</td>
										</tr>
										<!-- 
										<tr>
											<td>显示排序：</td>
											<td><input type="text" id="sort" name="sort	"
												style="width: 20%" >	</td>
										</tr> -->
										<tr>
											<td>简单介绍：</td>
											<td><textarea id="jdjs" style="width:549px;height:200px;" name="jdjs"></textarea>	</td>
										</tr>
										<tr>
											<td><button type="button" class="btn btn-default"
									onclick="savegame()">保存</button></td>
													</tr>
									</tbody>
								</table>
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
		<!-- 底部文字区域 -->
		<footer class="app-footer">
			<div class="wrapper">
				<span class="pull-right"><a href="http://www.njdgsoft.com" target="_blank">南京道格软件有限公司</a></span>
			</div>
		</footer>
		<!-- /底部文字区域 -->
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
	<!-- /.modal -->
	<!-- /.modal 修改密码模态框  -->
	<!-- 新增游戏模态框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
					</button>
					<h4 class="modal-title" id="addTitle">新增游戏</h4>
				</div>
				<div class="modal-body" style="height: 500px;overflow-y:auto;overflow-x:hidden;">
					<form class="form-horizontal" id="addForm" autocomplete="off">
						<div class="form-group">
							<label for="a_title" class="col-sm-2 control-label">游戏标题</label>
						    <div class="col-sm-10">
						 		<input type="text" class="form-control" id="a_title" name="title" placeholder="请输入游戏标题">
							</div>
						</div>
						<div class="form-group">
							<label for="a_title" class="col-sm-2 control-label">游戏类型</label>
						    <div class="col-sm-10">
						 		<input type="text" class="form-control" id="a_type" name="type" placeholder="请输入游戏类型">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" onclick="add()">新增</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id="template" style="display: none;">
		<table>
			<tr class="moreImgVideoTr">
				<td>
					<div style="float: left;">
						<input type="text" name="detailImg" readonly>
					</div>
					<div style="float: left;">
						<input type="file" name="a_more_" id="a_more_" multiple="multiple" accept="image/*" onchange="gameDetailImgUpload(this)" style="margin-left: 80px">
					</div>
					<div style="float: left; width: 15%">
						<a class="view" href="#" target="_blank">预览</a>
					</div> 
					<a class="deductButton" onclick="deductRow(this)">-</a>
					<a class="addButton" onclick="addRow(this)">+</a>
				</td>
			</tr>
		</table>
	</div>

	<!-- /.modal -->
	<!-- /游戏结果信息模态框 -->
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
	<script
		src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
	<!-- Javascript -->
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/app.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/pc/js/waitMe.js"></script>
	<!-- 编辑器JS -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.js"></script>
	<!-- 编辑器中文库 -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/lang/summernote-zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/admin/gameAdd.js"></script>
	
</body>
</html>
