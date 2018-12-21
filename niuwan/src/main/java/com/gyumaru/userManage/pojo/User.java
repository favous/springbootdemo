package com.gyumaru.userManage.pojo;

import com.gyumaru.regionManage.pojo.City;
import com.gyumaru.regionManage.pojo.County;
import com.gyumaru.regionManage.pojo.Region;

public class User {
	private Integer id;
	private String uid;//外部id
	private String userid;//内部用户id
	private String account;//登录邮箱
	private String pwd;//密码
	private String name;//昵称
	private Integer sex;//性别
	private Region region;//地区
	private County county;//县级
	private Integer age;//年龄
	private City city;//市级
	private Integer profession;//职业
	private Integer state;//状态暂时未用
	private String headimg;//头像
	private String idfa;//IDFA
	private Integer pl;//权限等级
	private String professionStr;//*职业中文
	private String ageStr;//*年龄中文
	private String sexStr;//*性别中文
	private String addressStr;//*地址
	private String plStr;//*权限等级
	public String getProfessionStr() {
		if(profession==null)
			return "";
		switch (profession) {
		case 11:
			return "金融";
		case 12:
			return "不動産";
		case 13:
			return "法律";
		case 14:
			return "医者";
		case 21:
			return "開発設計";
		case 22:
			return "エンジニア";
		case 23:
			return "運用";
		case 31:
			return "計画";
		case 32:
			return "貿易";
		case 33:
			return "購買";
		case 41:
			return "飲食";
		case 42:
			return "小売り";
		case 43:
			return "アミューズメント";
		case 51:
			return "広告";
		case 52:
			return "映像";
		case 53:
			return "デザイナ";
		case 6:
			return "学生";
		case 7:
			return "そのほか";
		default:
			return "";
		}
	}
	public String getAgeStr() {
		if(age==null)
			return "";
		switch (age) {
		case 1:
			return "10-15";
		case 2:
			return "16-20";
		case 3:
			return "21-25";
		case 4:
			return "26-30";
		case 5:
			return "31-40";
		case 6:
			return "40+";
		default:
			return "";
		}
	}
	public String getAddressStr() {
		String str = "";
		if(region!=null)
			str+=region.getName();
		if(county!=null)
			str+=county.getName();
		if(city!=null)
			str+=city.getName();
		return str;
	}
	
	
	public String getSexStr() {
		if(sex==null)
			return "";
		switch (sex) {
		case 0:
			return "女";
		case 1:
			return "男";
		default:
			return "";
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public County getCounty() {
		return county;
	}
	public void setCounty(County county) {
		this.county = county;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Integer getProfession() {
		return profession;
	}
	public void setProfession(Integer profession) {
		this.profession = profession;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getPl() {
		return pl;
	}
	public void setPl(Integer pl) {
		this.pl = pl;
	}
	public String getPlStr() {
		return plStr;
	}
	public void setPlStr(String plStr) {
		this.plStr = plStr;
	}

	
	
	

}
