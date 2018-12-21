package com.gyumaru.userManage.service;

import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.userManage.pojo.User;


public interface UserService extends BaseService<User>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
