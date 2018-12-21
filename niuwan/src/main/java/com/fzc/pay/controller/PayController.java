package com.fzc.pay.controller;

import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fzc.common.enums.SexEnum;
import com.fzc.common.utils.EnumUtil;
import com.fzc.pay.dto.PaybackBlackDto;
import com.fzc.pay.dto.PaybackDto;
import com.fzc.pay.utils.DesCryptUtil;

@Controller
@RequestMapping("/pay")
public class PayController {
	
	private static Logger LOGGER = Logger.getLogger(PayController.class);
	
	private static String DES_KEY = "4n9PQUQ0lo8S";// des密钥

	// 回调到前端
	@RequestMapping("/callbackFront")
    @ResponseBody
	public String callbackFront(Model model, String signstr) {

		String str = null;
		try {
			str = DesCryptUtil.getDecryptString("signstr", DES_KEY);
			str = URLDecoder.decode(str, "UTF-8");
			str = DesCryptUtil.getDecryptString(str, DES_KEY);
			LOGGER.info("signstr解密结果为" + str);
			
		} catch (Exception e) {
			LOGGER.error("signstr解密异常，signstr=" + signstr, e);
		}
		
		PaybackDto dto = JSON.parseObject(str, PaybackDto.class);
		
		// 以下返回前端处理
		
		return "";
	}
	
	// 回调到后端
	@RequestMapping("/callbackBlack")
	@ResponseBody
	public String callbackBlack(Model model, String signstr) {
		
		String str = null;
		try {
			str = DesCryptUtil.getDecryptString("signstr", DES_KEY);
			str = URLDecoder.decode(str, "UTF-8");
			str = DesCryptUtil.getDecryptString(str, DES_KEY);
			LOGGER.info("signstr解密结果为" + str);
			
		} catch (Exception e) {
			LOGGER.error("signstr解密异常，signstr=" + signstr, e);
		}
		
		PaybackBlackDto dto = JSON.parseObject(str, PaybackBlackDto.class);
		
		// 以下返回后端逻辑处理
		
		try {
			model.addAttribute("sexdict", EnumUtil.dictMap(SexEnum.class, "code", "desc"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

}
