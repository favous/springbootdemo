package com.gyumaru.pojo;


import java.io.Serializable;

public class Article implements Serializable {
	/** 文章id */
	private Integer id;
	/*** 作者admin*/
	private Admin articleAuthorAdmin;
	/*** 内容*/
	private String articleContent;
	/*** 文章标题*/
	private String articleTitle;
	/*** 文章摘要*/
	private String articleAbstract;
	/*** 作者*/
	private Integer articleAuthor;
	/*** 文章封面图片地址*/
	private String articleCoverPic;
	/*** 文章点赞人数*/
	private Integer articleThumbsupCount;
	/*** 文章的状态(10:待审核，20:审核驳回，30：审核通过)*/
	private Integer status;
	/*** 数据的状态(0:暂存，1:正常，9:删除)*/
	private Integer state;
	/*** 发布时间*/
	private String publishTime;
	/*** 创建时间*/
	private String createTime;
	/*** 更新时间*/
	private String modifyTime;
	/*** 权限等级*/
	private Integer pl;
	//文章状态中文
	private String statusStr;
	//用户是否点赞1是0不是
	private Integer collertMode;
	
	private static final Long serialVersionUID = 1L;

	/**
	 * 文章id
	 * 
	 * @return id 文章id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 文章id
	 * 
	 * @param id
	 *            文章id
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * 文章标题
	 * 
	 * @return article_title 文章标题
	 */
	public String getArticleTitle() {
		return articleTitle;
	}

	/**
	 * 文章标题
	 * 
	 * @param articleTitle
	 *            文章标题
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle == null ? null : articleTitle.trim();
	}

	/**
	 * 作者
	 * 
	 * @return article_author 作者
	 */
	public Integer getArticleAuthor() {
		return articleAuthor;
	}

	/**
	 * 作者
	 * 
	 * @param articleAuthor
	 *            作者
	 */
	public void setArticleAuthor(Integer articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	/**
	 * 文章封面图片地址
	 * 
	 * @return article_cover_pic 文章封面图片地址
	 */
	public String getArticleCoverPic() {
		return articleCoverPic;
	}

	/**
	 * 文章封面图片地址
	 * 
	 * @param articleCoverPic
	 *            文章封面图片地址
	 */
	public void setArticleCoverPic(String articleCoverPic) {
		this.articleCoverPic = articleCoverPic == null ? null : articleCoverPic.trim();
	}


	/**
	 * 文章的状态(0:未提交审核，1:待审核，2:审核驳回，3：已上线，4：已下线)
	 * 
	 * @return status 文章的状态(0:未提交审核，1:待审核，2:审核驳回，3：已上线，4：已下线)
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 文章的状态(0:未提交审核，1:待审核，2:审核驳回，3：已上线，4：已下线)
	 * 
	 * @param status
	 *            文章的状态(0:未提交审核，1:待审核，2:审核驳回，3：已上线，4：已下线)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 数据的状态(0:正常，1:暂存，9:删除)
	 * 
	 * @return state 数据的状态(0:正常，1:暂存，9:删除)
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 数据的状态(0:正常，1:暂存，9:删除)
	 * 
	 * @param state
	 *            数据的状态(0:正常，1:暂存，9:删除)
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 发布时间
	 * 
	 * @return publish_time 发布时间
	 */
	public String getPublishTime() {
		return publishTime;
	}

	/**
	 * 发布时间
	 * 
	 * @param publishTime
	 *            发布时间
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return modify_time 更新时间
	 */
	public String getModifyTime() {
		return modifyTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param modifyTime
	 *            更新时间
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Admin getArticleAuthorAdmin() {
		return articleAuthorAdmin;
	}

	public void setArticleAuthorAdmin(Admin articleAuthorAdmin) {
		this.articleAuthorAdmin = articleAuthorAdmin;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public Integer getArticleThumbsupCount() {
		return articleThumbsupCount;
	}

	public void setArticleThumbsupCount(Integer articleThumbsupCount) {
		this.articleThumbsupCount = articleThumbsupCount;
	}

	public String getStatusStr() {
		switch (status) {
		case 10:
			return "待审核";
		case 20:
			return "审核驳回";
		case 30:
			return "审核通过";
		default:
			return "未知";
		}
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getCollertMode() {
		return collertMode;
	}

	public void setCollertMode(Integer collertMode) {
		this.collertMode = collertMode;
	}

	public Integer getPl() {
		return pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

	
}