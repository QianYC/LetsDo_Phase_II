package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.util.List;

import YingYingMonster.LetsDo_Phase_II.dao.Persistant;

public class MockTable {

	public String path;
	
	private CSVHandler handler=new CSVHandler();
	
	public MockTable(String path){
		this.path=path;		
	}

	public boolean insert(Persistant obj) {
		// TODO Auto-generated method stub
		if(findOne(obj))
			return false;
		handler.appendCSV(obj.toStrArr(), path);
		return true;
		
	}

	public List<String[]> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modify(Persistant obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Persistant obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean findOne(Persistant obj){
		List<String[]>list=handler.readCSV(path);
		String key=obj.getKey();
		for(String[]arr:list){
			if(arr[0].equals(key))
				return true;
		}
		return false;
	}
}
