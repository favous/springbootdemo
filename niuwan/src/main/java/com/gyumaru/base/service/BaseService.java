package com.gyumaru.base.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	void insert(T t) throws Exception;
	
	void update(T t) throws Exception;
	
	void delete(Integer id) throws Exception;
	
	T getInfoById(Integer id) throws Exception;
	
	List<T> getInfoList(Map<String,Object> map) throws Exception;
}
