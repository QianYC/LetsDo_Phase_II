window.onload=function () {
    document.getElementById("username").innerHTML=getCookie("username");

}

function uploadDataSet() {
    var file = document.getElementById("fileselect").files[0];
    var userId=getCookie("userId");
    var projectId=$("#projectName").innerText;
    var maxWorkerNum=$("#workerNum").innerText;
    var packageSize=$("#packageSize").innerText;
    var picNum=$("#pictureAmount").innerText;
    var startDate=$("#startDate").innerText;
    var endDate=$("#endDate").innerText;
    var tags=$("#tags").innerText;
    var tagMode=$("#markMode").innerText;
    var tagRequirement=tagMode+"***"+tags;
    var workerRequirement=$("#note").innerText;
    var money=$("#payment").innerText;

    var formData = new FormData();
    formData.append("file",file,file.name);
    formData.append("userId",userId);
    formData.append("projectId",projectId);
    formData.append("maxWorkerNum",maxWorkerNum);
    formData.append("packageSize",packageSize);
    formData.append("picNum",picNum);
    formData.append("startDate",startDate);
    formData.append("endDate",endDate);
    formData.append("tagRequirement",tagRequirement);
    formData.append("workerRequirement",workerRequirement);
    formData.append("money",money);

    $.ajax({
        url:"/publisher/publish",
        type:"POST",
        secureuri : false,
        data:formData,
        dataType:"text",
        processData: false,
        contentType: false,
        success:function(res){
            if(res==="success"){
                alert("上传成功");
            }
            if(res==="fail"){
                alert("上传失败，数据库出错");
            }
            if(res==="repetitive"){
                alert("上传失败，该项目名已被使用");
            }
            console.log(res);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest+"///"+textStatus+"///"+errorThrown+"\n"+"发生了预料之外的错误，请稍后再试或联系开发人员");
        }
    })
}