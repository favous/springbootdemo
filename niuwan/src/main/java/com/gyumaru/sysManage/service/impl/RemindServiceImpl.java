package com.gyumaru.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.pojo.Remind;
import com.gyumaru.sysManage.dao.RemindDao;
import com.gyumaru.sysManage.service.RemindService;



@Service
public class RemindServiceImpl implements RemindService{
	@Autowired
	private RemindDao remindDao;
	
	public void insert(Remind remind) throws Exception {
		remindDao.insert(remind);
	}

	public void update(Remind remind) throws Exception {
		remindDao.update(remind);
	}

	public void delete(Integer id) throws Exception {
		remindDao.delete(id);
	}

	public Remind getInfoById(Integer id) throws Exception {
		return remindDao.getInfoById(id);
	}

	public List<Remind> getInfoList(Map<String, Object> map) throws Exception {
		return remindDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return remindDao.getTotal(map);
	};
}
