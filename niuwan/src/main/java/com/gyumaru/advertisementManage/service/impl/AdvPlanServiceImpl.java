package com.gyumaru.advertisementManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.advertisementManage.dao.AdvPlanDao;
import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.advertisementManage.service.AdvPlanService;

@Service
public class AdvPlanServiceImpl implements AdvPlanService{
	@Autowired
	private AdvPlanDao advPlanDao;
	
	public void insert(AdvPlan advPlan) throws Exception {
		advPlanDao.insert(advPlan);
	}

	public void update(AdvPlan advPlan) throws Exception {
		advPlanDao.update(advPlan);
	}

	public void delete(Integer id) throws Exception {
		advPlanDao.delete(id);
	}

	public AdvPlan getInfoById(Integer id) throws Exception {
		return advPlanDao.getInfoById(id);
	}

	public List<AdvPlan> getInfoList(Map<String, Object> map) throws Exception {
		return advPlanDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return advPlanDao.getTotal(map);
	};
}
