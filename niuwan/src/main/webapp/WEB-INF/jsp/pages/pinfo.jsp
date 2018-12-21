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
<meta content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Fonts -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/pc/css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>resources/pc/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/pc/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
</head>

<body>
<div class="container">
<div class="col-xs-12" style="height: 60px;"></div>
<div class="col-xs-12" style="height: 50px;position: fixed;top: 0;color:#fff; font-size: 20px; background-color: #dc5858; line-height: 50px; text-align: center; z-index: 999999; box-shadow: 0 0 2px #661900;left: 0px;">
		<div class="col-xs-2" style="text-align: left;font-size: 23px;padding-left: 0px;">
		<img height="20px" src="<%=basePath%>/resources/images/sysimg/fanhui.png"onclick="javascript:window.location.href =document.referrer;">
				</div>
		<div class="col-xs-8" style="text-align: center;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">
			<spring:message code="dataediting"></spring:message></div>
	</div>

	<div class="col-xs-12" style="margin-top: 20px; text-align: center; width: 100%;">
		<img alt="" src="${user.headimg }" style="border-radius: 50%; border: 1px solid #DDD;" width="100px;" height="100px;" onclick="upfile()">
		<form action="<%=basePath%>pages/upfile" id="upfileform">
			<input type="file" id="upfile" name="upfile" style="display: none">
		</form>
	</div>
	<form class="form-horizontal" role="form" id="updinfoform">
	 	<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="nickname"></spring:message></label> <input type="text" class="form-control" id="name" name="name" value="${user.name }" placeholder="请输入昵称">
		</div>
		<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="mailbox"></spring:message></label> <input type="text" disabled="disabled" class="form-control" value="${user.account }" placeholder="请输入邮箱">
		</div>
		<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="gender"></spring:message></label>
			 <select class="form-control" id="sex" name="sex">
				<option value="0" <c:if test="${user.sex==0 }">selected="selected"</c:if>>女</option>
				<option value="1" <c:if test="${user.sex==1 }">selected="selected"</c:if>>男</option>
			</select>
		</div>
		<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="Age"></spring:message></label>
			<select class="form-control" id="age" name="age">
				<option value="1" <c:if test="${user.age==1 }">selected="selected"</c:if>>10-15</option>
				<option value="2" <c:if test="${user.age==2 }">selected="selected"</c:if>>16-20</option>
				<option value="3" <c:if test="${user.age==3 }">selected="selected"</c:if>>21-25</option>
				<option value="4" <c:if test="${user.age==4 }">selected="selected"</c:if>>26-30</option>
				<option value="5" <c:if test="${user.age==5 }">selected="selected"</c:if>>31-40</option>
				<option value="6" <c:if test="${user.age==6 }">selected="selected"</c:if>>40+</option>
			</select>
		</div>
		<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="region"></spring:message></label>
		</div>
		<div class="form-group col-xs-12" style="margin-left: 0px; margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
			<div class="col-xs-4" style="padding: 0px 5px 0px 0px;">
			<select class="form-control " style="margin-right: 10px;" name="regioinId" id="regioinId" onchange="regionChange()">
				<c:forEach items="${ regions}" var="region">
					<c:if test="${ region.id == user.region.id}">
						<option value="${region.id }" selected="selected">${region.name }</option>
					</c:if>
					<c:if test="${ region.id != user.region.id}">
						<option value="${region.id }">${region.name }</option>
					</c:if>
				</c:forEach>
			</select> 
			</div>
			<div class="col-xs-4" style="padding: 0px 5px 0px 5px;">
			<select class="form-control" style="margin-right: 10px;" name="countyId" id="countyId" onchange="countyChange()">
				<c:forEach items="${ counties}" var="county">
					<c:if test="${ county.id == user.county.id}">
						<option value="${county.id }" selected="selected">${county.name }</option>
					</c:if>
					<c:if test="${ county.id != user.county.id}">
						<option value="${county.id }">${county.name }</option>
					</c:if>
				</c:forEach>
			</select> 
			</div>
			<div class="col-xs-4" style="padding: 0px 0px 0px 5px;">
			<select class="form-control" style="margin-right: 10px; float: left;" name="cityId" id="cityId">
				<c:forEach items="${ cities}" var="city">
					<c:if test="${ city.id == user.city.id}">
						<option value="${city.id }" selected="selected">${city.name }</option>
					</c:if>
					<c:if test="${ city.id != user.city.id}">
						<option value="${city.id }">${city.name }</option>
					</c:if>
				</c:forEach>
			</select>
			</div>
		</div>
		<div class="form-group col-xs-12" style="margin-top: 20px; margin-left: 0px; margin-right: 0px;margin-bottom: 0px;">
			<label for="name"><spring:message code="occupation"></spring:message></label>
		</div>
		<div class="form-group col-xs-12" style="margin-left: 0px; margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
			<input type="hidden" id="nowPrefession" value="${user. profession}">
			<div class="col-xs-6" style="padding: 0px 5px 0px 0px;">
			<select class="form-control col-xs-6" style="margin-right: 10px; float: left;" id="profession_1" name="profession1" onchange="professionChange()">
				<option value="1" <c:if test="${user.profession >10 && user.profession<20 }">selected="selected"</c:if>><spring:message code="professionalcategory"></spring:message></option>
				<option value="2" <c:if test="${user.profession >20 && user.profession<30 }">selected="selected"</c:if>><spring:message code="technologycategory"></spring:message></option>
				<option value="3" <c:if test="${user.profession >30 && user.profession<40  }">selected="selected"</c:if>><spring:message code="managementandadministration"></spring:message></option>
				<option value="4" <c:if test="${user.profession >40 && user.profession<50  }">selected="selected"</c:if>><spring:message code="salesandservicecategory"></spring:message></option>
				<option value="5" <c:if test="${user.profession >50 && user.profession<60  }">selected="selected"</c:if>><spring:message code="creationclass"></spring:message></option>
				<option value="6" <c:if test="${user.profession==6 }">selected="selected"</c:if>><spring:message code="student"></spring:message></option>
				<option value="7" <c:if test="${user.profession==7 }">selected="selected"</c:if>><spring:message code="other"></spring:message></option>
			</select>
			</div>
			<div class="col-xs-6" style="padding: 0px 0px 0px 5px;">
			 <select class="form-control col-xs-6" style="margin-right: 10px; float: left;display: none" name="profession2" id="profession_2">
			</select>
			</div>
		</div>
		<div class="form-group col-xs-12" style="margin-left: 0px; margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
			<div class="col-xs-12" style="margin: 10px;padding: 0px;margin-top: 20px;margin: 20px 0px 10px 0px;">
				<input type="button" onclick="modifyUser()" class="btn btn-primary btn-lg btn-block" style="color: #fff; background-color:#dc5858; border: 0px solid #dc5858;border-radius: 1px;" value="<spring:message code="preservation"></spring:message>">
			</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		var profession= $("#nowPrefession").val();
		if(profession>10){
			var secondItem = profession.substr(0,1);
			var str = "";
			if(secondItem == "1"){
				str += "<option value='11'><spring:message code='finance'></spring:message></option>";
				str += "<option value='12'><spring:message code='realestate'></spring:message></option>";
				str += "<option value='13'><spring:message code='law'></spring:message></option>";
				str += "<option value='14'><spring:message code='doctor'></spring:message></option>";
			}else if(secondItem == "2"){
				str += "<option value='21'><spring:message code='developmentanddesign'></spring:message></option>";
				str += "<option value='22'><spring:message code='engineer'></spring:message></option>";
				str += "<option value='23'><spring:message code='operate'></spring:message></option>";
			}else if(secondItem == "3"){
				str += "<option value='31'><spring:message code='planning'></spring:message></option>";
				str += "<option value='32'><spring:message code='trade'></spring:message></option>";
				str += "<option value='33'><spring:message code='purchase'></spring:message></option>";
			}else if(secondItem == "4"){
				str += "<option value='41'><spring:message code='restaurant'></spring:message></option>";
				str += "<option value='42'><spring:message code='retail'></spring:message></option>";
				str += "<option value='43'><spring:message code='entertainment'></spring:message></option>";
			}else if(secondItem == "5"){
				str += "<option value='51'><spring:message code='advertisement'></spring:message></option>";
				str += "<option value='52'><spring:message code='film'></spring:message></option>";
				str += "<option value='53'><spring:message code='design'></spring:message></option>";
			}
			$("#profession_2").html(str);
			$("#profession_2").show();
			$("#profession_2").val(profession);
		}
	});
	function modifyUser() {
		var name = $.trim($("#name").val());
		if(name==""){
			alert("<spring:message code='pleaseinputanickname'></spring:message>");
			
			return false;
		}
		$.ajax({
			type:"post",
			dataType:"text",
			url:"pages/modifyUser",
			data:{
				name:$.trim($("#name").val()),
				sex:$.trim($("#sex").val()),
				age:$.trim($("#age").val()),
				regioinId:$.trim($("#regioinId").val()),
				countyId:$.trim($("#countyId").val()),
				cityId:$.trim($("#cityId").val()),
				profession1:$.trim($("#profession_1").val()),
				profession2:$.trim($("#profession_2").val())
			},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data=="success"){
					alert("<spring:message code='modifiedsuccess'></spring:message>");
					window.location.href="<%=basePath%>pages/personal";
				}else{
					alert("<spring:message code='modificationfailed'></spring:message>");
				}
			},error:function (){
				alert("<spring:message code='modificationfailed'></spring:message>");
			}
		});
	}
	
	
	//一级职业类修改
	function professionChange(){
		var firstItem = $("#profession_1").val();
		var str = "";
		if(firstItem == "1"){
			str += "<option value='11'>金融</option>";
			str += "<option value='12'>地产</option>";
			str += "<option value='13'>法律</option>";
			str += "<option value='14'>医生</option>";
		}else if(firstItem == "2"){
			str += "<option value='21'>开发设计</option>";
			str += "<option value='22'>工程师</option>";
			str += "<option value='23'>运营</option>";
		}else if(firstItem == "3"){
			str += "<option value='31'>企划</option>";
			str += "<option value='32'>贸易</option>";
			str += "<option value='33'>采购</option>";
		}else if(firstItem == "4"){
			str += "<option value='41'>餐饮</option>";
			str += "<option value='42'>零售</option>";
			str += "<option value='43'>娱乐</option>";
		}else if(firstItem == "5"){
			str += "<option value='51'>广告</option>";
			str += "<option value='52'>电影</option>";
			str += "<option value='53'>设计</option>";
		}
		$("#profession_2").html(str);
		if(str!=""){
			$("#profession_2").show();
		}else{
			$("#profession_2").hide();
		}
	}
	//选择头像
	function upfile() {
		$("#upfile")[0].click();
	}
	//修改头像
	$("#upfile").change(function() {
			$("#upfileform").ajaxSubmit(
			{
				type : "POST",
				dataType : "text",
				data : $("#upfileform").serialize(),
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
	//地区选择
		function regionChange() {
			$.ajax({
						type : "post",
						dataType : "json",
						url : "user/getCountyById",
						data : {
							"id" : $("#regioinId").val()
						},
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						cache : false,
						success : function(data) {
							if (data.success == "success") {
								var countys = data.countys;
								var str = "";
								$.each(countys, function(idx, obj) {
									str += "<option value='"+obj.id+"'>"
											+ obj.name + "</option>";
								});
								$("#countyId").html(str);
								countyChange();
							} else {
								alert("<spring:message code='failuretoobtainregionalinformation'></spring:message>");
							}
						}
					});
		}
	//县级选择
		function countyChange() {
			$.ajax({
						type : "post",
						dataType : "json",
						url : "user/getCityById",
						data : {
							"id" : $("#countyId").val()
						},
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						cache : false,
						success : function(data) {
							if (data.success == "success") {
								var citys = data.citys;
								var str = "";
								$.each(citys, function(idx, obj) {
									str += "<option value='"+obj.id+"'>"
											+ obj.name + "</option>";
								});
								$("#cityId").html(str);
							} else {
								alert("<spring:message code='failuretoobtainmunicipalInformation'></spring:message>");
								
							}
						}
					});
		}
	</script>
</body>
</html>
