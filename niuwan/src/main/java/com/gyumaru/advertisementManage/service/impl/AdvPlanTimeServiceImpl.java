package com.gyumaru.advertisementManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.advertisementManage.dao.AdvPlanTimeDao;
import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.advertisementManage.service.AdvPlanTimeService;

@Service
public class AdvPlanTimeServiceImpl implements AdvPlanTimeService{
	@Autowired
	private AdvPlanTimeDao advPlantimeDao;
	
	public void insert(AdvPlanTime advPlanTime) throws Exception {
		advPlantimeDao.insert(advPlanTime);
	}

	public void update(AdvPlanTime advPlanTime) throws Exception {
		advPlantimeDao.update(advPlanTime);
	}

	public void delete(Integer id) throws Exception {
		advPlantimeDao.delete(id);
	}

	public AdvPlanTime getInfoById(Integer id) throws Exception {
		return advPlantimeDao.getInfoById(id);
	}

	public List<AdvPlanTime> getInfoList(Map<String, Object> map) throws Exception {
		return advPlantimeDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return advPlantimeDao.getTotal(map);
	};
}
