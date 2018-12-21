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


var setting={
		view : {
			showIcon : true,
			selectedMulti : true,     //可以多选
			showLine : false,
			expandSpeed : 'fast',
			dblClickExpand : false
		},
		check : {
			enable : true,
			chkStyle : "checkbox",    //复选框
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		data : {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "p_id",
				rootPId: ""
			}
		},
		callback : {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				nodes=treeObj.getCheckedNodes(true),  
				v="";  
				for(var i=0;i<nodes.length;i++){  
					v+=nodes[i].id + ",";  
				}
				$("#mid").val(v)
				alert(v);
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					return true;
				}
			},
			onCheck: function onCheck(e,treeId,treeNode){  
				var treeObj=$.fn.zTree.getZTreeObj("tree"),  
				nodes=treeObj.getCheckedNodes(true),  
				v="";  
				for(var i=0;i<nodes.length;i++){  
					v+=nodes[i].id + ",";  
				}
				$("#mid").val(v)
			},
			onClick:function(){}
		}
}
function toAddRole(){
	
}
function toModify(id){
	if(id!=null&&id!=""){//表示修改
		run_waitMe(".slide-body");
		$("#modifyAdd").html("修改角色权限");
			$.ajax({
				type:"post",
				dataType:"json",
				data:{"id":id},
				url:"admin/getRoleById",
				success:function(data){
					$('.slide-body').waitMe('hide');
					if(data.success=="success"){
					var role=data.role;
					$("#rid").val(role.id);
					$("#name").val(role.role_name);
					var menus=data.menus;
					$.fn.zTree.init($("#tree"), setting, menus); 
					$("#modifyModal").modal("show")
				}else{
					alert("获取角色权限信息失败,请联系管理员！");
				}
				}
			});
		}else{//表示新增
			$("#modifyAdd").html("新增角色授权");
				$("#name").val('');
				$("#rid").val("");
				$.ajax({
					type:"post",
					dataType:"json",
					url:"admin/getAllMenu",
					success:function(data){
						$('.side-body').waitMe('hide');
						if(data.success=="success"){
						var menus=data.menus;
						$.fn.zTree.init($("#tree"), setting, menus);  
						$("#modifyModal").modal("show")
						}else{
							alert("获取权限失败，请联系管理员！");
						}
					}
				});
		}
}

function modifyRole(){
	var name=$("#name").val();
	var mid=$("#mid").val();
	var rid=$("#rid").val();
	if(name==null ||name.trim()==""){
		alert("请输入角色名！");
		$('#name').parent().addClass("has-error");
		return false;
	}
	if(rid!=null&&rid!=""){//表示修改
		if(confirm("确定要修改角色吗？")){
			run_waitMe(".modal-body");
			$.ajax({
				type:"post",
				dataType:"text",
				data:$("#modifyRole").serialize(),
				url:"admin/updateRole",
				success:function(data){
					if(data=="success"){
						alert("修改成功！");
						window.location.href="admin/roleManage"
					}else if(data=="exist"){
						$('.modal-body').waitMe('hide');
						alert("账号已存在");
					}else{
						$('.modal-body').waitMe('hide');
						alert("修改失败，请联系管理员！")
					}
				}
			});
		}
	}else{//表示新增
		if(mid==null||mid.trim()==""){
			alert("请选择权限");
			return false;
		}
		if(confirm("确定要新增角色吗？")){
			run_waitMe(".modal-body");
			$.ajax({
				type:"post",
				dataType:"text",
				data:$("#modifyRole").serialize(),
				url:"admin/addRole",
				success:function(data){
					if(data=="success"){
						alert("新增成功！");
						window.location.href="admin/roleManage"
					}else if(data=="exist"){
						$('.modal-body').waitMe('hide');
						alert("账号已存在");
					}else{
						$('.modal-body').waitMe('hide');
						alert("新增失败，请联系管理员！");
					}
				}
			});
		}
	}
}

function toDelete(id){
	if(confirm("确定要删除该角色吗？该操作将清除所有该角色下的管理员账号")){
		run_waitMe(".side-body");
		$.ajax({
			type:"post",
			dataType:"text",
			data:{"id":id},
			url:"admin/deleteRole",
			success:function(data){
				if(data=="success"){
					alert("删除成功");
					window.location.href="admin/roleManage"
				}else{
					$('.side-body').waitMe('hide');
					alert("删除失败，请联系管理员！");
				}
			}
		});
	}
}
