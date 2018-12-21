package com.gyumaru.advertisementManage.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.advertisementManage.service.AdvPlanImgService;
import com.gyumaru.advertisementManage.service.AdvPlanService;
import com.gyumaru.advertisementManage.service.AdvPlanTimeService;
import com.gyumaru.advertisementManage.service.AdvPlanUserService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Admin;
import com.gyumaru.userManage.pojo.User;

@RequestMapping("advertisement")
@Controller
public class AdvertisementController1 {
	private static Logger logger = Logger.getLogger(AdvertisementController1.class);
	@Autowired
	private AdvPlanService advPlanService;
	@Autowired
	private AdvPlanTimeService advPlanTimeService;
	@Autowired
	private AdvPlanUserService advPlanUserService;
	@Autowired
	private AdvPlanImgService advPlanImgService;
	@Autowired
	private ActionLoggerService actionLoggerService;
	
	// 跳转广告审核管理
	@RequestMapping("advAuditManage")
	public String advAuditManage(Model model, String name, Integer type) {
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "admin/advertisementManage/advAuditManage";
	}
	// 分页查询广告审核
	@RequestMapping("advAuditList")
	public void advAuditList(HttpServletResponse response, Integer limit, Integer start, Integer page, String name,
			Integer type) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("state", 1);
			map.put("name", name);
			map.put("type", type);
			List<AdvPlan> advPlans = advPlanService.getInfoList(map);
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(advPlans, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", advPlanService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}
	// 广告审核
	@RequestMapping("changeState")
	public void changeState(HttpServletResponse response, Integer id, Integer state,HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try{
		Admin admin =(Admin)session.getAttribute("admin");
			AdvPlan advPlan = advPlanService.getInfoById(id);
			ActionLogger actionLogger = new ActionLogger();
			actionLogger.setAdminId(admin.getId());
				InetAddress ia=null;
	            ia=ia.getLocalHost();
	            String localname=ia.getHostName();
	            String localip=ia.getHostAddress();
	            System.out.println("本机名称是："+ localname);
	            System.out.println("本机的ip是 ："+localip);
		        actionLogger.setIp(localip);
		     
			if(state==3){
				advPlan.setState(3);
				advPlanService.update(advPlan);
				actionLogger.setHandle("账号"+admin.getCard()+"不通过了广告"+advPlan.getName()+",广告ID:"+advPlan.getId());
				System.out.println("bnginufiworbgjnofpn jfkonbgffnbfgj");
				json.put("success", "success");
			}else if(state==2){
				Map<String, Object> map = new HashMap<>();
				map.put("advPlanId", id);
				List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
				
				int a = 0;
				for (AdvPlanTime advPlanTime : advPlanTimes) {
					Map<String, Object> map2 = new HashMap<>();
					map2.put("tdate", advPlanTime.getTdate());
					map2.put("thour", advPlanTime.getThour());
					map2.put("type", advPlanTime.getType());
					map2.put("state", 2);
					List<AdvPlanTime> advPlanTimes2 = advPlanTimeService.getInfoList(map2);
					if(advPlanTimes2.size()>0){
						a=1;
						break;
					}
				}
				if(a==0){
					advPlan.setState(2);
					advPlanService.update(advPlan);
					Map<String, Object> map1 = new HashMap<>();
					map1.put("advPlanId", id);
					List<AdvPlanTime> advPlanTimes1 = advPlanTimeService.getInfoList(map1);
					for (AdvPlanTime advPlanTime : advPlanTimes1) {
						advPlanTime.setState(2);
						advPlanTimeService.update(advPlanTime);
					}
					actionLogger.setHandle("账号"+admin.getCard()+"通过了广告"+advPlan.getName()+"广告ID"+advPlan.getId());
					json.put("success", "success");
				}else{
					json.put("success", "success1");
					actionLogger.setHandle("账号"+admin.getCard()+"审核了广告"+advPlan.getName()+"时间冲突未通过"+"，广告ID"+advPlan.getId());
				}
				  
			}
			 actionLoggerService.insert(actionLogger);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}
	// 跳转广告数据分析管理
	@RequestMapping("advAnalysisManage")
	public String advAnalysisManage(Model model, String name, String startdate,String enddate) {
		model.addAttribute("name", name);
		model.addAttribute("startdate", startdate);
		model.addAttribute("enddate", enddate);
		return "admin/advertisementManage/advAnalysisManage";
	}
	// 分页查询广告审核
	@RequestMapping("advAnalysisList")
	public void advAnalysisList(HttpServletResponse response, Integer limit, Integer start, Integer page, String name,
			String startdate,String enddate,HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("state", 2);
			map.put("name", name);
			map.put("startdate", startdate);
			map.put("enddate", enddate);
			List<AdvPlan> advPlans = advPlanService.getInfoList(map);
			for (AdvPlan advPlan : advPlans) {
				Long realclickNum = (long) 0;
				Long realdispalyNum = (long) 0;
				Map<String, Object> map2 = new HashMap<>();
				map2.put("advPlanId", advPlan.getId());
				List<AdvPlanUser> advPlanUsers = advPlanUserService.getInfoList(map2);
				for (AdvPlanUser advPlanUser : advPlanUsers) {
					realclickNum = realclickNum + advPlanUser.getClickNum();
					realdispalyNum = realdispalyNum + advPlanUser.getDisplayNum();
				}
				advPlan.setRealdispalyNum(realdispalyNum);
				advPlan.setRealclickNum(realclickNum);
				if(realclickNum==0){
					advPlan.setClickrate("0%");
				}else{
					advPlan.setClickrate(""+realclickNum*100/realdispalyNum+"%");
				}
			}
			session.setAttribute("advPlans", advPlans);
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(advPlans, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", advPlanService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}
	//导出广告统计
	@RequestMapping({"downloadAdvAnalysis"})
	public void  downloadOnlineOrders(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");	
		Row row = sheet.createRow(0);
		Cell cell =row.createCell(0);
		cell.setCellValue("序号");
		cell=row.createCell(1);
		cell.setCellValue("计划名称");
		cell=row.createCell(2);
		cell.setCellValue("投放周期");
		cell=row.createCell(3);
		cell.setCellValue("期望展示数");
		cell=row.createCell(4);
		cell.setCellValue("真实展示数");
		cell=row.createCell(5);
		cell.setCellValue("点击数");
		cell=row.createCell(6);
		cell.setCellValue("点击率");
		List<AdvPlan> advPlans =(List<AdvPlan>)session.getAttribute("advPlans");
		
		int rowNum=1;
		for (AdvPlan advPlan : advPlans) {
			Row rowN = sheet.createRow(rowNum);
			rowN.createCell(0).setCellValue(rowNum);
			rowN.createCell(1).setCellValue(advPlan.getName());
			rowN.createCell(2).setCellValue(advPlan.getStartdate()+"——"+advPlan.getEnddate());
			rowN.createCell(3).setCellValue(advPlan.getExpectedNum());
			rowN.createCell(4).setCellValue(advPlan.getRealdispalyNum());
			rowN.createCell(5).setCellValue(advPlan.getRealclickNum());
			rowN.createCell(6).setCellValue(advPlan.getClickrate());
			rowNum++;
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		wb.close();
		byte[] fileContent = os.toByteArray();
		InputStream bis = new ByteArrayInputStream(fileContent);
		
		Date date = new Date();  
	    String now= new SimpleDateFormat("yyyyMMddHHmmss").format(date);  
	    String excelFileName = "广告数据分析报表"+now+".xls"; //设置下载的文件名
	    excelFileName = new String(excelFileName.getBytes(), "ISO8859-1");
	    response.addHeader("Content-Disposition", "attachment;filename=" + excelFileName);
	    response.setContentType("multipart/form-data");
	    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
	    byte[] buffer = new byte[1024*4];
	    int len = 0;  
	    while ((len=bis.read(buffer)) != -1){  
            //4.写到输出流(out)中  
	    	out.write(buffer,0,len); 
    	    out.flush(); 
        }  
	    out.close();
	}
	// 跳转广告计划管理
	@RequestMapping("advPlanManage")
	public String advPlanManage(Model model, String name, Integer state,Integer type) {
		model.addAttribute("name", name);
		model.addAttribute("state", state);
		model.addAttribute("type", type);
		return "admin/advertisementManage/advPlanManage";
	}
	// 分页查询广告审核
	@RequestMapping("advPlanList")
	public void advPlanList(HttpServletResponse response, Integer limit, Integer start, Integer page, String name,
			Integer type, Integer state) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("name", name);
			map.put("type", type);
			map.put("state", state);
			List<AdvPlan> advPlans = advPlanService.getInfoList(map);
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(advPlans, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", advPlanService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}
	// 广告计划删除（真删）
	@RequestMapping("advPlanDel")
	public String advPlanDel(Model model, Integer id,HttpSession session) throws Exception {
		Admin admin =(Admin)session.getAttribute("admin");
		Map<String, Object> map = new HashMap<>();
		AdvPlan advPlan = advPlanService.getInfoById(id);
		//删除这条广告相关的时间记录
		map.put("advPlanId", id);
		List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
		for (AdvPlanTime advPlanTime : advPlanTimes) {
			advPlanTime.setState(2);
			advPlanTimeService.delete(advPlanTime.getId());
		}
		//删除这条广告相关的用户广告记录
		Map<String, Object> map2 = new HashMap<>();
		map2.put("advPlanId", id);
		List<AdvPlanUser> advPlanUsers = advPlanUserService.getInfoList(map2);
		for (AdvPlanUser advPlanUser : advPlanUsers) {
			advPlanUserService.delete(advPlanUser.getId());
		}
		//删除这条广告相关的图片资源记录
		Map<String, Object> map3 = new HashMap<>();
		map3.put("advPlanId", id);
		List<AdvPlanImg> advPlanImgs = advPlanImgService.getInfoList(map3);
		for (AdvPlanImg advPlanImg : advPlanImgs) {
			advPlanImgService.delete(advPlanImg.getId());
		}
		//删除这条广告
		advPlanService.delete(id);
		
		
		ActionLogger actionLogger = new ActionLogger();
		actionLogger.setAdminId(admin.getId());
			InetAddress ia=null;
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println("本机名称是："+ localname);
            System.out.println("本机的ip是 ："+localip);
	        actionLogger.setIp(localip);
	        actionLogger.setHandle("账号"+admin.getCard()+"删除了广告"+advPlan.getName()+",广告ID:"+advPlan.getId());
	        actionLoggerService.insert(actionLogger);
		return "redirect:../advertisement/advPlanManage";
	}
	
}
