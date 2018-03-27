package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.FileNotFoundException;
import java.util.List;

import YingYingMonster.LetsDo_Phase_II.model.Persistant;

public class MockTable {

	public String path;
	
	private CSVHandler handler=new CSVHandler();
	
	public MockTable(String path){
		this.path=path;		
	}

	public boolean insert(Persistant obj) {
		if(findOne(obj))
			return false;
//				throw new DepublicateKeyException();
		handler.appendCSV(obj.toStrArr(), path);
		return true;
		
	}

	public List<String[]> readAll() {
		// TODO Auto-generated method stub
		List<String[]> list = null;
		try {
			list = handler.readCSV(path);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return list;
	}

	public boolean modify(Persistant obj) {
		// TODO Auto-generated method stub
		try {
			List<String[]> list=handler.readCSV(path);
			List<String[]> newList=null;
			for(String[] str:list){
				if(str[0].equals(obj.getKey()))
					newList.add(obj.toStrArr());
				else{
					newList.add(str);
				}
			}
			if(!newList.isEmpty())
				handler.writeCSV(newList, path);
			else
				return false;
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Persistant obj) {
		// TODO Auto-generated method stub
		if(!findOne(obj))
			return false;
		else{
			try {
				List<String[]> list=handler.readCSV(path);
				int i=-1;
				for(String[] str:list){
					if(str[0].equals(obj.getKey())){
						i=list.indexOf(str);
						break;
					}
				}
				if(i==-1)
					return false;
				else
					list.remove(i);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean findOne(Persistant obj){
		List<String[]> list=null;
		try {
			list = handler.readCSV(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		String key=obj.getKey();
		if(list!=null){			
			for(String[]arr:list){
				if(arr[0].equals(key))
					return true;
			}
		}
		return false;
	}
}
