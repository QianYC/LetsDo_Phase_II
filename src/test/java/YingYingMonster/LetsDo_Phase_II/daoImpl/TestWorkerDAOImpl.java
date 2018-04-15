package YingYingMonster.LetsDo_Phase_II.daoImpl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import YingYingMonster.LetsDo_Phase_II.dao.WorkerDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestWorkerDAOImpl {

	@Autowired
	WorkerDAO wkDao;
	
	@Test
	public void uploadTag1() throws FileNotFoundException, IOException {
		assertEquals(false,wkDao.uploadTag("wkid", "pbid", "pjid", "tgid", null));
	}

}
