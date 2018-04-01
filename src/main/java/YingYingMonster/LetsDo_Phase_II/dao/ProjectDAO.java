package YingYingMonster.LetsDo_Phase_II.dao;

import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Project;

public interface ProjectDAO {
	public List<String> viewPushEvents(String publisherId,String projectId);
	
	public boolean creatProject(Project project, String dataSetId);
	
	public boolean validateProject(String publisherId,String projectId);
	
	public List<Project> searchProjects(String publisherId,String keyword);
}
