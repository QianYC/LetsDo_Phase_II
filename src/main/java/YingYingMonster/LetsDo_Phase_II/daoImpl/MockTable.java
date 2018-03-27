package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.FileNotFoundException;
import java.util.List;
import YingYingMonster.LetsDo_Phase_II.dao.Persistant;
import YingYingMonster.LetsDo_Phase_II.exception.DepublicateKeyException;

public class MockTable {

	public String path;
	
	private CSVHandler handler=new CSVHandler();
	
	public MockTable(String path){
		this.path=path;		
	}

	public boolean insert(Persistant obj) {
		// TODO Auto-generated method stub
		try {
			if(findOne(obj))
				return false;
//				throw new DepublicateKeyException();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
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
	
	public boolean findOne(Persistant obj) throws FileNotFoundException{
		List<String[]>list=handler.readCSV(path);
		String key=obj.getKey();
		for(String[]arr:list){
			if(arr[0].equals(key))
				return true;
		}
		return false;
	}
}
