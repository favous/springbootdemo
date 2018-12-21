package com.gyumaru.sysManage.service;

import java.util.List;
import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.Role;


public interface RoleService extends BaseService<Role>{
	
	void insertRoleMenu(Role role,List<Map<String,Object>> maps) throws Exception;
	
	//带返回值的插入
	Integer insertRole(Role role) throws Exception;
	
	//新增角色授权
	void insertMenu(List<Map<String,Object>> maps) throws Exception;
	
	void deleteRole(Integer id) throws Exception;
}
