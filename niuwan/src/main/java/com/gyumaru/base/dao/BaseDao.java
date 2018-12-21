package com.gyumaru.base.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	<T> void insert(T t) throws Exception;
	
	<T> void update(T t) throws Exception;
	
	void delete(Integer id) throws Exception;
	
	<T> T getInfoById(Integer id) throws Exception;
	
	<T> List<T> getInfoList(Map<String,Object> map) throws Exception;
}
