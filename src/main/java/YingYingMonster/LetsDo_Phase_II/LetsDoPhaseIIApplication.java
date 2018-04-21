package YingYingMonster.LetsDo_Phase_II;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LetsDoPhaseIIApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LetsDoPhaseIIApplication.class, args);
		System.out.println();
		Initializer init=new Initializer();
		init.initialize();
		
	}
}
