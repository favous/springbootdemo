
var today = new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
 $(document).ready(function(){
	 $("#timeDiv").empty();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime==null||endTime==null)
			return ;
		if(startTime>endTime)
			return;
		var diff = DateDiff(startTime,endTime);
		if(diff>30){
			alert("跨度不得大于30天");
			return;
		}
		for(var i =0;i<=diff;i++){
			var str = "<div class=\"row\">	" +
					"<span class=\"col-sm-1 text-center\">";
				afterTime = addDay(i,new Date(Date.parse(startTime))).Format("yyyyMMdd");
				str +=today[addDay(i,new Date(Date.parse(startTime))).getDay()];
				str +="<br>";
				str +=addDay(i,new Date(Date.parse(startTime))).Format("yyyy-MM-dd");
				str +="</span>";
				str +="<div class=\"col-sm-11\">";
				str +="<span  class=\"badge\" id=\""+afterTime+"00\">00:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"01\">01:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"02\">02:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"03\">03:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"04\">04:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"05\">05:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"06\">06:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"07\">07:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"08\">08:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"09\">09:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"10\">10:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"11\">11:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"12\">12:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"13\">13:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"14\">14:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"15\">15:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"16\">16:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"17\">17:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"18\">18:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"19\">19:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"20\">20:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"21\">21:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"22\">22:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"23\">23:00</span>";
				str +="</div>";
			str +="</div>";
			$("#timeDiv").append(str);
		}
		// 将之前的标记为已完成
		beforeNow();
		// 查询现有的计划
		getNotMyArrange();
		// 查询自身的计划
		getNowTypeMyArrange();
		if($("#nowtype").val()==1){
			$("#kpdiv1").show();
			$("#kpdiv2").show();
			$("#bannerdiv").hide();
			$("#cpdiv").hide();
		}else if($("#nowtype").val()==2){
			$("#kpdiv1").hide();
			$("#kpdiv2").hide();
			$("#bannerdiv").show();
			$("#cpdiv").hide();
		}else if($("#nowtype").val()==3){
			$("#kpdiv1").hide();
			$("#kpdiv2").hide();
			$("#bannerdiv").hide();
			$("#cpdiv").show();
		}
 });
 function timeOK(){
		$("#timeDiv").empty();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime==null||endTime==null)
			return ;
		if(startTime>endTime)
			return;
		var diff = DateDiff(startTime,endTime);
		if(diff>30){
			alert("跨度不得大于30天");
			return;
		}
		for(var i =0;i<=diff;i++){
			var str = "<div class=\"row\">	" +
					"<span class=\"col-sm-1 text-center\">";
				afterTime = addDay(i,new Date(Date.parse(startTime))).Format("yyyyMMdd");
				str +=today[addDay(i,new Date(Date.parse(startTime))).getDay()];
				str +="<br>";
				str +=addDay(i,new Date(Date.parse(startTime))).Format("yyyy-MM-dd");
				str +="</span>";
				str +="<div class=\"col-sm-11\">";
				str +="<span  class=\"badge\" id=\""+afterTime+"00\">00:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"01\">01:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"02\">02:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"03\">03:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"04\">04:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"05\">05:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"06\">06:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"07\">07:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"08\">08:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"09\">09:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"10\">10:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"11\">11:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"12\">12:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"13\">13:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"14\">14:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"15\">15:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"16\">16:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"17\">17:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"18\">18:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"19\">19:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"20\">20:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"21\">21:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"22\">22:00</span>";
				str +="<span  class=\"badge\" id=\""+afterTime+"23\">23:00</span>";
				str +="</div>";
			str +="</div>";
			$("#timeDiv").append(str);
		}
		// 将之前的标记为已完成
		beforeNow();
		// 查询现有的计划
		getNotMyArrange();
		// 查询自身的计划
		getNowTypeMyArrange();
	}
 // 查询自身的计划
 function getNowTypeMyArrange(){
	 $.ajax({
			type:"post",
			dataType:"json",
			url:"advertisement/getNowTypeMyArrange",
			data:{
				"id":$("#id").val(),
				"type":$('input[name="type"]:checked').val()
				},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data.success=="success"){
					var advPlanTimes = data.advPlanTimes;
					$.each(advPlanTimes,function(idx,obj){
						var idStr=new Date(Date.parse(obj.tdate)).Format("yyyyMMdd")+obj.thour;
						$("#"+idStr).addClass("checked");
					});
				}else{
					alert("获取自身计划信息失败！");
				}
			},
			error:function(){
				alert("获取自身计划信息失败！");
			}
		});
 }
 // 查询非自身的计划
 function getNotMyArrange(){
	 $.ajax({
			type:"post",
			dataType:"json",
			url:"advertisement/getNotMyArrange",
			data:{
				"startTime":$("#startTime").val(),
				"endTime":$("#endTime").val(),
				"id":$("#id").val(),
				"type":$('input[name="type"]:checked').val()
				},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success:function(data){
				if(data.success=="success"){
					var advPlanTimes = data.advPlanTimes;
					$.each(advPlanTimes,function(idx,obj){
						var idStr=new Date(Date.parse(obj.tdate)).Format("yyyyMMdd")+obj.thour;
						$("#"+idStr).addClass("used");
					});
				}else{
					alert("获取计划信息失败！");
				}
			},
			error:function(){
				alert("获取计划信息失败！");
			}
		});
 }
// 今日之前的标识成已过期
function beforeNow(){
	date =new Date();
	var nowDate   = date.Format("yyyyMMddHH");
	$.each($(".badge"), function(i, item){      
		if(parseFloat(item.id)<parseFloat(nowDate)){
			$(this).addClass("go");
		}
	});  
}
// 时间段被点击
$('body').on('click', '	.badge', function(){
	if($(this).hasClass("used")&&$(this).hasClass("checked")){// 如果是冲突
		$(this).removeClass("checked");
	}else if($(this).hasClass("used")){// 如果是已被使用
		
	}else if($(this).hasClass("go")){// 如果是已过期
		
	}else if($(this).hasClass("checked")){// 如果是已选中
		$(this).removeClass("checked");
	}else if($(this).hasClass("error")){// 如果是冲突
		
	}else{// 如果是可用
		$(this).addClass("checked");
	}
})
// 计算日期+1
function addDay(dayNumber, date) {
    date = date ? date : new Date();
    var ms = dayNumber * (1000 * 60 * 60 * 24)
    var newDate = new Date(date.getTime() + ms);
    return newDate;
}
// 计算日期差
function DateDiff(sDate1, sDate2) {  // sDate1和sDate2是yyyy-MM-dd格式
    var aDate, oDate1, oDate2, iDays;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  // 转换为yyyy-MM-dd格式
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); // 把相差的毫秒数转换为天数
    return iDays;  // 返回相差天数
}
// 日期格式化
Date.prototype.Format = function (fmt) { // author: meizz
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$("input:radio[name=type]").change(function () {
	var vall= $('input:radio[name="type"]:checked').val(); 
	if(vall==1){
		document.getElementById("kpdiv1").style.display = "block";
		document.getElementById("kpdiv2").style.display = "block";
		document.getElementById("bannerdiv").style.display = "none";
		document.getElementById("cpdiv").style.display = "none";	
		document.getElementById("kp1Img").style.display = "block";	
		document.getElementById("kp2Img").style.display = "block";	
		document.getElementById("bannerImg").style.display = "none";	
		document.getElementById("cpImg").style.display = "none";	
	}else if(vall==2){
		
		document.getElementById("kpdiv1").style.display = "none";
		document.getElementById("kpdiv2").style.display = "none";
		document.getElementById("bannerdiv").style.display = "block";
		document.getElementById("cpdiv").style.display = "none";
		document.getElementById("kp1Img").style.display = "none";	
		document.getElementById("kp2Img").style.display = "none";	
		document.getElementById("bannerImg").style.display = "block";	
		document.getElementById("cpImg").style.display = "none";
	}else{
		document.getElementById("kpdiv1").style.display = "none";
		document.getElementById("kpdiv2").style.display = "none";
		document.getElementById("bannerdiv").style.display = "none";
		document.getElementById("cpdiv").style.display = "block";
		document.getElementById("kp1Img").style.display = "none";	
		document.getElementById("kp2Img").style.display = "none";	
		document.getElementById("bannerImg").style.display = "none";	
		document.getElementById("cpImg").style.display = "block";
	}
	timeOK();
});

//图片验证及上传
function muploadImgiphone8(type) {
		var hiddenUrl = $("#hiddenUrl").val();
		var _name, _fileName, commodityFile;
	if(type==1){
		commodityFile = document.getElementById("iphone8");
		$("#imgType").val(1);
	}else if(type==2){
		commodityFile = document.getElementById("iphonex");
		$("#imgType").val(2);
	}else if(type==3){
		commodityFile = document.getElementById("banner");
		$("#imgType").val(3);
	}else if(type==4){
		commodityFile = document.getElementById("cp");
		$("#imgType").val(4);
	}
	
	
	_name = commodityFile.value;
	_fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	if (_fileName !== "png" && _fileName !== "jpg") {
		alert("上传图片格式不正确，请重新上传");
	}else{
		$("#modifyAdvForm").ajaxSubmit({
			type : "POST",
			dataType : "json",
			data: {},
			url : "advertisement/saveicon",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success : function(data) {
				if(data.success=="success"){
					if(data.type==1){
						$("#a_kp1Img").html("<img src='"+data.outImgPreFixUrl+data.picPath+"'  width='140px' height='246px' style='top: 42px;left: 26px;position: absolute'>");
		  				$("#b_kp1Img").val("");
						$("#b_kp1Img").val(data.picPath);
					
					}else if(data.type==2){
					
						$("#a_kp2Img").html("<img src='"+data.outImgPreFixUrl+data.picPath+"'  width='140px' height='246px'  style='top: 42px;left: 26px;position: absolute'>");
  						$("#b_kp2Img").val("");
						$("#b_kp2Img").val(data.picPath);
					}else if(data.type==3){
						$("#a_bannerImg").html("<img src='"+data.outImgPreFixUrl+data.picPath+"'  width='83px' height='50px' style='top: 71px;left: 55px;position: absolute'>");
	  					$("#b_bannerImg").val("");
						$("#b_bannerImg").val(data.picPath);
					}else if(data.type==4){
						$("#a_cpImg").html("<img src='"+data.outImgPreFixUrl+data.picPath+"' width='139px' height='48px'style='top: 205px;left: 70px;position: absolute'>");
  						$("#b_cpImg").val("");
						$("#b_cpImg").val(data.picPath);
					}
					
					/*$("#icon").val(data.iconPath);
					$('#b_icon').attr('href',hiddenUrl+data.iconPath); */
				}else{
					alert("上传失败");
				}
			}
		});
	}
}


//保存
function modifyAdv() {
	var name = $.trim($("#name").val());
	var startTime = $.trim($("#startTime").val());
	var endTime = $.trim($("#endTime").val());
	var expectedNum = $.trim($("#expectedNum").val());
	var totalNum = $.trim($("#totalNum").val());
	var totaldisNum = $.trim($("#totaldisNum").val());
	 var radio_tag =$.trim( document.getElementsByName("type"));
	    for(var i=0;i<radio_tag.length;i++){
	        if(radio_tag[i].checked){
	            var checkvalue = radio_tag[i].value;  
	        }
	    }
	    if(checkvalue==1){
	    	var img =$("#b_kp1Img").val(); 
	    	var img2 =$("#b_kp2Img").val();
	    }else if(checkvalue==2){
	    	var img =$("#b_bannerImg").val(); 
	    }else if(checkvalue==3){
	    	var img =$("#b_cpImg").val(); 
	    }
	    var checkedTimes = new Array();//已选中时间段
		$.each($(".badge.checked"), function(i, item){  
			if(!$(this).hasClass("used"))
				checkedTimes.push(item.id);
		});  
		console.log(checkedTimes);
		$("#timeList").val(checkedTimes)
		var link =$.trim($("#link").val()); 
		var introduction =$.trim($("#introduction").val());
		var dispalyNum =$.trim($("#dispalyNum").val());
		var clickNum =$.trim($("#clickNum").val());
		//重置清空"has-error",重新判断
		$('#name').parent().removeClass("has-error");
		$('#dispalyNum').parent().removeClass("has-error");
		$('#clickNum').parent().removeClass("has-error");
		$('#link').parent().removeClass("has-error");
		$('#introduction').parent().removeClass("has-error");
		$('#totaldisNum').parent().removeClass("has-error");
		$('#totalNum').parent().removeClass("has-error");
		//验证是否为空
		if($("#timeList").val()==""){
			alert("未选择时间");
			return false;
		}
		if(name==""){
			$('#name').parent().addClass("has-error");
			alert("计划名称不能为空");
			return false;
		}
		if(totaldisNum==""){
			$('#totaldisNum').parent().addClass("has-error");
			alert("总展示数不能为空");
			return false;
		}
		if(totalNum==""){
			$('#totalNum').parent().addClass("has-error");
			alert("总点击数不能为空");
			return false;
		}
		if(link==""){
			$('#link').parent().addClass("has-error");
			alert("落地链接不能为空");
			return false;
		}
		if(dispalyNum==""){
			$('#dispalyNum').parent().addClass("has-error");
			alert("展示数不能为空");
			return false;
		}
		if(clickNum==""){
			$('#clickNum').parent().addClass("has-error");
			alert("点击数不能为空");
			return false;
		}
		if(introduction==""){
			$('#introduction').parent().addClass("has-error");
			alert("简单介绍不能为空");
			return false;
		}
		if(expectedNum==""){
			$('#expectedNum').parent().addClass("has-error");
			alert("期望展示数不能为空");
			return false;
		}
		var x=parseInt(dispalyNum);
		if(isNaN(x)|| x<1 || x<dispalyNum || x!=dispalyNum){ //判断库存是否为正整数
			alert("曝光次数必须为正整数"); 
			document.getElementById("dispalyNum").focus(); 
			return false;
		}
		var z=parseInt(clickNum);
		if(clickNum!=""){
			if(isNaN(z)|| z<1 || z<clickNum){ //判断新增库存是否为正整数
				alert("点击 次数必须为正整数"); 
				document.getElementById("clickNum").focus(); 
				return false;
			}
		}
		var c=parseInt(expectedNum);
		if(expectedNum!=""){
			if(isNaN(c)|| c<1 || c<expectedNum){ //判断新增库存是否为正整数
				alert("期望点击数必须为正整数"); 
				document.getElementById("expectedNum").focus(); 
				return false;
			}
		}
		$("#modifyAdvForm").ajaxSubmit({
	        type : "POST",
	        dataType : "text",
	        data:$("#modifyAdvForm").serialize(),
	        url : "advertisement/advUpdate",
	        success : function(data) {//成功的话
	        	alert("修改成功");
	        	window.location.href="advertisement/advPlanManage";
	        },
			error: function(request) {  //失败的话
				
				alert("修改失败，请联系管理员！");
		 	},  
	    });
}

