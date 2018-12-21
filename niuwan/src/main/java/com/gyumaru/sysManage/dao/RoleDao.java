package com.gyumaru.sysManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gyumaru.base.dao.BaseDao;
import com.gyumaru.pojo.Role;


@Repository
public interface RoleDao extends BaseDao{
	//角色授权
	void insertRoleMenu(List<Map<String,Object>> maps) throws Exception;
	//带返回值的插入
	Integer insertRole(Role role) throws Exception;
	//新增角色授权
	void insertMenu(List<Map<String,Object>> maps) throws Exception;
}
