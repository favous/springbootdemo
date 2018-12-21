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
<meta
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<!-- Fonts -->
<link rel="stylesheet" href="<%=basePath%>resources/zy/index/index1.css">
<link rel="stylesheet"
	href="<%=basePath%>resources/zy/index/swiper.3.1.2.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=basePath%>resources/zy/index/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/zy/index/flexible.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/zy/index/swiper.3.1.2.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		/*var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/MicroMessenger/i)=="micromessenger") {
			$(".bg").css({
				"display": "block"
			})
		}else{*/

		/* Mobile */
		var isMobile = {
			Android : function() {
				return navigator.userAgent.match(/Android/i) ? true : false;
			},
			iOS : function() {
				return navigator.userAgent.match(/iPhone|iPad|iPod/i) ? true
						: false;
			}
		};

		if (isMobile.iOS()) {
			$.ajax({
				type : "POST",
				url : '/index/fanwen.shtml',
				data : {
					equ : 'iOS'
				},
				success : function(json) {
				}
			})
		} else if (isMobile.Android()) {
			$.ajax({
				type : "POST",
				url : '/index/fanwen.shtml',
				data : {
					equ : '安卓'
				},
				success : function(json) {
				}
			})
		} else {
			$.ajax({
				type : "POST",
				url : '/index/fanwen.shtml',
				data : {
					equ : '电脑'
				},
				success : function(json) {
				}
			})
		}
		//}

	})

	function down(url) {

		if (url == '#') {
			alert('敬请期待！');
			return false;

		} else {
			window.location.href = url;
		}
	}

	function download(id) {
		window.location.href = 'pages/gamedtl?id=' + id;
	}
	function getmsgs() {
		var pages = $('#pages1').val();
		var tid = "rm";
		$
				.ajax({
					type : "POST",
					url : '/index/getmsgs.shtml',
					data : {
						pages : pages,
						tid : tid
					},
					success : function(json) {
						if (json != 1) {
							var a = Number(pages) + Number(1);
							$('#pages1').val(a);
							json = eval(("(" + json + ")"));

							var msg = "";
							var msghtml = $("#msg1").html();
							var xj = "";
							for ( var o in json) {
								var nav = '';
								if (json[o].xj == '5') {
									xj = "<span></span><span></span><span></span><span></span><span></span>";
								} else if (json[o].xj == '4') {
									xj = '<span></span><span></span><span></span><span></span><span class="skychang"></span>';
								} else if (json[o].xj == '3') {
									xj = '<span></span><span></span><span></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '2') {
									xj = '<span></span><span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '1') {
									xj = '<span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								}
								msg += '<li><a href="/index/details.shtml?id='
										+ json[o].id
										+ '"><dl><dt><img src="'+json[o].picture+'"></dt><dd><h2>'
										+ json[o].title
										+ '</h2><div class="sky clearfix">'
										+ xj
										+ '</div><div><span>'
										+ json[o].type
										+ '</span>|<span>'
										+ json[o].version
										+ '</span><p>'
										+ json[o].jdesc
										+ '</p></div></dd></dl></a><input type="hidden" id="rms'+json[o].id+'" value="1"><em id="rm'
										+ json[o].id
										+ '" onclick=download("rms","rm","'
										+ json[o].file + '","' + json[o].id
										+ '");>下载</em></li>';
							}
							$("#msg1").html(msghtml + msg);
						}
					}
				});
	}

	function getmsgs1() {
		var pages = $('#pages2').val();
		var tid = "wy";
		$
				.ajax({
					type : "POST",
					url : '/index/getmsgs.shtml',
					data : {
						pages : pages,
						tid : tid
					},
					success : function(json) {
						if (json != 1) {
							var a = Number(pages) + Number(1);
							$('#pages2').val(a);
							json = eval(("(" + json + ")"));

							var msg = "";
							var msghtml = $("#msg2").html();
							var xj = "";
							for ( var o in json) {
								var nav = '';
								if (json[o].xj == '5') {
									xj = "<span></span><span></span><span></span><span></span><span></span>";
								} else if (json[o].xj == '4') {
									xj = '<span></span><span></span><span></span><span></span><span class="skychang"></span>';
								} else if (json[o].xj == '3') {
									xj = '<span></span><span></span><span></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '2') {
									xj = '<span></span><span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '1') {
									xj = '<span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								}
								msg += '<li><a href="/index/details.shtml?id='
										+ json[o].id
										+ '"><dl><dt><img src="'+json[o].picture+'"></dt><dd><h2>'
										+ json[o].title
										+ '</h2><div class="sky clearfix">'
										+ xj
										+ '</div><div><span>'
										+ json[o].type
										+ '</span>|<span>'
										+ json[o].version
										+ '</span><p>'
										+ json[o].jdesc
										+ '</p></div></dd></dl></a><input type="hidden" id="wys'+json[o].id+'" value="1"><em id="wy'
										+ json[o].id
										+ '" onclick=download("wys","wy","'
										+ json[o].file + '","' + json[o].id
										+ '");>下载</em></li>';
							}
							$("#msg2").html(msghtml + msg);
						}
					}
				});
	}

	function getmsgs2() {
		var pages = $('#pages3').val();
		var tid = "bw";
		$
				.ajax({
					type : "POST",
					url : '/index/getmsgs.shtml',
					data : {
						pages : pages,
						tid : tid
					},
					success : function(json) {
						if (json != 1) {
							var a = Number(pages) + Number(1);
							$('#pages3').val(a);
							json = eval(("(" + json + ")"));
							var msg = "";
							var msghtml = $("#msg3").html();
							var xj = "";
							for ( var o in json) {
								var nav = '';
								if (json[o].xj == '5') {
									xj = "<span></span><span></span><span></span><span></span><span></span>";
								} else if (json[o].xj == '4') {
									xj = '<span></span><span></span><span></span><span></span><span class="skychang"></span>';
								} else if (json[o].xj == '3') {
									xj = '<span></span><span></span><span></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '2') {
									xj = '<span></span><span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								} else if (json[o].xj == '1') {
									xj = '<span></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span><span class="skychang"></span>';
								}
								msg += '<li><a href="/index/details.shtml?id='
										+ json[o].id
										+ '"><dl><dt><img src="'+json[o].picture+'"></dt><dd><h2>'
										+ json[o].title
										+ '</h2><div class="sky clearfix">'
										+ xj
										+ '</div><div><span>'
										+ json[o].type
										+ '</span>|<span>'
										+ json[o].version
										+ '</span><p>'
										+ json[o].jdesc
										+ '</p></div></dd></dl></a><input type="hidden" id="bws'+json[o].id+'" value="1"><em id="bw'
										+ json[o].id
										+ '" onclick=download("bws","bw","'
										+ json[o].file + '","' + json[o].id
										+ '");>下载</em></li>';
							}
							$("#msg3").html(msghtml + msg);
						}
					}
				});
	}
</script>
</head>

<body class="flat-blue" style="background-color: #efefef;">
	<div class="col-xs-12" style="height: 60px;"></div>
	<div class="col-xs-12"
		style="height: 50px; position: fixed; top: 0; color: #fff; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900">
		<spring:message code="homepage"></spring:message>
	</div>
	<div class="wrap">
		<div class="bg"></div>
		<!-- 轮播图 -->
		<div class="pic-scroll" style="padding: 10px;">

			<!-- 此处引用swrpe框架 -->
			<div class="swiper-container swiper-container-horizontal"
				style="display: block;">
				<div class="swiper-wrapper"
					style="transition-duration: 0ms; transform: translate3d(-5288px, 0px, 0px); border-radius: 0px;">
					<div class="swiper-slide" data-swiper-slide-index="0"
						style="width: 1322px;">
						<a href="javascript:void(0);"
							onclick="down('${adverts[0].gameUrl}');return false;"><img
							src="<%=basePath%>${adverts[0].imgUrl}" alt="" title=""
							style="border-radius: 0px;"></a>
					</div>
					<div class="swiper-slide" data-swiper-slide-index="1"
						style="width: 1322px;">
						<a href="javascript:void(0);"
							onclick="down('${adverts[1].gameUrl}');return false;"><img
							src="<%=basePath%>${adverts[1].imgUrl}" alt="" title=""
							style="border-radius: 0px;"></a>
					</div>
					<div class="swiper-slide swiper-slide-prev"
						data-swiper-slide-index="2" style="width: 1322px;">
						<a href="javascript:void(0);"
							onclick="down('${adverts[2].gameUrl}');return false;"><img
							src="<%=basePath%>${adverts[2].imgUrl}" alt="" title=""
							style="border-radius: 0px;"></a>
					</div>
				</div>
				<!-- 如果需要分页器 -->
				<div class="swiper-pagination">
					<span
						class="swiper-pagination-bullet swiper-pagination-bullet-active"></span><span
						class="swiper-pagination-bullet"></span><span
						class="swiper-pagination-bullet"></span>
				</div>
			</div>
		</div>
		<!-- 游戏区 -->
		<div class="con">
			<div
				style="width: 100%; height: 30px; font-size: 18px; line-height: 30px;">
				<div
					style="float: left; height: 22px; width: 10px; margin-top: 4px; margin-left: 15px; background-color: #FFCC00;"></div>
				&nbsp;&nbsp;&nbsp;
				<spring:message code="H5hotthisweek"></spring:message>
			</div>
			<div class="con-wrap">
				<div class="con-artul">

					<c:forEach items="${games }" var="game" varStatus="x">
						<c:if test="${game.dj==1 }">
						<div
							style="width: 25%; float: left; border-bottom-width: 0px; padding: 10px;"
							onclick="javascript:window.location.href='pages/gamedtl?id=${game.id}'">

							<div style="width: 100%; text-align: center;">
								<div style="padding: 0px; width: 100%;">
									<img src="<%=basePath%>${game.icon}" width="100%;">
								</div>
								<div style="text-align: center;">
									<div class="h2"
										style="margin-top: 5px; font-size: 16px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${game.title}</div>
									<div class="p" style="text-align: center; font-size: 14px;">${game.times}万次</div>
								</div>
							</div>
						</div>
						</c:if>

					</c:forEach>


					<div style="width: 100%;"></div>
				</div>

			</div>
			</div>
			<div class="con col-xs-12" style="padding-left: 0px;padding-right: 0px;">
			<div
				style="width: 100%; height: 30px; font-size: 18px; line-height: 30px;">

				<div
					style="float: left; height: 22px; width: 10px; margin-top: 4px; margin-left: 15px; background-color: #FFCC00;"></div>
				&nbsp;&nbsp;&nbsp;
				<spring:message code="clienthotthisweek"></spring:message>
			</div>
			<div class="con-wrap">
				<div class="con-artul">

					<c:forEach items="${games }" var="game" varStatus="x">
						<c:if test="${game.dj==2 }">
						<div
							style="width: 25%; float: left; border-bottom-width: 0px; padding: 10px;"
							onclick="javascript:window.location.href='pages/gamedtl?id=${game.id}'">

							<div style="width: 100%; text-align: center;">
								<div style="padding: 0px; width: 100%;">
									<img src="<%=basePath%>${game.icon}" width="100%;">
								</div>
								<div style="text-align: center;">
									<div class="h2"
										style="margin-top: 5px; font-size: 16px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${game.title}</div>
									<div class="p" style="text-align: center; font-size: 14px;">${game.times}万次</div>
								</div>
							</div>
						</div>
						</c:if>

					</c:forEach>


					<div style="width: 100%;"></div>
				</div>

			</div>
		</div>

		<div class="modal fade" id="modifyCommodityModal" tabindex="-1"
			role="dialog" style="margin-top: 50%;" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">


			<div class="modal-dialog">
				<div class="modal-content" style="padding: 10px;">
					<div style="text-align: center; margin-top:">
						<img src="<%=basePath%>/resources/images/sysimg/logo@2x.png"
							style="width: 20%; height: 20%">
					</div>
					<div class="modal-header">
						<ul id="myTab" class="nav nav-tabs"
							style="text-align: center; font-size: 20px;">
							<li class="active" style="width: 50%"><a
								style="color: #000;" href="#home" data-toggle="tab"><spring:message
										code="land"></spring:message> </a></li>
							<li style="width: 50%"><a href="#ios" data-toggle="tab"
								style="color: #000;"><spring:message code="register"></spring:message></a></li>

						</ul>
					</div>
					<div id="myTabContent" class="tab-content"
						style="margin-bottom: 20px;">
						<div class="tab-pane fade in active" id="home">
							<form id="signinForm" class="form-horizontal" method="post"
								autocomplete="off">
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/emil.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="email"
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 8px; margin-left: 3%"
										id="account1" name="account1"
										placeholder="<spring:message code="pleaseinputthemailbox"></spring:message>">
								</div>
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<!--  <input style="border:0;border-bottom:1 solid black;background:;"> -->
									<!--   <label for="name">2.密码</label> -->
									<img src="<%=basePath%>/resources/images/sysimg/mm.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 8px; margin-left: 3%"
										type="password" id="pwd" name="pwd"
										placeholder="<spring:message code="pleaseinputapassword"></spring:message>">

								</div>
								<div class="form-group"
									style="margin-left: 15px; margin-right: 15px;"></div>
								<div class="col-sm-offset-2 col-sm-10" style="margin: 10px;">
									<button type="button" class="btn btn-primary btn-lg btn-block"
										style="color: #fff; background-color: #dc5858; border: 0px solid #dc5858; border-radius: 1px;"
										onclick="signin()"><spring:message code="land"></spring:message></button>
								</div>
							</form>
						</div>
						<!-- 	注册 -->
						<div class="tab-pane fade" id="ios">

							<form id="addForm" class="form-horizontal" action="#"
								method="post" autocomplete="on">
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/emil.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="email" id="account" name="account"
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 7px; margin-left: 3%"
										placeholder="<spring:message code="pleaseinputthemailbox"></spring:message>">
								</div>
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/nickname.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="text"
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 7px; margin-left: 3%"
										id="name" name="name" placeholder="<spring:message code="pleaseinputanickname"></spring:message>">
								</div>
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/mm.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="password"
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 7px; margin-left: 3%"
										id="pwd1" name="pwd" placeholder="<spring:message code="pleaseinputapassword"></spring:message>">
								</div>
								<div class="form-group"
									style="margin-top: 20px; margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/qrmm.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="password"
										style="width: 92%; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 7px; margin-left: 3%"
										id="pwd2" name="pwd2" placeholder="<spring:message code="pleaseconfirmthepassword"></spring:message>">
								</div>

								<div class="form-group"
									style="margin-left: 15px; margin-right: 15px;">
									<img src="<%=basePath%>/resources/images/sysimg/ReCodes.png"
										style="float: left; width: 5%; padding-top: 8px; height: 24px">
									<input type="text"
										style="width: 55%; float: left; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-bottom-color: black; padding-top: 7px; margin-left: 3%;"
										id="recode" name="recode" placeholder="<spring:message code="pleaseinputverificationcode"></spring:message>"> <input
										type="button"
										style="width: 30%; border-radius: 3px; float: left; color: #fff; background-color: #dc5858; border: 1px solid #dc5858; margin-left: 3%"
										onclick="getReCodes()" class="testButton btn btn-default"
										value="<spring:message code="getmailboxverificationcode"></spring:message>" />
								</div>
								<div class="form-group"
									style="margin-left: 15px; margin-right: 15px;"></div>
								<div class="col-sm-offset-2 col-sm-10" style="margin: 10px;">
									<input type="button" class="btn btn-primary btn-lg btn-block"
										style="color: #fff; background-color: #dc5858; border: 1px solid #dc5858; border-radius: 3px;"
										onclick="signup()" value="<spring:message code="toregister1"></spring:message>" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<input type="hidden" value="${userMode } " id="userMode"
			name="userMode">
		<div class="col-xs-12" style="height: 60px;"></div>
		<!-- 导航菜单 -->
		<div class="col-xs-12"
			style="height: 60px; position: fixed; bottom: 0; line-height: 25px; font-size: 20px; width: 100%; background-color: #FFFFFF; padding: 5px; box-shadow: 0 0 2px #661900">
			<div class="col-xs-4" style="text-align: center;"
				onclick="javascript:window.location.href='pages/index'">
				<img name="syImg"
					src="<%=basePath%>/resources/images/sysimg/shouye@3x.png"
					style="width: 25px; height: 25px;"><br> <span
					style="font-size: 16px; color: #e01515"><spring:message code="homepage"></spring:message></span>
			</div>
			<div class="col-xs-4" style="text-align: center;"
				onclick="javascript:window.location.href='pages/article'">
				<img src="<%=basePath%>/resources/images/sysimg/资讯-点击@2x.png"
					style="width: 25px; height: 25px;"><br> <span
					style="font-size: 16px"><spring:message code="information"></spring:message></span>
			</div>
			<div class="col-xs-4" style="text-align: center;"
				onclick="javascript:window.location.href='pages/personal'">
				<img src="<%=basePath%>/resources/images/sysimg/我的(1)@2x.png"
					style="width: 25px; height: 25px;"><br> <span
					style="font-size: 16px"><spring:message code="my"></spring:message></span>
			</div>
		</div>
		<script type="text/javascript">
			var mySwiper = new Swiper('.pic-scroll .swiper-container', {
				direction : 'horizontal',
				loop : true,
				autoplay : 3000,

				// 如果需要分页器
				pagination : '.pic-scroll .swiper-pagination',
			})
		</script>
	</div>
	<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-overlay"
		title="拖动开始，双击或回车完成" style="display: none;">
		<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-top"></div>
		<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-left"></div>
		<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-right"></div>
		<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-bottom"></div>
		<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-target">
			<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-ctrlnw"></div>
			<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-ctrlne"></div>
			<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-ctrlse"></div>
			<div id="mococn-474774b8-8910-42a0-8c6e-6aaa761e381a-crop-ctrlsw"></div>
		</div>
	</div>

	<script type="text/javascript">
	$(function () {
		var userMode = $("#userMode").val();
		if(userMode==0){
			$("#modifyCommodityModal").modal("show");
		}
	  });
		//登录
		function signin() {
			var account=$.trim($("#account1").val());
			var pwd=$.trim($("#pwd").val());
			if(account==""){
				alert("<spring:message code='pleaseinputthemailbox'></spring:message>");
				return false;
			}
			if(pwd==""){
				alert("<spring:message code='pleaseinputapassword'></spring:message>");
				return false;
			}
			$.ajax({
				type:"post",
				dataType:"text",
				data:$("#signinForm").serialize(),
				url:"<%=basePath%>user/signin",
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						cache : false,
						success : function(data) {
							if (data == "success") {
								window.location.href = "pages/index";
							} else if (data == "error") {
								alert("<spring:message code='errorsinmailboxorpasswordentry'></spring:message>");
							} else if (data == "error2") {
								alert("<spring:message code='loginfailed'></spring:message>");
							}
						},
						error : function() {
							alert("<spring:message code='loginfailed'></spring:message>");
						}
					});
		}
		function getReCodes() {
			if($.trim($("#account").val()).indexOf("@")<0){
				alert("<spring:message code='pleaseenterthecorrectmailbox'></spring:message>");
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
									alert("<spring:message code='sendsuccessfully'></spring:message>");
									} else {
										alert("<spring:message code='failinsend'></spring:message>");
									}
								},
								error : function() {
									alert("<spring:message code='systemerror'></spring:message>");
								}
							});
				} else {
					alert("<spring:message code='pleaseinputthemailbox'></spring:message>");
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
					alert("<spring:message code='pleaseinputthemailbox'></spring:message>");
					return false;
				}
				if(name==""){
					alert("<spring:message code='pleaseinputanickname'></spring:message>");
					return false;
				}
				if(pwd1==""){
					alert("<spring:message code='pleaseinputapassword'></spring:message>");
					return false;
				}
				if(recode==""){
					alert("<spring:message code='pleaseinputverificationcode'></spring:message>");
					return false;
				}
				if(pwd1!=pwd2){
					alert("<spring:message code='getmailboxverificationcode'></spring:message>");
					return false;
				}
				$.ajax({
					type:"post",
					dataType:"text",
					data:$("#addForm").serialize(),
					url:"<%=basePath%>user/signup",
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						cache : false,
						success : function(data) {
							if (data == "noRecodes") {
								alert("<spring:message code='getthecorrectvalidationcode'></spring:message>");
							} else if (data == "errorRecodes") {
								alert("<spring:message code='verificationcodeerror'></spring:message>");
							} else if (data == "sameAccount") {
								alert("<spring:message code='accountalreadyexists'></spring:message>");
							} else if (data == "success") {
								alert("<spring:message code='loginwassuccessful'></spring:message>");
								window.location.href = "<%=basePath%>pages/index";
						}else if(data=="error"){
							alert("<spring:message code='loginhasfailed'></spring:message>");
						}
					},
					error:function(){
						alert("<spring:message code='loginhasfailed'></spring:message>");
					}
				});
				
			}
	</script>
</body>
</html>
