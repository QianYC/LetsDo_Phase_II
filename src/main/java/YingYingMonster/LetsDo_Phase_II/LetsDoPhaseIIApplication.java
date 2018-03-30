package YingYingMonster.LetsDo_Phase_II;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import YingYingMonster.LetsDo_Phase_II.dao.MockDB;

@SpringBootApplication
public class LetsDoPhaseIIApplication {
	
	@Autowired
	MockDB db;
	
	public static void main(String[] args) {
		SpringApplication.run(LetsDoPhaseIIApplication.class, args);
		System.out.println();
		initialize();
	}
	
	public static void initialize(){
		
		System.out.println("initializing...");
		System.out.println("===============");
		
		String root=System.getProperty("user.home").replaceAll("\\\\", "/")+"/database";
		File database=new File(root);
		if(!database.exists()){
			database.mkdirs();
			System.out.println("making dir for root : "+root);
		}
		
		String projectDir=root+"/projects";
		File projects=new File(projectDir);
		if(!projects.exists()){
			projects.mkdirs();
			System.out.println("making dir for projects : "+projectDir);
		}
		
		String tableDir=root+"/tables";
		File tables=new File(tableDir);
		if(!tables.exists()){
			tables.mkdirs();
			System.out.println("making dir for tables : "+tableDir);
		}
		
		String userDir=root+"/users";
		File users=new File(userDir);
		if(!users.exists()){
			users.mkdirs();
			System.out.println("making dir for users : "+userDir);
		}
		
		String batchDir=root+"/createTable.yym";
		File batch=new File(batchDir);
		if(batch.exists()){
			
			System.out.println("running batch");
		}
	}
}
