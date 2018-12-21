package com.gyumaru.advertisementManage.service;

import java.util.Map;

import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.base.service.BaseService;


public interface AdvPlanService extends BaseService<AdvPlan>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
