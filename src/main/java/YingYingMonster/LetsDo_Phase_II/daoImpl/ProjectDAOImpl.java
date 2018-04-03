package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.model.Project;

@Component
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	MockDB database;
	
	@Override
	public boolean addProject(Project project) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean validateProject(String publisherId, String projectId) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public List<Project> publisherViewProjects(String publisherId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Project> viewAllProjects() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean modifyCurrentWorkerNum(String publisherId, String projectId) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean deleteProject(String publisherId, String projectId) {
		// TODO 自动生成的方法存根
		return false;
	}

}
