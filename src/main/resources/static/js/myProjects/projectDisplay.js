/**
 * 展示worker正在进行的项目
 */

function test(){
	setCookie("projectId", "pjid");
	setCookie("publisherId", "pubid");
	setCookie("userId", "wk1");
	setCookie("projectType", "tips");
	setCookie("tipList", "tip1,tip2,tip3");
	window.location.href="/workspace/tips";
}