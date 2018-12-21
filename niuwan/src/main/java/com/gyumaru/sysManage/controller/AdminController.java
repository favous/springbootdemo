package com.gyumaru.sysManage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Admin;
import com.gyumaru.pojo.Menu;
import com.gyumaru.pojo.Remind;
import com.gyumaru.pojo.Role;
import com.gyumaru.util.AppMD5Util;
import com.gyumaru.sysManage.service.AdminService;
import com.gyumaru.sysManage.service.RemindService;
import com.gyumaru.sysManage.service.RoleService;

@Controller
@RequestMapping("admin")
public class AdminController {
	private static Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RemindService remindService;
	@Autowired
	private ActionLoggerService actionLoggerService;
	
	@RequestMapping("gotoLogin")
	public String gotoLogin(String key,String code,Model model){
		model.addAttribute("key", key);
		model.addAttribute("code", code);
		return "admin/login";
	}
	@RequestMapping("login")
	public String login(HttpServletRequest request,String card,String imageCode,String pwd,HttpSession session,Model model){//管理员登录
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(!imageCode.equals(session.getAttribute("sRand"))){//验证码输入错误
				return "redirect:gotoLogin?code=error";
		}
		if(card==null||"".equals(card)||pwd==null||"".equals(pwd))
			return "redirect:gotoLogin?key=error";
		map.put("card", card);
		map.put("pwd", AppMD5Util.getDigest(pwd));
		map.put("state", 1);
		try {
			List<Admin> admins=adminService.getInfoList(map);
			if(admins.size()>0){
				session.setAttribute("admin", admins.get(0));
				ActionLogger actionLogger = new ActionLogger();
				actionLogger.setAdminId(admins.get(0).getId());
				actionLogger.setHandle("账号"+admins.get(0).getCard()+"登录了系统");
					InetAddress ia=null;
		            ia=ia.getLocalHost();
		            String localname=ia.getHostName();
		            String localip=ia.getHostAddress();
		            System.out.println("本机名称是："+ localname);
		            System.out.println("本机的ip是 ："+localip);
			        actionLogger.setIp(localip);
			        actionLoggerService.insert(actionLogger);
				return "redirect:gotoAdminMain";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录时出错了！"+e);
		}
		return "redirect:gotoLogin?key=error";
	}
	
	@RequestMapping("gotoAdminMain")
	public String adminManage(HttpSession session,Model model) throws Exception{
		Admin adminTemp=(Admin) session.getAttribute("admin");
		Admin admin = adminService.getInfoById(adminTemp.getId());
		session.setAttribute("admin", admin);
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("adminId", admin.getId());
		map.put("key", "noEnd");
		List<Menu> menus=admin.getRole().getMenus();
		List<Menu> menus2=new ArrayList<Menu>();
		for (Menu menu : menus) {//第一次遍历menu
			if(menu.getP_id()==null||"".equals(menu.getP_id())){//若pid为空则表示为一级菜单 取出
				List<Menu> menus1=new ArrayList<Menu>();
				for (Menu menu1 : menus) {//二次遍历menu
					if(menu1.getP_id()!=null&&menu1.getP_id()==menu.getId()){//pid不为空表示二级菜单 取出与pid与一级菜单相同的菜单
						List<Menu> menus3=new ArrayList<Menu>();//三级菜单
						for (Menu menu2 : menus) {
							if(menu1.getId()==menu2.getP_id()){
								menus3.add(menu2);
							}
						}
						menu1.setMenus(menus3);
						menus1.add(menu1);//放入menulist中 预放入一级菜单中
					}
				}
				menu.setMenus(menus1);//放入一级菜单 
				menus2.add(menu);//重构新的menulist
			}
		}
		session.setAttribute("menus", menus2);
		map=new HashMap<String,Object>();
		/*for(Menu menu:menus) {
			if(menu.getId()==1007)
				map.put("type1", 1);
			if(menu.getId()==1008)
				map.put("type2", 1);
			if(menu.getId()==1010)
				map.put("type3", 1);
			if(menu.getId()==10023 && admin.getGroups_id()==null)
				map.put("type4", 1);
			if(menu.getId()==10023 && admin.getGroups_id()!=null) {
				map.put("groups_id", admin.getGroups_id());
			}
			if(menu.getId()==1003)
				map.put("type5", 1);
		}
		if(map.size()>0) {
			List<Remind> reminds = remindService.getInfoList(map);
			model.addAttribute("reminds",reminds);*/
		/*}else {*/
			model.addAttribute("reminds",null);
		/*}*/
		
		
		return "admin/adminMain";
	}
	@RequestMapping("getAdminById")
	public void getAdminById(HttpServletResponse response,@RequestParam Integer id) throws IOException{
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		try {
			Admin admin=adminService.getInfoById(id);//根据id查询单个账号
			json.put("ad", admin);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取账户信息时出错了！"+e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}
	//账号管理
	@RequestMapping("adminManage")
	public String getAdmins(Model model,String card,Integer roleId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("card", card);
		map.put("role_id", roleId);
		try {
			List<Admin> admins=adminService.getInfoList(map);//根据条件查询
			List<Role> roles=roleService.getInfoList(new HashMap<String,Object>());
			for(Admin admin: admins) {
					admin.setGroups_name("总管理");
			}
			
			model.addAttribute("roles", roles);//查询结果回传
			model.addAttribute("admins", admins);
			model.addAttribute("roleId", roleId);//查询条件回传
			model.addAttribute("card", card);
		} catch (Exception e) {
			logger.error("前往账户管理时出错了！"+e);
			e.printStackTrace();
		}
		return "admin/sysManage/adminManage";
	}
	//登出
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();//清除session
		return "redirect:gotoLogin";//返回登录页面
	}
	//单个添加管理员
	@RequestMapping("addAdmin")
	public void addAdmin(HttpServletResponse response,Admin admin) throws IOException{
		String md5pwd=AppMD5Util.getDigest(admin.getPwd());
		/*if(admin.getGroups_id()==0)
			admin.setGroups_id(null);*/
		admin.setPwd(md5pwd);
		admin.setState(1);
		try {
			Map<String ,Object> map=new HashMap<String,Object>();
			map.put("card", admin.getCard());//将预添加的账号放入查询条件 此处应精准查询
			List<Admin> admins=adminService.getInfoList(map);//查询
			if(admins.size()>0){//如果有结果 则表示账号已存在
				response.getWriter().write("exist");
			}else{
			adminService.insert(admin);//插入
			response.getWriter().write("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增账户时出错了！"+e);
			response.getWriter().write("error");
		}
	}
	//修改密码
	@RequestMapping("modifyPWD")
	public void modifyPWD(HttpServletResponse response,HttpSession session,@RequestParam String pwd,@RequestParam String pwd1,@RequestParam String pwd2) throws IOException{
		Admin admin=(Admin) session.getAttribute("admin");
		//将输入的原密码用md5加密
		String md5pwd=AppMD5Util.getDigest(pwd);
		//与当前密码比较是否相同
		if(md5pwd.equals(admin.getPwd())){
			//相同则比较输入的两次新密码是否相同
			if(pwd1.equals(pwd2)){
				//相同则修改密码
				String newmd5pwd=AppMD5Util.getDigest(pwd1);
				admin.setPwd(newmd5pwd);
				try {
					adminService.update(admin);
					//修改成功返回success
					response.getWriter().write("success");
				} catch (Exception e) {
					//修改失败 返回error
					e.printStackTrace();
					logger.error("修改账户密码时出错了！"+e);
					response.getWriter().write("error");
				}
			}else{
				//不相同则返回两次密码不一致 diff
				response.getWriter().write("diff");
			}
		}else{
			//输入的原密码与当前密码不一致 则返回失败 failed
			response.getWriter().write("failed");
		}
	}
	//修改账号信息
	@RequestMapping("updateAdmin")
	public void updateAdmin(HttpServletResponse response,Admin admin) throws IOException{
		//如果传来的admin对象密码不为空
		if(admin.getPwd()!=null && !"".equals(admin.getPwd()))
		//将传来的admin对象密码进行MD5加密
			admin.setPwd(AppMD5Util.getDigest(admin.getPwd()));
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("card", admin.getCard());//条件加入u_card 用于验证账号是否存在
			List<Admin> admins=adminService.getInfoList(map);
			if(admins.size()>0){
				if(admins.get(0).getId()!=admin.getId()){//如果查询到的结果id与当前预修改的id不同 则表示card重复
					response.getWriter().write("same");//返回same 表示重复
				}else{
				adminService.update(admin);//修改
				response.getWriter().write("success");
				}
			}else{
				adminService.update(admin);//未查询到结果则直接修改
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改账户信息时出错了！"+e);
			response.getWriter().write("error");
		}
	}
	//删除账号 物理删除
	@RequestMapping("deleteAdmin")
	public void deleteAdmin(@RequestParam Integer id,HttpServletResponse response) throws IOException{
		try {
			adminService.delete(id);//删除
			response.getWriter().write("success");//成功
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("error");//失败
		}
	}
	//修改状态 0禁用 1正常
	@RequestMapping("changeState")
	public void changeState(HttpServletResponse response,Integer id,Integer state) throws IOException{
		Admin admin=new Admin();
		admin.setId(id);
		admin.setState(state);
		try {
			adminService.update(admin);
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("error");
		}
		
	}
}
