/**
 * 用于提交tag的逻辑
 */
function submit(){
	//提交标记
	var projectType = getCookie("projectType") || "area";
	var userId = getCookie("userId") || "user1";
	var projectId = getCookie("projectId") || "project1" ;
	var pictureId = getCookie("pictureId") || "picture1" ;
	
	var canvas = document.getElementById("penal");
	var tag = canvas.toDataURL("image/png");
	var base64 = tag.substring(22);
	
	var pictureWidth = getCookie("pictureWidth");
	var pictureHeight = getCookie("pictureHeight");
	
	var rm = getTips() ;//remark,调用方法获得
	
	$.ajax({
		url: "/workspace/submit/"+projectType+"/"+userId+"/"+projectId+"/"+pictureId,
		type: "post",
		data:{
			'b64' : base64,
			'remark' : rm,
			'width' : pictureWidth,
			'height' : pictureHeight,
		}
	});
}

function getTips(){
	//返回标记
	//如果是area、mark类型，那么返回空串
	//如果是tips类型，返回 tip1;tip2(不包括tip名称只有内容)
	var projectType = getCookie("projectType") || "area";
	if(projectType === "tips"){
		//从html拿数据
		return "tmp1;tmp2";
	}
	else{
		return "";
	}
}