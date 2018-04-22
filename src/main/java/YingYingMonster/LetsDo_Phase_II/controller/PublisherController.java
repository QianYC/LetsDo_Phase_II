package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.model.MarkMode;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.TagRequirement;
import YingYingMonster.LetsDo_Phase_II.model.WorkerRequirement;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/publisherPage")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    //发布者界面
    @GetMapping("/publish")
    public String publishPage(){
        return "publisher/publish";
    }

    //请求发布项目
    @PostMapping("/publish")
    @ResponseBody
    public String createProject(@RequestParam(value = "file")MultipartFile dataSet,
                                @RequestParam ("userId") String publisherId,
                                @RequestParam("projectId")String projectId,
                                @RequestParam("maxWorkerNum")String maxWorkerNum,
                                @RequestParam("packageNum")String packageNum,
                                @RequestParam("picNum")String picNum,
                                @RequestParam("startDate")String startDate,
                                @RequestParam("endDate")String endDate,
                                @RequestParam("tags")String tags,
                                @RequestParam("markMode")String markMode,
                                @RequestParam("tagRequirement")String tagRequirement,
                                @RequestParam("levelLimit")String levelLimit,
                                @RequestParam("gradesLimit")String gradeLimit,
                                @RequestParam("money")String money){
        System.out.println("进入了方法");
        TagRequirement tagRequire=null;
        if(markMode.equals("tags")) {
            tagRequire=new TagRequirement(MarkMode.TAGS,tags,Integer.parseInt(gradeLimit));
        }else if(markMode.equals("entirety")){
            tagRequire=new TagRequirement(MarkMode.ENTIRETY,tagRequirement,Integer.parseInt(gradeLimit));
        }else if(markMode.equals("rectangle")){
            tagRequire=new TagRequirement(MarkMode.RECTANGLE,tagRequirement,Integer.parseInt(gradeLimit));
        }else if(markMode.equals("area")){
            tagRequire=new TagRequirement(MarkMode.AREA,tagRequirement,Integer.parseInt(gradeLimit));
        }
        WorkerRequirement workerRequire=new WorkerRequirement(Integer.parseInt(levelLimit));
        int workerNum=Integer.parseInt(maxWorkerNum);
        int numPackage=Integer.parseInt(packageNum);
        int numPic=Integer.parseInt(picNum);
        int payment=Integer.parseInt(money);
        Project project=new Project(publisherId,projectId,workerNum,numPackage,numPic,startDate,endDate,tagRequire,workerRequire,payment);

        boolean isValid=publisherService.validateProject(publisherId,projectId);
        if(isValid){
            boolean success=publisherService.createProject(project,dataSet);
            if(success){
                return "success";
            }else{
                return "fail";
            }
        }else{
            return "repetitive";
        }
    }

    //请求提交记录界面
    @GetMapping("/{projectName}/pushEvents")
    public String pushEventsPage(@PathVariable("id")String id,@PathVariable("projectName")String projectName){
        return "pushEvents";
    }

    //异步请求提交记录信息
    @PostMapping("/{projectName}/pushEvents")
    @ResponseBody
    public String pushEvents(@PathVariable("id")String id,@PathVariable("projectName")String projectName){
        return "消息记录";
        //返回消息记录
    }


    //查看项目内图片列表
    @GetMapping("/{projectName}/pictures")
    public String pictureListPage(@PathVariable("projectName")String projectName){
        return "pictureListPage";
    }

    @PostMapping("/{projectName}/pictures")
    @ResponseBody
    public String pictureList(@PathVariable("projectName")String projectName){
        return null;//返回图片列表
    }

    //查看标注情况
    @GetMapping("/{projectName}/{pictureId}")
    public String pictureTagPage(){
        return "tagPage";
    }

    @PostMapping("/{projectName}/{pictureId}")
    @ResponseBody
    public String pictureTag(@PathVariable("projectName")String projectName,@PathVariable("pictureId")String pictureId){
        return null;//返回各种图片信息，问下jbs
    }
}
