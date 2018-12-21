package com.gyumaru.actionLoggerManage.service;


import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.ActionLogger;
public interface ActionLoggerService extends BaseService<ActionLogger>{
	//获取总记录条数
		int getTotal(Map<String,Object> map);
}
