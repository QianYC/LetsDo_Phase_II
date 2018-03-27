package YingYingMonster.LetsDo_Phase_II.dao;

import java.io.IOException;
import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Persistant;

public interface MockDB {

	public boolean createTable(String name,String[] arrtibutes)throws IOException;
	
	public boolean insert(String tableName,Persistant obj);
	
	public List<String[]>readTable(String tableName);
	
	public boolean modify(String tableName,Persistant obj);
	
	public boolean delete(String tableName,Persistant obj);
	
}
