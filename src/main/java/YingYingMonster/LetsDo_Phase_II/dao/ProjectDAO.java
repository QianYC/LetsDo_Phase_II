package YingYingMonster.LetsDo_Phase_II.dao;

import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Project;

public interface ProjectDAO {

	/**
	 * 添加project
	 * @param project
	 * @return
	 */
	public boolean addProject(Project project);
	
	/**
	 * 验证projectId是否合法
	 * @param publisherId
	 * @param projectId
	 * @return
	 */
	public boolean validateProject(String publisherId,String projectId);
	
	/**
	 * 供发起者查看自己的项目
	 * @param publisherId
	 * @return
	 */
	public List<Project>publisherViewProjects(String publisherId);
	
	/**
	 * 供所有人查看所有上传的项目
	 * @return
	 */
	public List<Project>viewAllProjects();
	
//	/**
//	 * 供工人查看自己的项目
//	 * @param workerId
//	 * @return
//	 */
//	public List<Project>workerViewProjects(String workerId);
	
	/**
	 * 修改当前工人数
	 * @param projectId
	 * @return
	 */
	public boolean modifyCurrentWorkerNum(String publisherId,String projectId);
	
	/**
	 * 删除未开始的项目
	 * @param publisherId
	 * @param projectId
	 * @return
	 */
	public boolean deleteProject(String publisherId,String projectId);
}
