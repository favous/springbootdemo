package com.gyumaru.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.pojo.Menu;
import com.gyumaru.sysManage.dao.MenuDao;
import com.gyumaru.sysManage.service.MenuService;



@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuDao menuDao;
	
	public void insert(Menu menu) throws Exception {
		menuDao.insert(menu);
	}

	public void update(Menu menu) throws Exception {
		menuDao.update(menu);
	}

	public void delete(Integer id) throws Exception {
		menuDao.delete(id);
	}

	public Menu getInfoById(Integer id) throws Exception {
		return menuDao.getInfoById(id);
	}

	public List<Menu> getInfoList(Map<String, Object> map) throws Exception {
		return menuDao.getInfoList(map);
	}

	public void deleteRoleMenuByRoleId(Integer roleId) throws Exception {
		menuDao.deleteRoleMenuByRoleId(roleId);
	}

}
