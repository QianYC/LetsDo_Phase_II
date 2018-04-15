package YingYingMonster.LetsDo_Phase_II.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;

public interface WorkerDAO {

	public List<Project>viewWorkerProject(String workerId);
	
	public boolean fork(String workerId,String publisherId,String projectId) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	public boolean uploadTag(String userId, String projectId, String tagId, Tag tag);
	
	public Tag downloadTag(String userId, String projectId, String tagId);
	
	public List<String>viewUndoData(String workerId,String projectId);

	public List<String>viewDoneData(String workerId,String projectId);

	public byte[]getAData(String workerId,String projectId,String dataId);
	
	public boolean push(String workerId,String publisherId,String projectId);
}
