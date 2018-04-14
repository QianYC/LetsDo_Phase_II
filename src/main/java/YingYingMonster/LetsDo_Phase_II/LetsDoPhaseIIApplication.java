package YingYingMonster.LetsDo_Phase_II;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetsDoPhaseIIApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LetsDoPhaseIIApplication.class, args);
		System.out.println();
		Initializer init=new Initializer();
		init.initialize();
	}
}
