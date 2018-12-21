package com.gyumaru.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.pojo.Admin;
import com.gyumaru.sysManage.dao.AdminDao;
import com.gyumaru.sysManage.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	public void insert(Admin t) throws Exception {
		adminDao.insert(t);
	}

	public void update(Admin t) throws Exception {
		adminDao.update(t);
	}

	public void delete(Integer id) throws Exception {
		adminDao.delete(id);
	}

	public Admin getInfoById(Integer id) throws Exception {
		return adminDao.getInfoById(id);
	}

	public List<Admin> getInfoList(Map<String, Object> map) throws Exception {
		return adminDao.getInfoList(map);
	}

}
