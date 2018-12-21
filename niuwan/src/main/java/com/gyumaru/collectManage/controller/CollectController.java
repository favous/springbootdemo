package com.gyumaru.collectManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gyumaru.collectManage.service.CollectService;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.pojo.Collect;
import com.gyumaru.pojo.Game;
import com.gyumaru.userManage.pojo.User;

@RequestMapping("collect")
@Controller
public class CollectController {
	private static Logger logger = Logger.getLogger(CollectController.class);
	@Autowired
	private CollectService collectService;

	// 收藏点击事件
	@RequestMapping("chickcollect")
	public void chickcollect( HttpServletResponse response, Integer id,Integer type,HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		User user = (User) session.getAttribute("user");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("modeId", id);
			map.put("type", type);
			map.put("userId", user.getId());
			//根据传入参数查询收藏表中有无数据
			List<Collect> collects= collectService.getInfoList(map);
			if (collects.size()<1) {//没有查询到数据为收藏 （插入数据）
				Collect collect = new Collect();
				collect.setModeId(id);
				collect.setType(type);
				collect.setUserId(user.getId());
				try {
					collectService.insert(collect);
					response.getWriter().write("success1");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("用户收藏出错了！" + e);
					response.getWriter().write("error");
				}
			}else{//查询到了数据为取消收藏(删除表中数据)
				for (int i = 0; i < collects.size(); i++) {
					collectService.delete(collects.get(i).getId());
				}
				response.getWriter().write("success2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询收藏表时出错了！" + e);
			response.getWriter().write("error");
		}
	}
}
