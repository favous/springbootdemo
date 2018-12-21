package com.gyumaru.loginTokenManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.loginTokenManage.dao.LoginTokenDao;
import com.gyumaru.loginTokenManage.service.LoginTokenService;
import com.gyumaru.pojo.LoginToken;



@Service
public class LoginTokenServiceImpl implements LoginTokenService{
	@Autowired
	private LoginTokenDao loginTokenDao;
	
	public void insert(LoginToken loginToken) throws Exception {
		loginTokenDao.insert(loginToken);
	}

	public void update(LoginToken loginToken) throws Exception {
		loginTokenDao.update(loginToken);
	}

	public void delete(Integer id) throws Exception {
		loginTokenDao.delete(id);
	}

	public LoginToken getInfoById(Integer id) throws Exception {
		return loginTokenDao.getInfoById(id);
	}

	public List<LoginToken> getInfoList(Map<String, Object> map) throws Exception {
		return loginTokenDao.getInfoList(map); 
	}
	
}
