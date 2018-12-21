//图片验证1
function muploadImg1() {

	var _name, _fileName, commodityFile;
	commodityFile = document.getElementById("a_imgUrl1");
	_name = commodityFile.value;
	_fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	if (_fileName !== "png" && _fileName !== "jpg") {
		alert("上传图片格式不正确，请重新上传");
	}else{
		$("#img1").val(2);
	}
	
}
//图片验证2
function muploadImg2() {

	var _name, _fileName, commodityFile;
	commodityFile = document.getElementById("a_imgUrl2");
	_name = commodityFile.value;
	_fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	if (_fileName !== "png" && _fileName !== "jpg") {
		alert("上传图片格式不正确，请重新上传");
	}else{
		$("#img2").val(2);
	}
	
}
//图片验证3
function muploadImg3() {

	var _name, _fileName, commodityFile;
	commodityFile = document.getElementById("a_imgUrl3");
	_name = commodityFile.value;
	_fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	if (_fileName !== "png" && _fileName !== "jpg") {
		alert("上传图片格式不正确，请重新上传");
	}else{
		$("#img3").val(2);	
	}
	
}
// 提交修改
function saveAdvert() {
	if (confirm("确认修改吗？")) {
		var gameUrl1 = $("#gameUrl1").val();//游戏链接1
		var gameUrl2 = $("#gameUrl2").val();//游戏链接2
		var gameUrl3 = $("#gameUrl3").val();//游戏链接3
		var img1 = $("#img1").val();//是否修改图片1 1:未修改 2：修改
		var img2 = $("#img2").val();//是否修改图片2 1:未修改 2：修改
		var img3 = $("#img3").val();//是否修改图片3 1:未修改 2：修改

		$("#advert").ajaxSubmit({
			type : "POST",
			dataType : "text",
			data : $("#advert").serialize(),
			url : "advert/saveadvert",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success : function(data) {
				if (data == "success") {
					alert("添加成功！");
					window.location.href = "advert/advertManage"

				} else {
					alert("修改失败！");
				}
			}
		});
	}
}
