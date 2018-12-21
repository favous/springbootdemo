package com.fzc.common.enums;

public enum BooleanEnum {
	
	FALSE(0, "否"),
	TRUE(1, "是"),
	;
	
	private Integer code;
	private String desc;
	
	private BooleanEnum(Integer code, String desc) {
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
