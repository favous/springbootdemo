package com.gyumaru.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述:article_content表的实体类
 * @version
 * @author:  relieved
 * @创建时间: 2017-04-19
 */
public class ArticleContent implements Serializable {
    /**
     * 文章内容id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * article_content
     */
    private static final Long serialVersionUID = 1L;


    /**
     * 文章内容id
     * @return id 文章内容id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章内容id
     * @param id 文章内容id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 文章id
     * @return article_id 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     * @param articleId 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 文章内容
     * @return article_content 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 文章内容
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

}