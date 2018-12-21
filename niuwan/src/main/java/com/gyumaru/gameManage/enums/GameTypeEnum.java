package com.gyumaru.gameManage.enums;

public enum GameTypeEnum {
	/** 动作 */
	ACTION(1, "アクションゲーム"),
	
	/** 角色扮演 */
	ROLE(2, "ロールプレインゲーム"),
	
	/** 益智 */
	ALPINIA(3, "パズルゲーム"),
	
	/** 模仿 */
	IMITATE(4, "シミュレーションゲーム"),
	
	/** 冒险 */
	ADVENTURE(5, "アドベンチャーゲーム"),
	
	/** 射击 */
	SHOOT(6, "シューティングゲーム"),
	
	/** 体育 */
	SPORTS(7, "スポーツゲーム"),
	
	/** 休闲 */
	ARDER(8, "レースゲーム"),
	
	/** 音乐 */
	MUSIC(9, "音楽ゲーム"),
	;
	
	private Integer code;
	private String desc;
	
	private GameTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(Integer code) {
		if (code == null) {
			return null;
		}
		for (GameTypeEnum e :GameTypeEnum.values()) {
			if (code == e.getCode()) {
				return e.getDesc();
			}
		}
		return null;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}

}
