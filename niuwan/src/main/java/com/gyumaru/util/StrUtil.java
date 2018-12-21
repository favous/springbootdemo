package com.gyumaru.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * */
public class StrUtil {
	/**
	 * 判断字符串是否全部为数字
	 * */
	public static boolean isNumeric(String str) {  
		//正则表达式
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(str);  
        if (!isNum.matches()) {  //如果不匹配正则 则返回false
            return false;  
        }  
        return true;  //返回true
    }
	
	/**
	 * 判断字符串是否为正数
	 * */
	public static boolean isPlus(String str) {  
		//正则表达式
        Pattern pattern = Pattern.compile("\\d+|\\d+\\.\\d+");  
        Matcher isPlus = pattern.matcher(str);  
        if (!isPlus.matches()) {  //如果不匹配正则 则返回false
            return false;  
        }  
        return true;  //返回true
    }
	
	/**
	 * 判断字符串是否为正整数
	 * */
	public static boolean isPInteger(String str) {  
		//正则表达式
        Pattern pattern = Pattern.compile("^\\d+$");  
        Matcher isPInteger = pattern.matcher(str);  
        if (!isPInteger.matches()) {  //如果不匹配正则 则返回false
            return false;  
        }  
        return true;  //返回true
    }
	
}
