package YingYingMonster.LetsDo_Phase_II.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Persistent;

public interface MockDB {

	public boolean createTable(String name)throws IOException;
	
	public boolean insert(String tableName,Persistent obj) throws FileNotFoundException, IOException;
	
	public List<Persistent>readTable(String tableName) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	public boolean modify(String tableName,Persistent obj) throws FileNotFoundException, IOException;
	
	public boolean delete(String tableName,Persistent obj);
	
}
