package com.gyumaru.regionManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.regionManage.dao.CityDao;
import com.gyumaru.regionManage.pojo.City;
import com.gyumaru.regionManage.service.CityService;



@Service
public class CityServiceImpl implements CityService{
	@Autowired
	private CityDao cityDao;
	
	public void insert(City city) throws Exception {
		cityDao.insert(city);
	}

	public void update(City city) throws Exception {
		cityDao.update(city);
	}

	public void delete(Integer id) throws Exception {
		cityDao.delete(id);
	}

	public City getInfoById(Integer id) throws Exception {
		return cityDao.getInfoById(id);
	}

	public List<City> getInfoList(Map<String, Object> map) throws Exception {
		return cityDao.getInfoList(map); 
	}
}
