/**
 * 为区域标注提供加载requirement和提交标记的服务
 */

function setRequirement(){
	//加载要求
	var req = getCookie("projectRequirement")||"钱宇辰就是猪猪呀！";//需要调用ajax获得
	$("#requirement").val(req);
}

function submitTag(){
	//提交标签
	//这种标记只有图片没有标签
	var type = "area";
	var userId = getCookie("userId");
	var projectId = getCookie("projectId");
	var publisherId = getCookie("publisherId");
	var pictureId = getCookie("pictureId");
	var remark = "";
	var width = getCookie("pictureWidth");
	var height = getCookie("pictureHeight");
	var canvas = document.getElementById("penal");
	var tag = canvas.toDataURL("image/png");
	var base64 = tag.substring(22);
	
	$.ajax({
		url: "/workspace/submit/"+type+"/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId,
		type: "post",
		data: {
			'base64' : base64,
			'remark' : remark,
			'width' : width,
			'height' : height,
		},
		success: function(){
			alert("上传成功");
			clearCanvas();
			getNewPicture();//这个方法在getPicture.js里面
		}
	});
	
}

function clearCanvas(){
	//清空画板
	clearAllMark();//这个方法在canvas.js里
}