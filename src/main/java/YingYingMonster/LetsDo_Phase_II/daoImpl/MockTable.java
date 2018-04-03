package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.util.ArrayList;
import java.util.List;

public class MockTable {

	public String path;
	
	private CSVHandler handler=new CSVHandler();
	
	public MockTable(String path){
		this.path=path;		
	}

	public boolean insert(Object obj) {
		
		return true;
		
	}

	public List<Object> readAll() {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<>();
		
		return list;
	}

	public boolean modify(Object obj) {
		// TODO Auto-generated method stub
		
		return true;
	}

	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		
		return true;
	}
	
	public boolean findOne(Object obj){
		
		return false;
	}
}
