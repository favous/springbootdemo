package com.gyumaru.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述:article_comment表的实体类
 * @version
 * @author:  relieved
 * @创建时间: 2017-04-19
 */
public class ArticleComment implements Serializable {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 评论类型(1:评论,2:回复)
     */
    private String commentType;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论者、回复者id
     */
    private Integer fromUid;

    /**
     * 回复目标用户id
     */
    private Integer toUid;

    /**
     * 回复目标id(评论的id,非回复的id)
     */
    private Integer replyId;

    /**
     * 数据的状态(0:正常，1:暂存，9:删除)
     */
    private Integer state;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * article_comment
     */
    private static final Long serialVersionUID = 1L;


    /**
     * 评论id
     * @return id 评论id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 评论id
     * @param id 评论id
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
     * 评论类型(1:评论,2:回复)
     * @return comment_type 评论类型(1:评论,2:回复)
     */
    public String getCommentType() {
        return commentType;
    }

    /**
     * 评论类型(1:评论,2:回复)
     * @param commentType 评论类型(1:评论,2:回复)
     */
    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }

    /**
     * 评论内容
     * @return comment_content 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 评论内容
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * 评论者、回复者id
     * @return from_uid 评论者、回复者id
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * 评论者、回复者id
     * @param fromUid 评论者、回复者id
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * 回复目标用户id
     * @return to_uid 回复目标用户id
     */
    public Integer getToUid() {
        return toUid;
    }

    /**
     * 回复目标用户id
     * @param toUid 回复目标用户id
     */
    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    /**
     * 回复目标id(评论的id,非回复的id)
     * @return reply_id 回复目标id(评论的id,非回复的id)
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 回复目标id(评论的id,非回复的id)
     * @param replyId 回复目标id(评论的id,非回复的id)
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 数据的状态(0:正常，1:暂存，9:删除)
     * @return state 数据的状态(0:正常，1:暂存，9:删除)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 数据的状态(0:正常，1:暂存，9:删除)
     * @param state 数据的状态(0:正常，1:暂存，9:删除)
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return modify_time 更新时间
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * 更新时间
     * @param modifyTime 更新时间
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

}