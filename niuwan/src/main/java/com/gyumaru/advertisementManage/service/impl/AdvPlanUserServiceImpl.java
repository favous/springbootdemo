package com.gyumaru.advertisementManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.advertisementManage.dao.AdvPlanUserDao;
import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.advertisementManage.service.AdvPlanUserService;

@Service
public class AdvPlanUserServiceImpl implements AdvPlanUserService{
	@Autowired
	private AdvPlanUserDao advPlanUserDao;
	
	public void insert(AdvPlanUser advPlanUser) throws Exception {
		advPlanUserDao.insert(advPlanUser);
	}

	public void update(AdvPlanUser advPlanUser) throws Exception {
		advPlanUserDao.update(advPlanUser);
	}

	public void delete(Integer id) throws Exception {
		advPlanUserDao.delete(id);
	}

	public AdvPlanUser getInfoById(Integer id) throws Exception {
		return advPlanUserDao.getInfoById(id);
	}

	public List<AdvPlanUser> getInfoList(Map<String, Object> map) throws Exception {
		return advPlanUserDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return advPlanUserDao.getTotal(map);
	};
	//获取总显示和点击次数
	public List<AdvPlanUser> getdcNum(Map<String, Object> map) {
		return advPlanUserDao.getdcNum(map);
	}
}
