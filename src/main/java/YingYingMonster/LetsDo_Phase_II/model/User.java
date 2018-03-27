package YingYingMonster.LetsDo_Phase_II.model;

public class User implements Persistant{

	public String id,name,pw,type;

	@Override
	public String[] toStrArr() {
		// TODO Auto-generated method stub
		return new String[]{id,name,pw,type};
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id;
	}
}
