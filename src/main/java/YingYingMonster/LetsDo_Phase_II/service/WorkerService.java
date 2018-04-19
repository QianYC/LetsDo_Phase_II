package YingYingMonster.LetsDo_Phase_II.service;

import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Data;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;

public interface WorkerService {

	public List<String>viewAllProjects();//查看所有项目

	public Project getAProject(String publisherId,String projectId);//根据publisherId,projectId获取Project对象

	public boolean forkProject(String workerId,String publisherId,String projectId);//fork项目，返回值待定

	public List<String>viewMyProjects(String workerId);//查看某个用户参加的项目名称

	public int viewProgress(String workerId,String publisherId,String projectId);//查看某个用户某个项目的进度

	public List<String>viewUndoData(String workerId,String publisherId,String projectId);

	public List<String>viewDoneData(String workerId,String publisherId,String projectId);

	public Data getAData(String workerId,String publisherId,String projectId,String dataId);

	public Tag getATag(String workerId,String publisherId,String projectId,String tagId);

	public boolean uploadTag(String userId,String publisherId,String projectId,String tagId,Tag tag);

	public boolean push(String workerId,String publisherId,String projectId);
	
}
