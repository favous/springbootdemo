
//ICON图片验证及上传
function muploadImgicon() {
	uploadImg("game/saveicon", document.getElementById("a_icon"), function(data) {
		$("#icon").val(data.iconPath);
		$('#b_picture').attr('href',data.outImgPreFixUrl + data.iconPath);
	});
}
//展示图片验证及上传
function muploadImgpicture() {
	uploadImg("game/savepicture", document.getElementById("a_picture"), function(data) {
		$("#picture").val(data.picturePath);
		$('#b_picture').attr('href',data.outImgPreFixUrl+data.picturePath);
	});
}

function uploadImg(url, input, callback) {
	upload(url, input, callback, ['png', 'jpg', 'jpeg', 'png']);
}

// 更多图片及视频上传
function gameDetailImgUpload(node) {
	uploadImgVideo("game/saveDetailImg", node, function(data) {
		var tr = $(node).parents('tr');
		var block = $(node).attr('block');
		
		var inputImg = tr.find('input[name="' + block + '"]')
		inputImg.val(data.picturePath);
		
		var view = tr.find('.' + block)
		view.attr('href', data.outImgPreFixUrl + data.picturePath); 
		$(view).find('img').attr('src', data.outImgPreFixUrl + data.picturePath); 
		
	}, function() {
		var noVideo = true;
		$('.moreImgVideoTr .view').each(function() {
			let myreg = /^https?.+\.(swf|avi|flv|mpg|rm|mov|wav|asf|3gp|mp4|mkv|rmvb)$/i;
			if(myreg.test(this.value)){
				alert('已有视频不可以再上传');
				noVideo = false;
				return false;
			}
		});
		return noVideo;
	});
}

function uploadImgVideo(url, input, callback) {
	upload(url, input, callback, ['png', 'jpg', 'jpeg', 'png', 'mp4', 'avi', 'rmvb', '3gp']);
}

// 通用验证及上传
function upload(url, input, callback, checkSuffix, checkMore) {
	var _name = input.value;
	var _fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	
	if (checkSuffix && checkSuffix.length < 0) {
		if (checkSuffix.indexOf(_fileName)) {
			alert("上传格式不正确，请重新上传");
			return;
		}
	}
	
	if (checkMore && !checkMore()) {
		return;
	}
	
	var form = createUploadForm(input);
	
	form.ajaxSubmit({
		type : "POST",
		dataType : "json",
		data: {},
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success : function(data) {
			form.remove();
			if(data.success=="success"){
				callback(data); 
			}else{
				alert("上传失败");
			}
		}, error : function() {			
			form.remove();
		}
	});
}

function createUploadForm(fileElement, data) {
	var id = new Date().getTime();
	var formId = 'jUploadForm' + id;
	var fileId = 'jUploadFile' + id;
	var form = jQuery('<form  action="" method="POST" name="' + formId
			+ '" id="' + formId + '" enctype="multipart/form-data"></form>');
	if (data) {
		for ( var i in data) {
			jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
		}
	}

	var newElement = jQuery(fileElement).clone();
	jQuery(newElement).appendTo(form);

	jQuery(form).css('position', 'absolute');
	jQuery(form).css('top', '-1200px');
	jQuery(form).css('left', '-1200px');
	jQuery(form).appendTo('body');
	return form;
}

function deductRow(node) {
	$(node).parents('tr').remove();
	$('#moreTitleTd').prop('rowspan', $('#addgame table .moreImgVideoTr').length);
}

function addRow(node) {
	var moreTrs = $(node).parents('table').find('.moreImgVideoTr');
	var cloneTr = $('#template .moreImgVideoTr').clone();
	var fileInput = cloneTr.find('input[type="file"]');
	
	if (moreTrs.length >= 6) {
		return;
	}
	
	$(moreTrs[moreTrs.length - 1]).after(cloneTr);
	$('#moreTitleTd').prop('rowspan', $('#addgame table .moreImgVideoTr').length);
}


//保存game信息
function updategame() {
	var url = $('#opType').val() == '1' ? "game/savegame" : "game/updategame";
	
	$("#addgame").ajaxSubmit({
		type : "POST",
		dataType : "text",
		data : $("#addgame").serialize(),
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		cache : false,
		success : function(data) {

			if (data == "success") {
				alert("修改成功");
				window.location.href = "game/gameManage";
			} else {
				alert("修改失败");
			}
		}
	});

}






