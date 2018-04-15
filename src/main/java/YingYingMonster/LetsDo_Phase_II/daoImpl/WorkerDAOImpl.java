package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
	
	@Autowired
	SerializeHandler serialize;
	
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
	public boolean uploadTag(String workerId,String publisherId, String projectId, String tagId, Tag tag) 
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		File folder=new File(root+"/workers/"+workerId);
		if(!folder.exists())
			return false;
		
		Iterator<String>it=Stream.of(folder.list()).filter(x->x.contains(publisherId+"_"+projectId))
				.iterator();
		
		if(!it.hasNext())
			return false;
		
		String pk=it.next();
		
		serialize.writeObj(root+"/workers/"+workerId+"/"+pk+"/"+tagId+".tag", tag);
		return true;
	}

	@Override
	public Tag downloadTag(String workerId,String publisherId, String projectId, String tagId) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File folder=new File(root+"/workers/"+workerId);
		if(!folder.exists())
			return null;
		
		Iterator<String>it=Stream.of(folder.list()).filter(x->x.contains(publisherId+"_"+projectId))
				.iterator();
		
		if(!it.hasNext())
			return null;
		
		String pk=it.next();
		
		Tag tag=(Tag) serialize.readObj(root+"/workers/"+workerId+"/"+pk+"/"+tagId+".tag");
		return tag;
	}

	@Override
	public List<String> viewUndoData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> viewDoneData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAData(String workerId,String publisherId, String projectId, String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean push(String workerId, String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

}
