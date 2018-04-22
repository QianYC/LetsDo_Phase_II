package YingYingMonster.LetsDo_Phase_II.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import YingYingMonster.LetsDo_Phase_II.model.Data;
import YingYingMonster.LetsDo_Phase_II.service.WorkerService;


@Controller
@RequestMapping("/workspace")
public class WorkSpaceController {
	
	@Autowired
	private WorkerService service;
	
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
	 * @return 返回已经完成的图片的id  id之间以; 隔开
	 */
	@GetMapping("/viewDone/{userId}/{projectId}/getPictureIdList")
	@ResponseBody
	public String getDonePictureIdList(@PathVariable("projectId")String projectId ,
			@PathVariable("userId")String userId ) {
		String str = "picture1;picture2;picture3;picture4;picture5;picture6;picture7;picture8;picture9;picture10;picture11;picture12;picture13;picture14;picture15;picture16;picture17;picture18";//从service获得
		return str;
	}
	
	
	/**
	 * 把一张已完成的图片放到url里
	 */
    @RequestMapping("/viewDone/putPicture/{userId}/{projectId}/{pictureId}")  
    @ResponseBody  
    public void getDoneImage(HttpServletRequest request, HttpServletResponse response,@PathVariable("projectId")String projectId ,
			@PathVariable("userId")String userId,
			@PathVariable("pictureId")String pictureId) throws Exception{  
    	
    	String JPG="image/jpeg;charset=UTF-8";      
        // 获取输出流  
        OutputStream outputStream = response.getOutputStream();  
        // 读数据  
        byte[] data = null; //service.getPicture(pictureId);//从service获得
        // 回写  
        response.setContentType(JPG);  
        outputStream.write(data);  
        outputStream.flush();  
        outputStream.close();  
    }  
    
     
    
    /**
     * 随机获得一个待完成的图片的id
     * @return 图片id
     */
    @RequestMapping("/getNewPictureId/{userId}/{projectId}/{publisherId}")  
    @ResponseBody  
    public String getNewPictureId(@PathVariable("projectId")String projectId ,
   			@PathVariable("userId")String userId,
   			@PathVariable("publisherId")String publisherId) {
    	List<String> list = service.viewUndoData(userId, publisherId, projectId);
    	
    	if(list==null||list.size()==0) {
    		return "";//没有内容
    	}
    	
    	String id = list.get(0) ; //需要检查是否有内容！！
    	
    	return id;
    }
    
    
    /**
   	 * 把一张等待完成的图片放到url里
   	 */
     @RequestMapping("/getNewPicture/{userId}/{projectId}/{publisherId}/{pictureId}")  
     @ResponseBody  
     public void getNewImage(HttpServletRequest request, HttpServletResponse response,@PathVariable("projectId")String projectId ,
   			@PathVariable("userId")String userId,
   			@PathVariable("publisherId")String publisherId,
   			@PathVariable("pictureId")String pictureId) throws Exception{  
       	
       	String JPG="image/jpeg;charset=UTF-8";      
           // 获取输出流  
           OutputStream outputStream = response.getOutputStream();  
           // 读数据  
           Data dataPac = service.getAData(userId, publisherId, projectId, pictureId);
           System.out.println(userId+" "+publisherId+" "+projectId+" "+pictureId);
           byte[] data = dataPac.getData();
           int height = dataPac.getHeight();
           int width = dataPac.getWidth();
           String size = width+","+height;
           // 回写  
           response.setContentType(JPG);  
           outputStream.write(data);  
           outputStream.flush();  
           outputStream.close();  
       }  
     
     
     /**
      * 提交标记
      * @param request
      * @param response
      * @param type
      * @param userId
      * @param projectId
      * @param pictureId
      * @param remark  格式为
      * content1;content2(结尾无分号,没有tag内容的 remark为空串)
      */
     @PostMapping("/submit/{type}/{userId}/{projectId}/{pictureId}")
     public void submitTag(HttpServletRequest request, HttpServletResponse response,
     		@PathVariable("type")String type,
     		@PathVariable("userId")String userId,
     		@PathVariable("projectId")String projectId,
     		@PathVariable("pictureId")String pictureId) {
    	 
    	String imageDataURL = request.getParameter("b64");
     	String remark = request.getParameter("remark");  //tag图片的64位编码
     	String w = request.getParameter("width");
     	String h = request.getParameter("height");
     	int width = Integer.parseInt(w);
     	int height = Integer.parseInt(h);//标记的长宽
     	
     }
}

