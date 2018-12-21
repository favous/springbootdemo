package com.fzc.pay.service.impl;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.fzc.common.utils.HttpUtils;
import com.fzc.pay.dto.PayQueryDto;
import com.fzc.pay.dto.PaySubmitDto;
import com.fzc.pay.service.PayService;
import com.fzc.pay.utils.DesCryptUtil;

public class PayServiceImpl implements PayService {
	
	private Logger LOGGER = Logger.getLogger(this.getClass());
	
	private static final String APP_KEY = "k_1543574807";// APP_KEY
	private static final String DES_KEY = "4n9PQUQ0lo8S";// des密钥
	private static final String URL_PAY_SUBMIT = "http://dev.web.hotravel.cn/azeroth/api/pay/status?app_key=";// 提交支付url
	private static final String URL_PAY_QUERY = "http://dev.web.hotravel.cn/azeroth/api/pay/query?app_key=";// 支付查询url
	private static final String RETURN_URL = "return_url";
	private static final String NOTIFY_URL = "notify_url";
	
	private static String TRUE = "Y";
	private static String FALSE = "N";

	@Override
	public String submit(PaySubmitDto submitDto) {
		try {
			String str = JSON.toJSONString(submitDto);
			str = DesCryptUtil.getEncryptString(str, DES_KEY);
			str = URLEncoder.encode(str, "UTF-8");
			
			String url = URL_PAY_SUBMIT + APP_KEY + "&signstr=" + str;
			
			String returnStr = new HttpUtils().doGet(url);
			
			return returnStr;
			
		} catch (Exception e) {
			LOGGER.error("订单支付提交异常，submitDto=" + JSON.toJSONString(submitDto), e);
			return null;
		}
		
	}

	@Override
	public boolean check(PayQueryDto queryDto) {
		try {
			String str = JSON.toJSONString(queryDto);
			str = DesCryptUtil.getEncryptString(str, DES_KEY);
			str = URLEncoder.encode(str, "UTF-8");
			
			String url = URL_PAY_QUERY + APP_KEY + "&signstr=" + str;
			
			String resultStr = new HttpUtils().doGet(url);
			
			return StringUtils.isNotBlank(resultStr) && (resultStr.trim().equals(TRUE));
			
		} catch (Exception e) {
			LOGGER.error("订单支付状态查询异常，queryDto=" + JSON.toJSONString(queryDto), e);
			return false;
		}
	}

}
