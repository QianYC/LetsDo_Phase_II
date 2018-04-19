/**
 * 为打标签标注提供逻辑
 */

function setInput(){
	//加载图片时调用
	//设置标签输入框
	var tips = "tip1,tip2,tip3,tip4,tip5";//调用方法获得;cookie中也有,用，分割；cookie会截断；
    setCookie("tipList", tips);
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

function testgetUserTips(){
	var res = getUserTips();
	alert(res);
}

function getUserTips(){
	//获得用户的输入
	//若有一个或多个空未填返回空串""
	//只记用户的输入，以逗号 ， 隔开
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
			result = result+content;
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