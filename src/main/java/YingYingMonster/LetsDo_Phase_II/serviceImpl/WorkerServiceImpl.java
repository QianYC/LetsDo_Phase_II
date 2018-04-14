package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.daoImpl.DataDAOImpl;
import YingYingMonster.LetsDo_Phase_II.daoImpl.ProjectDAOImpl;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;
import YingYingMonster.LetsDo_Phase_II.service.WorkerService;

@Component
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	ProjectDAOImpl pjDao;
	
	@Autowired
	DataDAOImpl dtDao;
	
	@Override
	public List<String> viewAllProjects() {
		// TODO Auto-generated method stub
		ArrayList<String>list=null;
		try {
			list=pjDao.viewAllProjects().stream().map(p->p.getProjectId()).collect(ArrayList::new,
					ArrayList::add,ArrayList::addAll);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Project getAProject(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean forkProject(String workerId, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> viewMyProjects(String workerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int viewProgress(String workerId, String projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> viewUndoData(String workerId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> viewDoneData(String workerId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAData(String workerId, String projectId, String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag getATag(String workerId, String projectId, String tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean uploadTag(String userId, String projectId, String tagId, Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean push(String workerId, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

}
