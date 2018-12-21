package com.gyumaru.userManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.userManage.dao.UserDao;
import com.gyumaru.userManage.pojo.User;
import com.gyumaru.userManage.service.UserService;




@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public void insert(User user) throws Exception {
		userDao.insert(user);
	}

	public void update(User user) throws Exception {
		userDao.update(user);
	}

	public void delete(Integer id) throws Exception {
		userDao.delete(id);
	}

	public User getInfoById(Integer id) throws Exception {
		return userDao.getInfoById(id);
	}

	public List<User> getInfoList(Map<String, Object> map) throws Exception {
		return userDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return userDao.getTotal(map);
	};
}
