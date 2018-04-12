package YingYingMonster.LetsDo_Phase_II.model;

public class Worker extends User {

	private int level,exp,gap;//等级，经验值，升级所需经验	
	private int tagNum,passedTagNum;//完成的tag总数，合格的tag总数
	
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
