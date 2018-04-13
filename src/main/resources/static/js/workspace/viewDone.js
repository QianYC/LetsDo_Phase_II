/**
 * 查看已经完成的图片的逻辑
 * 方法1:  根据projectId和userId拿到图片的id列表，调用方法2，将图片放进组件里
 *方法2先调用ajax把图片放到url里，再用jQuery操作html, 动态生成组件
 */



function getPictureIdList(){
	var userId = "user1";
	var projectId = "project1";
	$.ajax({
		url: "/workspace/viewDone/"+userId+"/"+projectId+"/getPictureIdList",
		type: "get",
		success: function(data){
			var list = data.split(";");
			var len = list.length;
			if(list[0]!==""){
				for(let i=0 ; i<len/2+1; i++){
					test_setPicture(list[i]);
				}
			}
		}
	});
}

function test_setPicture(pictureId){
	
	preHtml(pictureId ,function(){//这是回调函数
	var canvas = document.getElementById("canvas_"+pictureId);
	var ctx = canvas.getContext('2d');
//		ctx.strokeStyle = 'red';
//		ctx.fillRect(0,0,50, 50);
	})	
}


function setPicture(pictureId){
	alert("add");
	var userId = "user1";
	var projectId = "project1";
	
	
	
	//调用ajax
	$.ajax({
		url:"/workspace/viewDone/putPicture/"+userId+"/"+projectId+"/"+pictureId,
		type:"post"
		
		
	});
	
	
	preImage("/workspace/viewDone/putPicture/"+userId+"/"+projectId+"/"+pictureId,function(){
		ctx.drawImage(this,0,0);
	});
	
	
}

function preHtml(pictureId, callback){
	
     var txt = "<div class='item' >	"+		
					"<div class='item_image'>"+
						"<canvas  id = 'canvas_"+pictureId+"' class='canvas_image' ></canvas>"+
						"</div>"+
					"<div class='image_hover' onMouseMove='showHover(this)' onMouseOut='hideHover(this)' id='hover_"+pictureId+"'>"+
			           "<input class='btn_edit' type='button' value='修改' hidden='hidden' />"+
					"</div>"+
					"</div>"
	
	$("items").append(txt);
	
	callback();
	
}

function preImage(url, callback){//使用回调函数保证图片加载到url后才可以被使用
	var img = new Image();
	img.src = url;
	if(img.complete){
		callback.call(img);
		return;
	}
	img.onload = function(){
		callback.call(img);
	}
}


function showHover(that) {
	$("#"+that.id).children().show();
}

function hideHover(that) {
	$("#"+that.id).children().hide();
}