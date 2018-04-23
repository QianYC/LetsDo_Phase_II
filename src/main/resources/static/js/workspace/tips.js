/**
 * 为打标签标注提供逻辑
 */



function setInput(){
	//加载图片时调用
	//设置标签输入框
	var tips = getCookie("tipList");//调用方法获得;cookie中也有,用，分割；cookie会截断；
	var list = tips.split(",");
	var len = list.length;
	var id = "";
	for(let i=0 ; i<len; i++){
		id = "tip"+i;
		var txt = "<div class='col-3 input-effect'>"+
		      "<input id='"+id+"' class='effect-21' type='text' placeholder='"+list[i]+"'>"+
		         "<label>"+list[i]+"</label>"+
		         "<span class='focus-border'>"+
		          	"<i></i>"+
		         "</span>"+
	    "</div>";
		$("#tipInput").append(txt);
		addStyle(id);
	}
}


function getUserTips(){
	//获得用户的输入
	//name1:content1,name2:content2
	var tips = getCookie("tipList");
	var list = tips.split(",");
	var len = list.length;
	var result = "";
	var content = "";//标记内容，在循环中使用
	for(let i=0 ; i<len ; i++){
		content = $("#tip"+i).val();
		if(content===""){
			return "";
		}
		else{
			result = result+list[i]+":"+content;
			if(i!==len-1){
				result = result + ",";
			}
		}
	}
	return result;
}


function addStyle(id){
	//添加输入框时为输入框添加css属性
	$("#"+id).focusout(function(){
		if($("#"+id).val() !== ""){
			$("#"+id).addClass("has-content");
		}else{
			$("#"+id).removeClass("has-content");
		}
	})
}

function submitTag(){
	//提交标记
	//这种标记只有标签，没有图片
	var type = "tips";
	var userId = getCookie("userId");
	var projectId = getCookie("projectId");
	var publisherId = getCookie("publisherId");
	var pictureId = getCookie("pictureId");
	var remark = getUserTips();
	var width = getCookie("pictureWidth");
	var height = getCookie("pictureHeight");
	
	if(remark===""){
		//有未填写的tip
		//可以用别的方式提醒
		//暂时使用alert
		alert("请填写所有标签后提交！");
		return;
	}
	
	$.ajax({
		url: "/workspace/submit/"+type+"/"+userId+"/"+projectId+"/"+publisherId+"/"+pictureId,
		type: "post",
		data: {
			'base64' : "",
			'remark' : remark,
			'width' : width,
			'height' : height,
		},
		success: function(){
			alert("上传成功");
			clearTips();//清空tips
			getNewPicture();//这个方法在getPicture.js里面
		}
	});
	
}

function clearTips(){
	//清空tip的内容
	//为下一张图片做准备
	$("#tipInput").empty();
	setInput();
}

