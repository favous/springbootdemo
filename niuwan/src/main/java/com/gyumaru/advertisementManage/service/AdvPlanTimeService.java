package com.gyumaru.advertisementManage.service;

import java.util.Map;

import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.base.service.BaseService;


public interface AdvPlanTimeService extends BaseService<AdvPlanTime>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
