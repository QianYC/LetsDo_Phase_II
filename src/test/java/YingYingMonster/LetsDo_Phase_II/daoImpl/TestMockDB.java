package YingYingMonster.LetsDo_Phase_II.daoImpl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import YingYingMonster.LetsDo_Phase_II.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMockDB {

	@Autowired
	MockDBImpl db;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
		User user=new User();
		user.name="testName";
		user.pw="testPw";
		
//		db.createTable("users", new String[]{"id","name","password"});
		assertEquals(true,db.insert("users", user));
	}

}
