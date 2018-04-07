package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.File;
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
	
	@Autowired
	String root;
	
	@Autowired
	CSVHandler handler;
	
	@Override
	public boolean addProject(Project project) throws FileNotFoundException, IOException {
		// TODO 自动生成的方法存根
		mkdirsInPublishers(project.getPublisherId(),project.getProjectId());
		return database.insert("projects", project);
	}
	
	/**
	 * 上传project时为上传者在自己的文件夹里创建project的文件夹
	 * @param publisherId
	 * @param projectId
	 */
	private void mkdirsInPublishers(String publisherId,String projectId){
		String path=root+File.separator+"publishers"+File.separator+publisherId
				+File.separator+projectId;
		File pj=new File(path);
		if(!pj.exists())
			pj.mkdirs();
		
		File tags=new File(path+File.separator+"tags");
		if(!tags.exists())
			tags.mkdirs();
		
		File pushEv=new File(path+File.separator+"pushEvents.csv");
		if(!pushEv.exists())
			try {
				pushEv.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		handler.appendCSV(new String[]{"workerId","date","tagId"}, pushEv.getPath());
		
		File fork=new File(path+File.separator+"fork.csv");
		if(!fork.exists())
			try {
				fork.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		handler.appendCSV(new String[]{"workerId"}, fork.getPath());
	}

	@Override
	public boolean validateProject(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("projects");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId))
				return false;
		}
		return true;
	}

	@Override
	public List<Project> publisherViewProjects(String publisherId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("projects");
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
		List<Persistent> list=database.readTable("projects");
		List<Project> proList=new ArrayList<>();
		for(Persistent p:list){
			proList.add((Project)p);
		}
		return proList;
	}

	@Override
	public boolean modifyCurrentWorkerNum(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		List<Persistent> list=database.readTable("projects");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId)){
				Project newP=(Project)p;
				newP.setCurrWorkerNum(newP.getCurrWorkerNum()+1);
				return database.modify("projects", newP);
			}
		}
		return false;
	}

	@Override
	public boolean deleteProject(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根	
		List<Persistent> list=database.readTable("projects");
		for(Persistent p:list){
			if(((Project)p).getPublisherId().equals(publisherId)&&((Project)p).getProjectId().equals(projectId))
				return database.delete("projects", (Project)p);
		}
		return false;
	}

}
