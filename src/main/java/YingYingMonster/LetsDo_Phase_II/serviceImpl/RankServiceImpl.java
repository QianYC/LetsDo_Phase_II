package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.UserDAO;
import YingYingMonster.LetsDo_Phase_II.daoImpl.SerializeHandler;
import YingYingMonster.LetsDo_Phase_II.model.User;
import YingYingMonster.LetsDo_Phase_II.model.Worker;
import YingYingMonster.LetsDo_Phase_II.service.RankService;

@Component
public class RankServiceImpl implements RankService {
	
	@Autowired
	private UserDAO usrDao;
	
	@Override
	public List<User> rankByExp() throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return usrDao.findUsers(null).stream().filter(x->x instanceof Worker)
				.map(x->(Worker)x).sorted(new Comparator<Worker>(){

			@Override
			public int compare(Worker o1, Worker o2) {
				// TODO Auto-generated method stub
				if(o1.getLevel()<o2.getLevel()
						||(o1.getLevel()==o2.getLevel()&&o1.getExp()<o2.getExp()))
					return -1;
				
				if(o1.getLevel()==o2.getLevel()&&o1.getExp()==o2.getExp())
					return 0;
				
				return 1;
			}
			
		}).collect(Collectors.toList());
	}

	@Override
	public List<User> rankByAccuracy() {
		// TODO Auto-generated method stub
		return null;
	}

}
