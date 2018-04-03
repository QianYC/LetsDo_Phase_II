package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import YingYingMonster.LetsDo_Phase_II.dao.MockDB;

@Component
public class MockDBImpl implements MockDB {

	@Autowired
	private String ROOT;
	
	@Autowired
	private CSVHandler handler;
	
	private HashMap<String,MockTable>tables;
	
	//for test
	public HashMap<String,MockTable>getTables(){
		return tables;
	}
	
	public MockDBImpl(){
		tables=new HashMap<>();
		String path=System.getProperty("user.home").replaceAll("\\\\", "/")+"/database/tables";
		File file=new File(path);
		String[]fileNames=file.list();
		if(fileNames!=null){			
			for(String str:fileNames){
				tables.put(str, new MockTable(path+"/"+str));
			}
		}
	}
	
	@Override
	public boolean createTable(String name) throws IOException {
		// TODO Auto-generated method stub
		if(tables.get(name)!=null)
			return false;
		
		String path=ROOT+"/tables/"+name;
		File file=new File(path);
		file.mkdirs();
		
		MockTable table=new MockTable(path);
		tables.put(name, table);
		return true;
	}

	@Override
	public boolean insert(String tableName, Object obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		
		return tables.get(tableName).insert(obj);
	}

	@Override
	public List<Object> readTable(String tableName) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return null;
		return tables.get(tableName).readAll();
	}

	@Override
	public boolean modify(String tableName,Object obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		return tables.get(tableName).modify(obj);
	}

	@Override
	public boolean delete(String tableName,Object obj) {
		// TODO Auto-generated method stub
		if(tables.get(tableName)==null)
			return false;
		return tables.get(tableName).delete(obj);
	}

}
