package YingYingMonster.LetsDo_Phase_II.model;

public class User implements Persistent{

	public String id,name,pw,type;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id;
	}
	
}
