package com.gyumaru.actionLoggerManage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.actionLoggerManage.dao.ActionLoggerDao;
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.advertManage.dao.AdvertDao;
import com.gyumaru.advertManage.service.AdvertService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Advert;



@Service
public  class ActionLoggerServiceImpl implements ActionLoggerService{
	@Autowired
	private ActionLoggerDao actionLoggerDao;
	
	public void insert(ActionLogger actionLogger) throws Exception {
		actionLoggerDao.insert(actionLogger);
	}

	public void update(ActionLogger actionLogger) throws Exception {
		actionLoggerDao.update(actionLogger);
	}

	public void delete(Integer id) throws Exception {
		actionLoggerDao.delete(id);
	}

	public ActionLogger getInfoById(Integer id) throws Exception {
		return actionLoggerDao.getInfoById(id);
	}

	public List<ActionLogger> getInfoList(Map<String, Object> map) throws Exception {
		return actionLoggerDao.getInfoList(map);
	}
	//获取总记录条数
		public int getTotal(Map<String,Object> map){
			return actionLoggerDao.getTotal(map);
		};
}
