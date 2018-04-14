package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.UserDAO;
import YingYingMonster.LetsDo_Phase_II.exception.LoginFailException;
import YingYingMonster.LetsDo_Phase_II.exception.ModifyException;
import YingYingMonster.LetsDo_Phase_II.exception.TargetNotFoundException;
import YingYingMonster.LetsDo_Phase_II.model.User;
import YingYingMonster.LetsDo_Phase_II.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=userDao.register(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User login(String id, String pw) {
		// TODO Auto-generated method stub
		User user=null;
		try {
			user=userDao.login(id, pw);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null)
			throw new LoginFailException();
		return user;
	}

	@Override
	public User modify(User user) {
		// TODO Auto-generated method stub
		User res=null;
		try {
			res=userDao.modify(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res==null)
			throw new ModifyException();
		return res;
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		User user=null;
		try {
			user=userDao.getUser(id);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null)
			throw new TargetNotFoundException();
		return user;
	}

	@Override
	public List<User> findUsers(String name) {
		// TODO Auto-generated method stub
		List<User>list=null;
		try {
			list=userDao.findUsers(name);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list==null)
			throw new TargetNotFoundException();
		return list;
	}

	@Override
	public User financeTransaction(String userId, long money) {
		// TODO Auto-generated method stub
		User user=null;
		try {
			user=userDao.financeTransaction(userId, money);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null)
			throw new TargetNotFoundException();
		return user;
	}

	@Override
	public boolean userExist(String id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=userDao.getUser(id)!=null;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
