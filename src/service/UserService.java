package service;

import java.util.List;
import java.util.Map;

import common.UserInfo;
import dao.UserDao;

public class UserService {

	private UserDao userDao = new UserDao();
	
	public boolean insert(String name,String password,String email){
		UserInfo user= new UserInfo();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		return userDao.insert(user);
	}
	
	public List<String> login(String name,String password){
	    return userDao.login(name, password);
	}
	
	public boolean modify(List<String> list,Object...obj){
		return userDao.modify(list,obj);
	}
	public Map<String,List<String>> query(Object...obj){
		return userDao.queryInfo(obj);
	}
	
	public boolean deleteUser(int id){
		return userDao.delete(id);
	}
}
