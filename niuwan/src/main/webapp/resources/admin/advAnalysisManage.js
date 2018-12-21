var doUserId=0;
$(document).ready(function(){
	//提示信息
		var lang = {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "每页 _MENU_ 项",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
			"sInfoEmpty" : "当前显示第 0 至 0 项，共 0 项",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页",
				"sJump" : "跳转"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		};

		//初始化表格
		var table = $("#dataTable").dataTable({
			language : lang, //提示信息
			autoWidth : false, //禁用自动调整列宽
			//stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
			processing : true, //隐藏加载提示,自行处理
			serverSide : true, //启用服务器端分页
			searching : false, //禁用原生搜索
			orderMulti : false, //启用多列排序
			bSort : false, //取消各行排序
			bLengthChange : false, //取消选择一次展示多少行
			iDisplayLength : 10, //默认一次展示10行
			order : [], //取消默认排序查询,否则复选框一列会出现小箭头
			renderer : "bootstrap", //渲染样式：Bootstrap和jquery-ui
			pagingType : "simple_numbers", //分页样式：simple,simple_numbers,full,full_numbers
			columnDefs : [ {
				"targets" : 'nosort', //列的样式名
				"orderable" : false //包含上样式名‘nosort’的禁止排序
			} ],
			ajax : function(data, callback, settings) {
				//封装请求参数
				var param = {};
				param.limit = data.length; //页面显示记录条数，在页面显示每页显示多少项的时候
				param.start = data.start; //开始的记录序号
				param.page = (data.start / data.length) + 1; //当前页码
				//查询条件
				param.name = $.trim($("#searchName").val());
				param.startdate = $.trim($("#searchStartdate").val());
				param.enddate = $.trim($("#searchEnddate").val());
				//console.log(param);
				//ajax请求数据
				$.ajax({
					type : "GET",
					url : "advertisement/advAnalysisList",
					cache : false, //禁用缓存
					data : param, //传入组装的参数
					dataType : "json",
					success : function(result) {
						console.log(result);
						//封装返回数据
						var returnData = {};
						returnData.draw = data.draw; //这里直接自行返回了draw计数器,应该由后台返回
						returnData.recordsTotal = result.total; //返回数据全部记录
						returnData.recordsFiltered = result.total; //后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.data; //返回的数据列表
						//console.log(returnData);
						//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						callback(returnData);
					}
				});
			},
			//列表表头字段
			columns : [
				{
					sTitle : '编号',
					render : function(data, type, row, meta) {
						return meta.row + 1 +
							meta.settings._iDisplayStart;
					}
				},  {	
					sTitle : '计划名称',
					data : null,
					render : function(data, type, row, meta) {
						var tdStr =  "<a href='advertisement/detail?id="+data.id+"'>"+data.name+"</a>";
						return tdStr;
					}
				},  {	
					sTitle : '投放周期',
					data : null,
					render : function(data, type, row, meta) {
						var tdStr = ""+data.startdate+"——"+data.enddate+"";
						return tdStr;
					}
				}, {	
					sTitle : '期望展示数',
					data : "expectedNum"
				}, {
					sTitle : '真实展示数',
					data : "realdispalyNum"
				}, {
					sTitle : '点击数',
					data : "realclickNum"
				}, {
					sTitle : '点击率',
					data : "clickrate"
				}

			]
		}).api();
	//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
});
//去审核
function examine(id){
	doUserId=id;
	$("#e_company").html("");
	$("#e_name").html("");
	$("#e_phone").html("");
	$("#e_sex").html("");
	$("#e_nation").html("");
	$("#e_political").html("");
	$("#e_birthday").html("");
    $.ajax({
		type:"post",
		dataType:"json",
		url:"user/getUserById",
		data:{"id":id},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success:function(data){
			if(data.success=="success"){
				$("#e_company").html(data.user.companyName);
				$("#e_name").html(data.user.name);
				$("#e_phone").html(data.user.phone);
				$("#e_sex").html(data.user.sexStr);
				$("#e_nation").html(data.user.nation);
				$("#e_political").html(data.user.political);
				$("#e_birthday").html(data.user.birthday);
				$("#examineModal").modal({
					keyboard: false
				});
			}else{
				alert("获取服务信息失败！");
			}
		},
		error:function(){
			alert("获取服务信息失败！");
		}
	});
}
//修改状态
function changeState(state){
	var str="确认通过此审核吗？";
	if(state == -1)
		str="确认不通过此审核吗？该操作会删除此条申请！";
	if(confirm(str)){ 
		$.ajax({
			type:"post",
			dataType:"json",
			url:"user/changeState",
			data:{
				"id":doUserId,
				"state":state
				},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data.success=="success"){
					alert("操作成功！");
					$('#searchForm').submit();
				}else{
					alert("操作失败！");
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}
function toDownload(){
	window.location.href="advertisement/downloadAdvAnalysis";
}