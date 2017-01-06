package edu.xuchu.user.servlce;

import edu.xuchu.domain.User;
import edu.xuchu.user.dao.UserDao;

public class UserService {
	UserDao dao=new UserDao();
	public User login(String name,String pwd){
		return dao.login(name, pwd);
	}

}
