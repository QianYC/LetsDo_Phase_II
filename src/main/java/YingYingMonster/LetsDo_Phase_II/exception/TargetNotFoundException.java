package YingYingMonster.LetsDo_Phase_II.exception;

public class TargetNotFoundException extends RuntimeException {

	public TargetNotFoundException(){
		super("目标对象不存在！");
	}
}
