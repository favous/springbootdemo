package com.gyumaru.advertisementManage.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.advertisementManage.service.AdvPlanImgService;
import com.gyumaru.advertisementManage.service.AdvPlanService;
import com.gyumaru.advertisementManage.service.AdvPlanTimeService;
import com.gyumaru.util.UtilUpName;

@RequestMapping("advertisement")
@Controller
public class AdvertisementController {
	private static Logger logger = Logger.getLogger(AdvertisementController.class);
	@Autowired
	private AdvPlanService advPlanService;
	@Autowired
	private AdvPlanImgService advPlanImgService;
	@Autowired
	private AdvPlanTimeService advPlanTimeService;
	
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;
	
	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;
	
	// 广告新增
	@RequestMapping("advsaveManage")
	public String advsave(Model model) {
		model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);// 查询结果回传
		return "admin/advertisementManage/advsaveManage";// 跳转
	}

	// 跳转到广告修改
	@RequestMapping("advModify")
	public String advModify(Model model, Integer id) {
		try {
			AdvPlan advPlan = advPlanService.getInfoById(id);
			model.addAttribute("advPlan", advPlan);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("advPlanId", id);
			List<AdvPlanImg> advPlans = advPlanImgService.getInfoList(map);
			if (advPlans.size() > 1) {
				for (int i = 0; i < advPlans.size(); i++) {
					if (advPlans.get(i).getType() == 1) {
						model.addAttribute("advPlanImg1", advPlans.get(i).getUrl());
					} else {
						model.addAttribute("advPlanImg2", advPlans.get(i).getUrl());
					}
				}
			}
			model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);// 查询结果回传
			model.addAttribute("advPlanImg", advPlans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/advertisementManage/advModify";// 跳转
	}

	// 保存icon图
	@RequestMapping("saveicon")
	public void saveicon(HttpServletRequest request, HttpServletResponse response, Integer imgType) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			String url = imgPreFixUrl+"advImg";	
			String basePath = "advImg/";
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			if (imgType == 1) {
				MultipartFile filename = multiRequest.getFile("iphone8");// 获取图片
				String MyfileFileName = filename.getOriginalFilename();
				InputStream is = filename.getInputStream();
				File upFile = new File(url);
				if (!upFile.isDirectory() && !upFile.exists()) {
					upFile.mkdir();
				}
				// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
				String newFilename =UtilUpName.getImgUpName(MyfileFileName);
				// 输出文件
				FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);
				byte[] b = new byte[is.available()];
				is.read(b);
				fileOutputStream.write(b);
				// 关闭流
				is.close();
				fileOutputStream.close();
				json.put("picPath", basePath+newFilename);
				json.put("type", imgType);
				json.put("success", "success");
			} else if (imgType == 2) {
				MultipartFile filename = multiRequest.getFile("iphonex");// 获取图片
				String MyfileFileName = filename.getOriginalFilename();
				InputStream is = filename.getInputStream();
				File upFile = new File(url);
				if (!upFile.isDirectory() && !upFile.exists()) {
					upFile.mkdir();
				}
				// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
				String newFilename =UtilUpName.getImgUpName(MyfileFileName);
				// 输出文件
				FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);

				byte[] b = new byte[is.available()];
				is.read(b);
				fileOutputStream.write(b);
				// 关闭流
				is.close();
				fileOutputStream.close();
				json.put("picPath", basePath+newFilename);
				json.put("type", imgType);
				json.put("success", "success");
			} else if (imgType == 3) {
				MultipartFile filename = multiRequest.getFile("banner");// 获取图片
				String MyfileFileName = filename.getOriginalFilename();
				InputStream is = filename.getInputStream();
				File upFile = new File(url);
				if (!upFile.isDirectory() && !upFile.exists()) {
					upFile.mkdir();
				}
				// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
				String newFilename =UtilUpName.getImgUpName(MyfileFileName);
				// 输出文件
				FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);

				byte[] b = new byte[is.available()];
				is.read(b);
				fileOutputStream.write(b);
				// 关闭流
				is.close();
				fileOutputStream.close();
				json.put("picPath", basePath+newFilename);
				json.put("type", imgType);
				json.put("success", "success");
			} else if (imgType == 4) {
				MultipartFile filename = multiRequest.getFile("cp");// 获取图片
				String MyfileFileName = filename.getOriginalFilename();
				InputStream is = filename.getInputStream();
				File upFile = new File(url);
				if (!upFile.isDirectory() && !upFile.exists()) {
					upFile.mkdir();
				}
				// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
				 String newFilename =UtilUpName.getImgUpName(MyfileFileName);
				// 输出文件
				FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);

				byte[] b = new byte[is.available()];
				is.read(b);
				fileOutputStream.write(b);
				// 关闭流
				is.close();
				fileOutputStream.close();
				json.put("picPath", basePath+newFilename);
				json.put("type", imgType);
				json.put("success", "success");
			}
			json.put("outImgPreFixUrl",outImgPreFixUrl);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增游戏时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 广告新增
	@RequestMapping("advAdd")
	public void advAdd(HttpServletResponse response, HttpServletRequest request, HttpSession session, String name, Integer type, String startTime, String endTime, String link, String introduction, Long expectedNum, Long dispalyNum, Long clickNum, String timeList, String b_bannerImg, String b_kp1Img, Integer id, String b_kp2Img, String b_cpImg,Long totalNum,Long totaldisNum) {
		response.setCharacterEncoding("utf-8");
		if (id == null) {
			AdvPlan advPlan = new AdvPlan();
			advPlan.setName(name);
			advPlan.setStartdate(startTime);
			advPlan.setEnddate(endTime);
			advPlan.setClickNum(clickNum);
			advPlan.setDispalyNum(dispalyNum);
			advPlan.setExpectedNum(expectedNum);
			advPlan.setIntroduction(introduction);
			advPlan.setTotaldisNum(totaldisNum);
			advPlan.setTotalNum(totalNum);
			advPlan.setLink(link);
			advPlan.setType(type);
			advPlan.setState(1);
			try {
				advPlanService.insert(advPlan);
				AdvPlanImg advPlanImg = new AdvPlanImg();
				advPlanImg.setAdvPlanId(advPlan.getId());
				advPlanImg.setType(1);
				if (type == 1) {
					advPlanImg.setUrl(b_kp1Img);
					advPlanImgService.insert(advPlanImg);
					advPlanImg.setUrl(b_kp2Img);
					advPlanImg.setType(2);
					advPlanImgService.insert(advPlanImg);
				} else if (type == 2) {
					advPlanImg.setUrl(b_bannerImg);
					advPlanImgService.insert(advPlanImg);
				} else if (type == 3) {
					advPlanImg.setUrl(b_cpImg);
					advPlanImgService.insert(advPlanImg);
				}
				String[] timeList2 = timeList.split(",");
				for (String timeList3 : timeList2) {
					AdvPlanTime advPlanTime = new AdvPlanTime();
					advPlanTime.setAdvPlanId(advPlan.getId());
					advPlanTime.setState(1);
					advPlanTime.setType(advPlan.getType());
					advPlanTime.setTdate(timeList3.substring(0, 8));
					advPlanTime.setThour(timeList3.substring(8, 10));
					advPlanTimeService.insert(advPlanTime);
				}
				response.getWriter().write("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					response.getWriter().write("error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	// 广告修改
	@RequestMapping("advUpdate")
	public void advUpdate(HttpServletResponse response, HttpServletRequest request, HttpSession session, String name, Integer type, String startTime, String endTime, String link, String introduction, Long expectedNum, Long dispalyNum, Long clickNum, String timeList, String b_bannerImg, String b_kp1Img, Integer id, String b_kp2Img, String b_cpImg,Long totalNum,Long totaldisNum) {
		try {
			AdvPlan advPlan = advPlanService.getInfoById(id);
			advPlan.setName(name);
			advPlan.setStartdate(startTime);
			advPlan.setEnddate(endTime);
			advPlan.setClickNum(clickNum);
			advPlan.setDispalyNum(dispalyNum);
			advPlan.setExpectedNum(expectedNum);
			advPlan.setIntroduction(introduction);
			advPlan.setTotaldisNum(totaldisNum);
			advPlan.setTotalNum(totalNum);
			advPlan.setLink(link);
			advPlan.setType(type);
			advPlan.setState(1);
			advPlanService.update(advPlan);
			if ((b_kp1Img != null&&b_kp1Img.length()>0) ||(b_kp2Img != null&&b_kp2Img.length()>0)||(b_bannerImg != null&&b_bannerImg.length()>0)||(b_cpImg != null&&b_cpImg.length()>0)) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("advPlanId", id);
				List<AdvPlanImg> advPlanImgss = advPlanImgService.getInfoList(map1);
				for (AdvPlanImg advPlanImg : advPlanImgss) {
					advPlanImgService.delete(advPlanImg.getId());
				}
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("advPlanId", id);
			List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map2);
			for (AdvPlanTime advPlanTime : advPlanTimes) {
				advPlanTimeService.delete(advPlanTime.getId());
			}
			AdvPlanImg advPlanImg = new AdvPlanImg();
			advPlanImg.setType(1);
			advPlanImg.setAdvPlanId(id);
			if (type == 1 && b_kp1Img != null&&b_kp1Img.length()>0 && b_kp2Img != null&&b_kp2Img.length()>0) {
				advPlanImg.setUrl(b_kp1Img);
				advPlanImgService.insert(advPlanImg);
				advPlanImg.setUrl(b_kp2Img);
				advPlanImg.setType(2);
				advPlanImgService.insert(advPlanImg);
			} else if (type == 2 && b_bannerImg != null&&b_bannerImg.length()>0) {
				advPlanImg.setUrl(b_bannerImg);
				advPlanImgService.insert(advPlanImg);
			} else if (type == 3 && b_cpImg != null&&b_cpImg.length()>0) {
				advPlanImg.setUrl(b_cpImg);
				advPlanImgService.insert(advPlanImg);
			}
			String[] timeList2 = timeList.split(",");
			for (String timeList3 : timeList2) {
				AdvPlanTime advPlanTime = new AdvPlanTime();
				advPlanTime.setAdvPlanId(id);
				advPlanTime.setType(advPlan.getType());
				advPlanTime.setState(1);
				advPlanTime.setTdate(timeList3.substring(0, 8));
				advPlanTime.setThour(timeList3.substring(8, 10));
				advPlanTimeService.insert(advPlanTime);
			}
			response.getWriter().write("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
