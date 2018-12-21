package com.gyumaru.sysManage.service;

import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.Remind;

public interface RemindService extends BaseService<Remind>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
