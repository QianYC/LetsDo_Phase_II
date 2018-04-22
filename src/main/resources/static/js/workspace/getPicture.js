/**
 * 从服务器获取图片,为所有的标记界面提供服务
 */


function getNewPicture(){
	var pictureId = getNewPictureId();
	alert("id "+ pictureId);
	getNewPicture_(pictureId);
}

function getNewPicture_(pictureId){
	//拿到图片
	//1.拿到一张图片的id
	var userId = getCookie("userId")||"wk1";
	var projectId ="pjid";// getCookie("projectId")||"pjid";
	var publisherId = getCookie("publisherId")||"pubid"
	if(pictureId === ""){
			//没有图片或发生异常
			alert("已完成所有图片！");
	}
	else{
		//2.拿到图片
		setCookie("pictureId", pictureId);
		$.ajax({
			url: "/workspace/getNewPicture/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId,
			type:"get",
			success: function(){
				//alert(data);
				var url = "/workspace/getNewPicture/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId;
				setCssBackground(url);
			}
		});
	}
}

function getNewPictureId(){
	//得到一个picture的id
	var userId = getCookie("userId")||"wk1";
	var projectId ="pjid";// getCookie("projectId")||"pjid";
	var publisherId = getCookie("publisherId")||"pubid";
	alert(userId+" "+projectId+" "+publisherId);
	var id = "";
	$.ajax({
		url: "/workspace/getNewPictureId/"+userId+"/"+projectId+"/"+publisherId,
		type: "get",
		async:false, //同步
		success: function(data){
			id = data;
			
		}
	});
	return id;
}

function setCssBackground(url){
	//图片放入url以后,用css加载为背景
	alert("url "+url);
	$("#penal").css("background-image", "url('"+url+"')");
}