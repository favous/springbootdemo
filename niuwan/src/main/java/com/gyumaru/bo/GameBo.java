package com.gyumaru.bo;

import java.util.List;

import com.gyumaru.pojo.Game;

public class GameBo extends Game{
	
	private static final long serialVersionUID = 8716839577366678627L;
	
	private List<ImgBo> imgList;
	
	public List<ImgBo> getImgList() {
		return imgList;
	}

	public void setImgList(List<ImgBo> imgList) {
		this.imgList = imgList;
	}

	public static class ImgBo {
		public ImgBo(Integer type, String url, String surl) {
			super();
			this.type = type;
			this.url = url;
			this.surl = surl;
		}
		private Integer type;//图片类型
		private String url;//
		private String surl;//
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getSurl() {
			return surl;
		}
		public void setSurl(String surl) {
			this.surl = surl;
		}
	}
	
}
