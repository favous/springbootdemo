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
		//查询自身的计划
		getMyArrange();
 });
 //查询自身的计划
 function getMyArrange(){
	 $.ajax({
			type:"post",
			dataType:"json",
			url:"advertisement/getMyArrange",
			data:{
				"id":$("#id").val()
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
 //查询非自身的计划
 function getNotMyArrange(){
	 $.ajax({
			type:"post",
			dataType:"json",
			url:"advertisement/getNotMyArrange",
			data:{
				"startTime":$("#startTime").val(),
				"endTime":$("#endTime").val(),
				"id":$("#id").val(),
				"type":$("#advtype").val()
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

