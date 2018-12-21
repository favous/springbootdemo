package com.gyumaru.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class MailOperation {
	private static Logger logger = Logger.getLogger(MailUtil.class);
    /**
     * 发送邮件
     * @param user 发件人邮箱
     * @param password 授权码（注意不是邮箱登录密码）
     * @param host 
     * @param from 发件人
     * @param to 接收者邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return success 发送成功 failure 发送失败
     * @throws Exception
     */
    public String sendMail(String user, String password, String host,
            String from, String to, String subject, String content)
            throws Exception {
    	logger.info("进入方法");
        if (to != null){
        	logger.info("to！=null");
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            MailAuthenticator auth = new MailAuthenticator();
            logger.info("用户名"+user);
            logger.info("密码"+password);
            logger.info("发往"+to);
            MailAuthenticator.USERNAME = user;
            MailAuthenticator.PASSWORD = password;
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            try {
            	logger.info("进入try");
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                if (!to.trim().equals("")){
                	logger.info("!to.trim().equals");
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to.trim()));
                }
                message.setSubject(subject);
                MimeBodyPart mbp1 = new MimeBodyPart(); // 正文
                mbp1.setContent(content, "text/html;charset=utf-8");
                Multipart mp = new MimeMultipart(); // 整个邮件：正文+附件
                mp.addBodyPart(mbp1);
                // mp.addBodyPart(mbp2);
                logger.info("++++++++++++++++++++++++++++++++++++++setconntent");
                message.setContent(mp);
                logger.info("++++++++++++++++++++++++++++++++++++++setSentDate");
                message.setSentDate(new Date());
                logger.info("++++++++++++++++++++++++++++++++++++++saveChanges");
                message.saveChanges();
                logger.info("++++++++++++++++++++++++++++++++++++++getTransport");
                Transport trans = session.getTransport("smtp");
                logger.info("++++++++++++++++++++++++++++++++++++++send");
                trans.send(message);
                //System.out.println(message.toString());
            } catch (Exception e){
                e.printStackTrace();
                logger.info("failure2");
                return "failure";
            }
            return "success";
        }else{            
        	logger.info("failure1");
            return "failure";
        }
    }

}
