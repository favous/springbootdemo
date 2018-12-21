package com.gyumaru.advertisementManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.base.dao.BaseDao;


@Repository
public interface AdvPlanUserDao extends BaseDao{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
	
	//获取总显示和点击次数
	List<AdvPlanUser> getdcNum(Map<String,Object> map);
}
