package YingYingMonster.LetsDo_Phase_II.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workspace")
public class WorkSpaceController {
	
	/**
	 * 
	 * @return 返回标注的界面
	 */
	@GetMapping("/{workType}")
	public String getWorkSpace1(@PathVariable("workType")String type) {
		if(type.equals("total")) {
			//整体
			return "workspace/total";
		}
		else if(type.equals("area")) {
			//分区
			return "workspace/area";
		}
//		else if(type.equals("tips")) {
//			//标记
//		}
		else {
			//default
			return "workspace/total";
		}
	}
}
