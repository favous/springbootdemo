package com.gyumaru.userManage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzc.common.enums.PlEnum;
import com.fzc.common.utils.EnumUtil;
import com.gyumaru.regionManage.pojo.City;
import com.gyumaru.regionManage.pojo.County;
import com.gyumaru.regionManage.pojo.Region;
import com.gyumaru.regionManage.service.CityService;
import com.gyumaru.regionManage.service.CountyService;
import com.gyumaru.regionManage.service.RegionService;
import com.gyumaru.userManage.pojo.Recode;
import com.gyumaru.userManage.pojo.User;
import com.gyumaru.userManage.service.RecodeService;
import com.gyumaru.userManage.service.UserService;
import com.gyumaru.util.MailUtil;
import com.gyumaru.util.MyAESUtil;

@RequestMapping("user")
@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private CountyService countyService;
	@Autowired
	private CityService cityService;
	@Autowired
	private RecodeService recodeService;

	// 用户管理
	@RequestMapping("userManage")
	public String userManage(Model model, String name, Integer state, HttpSession session) throws Exception {
		List<Region> regions = regionService.getInfoList(null);
		LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
		model.addAttribute("regions", regions);
		model.addAttribute("name", name);
		model.addAttribute("plDict", plDict);
		session.setAttribute("name", name);
		return "admin/userManage/userManage";// 跳转
	}

	@RequestMapping("list")
	public void getList(HttpServletResponse response, Integer limit, Integer start, Integer page, String name) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("name", name);
			map.put("state", 1);
			
			List<User> users = userService.getInfoList(map);
			LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
			for(User user:users)
				user.setPlStr(plDict.get(user.getPl()).toString());
			
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(users, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", userService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 根据id获取用户记录
	@RequestMapping("getUserById")
	public void getUserById(HttpServletResponse response, @RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = userService.getInfoById(id);// 根据id查询单个账号
			json.put("user", JSON.parse(JSON.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 根据地区id获取县级记录
	@RequestMapping("getCountyById")
	public void getCountyById(HttpServletResponse response, @RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("regionId", id);
			List<County> countys = countyService.getInfoList(map);
			json.put("countys", countys);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取县级信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 根据县级id获取市级记录
	@RequestMapping("getCityById")
	public void getCityById(HttpServletResponse response, @RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("countyId", id);
			List<City> citys = cityService.getInfoList(map);
			json.put("citys", citys);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取市级信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 根据id删除用户记录
	@RequestMapping("delUser")
	public void delUser(HttpServletResponse response, @RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = userService.getInfoById(id);
			user.setState(-1);
			userService.update(user);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户记录时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 根据id修改需求状态
	@RequestMapping("changeState")
	public void changeState(HttpServletResponse response, @RequestParam Integer id, @RequestParam Integer state) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			if (state == -1) {
				userService.delete(id);
			} else if (state == 2) {
				User user = userService.getInfoById(id);
				user.setState(state);
				userService.update(user);
			}
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("改变用户记录状态时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 新增用户记录
	@RequestMapping("addUser")
	public void addUser(HttpServletResponse response, User user) throws IOException {
		try {
			userService.insert(user);// 插入
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增用户记录时出错了！" + e);
			response.getWriter().write("error");
		}
	}

	// 修改用户记录
	@RequestMapping("modifyUser")
	public void modifyUser(HttpServletResponse response, Integer id,Integer pl) throws IOException {
		try {
			User user = userService.getInfoById(id);
			user.setPl(pl);
			userService.update(user);// 插入
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改用户记录时出错了！" + e);
			response.getWriter().write("error");
		}
	}

	// 获取验证码
	@RequestMapping("getSignupCodes")
	public void getSignupCodes(HttpServletResponse response, String account) throws IOException {
		try {
			Recode recode = new Recode();
			recode.setAccount(account);
			recode.setCodes(MailUtil.mail(account));
			recodeService.insert(recode);
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			response.getWriter().write("error");
		}
	}
	// 注册
	@RequestMapping("signup")
	public void signup(HttpServletResponse response, String account,String name,String pwd,String recode,HttpSession session) throws IOException {
		try {
			User user = new User();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account);
			List<Recode> recodes = recodeService.getInfoList(map);
			if(recodes.size()>0) {
				Recode newRecode = recodes.get(0);
				if(recode.equals(newRecode.getCodes())) {
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<User> users = userService.getInfoList(map);
					if(users.size()>0) {
						response.getWriter().write("sameAccount");
					}else {
						user.setAccount(account);
						String newPwd = MyAESUtil.Encrypt(pwd);
						user.setName(name);
						user.setPwd(newPwd);
						user.setState(1);
						user.setHeadimg("resources/images/moren.png");
						userService.insert(user);
						response.getWriter().write("success");
						session.setAttribute("user", user);
					}
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<Recode> recodes2 = recodeService.getInfoList(map);
					for(Recode recodeTemp:recodes2) {
						recodeService.delete(recodeTemp.getId());
					}
				}else {
					response.getWriter().write("errorRecodes");
				}
			}else {
				response.getWriter().write("noRecodes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			response.getWriter().write("error");
		}
	}
	// 登录
		@RequestMapping("signin")
		public void signin(HttpServletResponse response, String account1,String pwd,HttpSession session) throws IOException {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("account", account1);
				map.put("pwd",MyAESUtil.Encrypt(pwd));
				List<User> users = userService.getInfoList(map);
				if(users.size()>0) {
					session.setAttribute("user", users.get(0));
					response.getWriter().write("success");
				}else{
					response.getWriter().write("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("登录出错了！" + e);
				response.getWriter().write("error2");
			}
		}
}
