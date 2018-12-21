package com.gyumaru.util;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.ws.rs.client.Client;

public class UtilUpName {

	 /**
	    * 对上传文件名根据时间毫秒数重写
	    *
	    * @param 上传文件原文件名
	    * @return 根据时间毫秒数生成的随机字符串
	    */
	   public static String getImgUpName(String ImgName) {
	       try {
	    	   	int index = 0; 
				Date date = new Date();//获取当前时间
				long now = date.getTime();
				index = ImgName.lastIndexOf(".");	//获取文件名.的下标
				String newFilename;// 文件名带后缀
				// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
				newFilename =  Long.toString(now)+ ImgName.substring(index);
	           return newFilename;
	       } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	       }
	   }
	
	   /**
	    * 对上传文件名根据时间毫秒数重写
	    *
	    * @param 上传文件原文件名
	    * @return 根据时间毫秒数生成的随机字符串
	    */
	   public static String getimgPreFixUrl() {
	       try {
	    	   String yzm="";
	   			Properties prop = new Properties();     
	    	   //读取属性文件a.properties
	        	InputStreamReader in = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("sys.properties"), "UTF-8");
	            prop.load(in);     ///加载属性列表
	            Iterator<String> it=prop.stringPropertyNames().iterator();
	            String imgPreFixUrl = prop.getProperty("imgPreFixUrl");
	           return imgPreFixUrl;
	       } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	       }
	   }
	
	
	
}
