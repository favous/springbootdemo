package com.gyumaru.advertisementManage.service;


import java.util.List;
import java.util.Map;

import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.base.service.BaseService;


public interface AdvPlanUserService extends BaseService<AdvPlanUser>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
	
	//获取总显示和点击次数
	List<AdvPlanUser> getdcNum(Map<String,Object> map);
}
