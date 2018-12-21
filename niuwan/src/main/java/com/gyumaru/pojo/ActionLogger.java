package com.gyumaru.pojo;

import java.io.Serializable;

import com.gyumaru.userManage.pojo.User;

public class ActionLogger implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5653430251611700064L;
	private Integer id;//日志ID
	private Integer adminId;//操作人ID
	private String handle;//操作	
	private String ip;//操作人ip
	private String createTime;//创建时间
	private Admin admin;//用户
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
