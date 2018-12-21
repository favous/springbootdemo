package com.gyumaru.collectManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.collectManage.dao.CollectDao;
import com.gyumaru.collectManage.service.CollectService;
import com.gyumaru.gameManage.dao.GameDao;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.pojo.Collect;
import com.gyumaru.pojo.Game;



@Service
public class CollectServiceImpl implements CollectService{
	@Autowired
	private CollectDao collectDao;
	
	public void insert(Collect collect) throws Exception {
		collectDao.insert(collect);
	}

	public void update(Collect collect) throws Exception {
		collectDao.update(collect);
	}

	public void delete(Integer id) throws Exception {
		collectDao.delete(id);
	}

	public Collect getInfoById(Integer id) throws Exception {
		return collectDao.getInfoById(id);
	}

	public List<Collect> getInfoList(Map<String, Object> map) throws Exception {
		return collectDao.getInfoList(map); 
	}
	
}
