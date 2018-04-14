package YingYingMonster.LetsDo_Phase_II.model;

public class Worker extends User {

	private int level,exp,gap;//等级，经验值，升级所需经验	
	private int tagNum,passedTagNum;//完成的tag总数，合格的tag总数
	
	
	
	public Worker() {
		super();
	}
	public Worker(String id,String name,String pw,String email,String intro,long money,int level, int exp, int gap, int tagNum, int passedTagNum) {
		super();
		this.setId(id);
		this.setName(name);
		this.setPw(pw);
		this.setEmail(email);
		this.setIntro(intro);
		this.setMoney(money);
		this.level = level;
		this.exp = exp;
		this.gap = gap;
		this.tagNum = tagNum;
		this.passedTagNum = passedTagNum;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getTagNum() {
		return tagNum;
	}
	public void setTagNum(int tagNum) {
		this.tagNum = tagNum;
	}
	public int getPassedTagNum() {
		return passedTagNum;
	}
	public void setPassedTagNum(int passedTagNum) {
		this.passedTagNum = passedTagNum;
	}
	public int getGap() {
		return gap;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}
	
	
}
