package com.gyumaru.util;


import java.io.IOException;

import org.json.JSONException;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;



public class MessageUtil {
    /**
	 * 短信接口appkey
	 * */
	private static final String APPKEY="3c3a792067d05591c230586654ce1121";
	private static final int SDK_APPID=1400116890;
	/**
	 * 发送验证码接口
	 * */
	public static void sendMessage(String phone,String code){
		try {
			String[] params = {code};
		    SmsSingleSender msender = new SmsSingleSender(SDK_APPID, APPKEY);
		    SmsSingleSenderResult result = msender.sendWithParam("86", phone,
		    		165391, params, "唐夏文化", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.print(result);
		} catch (HTTPException e) {
		    // HTTP响应码错误
		    e.printStackTrace();
		} catch (JSONException e) {
		    // json解析错误
		    e.printStackTrace();
		} catch (IOException e) {
		    // 网络IO错误
		    e.printStackTrace();
		}
    }
	/**
	 * 发送投诉接口
	 * */
	public static void sendsendComplaint(String name,String phone,String content,String sys_phone) throws Exception{
		try {
		    String[] params = {name,phone,content};
		    SmsSingleSender msender = new SmsSingleSender(SDK_APPID, APPKEY);
		    SmsSingleSenderResult result = msender.sendWithParam("86", sys_phone,
		    		165375, params, "唐夏文化", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.print(result);
		} catch (HTTPException e) {
		    // HTTP响应码错误
		    e.printStackTrace();
		} catch (JSONException e) {
		    // json解析错误
		    e.printStackTrace();
		} catch (IOException e) {
		    // 网络IO错误
		    e.printStackTrace();
		}
	}
	
	public static void sendMessages(String[] params,String[] phones) throws Exception{
		SmsMultiSender msender = new SmsMultiSender(SDK_APPID, APPKEY);
	    SmsMultiSenderResult result =  msender.sendWithParam("86", phones,
	    		165375, params, "唐夏文化", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
	    System.out.print(result);
	}
	
}
