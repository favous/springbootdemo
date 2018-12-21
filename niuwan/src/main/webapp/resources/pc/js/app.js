$(function() {
  $(".navbar-expand-toggle").click(function() {
    $(".app-container").toggleClass("expanded");
    return $(".navbar-expand-toggle").toggleClass("fa-rotate-90");
  });
  return $(".navbar-right-expand-toggle").click(function() {
    $(".navbar-right").toggleClass("expanded");
    return $(".navbar-right-expand-toggle").toggleClass("fa-rotate-90");
  });
});

$(function() {
  return $('select').select2();
});

$(function() {
  return $('.toggle-checkbox').bootstrapSwitch({
    size: "small"
  });
});

$(function() {
  return $('.match-height').matchHeight();
});

$(function() {
  return $('.datatable').DataTable({
    "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
  });
});

$(function() {
  return $(".side-menu .nav .dropdown").on('show.bs.collapse', function() {
    return $(".side-menu .nav .dropdown .collapse").collapse('hide');
  });
});
//点击修改密码按钮弹出修改密码模态框
function modifyPwd(){
	$('#old_pwd').val("");
	$('#new_pwd1').val("");
	$('#new_pwd2').val("");
	keyUp($('#old_pwd'));
	keyUp($('#new_pwd1'));
	keyUp($('#new_pwd2'));
	$('#modifyPwdModal').modal({
		keyboard: false
	});
}
//跳转到修改当前登录帐号密码的方法
function toModifyPwd(){
	
	old_pwd=$.trim($('#old_pwd').val());//原始的密码
	new_pwd1=$.trim($('#new_pwd1').val());//新的密码
	new_pwd2=$.trim($('#new_pwd2').val());//新的重复密码
	
	var checkBoolean = true;
	//验证是否为空
	if(old_pwd==""){
		$('#old_pwd').parent().addClass("has-error");
		checkBoolean=false;
	}
	if(new_pwd1==""){
		$('#new_pwd1').parent().addClass("has-error");
		checkBoolean=false;
	}
	if(new_pwd2==""){
		$('#new_pwd2').parent().addClass("has-error");
		checkBoolean=false;
	}
	//验证密码是否相等
	if(new_pwd1!=new_pwd2){
		$('#new_pwd2').parent().addClass("has-error");
		checkBoolean=false;
	}
	if(checkBoolean==false){//若有错误则提示
		alert("您有项目未填写或两次密码不一致！");
	}else{//若无错误则通过ajax新增
		if(confirm("确认要修改密码吗？")){
			run_waitMe(".modal-body")
		$.ajax({
			url : "admin/modifyPWD",
			type : 'post',
			data : {
				pwd:old_pwd,
				pwd1:new_pwd1,
				pwd2:new_pwd2
				
			},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success : function(data) {
				$('.modal-body').waitMe('hide');
				if(data=="success"){//如果修改成功
					$('#modifyPwdModal').modal('hide');
					alert("密码修改成功，请重新登录！");
					window.location.href="admin/gotoLogin"; 
				}else if(data=="failed"){//如果修改失败
					$('#old_pwd').parent().addClass("has-error");
					alert("原始密码错误，请重新输入！");
				}else if(data=="diff"){
					$('#new_pwd2').parent().addClass("has-error");
					alert("两次密码输入不一致，请重新输入")
				}else if(data=="error"){
					alert("系统错误，请联系管理员");
				}
			},
			error:function(data){//如果未登录
				alert("请登录后再操作！");
				window.location.href="admin/gotoLogin"; 
		   }
		});
		}
	}
}

//当输入内容时，若原来为错误提示框，则去除红色边框
function keyUp(obj){
	if($(obj).parent().hasClass("has-error")){
		$(obj).parent().removeClass("has-error")
	}
}
//表单提交 运行此方法可显示 please wait提示框
function run_waitMe(c){
	$(c).waitMe({
		effect: "bounce",
		text: 'Please wait...',
		bg: 'rgba(255,255,255,0.7)',
		color:'#000',
		sizeW:'',
		sizeH:'',
		source: 'img.svg'
	});
}
