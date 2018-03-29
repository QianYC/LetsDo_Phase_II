package YingYingMonster.LetsDo_Phase_II;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetsDoPhaseIiApplication {
	
	public static void main(String[] args) {
		initialize();
		SpringApplication.run(LetsDoPhaseIiApplication.class, args);
	}
	
	public static void initialize(){
		
		String root=System.getProperty("user.home").replaceAll("\\\\", "/")+"/database";
		File database=new File(root);
		if(!database.exists())
			database.mkdirs();
		
		
	}
}
