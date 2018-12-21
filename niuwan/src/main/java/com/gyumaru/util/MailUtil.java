package com.gyumaru.util;

import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

import javax.ws.rs.client.Client;

import org.apache.log4j.Logger;

import com.foxinmy.weixin4j.util.RandomUtil;

public class MailUtil {
	private static Logger logger = Logger.getLogger(MailUtil.class);
	/**
	 * @param to 对方的邮箱
	 * 
	 * 	 * */
	public static String mail(String to)  throws Exception{
		String yzm="";
		Properties prop = new Properties();     
            //读取属性文件a.properties
        	InputStreamReader in = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("mail.properties"), "UTF-8");
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String host = prop.getProperty("host");
            String from = prop.getProperty("from");
            String subject = prop.getProperty("subject");
            //String basepath = prop.getProperty("basepath");
            in.close();
            MailOperation operation = new MailOperation();
            //邮箱内容
            StringBuffer sb = new StringBuffer();
            //String enString = MyAESUtil.Encrypt(userId.toString());  
            yzm = RandomUtil.generateStringByNumberChar(6);
            sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'>"
            		+ "<span style='font-weight:bold;'>"+to+"様</span>"
                              + "<div style='width:950px;font-family:arial;'>このたびは牛丸にご登録いただき、誠にありがとうございます。"
                              + "<br/>お客様の会員登録情報"
                              + "<br/>ご登録いただいたユーザ ID：<span style='color:green'>"+to+"</span>"+
                               "<br/>登録番号は：<h2 style='color:green'>"+yzm+"</h2>"
                              + "<br/>■このメールは送信専用メールアドレスから配信されています。ご返信いただいてもお答えできませんのでご了承ください。"
                              + "<br/>■ご登録に心あたりがない場合、ご質問等のある方は、こちらのヘルプページご参照のうえ、お問い合わせフォームからご連絡ください。</div>"
                             +"</div>");
           String res = operation.sendMail(user, password, host, from, to, subject, sb.toString());
            logger.info(res);
        return yzm;
    }
	public static String mailForgetCodes(String to)  throws Exception{
		String yzm="";
		Properties prop = new Properties();     
            //读取属性文件a.properties
        	InputStreamReader in = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("mail.properties"), "UTF-8");
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String host = prop.getProperty("host");
            String from = prop.getProperty("from");
            String subject = prop.getProperty("subject");
            //String basepath = prop.getProperty("basepath");
            in.close();
            MailOperation operation = new MailOperation();
            //邮箱内容
            StringBuffer sb = new StringBuffer();
            //String enString = MyAESUtil.Encrypt(userId.toString());  
            yzm = RandomUtil.generateStringByNumberChar(6);
            sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'>"
            		+ "<span style='font-weight:bold;'>お客様へ</span>"
                              + "<div style='width:950px;font-family:arial;'>パスワードをお忘れの場合は「リセット」をクリックしください。"
                              + "<br/>登録しているメールアドレスを入力後、「送信」をクリックすると、パスワード変更のためのメールが届きます。"
                              + "<br/>メールに表示されている認証コードからパスワードを変更します。"+
                               "<br/>認証コード：<h2 style='color:green'>"+yzm+"</h2>"
                             +"</div>");
           String res = operation.sendMail(user, password, host, from, to, subject, sb.toString());
            logger.info(res);
        return yzm;
    }
	public static void main(String[] args) {
		try {
			mailForgetCodes("836610073@qq.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
