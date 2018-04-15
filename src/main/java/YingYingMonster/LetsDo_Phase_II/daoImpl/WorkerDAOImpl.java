package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.dao.WorkerDAO;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;

@Component
public class WorkerDAOImpl implements WorkerDAO {

	@Autowired
	String root;
	
	@Autowired
	MockDB db;
	
	@Override
	public List<Project> viewWorkerProject(String workerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fork(String workerId, String publisherId, String projectId) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File file=new File(root+"/dataset/"+publisherId+"_"+projectId);
		Project pj=(Project) db.retrieve("projects", publisherId+"_"+projectId);
		if(pj==null||!file.exists())
			return false;
		
		//随机分包
		
		//在workers下建立文件夹
		
		return true;
	}

	@Override
	public boolean uploadTag(String userId, String projectId, String tagId, Tag tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tag downloadTag(String userId, String projectId, String tagId) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean push(String workerId, String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

}
