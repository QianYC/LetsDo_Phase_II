package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/publisher/{id}")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publish")
    public String publishPage(){
        return "publish";
    }

    @PostMapping("/publish")
    @ResponseBody
    public String createProject(@RequestParam("dataSet")MultipartFile dataSet,
                              @PathVariable ("id") String publisherId,
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
}
