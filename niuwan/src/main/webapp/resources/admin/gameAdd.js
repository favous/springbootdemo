var hiddenUrl = $("#hiddenUrl").val();

//ICON图片验证及上传
function muploadImgicon() {
	upload("game/saveicon", document.getElementById("a_icon"), function(data) {
		$("#icon").val(data.iconPath);
		$('#b_picture').attr('href',hiddenUrl + data.iconPath);
	});
}
//展示图片验证及上传
function muploadImgpicture() {
	upload("game/savepicture", document.getElementById("a_picture"), function(data) {
		$("#picture").val(data.picturePath);
		$('#b_picture').attr('href',hiddenUrl+data.picturePath);
	});
}

// 更多图片及视频上传
function gameDetailImgUpload(node) {
	upload("game/saveDetailImg", node, function(data) {
		var tr = $(node).parents('tr');
		tr.find('input[name="detailImg"]').val(data.picturePath);
		tr.find('.view').attr('href',hiddenUrl+data.picturePath); 
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

// 通用图片验证及上传
function upload(url, input, callback, checkMore) {
	var _name = input.value;
	var _fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
	
	if (_fileName !== "png" && _fileName !== "jpg") {
		alert("上传图片格式不正确，请重新上传");
		return;
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
			if(data.success=="success"){
				callback(data); 
			}else{
				alert("上传失败");
			}
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
	
	fileInput.prop('id', fileInput.prop('id') + moreTrs.length);
	$(moreTrs[moreTrs.length - 1]).after(cloneTr);
	$('#moreTitleTd').prop('rowspan', $('#addgame table .moreImgVideoTr').length);
}

//保存game信息
function savegame () {

	
		$("#addgame").ajaxSubmit({
			type : "POST",
			dataType : "text",
			data: $("#addgame").serialize(),
			url : "game/savegame",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,
			success : function(data) {
			debugger;
				if(data=="success"){
					alert("新增成功");
					window.location.href="game/gameManage";
					
				}else{
					alert("新增失败");
				}
			}
		});
		
	}

