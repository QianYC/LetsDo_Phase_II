package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.dao.WorkerDAO;
import YingYingMonster.LetsDo_Phase_II.model.Data;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;
import YingYingMonster.LetsDo_Phase_II.service.WorkerService;

@Component
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	ProjectDAO pjDao;
	
	@Autowired
	WorkerDAO wkDao;
	
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
	public Project getAProject(String publisherId,String projectId) {
		// TODO Auto-generated method stub
		Project pj=null;
		try {
			pj=pjDao.getAProject(publisherId, projectId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pj;
	}

	@Override
	public boolean forkProject(String workerId, String publisherId,String projectId) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=wkDao.fork(workerId, publisherId, projectId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<String> viewMyProjects(String workerId) {
		// TODO Auto-generated method stub
		List<String>list=null;
		try {
			list=wkDao.viewWorkerProject(workerId).stream().map(x->x.getKey())
					.collect(Collectors.toList());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int viewProgress(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		int s1=viewUndoData(workerId, publisherId, projectId).size();
		int s2=viewDoneData(workerId, publisherId, projectId).size();
		return (int)s2/(s1+s2);
	}

	@Override
	public List<String> viewUndoData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return wkDao.viewUndoData(workerId, publisherId, projectId);
	}

	@Override
	public List<String> viewDoneData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return wkDao.viewDoneData(workerId, publisherId, projectId);
	}

	@Override
	public Data getAData(String workerId, String publisherId,String projectId, String dataId) {
		// TODO Auto-generated method stub
		Data data=null;
		try {
			data=wkDao.getAData(workerId, publisherId, projectId, dataId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Tag getATag(String workerId, String publisherId,String projectId, String tagId) {
		// TODO Auto-generated method stub
		Tag tag=null;
		try {
			tag=wkDao.downloadTag(workerId, publisherId, projectId, tagId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean uploadTag(String userId, String publisherId,String projectId, String tagId, Tag tag) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		/**
		 * 对tag进行打分
		 * 目前暂时随机生成
		 */
		tag.setScore((int)(Math.random()*100));
		
		try {
			flag=wkDao.uploadTag(userId, publisherId, projectId, tagId, tag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int push(String workerId, String publisherId,String projectId) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			flag=wkDao.push(workerId, publisherId, projectId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
