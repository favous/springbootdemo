package com.fzc.common.enums;

public enum PlEnum {
	
	pl1(0, "游客"),
	pl2(15, "用户"),
	pl3(25, "会员"),
	pl4(35, "管理员"),
	pl5(50, "超级管理员"),
	;
	
	private Integer code;
	private String desc;
	
	private PlEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
}
