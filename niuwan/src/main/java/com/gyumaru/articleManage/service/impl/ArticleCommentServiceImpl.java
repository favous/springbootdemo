package com.gyumaru.articleManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.articleManage.dao.ArticleCommentDao;
import com.gyumaru.articleManage.service.ArticleCommentService;
import com.gyumaru.pojo.ArticleComment;




@Service
public class ArticleCommentServiceImpl implements ArticleCommentService{
	@Autowired
	private ArticleCommentDao ArticleCommentDao;
	
	public void insert(ArticleComment ArticleComment) throws Exception {
		ArticleCommentDao.insert(ArticleComment);
	}

	public void update(ArticleComment ArticleComment) throws Exception {
		ArticleCommentDao.update(ArticleComment);
	}

	public void delete(Integer id) throws Exception {
		ArticleCommentDao.delete(id);
	}

	public ArticleComment getInfoById(Integer id) throws Exception {
		return ArticleCommentDao.getInfoById(id);
	}

	public List<ArticleComment> getInfoList(Map<String, Object> map) throws Exception {
		return ArticleCommentDao.getInfoList(map);
	}
}
