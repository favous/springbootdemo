package com.gyumaru.actionLoggerManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Game;

@RequestMapping("actionLogger")
@Controller
public class ActionLoggerController {
	private static Logger logger = Logger.getLogger(ActionLoggerController.class);
	@Autowired
	private ActionLoggerService actionLoggerService;

	// 日志管理
	@RequestMapping("actionLoggerManage")
	public String actionLogger(Model model,String startTime, String endTime) {
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "admin/ActionLoggerManage/ActionLoggerManage";// 跳转
	}
	
	@RequestMapping("list")	
	public void getList(HttpServletResponse response, Integer limit, Integer start, Integer page, String startTime, String endTime) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			List<ActionLogger> actionLogger = actionLoggerService.getInfoList(map);
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(actionLogger, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", actionLoggerService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

				// 删除  （物理删除）
				@RequestMapping("delLog")
				public void delLog(HttpServletRequest request, HttpServletResponse response,Integer id) throws IOException {
					response.setCharacterEncoding("utf-8");
					try {
						actionLoggerService.delete(id);
					response.getWriter().write("success");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("删除游戏时出错了！" + e);
						response.getWriter().write("error");
					}
				}
}
