package YingYingMonster.LetsDo_Phase_II.controller;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    PublisherService publisherService;

    /**
    * 查找当前发布的项目列表
    * @param id 上传者用户id
    * @param keyword 关键字，可以为空
    */
    @PostMapping("/publisherProjects")
    @ResponseBody
    public String queryProjects(@RequestParam("userId") String id, @RequestParam("keyword") String keyword){
        List<Project> temp=publisherService.searchProjects(id, keyword);
        String result="";
        for(int i=0;i<temp.size();i++){
            if(i==temp.size()-1)
                result+=temp.get(i).getProjectId()+","+temp.get(i).getTagRequirement();
            else
                result+=temp.get(i).getProjectId()+","+temp.get(i).getTagRequirement()+",";
        }
        return result;
    }

    //这里还是需要一个返回单个Project对象的接口

    //请求项目详情页面
    @GetMapping("/{projectName}")
    public String projectDetailPage(@PathVariable("projectName") String projectName){
        return "projects/publisherProjectDetail";
    }

    //异步获取项目详情
    //返回json，待解决
    @PostMapping("/{projectName}")
    @ResponseBody
    public Project projectDetail(@PathVariable("projectName") String projectName){
        return null;
    }

}
