package com.gyumaru.advertisementManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.advertisementManage.dao.AdvPlanImgDao;
import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.advertisementManage.service.AdvPlanImgService;

@Service
public class AdvPlanImgServiceImpl implements AdvPlanImgService{
	@Autowired
	private AdvPlanImgDao materialImgDao;
	
	public void insert(AdvPlanImg materialImg) throws Exception {
		materialImgDao.insert(materialImg);
	}

	public void update(AdvPlanImg materialImg) throws Exception {
		materialImgDao.update(materialImg);
	}

	public void delete(Integer id) throws Exception {
		materialImgDao.delete(id);
	}

	public AdvPlanImg getInfoById(Integer id) throws Exception {
		return materialImgDao.getInfoById(id);
	}

	public List<AdvPlanImg> getInfoList(Map<String, Object> map) throws Exception {
		return materialImgDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return materialImgDao.getTotal(map);
	};
}
