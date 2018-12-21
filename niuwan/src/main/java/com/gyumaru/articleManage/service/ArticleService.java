package com.gyumaru.articleManage.service;


import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.Article;


public interface ArticleService extends BaseService<Article>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
}
