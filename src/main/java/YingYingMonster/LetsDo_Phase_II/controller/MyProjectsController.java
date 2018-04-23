package YingYingMonster.LetsDo_Phase_II.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
