package com.gyumaru.sysManage.service;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.Menu;

public interface MenuService extends BaseService<Menu>{
	//根据角色id删除所有权限 权限更改时使用
	void deleteRoleMenuByRoleId(Integer roleId) throws Exception;
}
