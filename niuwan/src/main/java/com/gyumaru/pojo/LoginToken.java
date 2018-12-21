package com.gyumaru.pojo;

public class LoginToken {
	private Integer id;
	private Integer userId;
	private String token;
	private Integer loginTime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
