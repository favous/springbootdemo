package com.gyumaru.advertisementManage.service;

import java.util.Map;

import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.base.service.BaseService;


public interface AdvPlanImgService extends BaseService<AdvPlanImg>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
