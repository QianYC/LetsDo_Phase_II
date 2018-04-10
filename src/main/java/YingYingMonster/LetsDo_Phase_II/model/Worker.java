package YingYingMonster.LetsDo_Phase_II.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Worker extends User {

	private int level,exp,gap;//等级，经验值，升级所需经验	
	private int tagNum,passedTagNum;//完成的tag总数，合格的tag总数
	private String id,name,pw,email,intro;//账号，昵称，密码，邮箱，简介
	private long money;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		//md5 encode
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[]bytes=md.digest(pw.getBytes());
			this.pw=new String(bytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
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
		return id;
	}
	
	
}
