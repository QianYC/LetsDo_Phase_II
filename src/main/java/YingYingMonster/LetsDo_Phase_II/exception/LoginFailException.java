package YingYingMonster.LetsDo_Phase_II.exception;

public class LoginFailException extends RuntimeException {

	public LoginFailException(){
		super("账号或密码不正确");
	}
}
