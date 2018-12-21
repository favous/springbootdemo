package com.gyumaru.pojo;

public class Remind {//提醒
	private Integer id;
	private Integer type;//类型 1-服务 2-需求 3-活动 4-组织 5-用户
	private Integer out_id;//关联记录的id
	private Integer groups_id;//组织id
	private String create_time;//创建时间
	private String typeStr;//类型
	public String getTypeStr() {
		switch (type) {
		case 1:
			return "新的服务";
		case 2:
			return "新的需求";
		case 3:
			return "新的活动";
		case 4:
			return "新的组织申请";
		case 5:
			return "新的用户申请";
		default:
			return "未知";
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOut_id() {
		return out_id;
	}
	public void setOut_id(Integer out_id) {
		this.out_id = out_id;
	}
	public Integer getGroups_id() {
		return groups_id;
	}
	public void setGroups_id(Integer groups_id) {
		this.groups_id = groups_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
}
