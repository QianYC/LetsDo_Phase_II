package YingYingMonster.LetsDo_Phase_II.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import YingYingMonster.LetsDo_Phase_II.model.Project;

public interface PublisherService {

	public Project getAProject(String projectId);//根据projectId获取Project对象
	
	public boolean createProject(Project project, MultipartFile dataSet);

	public boolean validateProject(String publisherId,String projectId);//新建项目时检查项目名是否合法

	public List<Project> searchProjects(String publisherId,String keyword);//根据keyword查找已有的项目，支持模糊查询

	public List<String[]> viewPushEvents(String publisherId,String projectId);//查看某个项目的提交记录

	public byte[] downloadTags(String publisherId,String projectId);//下载所有标注

}
