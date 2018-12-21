package com.gyumaru.advertManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.advertManage.dao.AdvertDao;
import com.gyumaru.advertManage.service.AdvertService;
import com.gyumaru.pojo.Advert;



@Service
public class AdvertServiceImpl implements AdvertService{
	@Autowired
	private AdvertDao advertDao;
	
	public void insert(Advert advert) throws Exception {
		advertDao.insert(advert);
	}

	public void update(Advert advert) throws Exception {
		advertDao.update(advert);
	}

	public void delete(Integer id) throws Exception {
		advertDao.delete(id);
	}

	public Advert getInfoById(Integer id) throws Exception {
		return advertDao.getInfoById(id);
	}

	public List<Advert> getInfoList(Map<String, Object> map) throws Exception {
		return advertDao.getInfoList(map);
	}
	
}
