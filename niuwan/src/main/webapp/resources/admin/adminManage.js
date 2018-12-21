$(document).ready(function(){
	$('#dataTable').DataTable({
	 	"bDeferRender":false,  
  		"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
  		responsive: true,
        "bLengthChange": false, //是否可选择行数
        "bInfo": true,//是否显示页脚信息，DataTables插件左下角显示记录数  
        "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变 
        "bSort" : false,//是否启动各个字段的排序功能  
        "bFilter" : false,//是否启动过滤、搜索功能 
        "iDisplayLength" : 10, //默认显示的记录数  
        "oLanguage": { //国际化配置  
            "sProcessing" : "正在获取数据，请稍后...",    
            "sLengthMenu" : "显示 _MENU_ 条",    
            "sZeroRecords" : "没有内容",    
            //"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
            "sInfo" : "总计_TOTAL_条记录",    
            "sInfoEmpty" : "记录数为0",    
            "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
            "sInfoPostFix" : "",    
            "sSearch" : "搜索",    
            "sUrl" : "",    
            "oPaginate": {    
                "sFirst" : "第一页",    
                "sPrevious" : "上一页",    
                "sNext" : "下一页",    
                "sLast" : "最后一页"    
            }  
        },  
   });
});
function toAddAdmin(){//前往新增
	$("#adminName").val("");
	$("#name").val("");
	$("#aid").val("");
	$("#role").val("");
	$("#hotel").val("");
	$("#phone").val("");
	$("#modifyAdd").html("新增账号");
	getRole();
	$("#modifyModal").modal("show");
}

function toModify(id){//修改之前的查询
	run_waitMe(".slide-body");
	$("#modifyAdd").html("修改账号");
	getRole();
	getGroups();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"admin/getAdminById",
		data:{"id":id},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success:function(data){
			$('.side-body').waitMe('hide')
			if(data.success=="success"){
				$("#card").val(data.ad.card);
				$("#aid").val(data.ad.id);
				$("#role").val(data.ad.role.id);
				if(data.ad.groups_id!=null)
					$("#groups").val(data.ad.groups_id);
				$("#modifyModal").modal({
					keyboard: false
				});
			}else{
				alert("拉取账号信息失败");
			}
				
		}
	});
}
function getGroups(){//获取所有角色
	$.ajax({
		type:"post",
		dataType:"json",
		url:"groups/getAllGroups",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success:function(data){
			if(data.success=="success"){
				var str="<select class='form-control' id='groups' name='groups_id'>";
				str+="<option value='0'>总管理</option>";
				var groupss=data.groupss;
				$.each(groupss,function(idx,obj){
					str+="<option value='"+obj.id+"'>"+obj.name+"</option>";
				});
				str+="</select>"
				$("#groupsdiv").html(str);
			}else{
				alert("获取组织信息失败！");
				return false;
			}
		}
	});
}
function getRole(){//获取所有角色
	$.ajax({
		type:"post",
		dataType:"json",
		url:"admin/getAllRole",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success:function(data){
			if(data.success=="success"){
				var str="<select class='form-control' id='role' name='role.id'>";
				var roles=data.roles;
				$.each(roles,function(idx,obj){
					str+="<option value='"+obj.id+"'>"+obj.role_name+"</option>";
				});
				str+="</select>"
				$("#rolediv").html(str);
			}else{
				alert("获取角色信息失败！");
				return false;
			}
		}
	});
}
function modifyAdmin(){
	var aid=$("#aid").val();
	var pwd=$("#pwd").val();
	var pwd1=$("#pwd1").val();
	var adminName=$("#card").val();
	if(adminName==null||adminName.trim()==""){//判断账号是否为空
		alert("账号不能为空");
		$('#adminName').parent().addClass("has-error");
		return false;
	}
	if(pwd!=null&&pwd.trim()!=""){//如果输入了新的密码
		if(pwd!=pwd1){//判断两次密码是否一致
			alert("两次密码输入不一致");
			$('#pwd1').parent().addClass("has-error");
			return false;
		}
	}
	if(aid!=null &&aid!="" ){//如果id不为空 则表示修改
		if(confirm("确认修改吗？")){
		run_waitMe(".modal-body");
		$.ajax({
			type:"post",
			dataType:"text",
			data:$("#modifyAdmin").serialize(),
			url:"admin/updateAdmin",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				$('.modal-body').waitMe('hide')
				if(data=="success"){
					alert("修改成功！");
					window.location.href="admin/adminManage"
				}else if(data=="error"){
					alert("修改失败！");
				}else{
					alert("账号已存在！");
					$('#card').parent().addClass("has-error");
				}
			}
		});
	}
	}else{//如果id为空 则表示新增
		if(confirm("确定要添加吗？")){
		run_waitMe(".modal-body");
		$.ajax({
			type:"post",
			dataType:"text",
			data:$("#modifyAdmin").serialize(),
			url:"admin/addAdmin",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				$('.modal-body').waitMe('hide')
				if(data=="success"){
					alert("添加成功！");
					window.location.href="admin/adminManage"
				}else if(data=="error"){
					alert("添加失败！");
				}else if(data=="exist"){
					alert("账号已存在！");
				}
			}
		});
		}
	}
}
function toDelete(id){//删除
	if(confirm("确认要删除吗？此操作不可恢复")){
		run_waitMe(".slide-body");
		$.ajax({
			type:"post",
			dataType:"text",
			data:{"id":id},
			url:"admin/deleteAdmin",
			success:function(data){
				$('.side-body').waitMe('hide')
				if(data=="success"){
					alert("删除成功！");
					window.location.href="admin/adminManage"
				}else{
					alert("删除失败！");
				}
			}
		});
	}
}


function changeState(id,state){
	var msg;
	if(state==0){
		msg="禁用";
	}else if(state==1){
		msg="取消禁用/删除";
	}else if(state==-1){
		msg="删除";
	}
	if(confirm("确定要"+msg+"吗？"))
	$.ajax({
		type:"post",
		dataType:"text",
		data:{"id":id,"state":state},
		url:"admin/changeState",
		success:function(data){
			if(data=="success"){
				alert(msg+"成功!");
				window.location.href="admin/adminManage";
			}else{
				alert("操作失败!");
			}
		}
	});
}
