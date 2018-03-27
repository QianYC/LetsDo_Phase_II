package YingYingMonster.LetsDo_Phase_II;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public String string(){
		return System.getProperty("user.home").replaceAll("\\\\", "/")+"/database";
	}
}
