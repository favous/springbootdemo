package com.gyumaru.advertisementManage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.advertisementManage.service.AdvPlanImgService;
import com.gyumaru.advertisementManage.service.AdvPlanService;
import com.gyumaru.advertisementManage.service.AdvPlanTimeService;
import com.gyumaru.advertisementManage.service.AdvPlanUserService;
import com.gyumaru.userManage.pojo.User;

@RequestMapping("advertisement")
@Controller
public class AdvertisementController2 {
	private static Logger logger = Logger.getLogger(AdvertisementController2.class);
	@Autowired
	private AdvPlanTimeService advPlanTimeService;
	@Autowired
	private AdvPlanService advPlanService;
	@Autowired
	private AdvPlanUserService advPlanUserService;
	@Autowired
	private AdvPlanImgService advPlanImgService;
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;
	
	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;
	// 根据日期获取历史记录
	@RequestMapping("getArrange")
	public void getArrange(HttpServletResponse response, @RequestParam String startTime, @RequestParam String endTime, @RequestParam Integer type) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("state", 2);
			List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
			json.put("advPlanTimes", advPlanTimes);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取广告计划信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}
	// 根据id获取某广告的时间安排
	@RequestMapping("getMyArrange")
	public void getMyArrange(HttpServletResponse response, @RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("advPlanId", id);
			List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
			json.put("advPlanTimes", advPlanTimes);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取广告计划信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}
	// 根据id和当前类型获取某广告的时间安排
	@RequestMapping("getNowTypeMyArrange")
	public void getNowTypeMyArrange(HttpServletResponse response, @RequestParam Integer id, @RequestParam Integer type) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("advPlanId", id);
			map.put("type", type);
			List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
			json.put("advPlanTimes", advPlanTimes);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取广告计划信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}
	// 根据时间段获取非自身的广告计划
	@RequestMapping("getNotMyArrange")
	public void getNotMyArrange(HttpServletResponse response, @RequestParam String startTime, @RequestParam String endTime, @RequestParam Integer type,@RequestParam Integer id) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("notAdvPlanId", id);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("state", 2);
			map.put("type", type);
			List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
			json.put("advPlanTimes", advPlanTimes);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取广告计划信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 查询广告详细内容
	@RequestMapping("detail")
	public String detail(Model model,  Integer id, HttpSession session) throws Exception {
		AdvPlan advPlan =advPlanService.getInfoById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advPlanId", advPlan.getId());
		List<AdvPlanImg> advPlanImgs = advPlanImgService.getInfoList(map);
		if(advPlan.getType()==1) {
			for(AdvPlanImg advPlanImg:advPlanImgs) {
				if(advPlanImg.getType()==1) {
					model.addAttribute("advPlanImg1", advPlanImg);
				}else {
					model.addAttribute("advPlanImg2", advPlanImg);
				}
			}
		}else {
			if(advPlanImgs.size()>0) {
				model.addAttribute("advPlanImg", advPlanImgs.get(0));
			}else {
				model.addAttribute("advPlanImg",new AdvPlanImg());
			}
		}
		model.addAttribute("advPlan", advPlan);
		model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);// 查询结果回传
		return "admin/advertisementManage/advPlanDtl";// 跳转
	}
}
