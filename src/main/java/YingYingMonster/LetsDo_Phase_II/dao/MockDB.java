package YingYingMonster.LetsDo_Phase_II.dao;

import java.io.IOException;
import java.util.List;

public interface MockDB {

	public boolean createTable(String name)throws IOException;
	
	public boolean insert(String tableName,Object obj);
	
	public List<Object>readTable(String tableName);
	
	public boolean modify(String tableName,Object obj);
	
	public boolean delete(String tableName,Object obj);
	
}
