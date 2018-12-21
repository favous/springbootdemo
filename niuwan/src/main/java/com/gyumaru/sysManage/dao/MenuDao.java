package com.gyumaru.sysManage.dao;

import org.springframework.stereotype.Repository;

import com.gyumaru.base.dao.BaseDao;

@Repository
public interface MenuDao extends BaseDao{
	//根据角色id删除所有权限 权限更改时使用
	void deleteRoleMenuByRoleId(Integer roleId) throws Exception;
}
