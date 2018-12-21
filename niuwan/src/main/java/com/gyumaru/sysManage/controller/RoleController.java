package com.gyumaru.sysManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.gyumaru.pojo.Menu;
import com.gyumaru.pojo.Role;
import com.gyumaru.sysManage.service.MenuService;
import com.gyumaru.sysManage.service.RoleService;



@RequestMapping("admin")
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	//供ajax获取所有角色 json传递
		@RequestMapping("getAllRole")
		public void getAllRole(HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
			JSONObject json=new JSONObject();
			Map<String,Object> map=new HashMap<String,Object>();
			try {
				List<Role> roles=roleService.getInfoList(map);//map 为空 查询所有角色信息
				json.put("success", "success");
				json.put("roles", roles);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("success", "error");
			}
			response.getWriter().write(json.toString());//传值
		}
		
		//角色管理
		@RequestMapping("roleManage")
		public String roleManage(Model model,Integer roleId){
			Map<String,Object> map=new HashMap<String,Object>();
			try {
				List<Role> roles1=roleService.getInfoList(map);//查询所有角色信息 用于查询条件下拉框使用
				map.put("roleId", roleId);
				List<Role> roles=roleService.getInfoList(map);//用于显示查询结果
				model.addAttribute("roles", roles);
				model.addAttribute("allRoles", roles1);
				model.addAttribute("roleId", roleId);
				return "admin/sysManage/roleManage";//跳转
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "404";
		}
		//根据id获取角色信息 
		@RequestMapping("getRoleById")
		public void getRoleById(HttpServletResponse response,Integer id) throws IOException{
			JSONObject json=new JSONObject();
			response.setCharacterEncoding("utf-8");
			try {
				Role role=roleService.getInfoById(id);
				List<Menu> menus=role.getMenus();
				List<Menu> menus2=menuService.getInfoList(new HashMap<String,Object>());
				for (Menu menu : menus2) {//第一遍遍历所有菜单 位角色拥有的菜单赋值选中
					for (Menu menu1 : menus) {//遍历当前选择的角色菜单
						//将当前角色的菜单设置选中状态
						if(menu.getId()==menu1.getId()){
							menu.setChecked(true);
						}
						if(menu.getP_id()==null){//一级菜单 为页面美观添加一个"全部"为二级菜单
							menu.setP_id(0);//因此为一级菜单写入一个id=0
						}
						menu.setMenu_url("");//防止点击跳转 设置url为空
					}
				}
				Menu menu=new Menu();//添加一个"全部"为一级菜单
				menu.setName("全部");
				menu.setId(0);
				menu.setChecked(true);
				menus2.add(menu);
				json.put("success", "success");
				json.put("role", role);
				json.put("menus", menus2);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("success", "error");
			}
			response.getWriter().write(json.toString());
		}
		//新增角色时使用
		@RequestMapping("getAllMenu")
		public void getAllMenu(HttpServletResponse response) throws IOException{
			Map<String,Object> map=new HashMap<String,Object>();
			response.setCharacterEncoding("utf-8");
			JSONObject json=new JSONObject();
			map.put("state", 1);
			try {
				//查询所有菜单
				List<Menu> menus=menuService.getInfoList(map);
				for (Menu menu : menus) {
					if(menu.getP_id()==null){
						menu.setP_id(0);//一级菜单全部设置为"全部"菜单的二级菜单
					}
					menu.setChecked(false);//全部设置为未选择
					menu.setMenu_url("");//防止跳转
				}
				Menu menu=new Menu();
				menu.setId(0);
				menu.setName("全部");
				menus.add(menu);
				json.put("menus", menus);
				json.put("success", "success");
			} catch (Exception e) {
				e.printStackTrace();
				json.put("success", "error");
			}
			response.getWriter().write(json.toJSONString());
		}
		//权限管理
		@RequestMapping("updateRole")
		public void updateRole(Role role,HttpServletResponse response,String mid) throws IOException{
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("roleName", role.getRole_name());
			try {
				List<Role> roles=roleService.getInfoList(m);//验证名称是否已存在
				if(roles.size()>0&&roles.get(0).getId()!=role.getId()){
					response.getWriter().write("exist");
				}else{
				if(mid!=null&&!"".equals(mid)){//如果参数mid不为空 则表示权限有更改 需要重置
					String[] mids=mid.split(",");
					List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
					for (String s : mids) {
						if(!"0".equals(s)){
							Map<String,Object> map=new HashMap<String,Object>();
							map.put("roleId", role.getId());
							map.put("menuId", s);
							maps.add(map);
						}
					}
				roleService.insertRoleMenu(role, maps);//修改授权
				response.getWriter().write("success");
				}else{
						roleService.update(role);
						response.getWriter().write("success");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("error");
			}
		}
		@RequestMapping("addRole")
		public void addRole(Role role,String mid,HttpServletResponse response) throws IOException{

			try {
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("roleName", role.getRole_name());
				List<Role> roles=roleService.getInfoList(m);//验证名称是否已存在
				if(roles.size()>0){
					response.getWriter().write("exist");//返回'exist'
				}else{
				if(mid!=null&&!"".equals(mid)){//如果参数mid不为空 则表示权限有更改 需要重置
				roleService.insertRole(role);
				String[] mids=mid.split(",");
				List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
				for (String s : mids) {
					if(!"0".equals(s)){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("roleId", role.getId());
					map.put("menuId", s);
					maps.add(map);
					}
				}
				roleService.insertMenu(maps);//授权
				response.getWriter().write("success");
				}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("error");
			}
		}
		//删除角色
		@RequestMapping("deleteRole")
		public void deleteRole(Integer id,HttpServletResponse response) throws IOException{
			try {
				roleService.deleteRole(id);
				response.getWriter().write("success");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("error");
			}
		}
		
}
