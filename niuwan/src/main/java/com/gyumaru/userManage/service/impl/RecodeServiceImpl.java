package com.gyumaru.userManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.userManage.dao.RecodeDao;
import com.gyumaru.userManage.pojo.Recode;
import com.gyumaru.userManage.service.RecodeService;


@Service
public class RecodeServiceImpl implements RecodeService{
	@Autowired
	private RecodeDao recodeDao;
	
	public void insert(Recode recode) throws Exception {
		recodeDao.insert(recode);
	}

	public void update(Recode recode) throws Exception {
		recodeDao.update(recode);
	}

	public void delete(Integer id) throws Exception {
		recodeDao.delete(id);
	}

	public Recode getInfoById(Integer id) throws Exception {
		return recodeDao.getInfoById(id);
	}

	public List<Recode> getInfoList(Map<String, Object> map) throws Exception {
		return recodeDao.getInfoList(map);
	}
}
