package com.gyumaru.pojo;

import java.util.List;


public class Role {//角色
	private Integer id;
	private String role_name;
	private List<Menu> menus;//用来存放该角色权限菜单
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}
