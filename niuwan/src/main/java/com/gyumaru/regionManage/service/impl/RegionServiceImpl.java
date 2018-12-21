package com.gyumaru.regionManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.regionManage.dao.RegionDao;
import com.gyumaru.regionManage.pojo.Region;
import com.gyumaru.regionManage.service.RegionService;



@Service
public class RegionServiceImpl implements RegionService{
	@Autowired
	private RegionDao regionDao;
	
	public void insert(Region region) throws Exception {
		regionDao.insert(region);
	}

	public void update(Region region) throws Exception {
		regionDao.update(region);
	}

	public void delete(Integer id) throws Exception {
		regionDao.delete(id);
	}

	public Region getInfoById(Integer id) throws Exception {
		return regionDao.getInfoById(id);
	}

	public List<Region> getInfoList(Map<String, Object> map) throws Exception {
		return regionDao.getInfoList(map); 
	}
}
