package com.gyumaru.sysManage.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gyumaru.base.dao.BaseDao;

@Repository
public interface RemindDao extends BaseDao{
	//获取总记录条数
		int getTotal(Map<String,Object> map);
}
