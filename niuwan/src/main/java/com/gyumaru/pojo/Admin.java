package com.gyumaru.pojo;

public class Admin {
	private Integer id;
	private String card;//账号
	private String pwd;//密码
	private Integer state;//0 禁用 1正常 -1删除
	private Role role;
	private String groups_name;//组织名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getGroups_name() {
		return groups_name;
	}
	public void setGroups_name(String groups_name) {
		this.groups_name = groups_name;
	}
}
