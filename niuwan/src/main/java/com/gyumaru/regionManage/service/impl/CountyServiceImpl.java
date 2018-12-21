package com.gyumaru.regionManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.regionManage.dao.CountyDao;
import com.gyumaru.regionManage.pojo.County;
import com.gyumaru.regionManage.service.CountyService;



@Service
public class CountyServiceImpl implements CountyService{
	@Autowired
	private CountyDao countyDao;
	
	public void insert(County county) throws Exception {
		countyDao.insert(county);
	}

	public void update(County county) throws Exception {
		countyDao.update(county);
	}

	public void delete(Integer id) throws Exception {
		countyDao.delete(id);
	}

	public County getInfoById(Integer id) throws Exception {
		return countyDao.getInfoById(id);
	}

	public List<County> getInfoList(Map<String, Object> map) throws Exception {
		return countyDao.getInfoList(map); 
	}
}
