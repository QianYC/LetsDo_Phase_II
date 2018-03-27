package YingYingMonster.LetsDo_Phase_II.model;

import YingYingMonster.LetsDo_Phase_II.dao.Persistant;

public class User implements Persistant{

	public String id,name,pw;

	@Override
	public String[] toStrArr() {
		// TODO Auto-generated method stub
		return new String[]{id,name,pw};
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id;
	}
}
