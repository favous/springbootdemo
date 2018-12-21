package com.gyumaru.advertisementManage.constant;

public enum AdvstateEnum {
	
	state1(1, "待审核"),
	state2(2, "审核通过"),
	state3(3,"审核不通过")
	;
	
	private Integer code;
	private String desc;
	
	private AdvstateEnum(Integer code, String desc) {
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
