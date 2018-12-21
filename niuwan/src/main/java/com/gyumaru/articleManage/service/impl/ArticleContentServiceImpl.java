package com.gyumaru.articleManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gyumaru.articleManage.dao.ArticleContentDao;
import com.gyumaru.articleManage.service.ArticleContentService;
import com.gyumaru.pojo.ArticleContent;





@Service
public class ArticleContentServiceImpl implements ArticleContentService{
	@Autowired
	private ArticleContentDao ArticleContentDao;
	
	public void insert(ArticleContent ArticleContent) throws Exception {
		ArticleContentDao.insert(ArticleContent);
	}

	public void update(ArticleContent ArticleContent) throws Exception {
		ArticleContentDao.update(ArticleContent);
	}

	public void delete(Integer id) throws Exception {
		ArticleContentDao.delete(id);
	}

	public ArticleContent getInfoById(Integer id) throws Exception {
		return ArticleContentDao.getInfoById(id);
	}

	public List<ArticleContent> getInfoList(Map<String, Object> map) throws Exception {
		return ArticleContentDao.getInfoList(map);
	}
}
