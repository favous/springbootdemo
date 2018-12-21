package com.gyumaru.sysManage.dao;

import org.springframework.stereotype.Repository;

import com.gyumaru.base.dao.BaseDao;

@Repository
public interface AdminDao extends BaseDao{
	//根据roleId批量删除admin
	void deleteAdminList(Integer roleId) throws Exception;
}
