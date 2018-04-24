window.onload=function () {
    document.getElementById("username").innerHTML=getCookie("userId")+"&nbsp;";
}

function uploadDataSet() {
    var file = document.getElementById("fileselect").files[0];
    var userId=getCookie("userId");
    var projectId=$("#projectName").text();
    var maxWorkerNum=$("#workerNum").text();
    var packageNum=$("#packageNum").text();
    var picNum=$("#pictureAmount").text();
    var startDate=$("#startDate").text();
    var endDate=$("#endDate").text();
    var tags=$("#tags").text();
    var markMode=$("#markMode").text();
    var tagRequirement=$("#note").html();
    var levelLimit=$("#levelLimit").text();
    var gradesLimit=$("#gradesLimit").text();
    var money=$("#payment").text();

    var formData = new FormData();
    formData.append("file",file);
    formData.append("userId",userId);
    formData.append("projectId",projectId);
    formData.append("maxWorkerNum",maxWorkerNum);
    formData.append("packageNum",packageNum);
    formData.append("picNum",picNum);
    formData.append("startDate",startDate);
    formData.append("endDate",endDate);
    formData.append("tags",tags);
    formData.append("markMode",markMode);
    formData.append("tagRequirement",tagRequirement);
    formData.append("levelLimit",levelLimit);
    formData.append("gradesLimit",gradesLimit);
    formData.append("money",money);

    $.ajax({
        url:"/publisherPage/publish",
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