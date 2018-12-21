package com.gyumaru.advertisementManage.constant;

public enum AdvtypeEnum {
	
	OpenPage(1, "开屏页"),
	banner(2, "banner"),
	Screen(3,"插屏")
	;
	
	private Integer code;
	private String desc;
	
	private AdvtypeEnum(Integer code, String desc) {
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
