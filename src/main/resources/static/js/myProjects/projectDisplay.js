/**
 * 展示worker正在进行的项目
 */

function loadProjects(){
	var userId ="wk1";// getCookie("userId");
	$.ajax({
		url: "/myProjects/getList/"+userId,
		type: "get",
		success: function(data){
			
			var list = data.split(",");
			var len = list.length;
			for(let i=0 ; i<len ; i++){
				var ids = list[i].split("_");
				//pubid_pjid
				var pubid = ids[0];
				var pjid = ids[1];
				var txt = " <div class='single-member effect-3' onClick='chooseProject(this)' id='"+pubid+"_"+pjid+"'>"+
		            	"<div class='member-image'>"+
		                	"<img src='/pic/projects/0"+(i+1)+".png' alt='Member'>"+
		                "</div>"+
		                "<div class='member-info'>"+
		                	"<h3>"+pjid+"</h3>"+
		                    "<h5>"+pubid+"</h5>"+
		                    "<p>一些描述的话</p>"+
		                    "<div class='social-touch' >"+
		                    "</div>"+
		                "</div>"+
		            "</div>";
				$("#projects").append(txt);
			}
		}
	});
}

function chooseProject(that){
	var id = that.id;
	var ids = id.split("_");//pubid_pjid
	$.ajax({
		url: "/myProjects/getProject/"+ids[0]+"/"+ids[1],
		type: "get",
		success: function(data){
			alert(data);
			setCookie("projectId", ids[1]);
			setCookie("publisherId", ids[0]);
			setCookie("userId", "wk1");//这个不需要，登录时应该已经设置好了
			
			var datas = data.split(":");//type:requirement
			setCookie("projectType",datas[0]);
			if(datas[0]==="tips"){
				setCookie("tipList", datas[1]);
			}
			else{
				setCookie("tipList", "");
				setCookie("projectRequirement",data[1]);
			}
			
			window.location.href="/workspace/"+datas[0];
		}
	});
	
}

function test1(){
	setCookie("projectId", "pjid");
	setCookie("publisherId", "pubid");
	setCookie("userId", "wk1");
	setCookie("projectType", "tips");
	setCookie("tipList", "tip1,tip2,tip3");
	window.location.href="/workspace/tips";
}

function test2(){
	setCookie("projectId", "pjid");
	setCookie("publisherId", "pubid");
	setCookie("userId", "wk1");
	setCookie("projectType", "area");
	setCookie("tipList", "");
	setCookie("projectRequirement","请圈出猪");
	window.location.href="/workspace/area";
}

