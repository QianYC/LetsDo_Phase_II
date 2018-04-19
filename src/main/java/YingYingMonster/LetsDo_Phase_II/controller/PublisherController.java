package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/publisher")
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
    public String createProject(@RequestParam("dataSet")MultipartFile dataSet,
                              @RequestParam ("userId") String publisherId,
                              @RequestParam("projectId")String projectId,
                              @RequestParam("maxWorkerNum")int maxWorkerNum,
                              @RequestParam("packageSize")int packageSize,
                              @RequestParam("picNum")int picNum,
                              @RequestParam("startDate")String startDate,
                              @RequestParam("endDate")String endDate,
                              @RequestParam("tagRequirement")String tagRequirement,
                              @RequestParam("workerRequirement")String workerRequirement,
                              @RequestParam("money")int money){
        Project project=new Project(publisherId,projectId,maxWorkerNum,packageSize,picNum,startDate,endDate,tagRequirement,workerRequirement,money);

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
