package YingYingMonster.LetsDo_Phase_II.dao;

import java.util.List;

public interface FileDAO {
	public boolean uploadDataSet(String publisherId,byte[] file,String dataSetId);
	
	public byte[] getTags(String dataSetId,String publisherId);
	
	public List<String> viewMyProjects(String publisherId); 
}
