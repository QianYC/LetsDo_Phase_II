/**
 * 从服务器获取图片,为所有的标记界面提供服务
 */


function getNewPicture(){
	var pictureId = getNewPictureId();
	getPictureSize(pictureId);
	getNewPicture_(pictureId);
	refreshProgress();
}

function getNewPicture_(pictureId){
	//拿到图片
	//1.拿到一张图片的id
	var userId = getCookie("userId")||"wk1";
	var projectId =getCookie("projectId")||"pjid";
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
			success: function(data){
				
				var url = "/workspace/getNewPicture/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId;
				setCssBackground(url);
			}
		});
	}
}

function getPictureSize(pictureId){
	//获得图片的长宽属性，设置cookie
	var userId = getCookie("userId")||"wk1";
	var projectId =getCookie("projectId")||"pjid";
	var publisherId = getCookie("publisherId")||"pubid"
	$.ajax({
			url: "/workspace/getNewPictureSize/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId,
			type:"get",
		    async:false, //同步
			success: function(data){
				alert(data);
				var size = data.split(",");
				var width = parseInt(size[0]);
				var height = parseInt(size[1]);
				
				setCookie("pictureWidth", width);
				setCookie("pictureHeight", height);
			}
		});
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

function refreshProgress(){
	//提交图片后刷新进度
	var userId = getCookie("userId");
	var projectId = getCookie("projectId");
	var publisherId = getCookie("publisherId");
	var progress = 0;
	$.ajax({
		url: "/workspace/getProgress/"+userId+"/"+projectId+"/"+publisherId,
		type: "get",
		success: function(data){
			progress = parseInt(data);
			setProgress(progress);//这个方法在progress.js里
		}
	});
}