package YingYingMonster.LetsDo_Phase_II.model;

/**
 * Publisher暂时还没想到有什么特殊属性
 * @author 17678
 *
 */
public class Publisher extends User {


	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	public Publisher(){}
	
	public Publisher(String id,String name,String pw,String email,String intro,long money){
		super();
		this.setId(id);
		this.setName(name);
		this.setPw(pw);
		this.setEmail(email);
		this.setIntro(intro);
		this.setMoney(money);
	}
	
}
