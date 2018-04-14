package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import YingYingMonster.LetsDo_Phase_II.dao.DataDAO;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.exception.TargetNotFoundException;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;

@Component
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	ProjectDAO pjDao;
	
	@Autowired
	DataDAO dtDao;
	
	@Override
	public boolean createProject(Project project, MultipartFile dataSet) {
		// TODO Autproject.go-generated method stub
		try {
			pjDao.addProject(project);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] bytes = null;
		try {
			bytes = dataSet.getBytes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dtDao.uploadDataSet(project.getPublisherId(), project.getProjectId(),
					project.getPackageNum(), bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validateProject(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=pjDao.validateProject(publisherId, projectId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Project> searchProjects(String publisherId, String keyword) {
		// TODO Auto-generated method stub
		List<Project>list=null;
		try {
			list=pjDao.publisherViewProjects(publisherId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Project>it=list.iterator();
		while(it.hasNext()){
			Project p=it.next();
			if(!p.getProjectId().contains(keyword))
				it.remove();
		}
		return list;
	}

	@Override
	public List<String[]> viewPushEvents(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		List<String[]>list=dtDao.viewPushEvents(publisherId, projectId);
		return list;
	}

	@Override
	public byte[] downloadTags(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return dtDao.downloadTags(publisherId, projectId);
	}

	@Override
	public Project getAProject(String projectId) {
		// TODO Auto-generated method stub
		Project pj=null;
		try {
			pj=pjDao.getAProject(projectId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pj==null)
			throw new TargetNotFoundException();
		return pj;
	}

}
