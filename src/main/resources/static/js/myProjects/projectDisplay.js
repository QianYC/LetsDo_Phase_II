/**
 * 展示worker正在进行的项目
 */

function loadProjects(){
	var userId ="wk1";// getCookie("userId");
	$.ajax({
		url: "/myProjects/getList/"+userId,
		type: "get",
		success: function(data){
			alert(data);
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

