package com.gyumaru.articleManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.articleManage.dao.ArticleDao;
import com.gyumaru.articleManage.service.ArticleService;
import com.gyumaru.pojo.Article;


@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleDao ArticleDao;
	
	public void insert(Article Article) throws Exception {
		ArticleDao.insert(Article);
	}

	public void update(Article Article) throws Exception {
		ArticleDao.update(Article);
	}

	public void delete(Integer id) throws Exception {
		ArticleDao.delete(id);
	}

	public Article getInfoById(Integer id) throws Exception {
		return ArticleDao.getInfoById(id);
	}

	public List<Article> getInfoList(Map<String, Object> map) throws Exception {
		return ArticleDao.getInfoList(map);
	}
	//获取总记录条数
	public int getTotal(Map<String, Object> map) {
		return ArticleDao.getTotal(map);
	}
}
