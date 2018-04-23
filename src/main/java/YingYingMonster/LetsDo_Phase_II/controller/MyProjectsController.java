package YingYingMonster.LetsDo_Phase_II.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.TagRequirement;
import YingYingMonster.LetsDo_Phase_II.model.MarkMode;
import YingYingMonster.LetsDo_Phase_II.service.WorkerService;

@Controller
@RequestMapping("/myProjects")
public class MyProjectsController {

	@Autowired
	private WorkerService service;
	
	/**
	 * 
	 * @return 返回展示界面
	 */
    @GetMapping("/projects")
    public String getPage() {
    	return "myProjects/projectDisplay";
    }
    
    /**
     * 
     * @param userId
     * @return 返回项目列表
     */
    @GetMapping("/getList/{userId}")
    @ResponseBody
    public String getList(@PathVariable("userId")String userId) {
    	
    	String res = "";
    	ArrayList<String> list = (ArrayList<String>) service.viewMyProjects(userId);
    	//pubid_projectid,
    	int len = list.size();
    	for(int i=0 ; i<len ; i++) {
    		res += list.get(i);
    		if(i!=len-1) {
    			res += ",";
    		}
    	}
    	System.out.println("getList "+res);
    	return res;
    }
    
    @GetMapping("/getProject/{publisherId}/{projectId}")
    @ResponseBody
    public String getProject(@PathVariable("publisherId")String publisherId,
    		@PathVariable("projectId")String projectId) {
    	
    	String res = "";
        Project project = service.getAProject(publisherId, projectId);
        TagRequirement requirement = project.getTagRequirement();
        String type = "";
        switch(requirement.getMarkMode()) {
        case ENTIRETY:
        	type="total";
        	break;
        case TAGS:
        	type="tips";
        	break;
        case AREA:
        	type="area";
        	break;
        case RECTANGLE:
        	type="mark";
        	break;
        }
        
        String req = requirement.getRequirement(); /*markMode是tags的时候，requirement为tag列表，tag之间以逗号隔开，其他模式都为具体要求*/
        
    	return type+":"+req ;
    }
}
