package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.model.Persistant;

@Component
public class MockDBImpl implements MockDB {

	@Autowired
	private String ROOT;
	
	@Autowired
	private CSVHandler handler;
	
	private HashMap<String,MockTable>tables;
	
	public MockDBImpl(){
		tables=new HashMap<>();
		String path=System.getProperty("user.home").replaceAll("\\\\", "/")+"/database/tables";
		File file=new File(path);
		String[]fileNames=file.list();
		if(fileNames!=null){			
			for(String str:fileNames){
				String tableName=str.split("\\.")[0];
				tables.put(tableName, new MockTable(path+"/"+str));
			}
		}
	}
	
	@Override
	public boolean createTable(String name, String[] attributes) throws IOException {
		// TODO Auto-generated method stub
		if(tables.get(name)!=null)
			return false;
		
		String path=ROOT+"/tables/"+name+".csv";
		File file=new File(path);
		file.createNewFile();
		List<String[]>list=new ArrayList<>();
		list.add(attributes);
		handler.writeCSV(list, path);
		
		MockTable table=new MockTable(path);
		tables.put(name, table);
		return true;
	}

	@Override
	public boolean insert(String tableName, Persistant obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		
		return tables.get(tableName).insert(obj);
	}

	@Override
	public List<String[]> readTable(String tableName) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return null;
		return tables.get(tableName).readAll();
	}

	@Override
	public boolean modify(String tableName,Persistant obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		return tables.get(tableName).modify(obj);
	}

	@Override
	public boolean delete(String tableName,Persistant obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		return tables.get(tableName).delete(obj);
	}

}
