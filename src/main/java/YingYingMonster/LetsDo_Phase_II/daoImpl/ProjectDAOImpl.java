package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.model.Persistent;
import YingYingMonster.LetsDo_Phase_II.model.Project;

@Component
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	MockDB database;
	
	@Override
	public boolean addProject(Project project) throws FileNotFoundException, IOException {
		// TODO 自动生成的方法存根
		return database.insert("Project", project);
	}

	@Override
	public boolean validateProject(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("Project");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId))
				return false;
		}
		return true;
	}

	@Override
	public List<Project> publisherViewProjects(String publisherId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("Project");
		List<Project> proList=new ArrayList<>();
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId))
				proList.add((Project)p);
		}
		return proList;
	}

	@Override
	public List<Project> viewAllProjects() throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("Project");
		List<Project> proList=new ArrayList<>();
		for(Persistent p:list){
			proList.add((Project)p);
		}
		return proList;
	}

	@Override
	public boolean modifyCurrentWorkerNum(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("Project");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId)){
				Project newP=(Project)p;
				newP.setCurrWorkerNum(newP.getCurrWorkerNum()+1);
				return database.modify("Project", newP);
			}
		}
		return false;
	}

	@Override
	public boolean deleteProject(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根	
		List<Persistent> list=database.readTable("Project");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId))
				return database.delete("Project", (Project)p);
		}
		return false;
	}

}
