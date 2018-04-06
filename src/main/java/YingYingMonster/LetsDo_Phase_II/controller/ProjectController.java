package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project/{id}")
public class ProjectController {
    @Autowired
    PublisherService publisherService;

    @PostMapping("/publisherProjects")
    @ResponseBody
    public String queryProjects(@PathVariable("id") String id, @RequestParam("keyword") String keyword){
        List<Project> temp=publisherService.searchProjects(id, keyword);
        String result="";
        for(int i=0;i<temp.size();i++){
            if(i==temp.size()-1)
                result+=temp.get(i).getProjectId();
            else
                result+=temp.get(i).getProjectId()+",";
        }
        return result;
    }

    //这里还是需要一个返回单个Project对象的接口

    //请求项目详情页面
    @GetMapping("/{projectName}")
    public String projectDetailPage(@PathVariable("projectName") String projectName){
        return "projects/publisherProjectDetail";
    }

    //获取项目详情
    //返回json，待解决
    @PostMapping("/{projectName}")
    @ResponseBody
    public Project projectDetail(@PathVariable("projectName") String projectName){
        return null;
    }

    //请求提交记录界面
    public String pushEventsPage(){

    }

}
