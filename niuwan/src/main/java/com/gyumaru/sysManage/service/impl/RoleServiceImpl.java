package com.gyumaru.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gyumaru.pojo.Role;
import com.gyumaru.sysManage.dao.AdminDao;
import com.gyumaru.sysManage.dao.MenuDao;
import com.gyumaru.sysManage.dao.RoleDao;
import com.gyumaru.sysManage.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private AdminDao adminDao;
	public void insert(Role role) throws Exception {
		roleDao.insert(role);
	}

	public void update(Role role) throws Exception {
		roleDao.update(role);
	}

	public void delete(Integer id) throws Exception {
		roleDao.delete(id);
	}

	public Role getInfoById(Integer id) throws Exception {
		return roleDao.getInfoById(id);
	}

	public List<Role> getInfoList(Map<String, Object> map) throws Exception {
		return roleDao.getInfoList(map);
	}
	@Transactional
	public void insertRoleMenu(Role role, List<Map<String, Object>> maps) throws Exception {
		roleDao.update(role);//修改角色
		menuDao.deleteRoleMenuByRoleId(role.getId());//先取消角色所有权�?
		roleDao.insertRoleMenu(maps);//重新授权
	}

	public Integer insertRole(Role role) throws Exception {
		return roleDao.insertRole(role);
	}

	public void insertMenu(List<Map<String, Object>> maps) throws Exception {
		roleDao.insertMenu(maps);
	}
	@Transactional
	public void deleteRole(Integer id) throws Exception {
		roleDao.delete(id);//根据id删除角色
		menuDao.deleteRoleMenuByRoleId(id);//删除�?有授�?
		adminDao.deleteAdminList(id);//删除�?有该角色下的管理员账�?
	}

}
