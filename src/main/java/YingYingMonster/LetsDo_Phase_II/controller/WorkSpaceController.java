package YingYingMonster.LetsDo_Phase_II.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import YingYingMonster.LetsDo_Phase_II.service.Mock_WorkSpaceServiceImpl;


@Controller
@RequestMapping("/workspace")
public class WorkSpaceController {
	
	Mock_WorkSpaceServiceImpl service = new Mock_WorkSpaceServiceImpl();
	
	/**
	 * 
	 * @return 返回标注的界面
	 */
	@GetMapping("/{workType}")
	public String getWorkSpace(@PathVariable("workType")String type) {
		if(type.equals("tips")) {
			//填写标签，包括整体标注
			return "workspace/tips";
		}
		else if(type.equals("area")) {
			//按要求覆盖指定区域
			return "workspace/area";
		}
		else if(type.equals("mark")) {
			//按要圈出指定物体
			return "workspace/mark";
		}
		else {
			//default
			return "workspace/total";
		}
	}
	
	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @return  返回查看已经完成的标注的界面
	 */
	@GetMapping("/viewDone/{userId}/{projectId}")
	public String getViewDone(@PathVariable("projectId")String projectId ,
			@PathVariable("userId")String userId ) {
		
		//需要根据id进行一些图片的准备工作
		return "workspace/viewDone";
	}
	
	
	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @return 返回图片的id  id之间以; 隔开
	 */
	@GetMapping("/viewDone/{userId}/{projectId}/getPictureIdList")
	@ResponseBody
	public String getPictureIdList(@PathVariable("projectId")String projectId ,
			@PathVariable("userId")String userId ) {
		String str = "picture1;picture2;picture3;picture4;picture5;picture6;picture7;picture8;picture9;picture10;picture11;picture12;picture13;picture14;picture15;picture16;picture17;picture18";//从service获得
		return str;
	}
	
	/**
	 * 把一张图片归位
	 */
    @RequestMapping("/viewDone/putPicture/{userId}/{projectId}/{pictureId}")  
    @ResponseBody  
    public void getImage(HttpServletRequest request, HttpServletResponse response,@PathVariable("projectId")String projectId ,
			@PathVariable("userId")String userId,
			@PathVariable("pictureId")String pictureId) throws Exception{  
    	
    	String JPG="image/jpeg;charset=UTF-8";      
        // 获取输出流  
        OutputStream outputStream = response.getOutputStream();  
        // 读数据  
        byte[] data = service.getPicture(pictureId);//从service获得
        // 回写  
        response.setContentType(JPG);  
        outputStream.write(data);  
        outputStream.flush();  
        outputStream.close();  
    }  
}

