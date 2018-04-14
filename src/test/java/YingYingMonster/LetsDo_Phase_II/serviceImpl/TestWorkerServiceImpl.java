package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.WorkerService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestWorkerServiceImpl {

	@Autowired
	ProjectDAO pjDao;
	
	@Autowired
	WorkerService wkService;
	
	public void setUp() throws FileNotFoundException, IOException{
		Project p1=new Project("pubid","pid",100,10,100,"2018-04-14","2018-04-15",null,null,100);
		pjDao.addProject(p1);
		Project p2=new Project("kate","pig",100,10,100,"2018-04-14","2018-04-15",null,null,100);
		pjDao.addProject(p2);
	}
	
	public void tearDown() throws FileNotFoundException, ClassNotFoundException, IOException {
		pjDao.viewAllProjects().stream().forEach(p->{
			String pubid=p.getPublisherId();
			String pid=p.getProjectId();
			try {
				pjDao.deleteProject(pubid, pid);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void viewAllProjects() throws FileNotFoundException, ClassNotFoundException, IOException {
		setUp();
		List<String>list=wkService.viewAllProjects();
		assertEquals("pig",list.get(0));
		tearDown();
	}

}
