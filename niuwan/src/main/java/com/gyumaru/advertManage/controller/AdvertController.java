package com.gyumaru.advertManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

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

import com.gyumaru.advertManage.service.AdvertService;
import com.gyumaru.pojo.Advert;
import com.gyumaru.util.UtilUpName;

@RequestMapping("advert")
@Controller
public class AdvertController {
	private static Logger logger = Logger.getLogger(AdvertController.class);
	@Autowired
	private AdvertService advertService;
	
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;
	
	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;

	// 顶部广告管理
	@RequestMapping("advertManage")
	public String userManage(Model model, String name, Integer state, HttpSession session) {
		try {
			String url1 = imgPreFixUrl;	
			Advert advert1 = advertService.getInfoById(1);
			Advert advert2 = advertService.getInfoById(2);
			Advert advert3 = advertService.getInfoById(3);
			model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);// 查询结果回传
			model.addAttribute("advert1", advert1);// 查询结果回传
			model.addAttribute("url1", url1);// 图片地址前段
			model.addAttribute("advert2", advert2);
			model.addAttribute("advert3", advert3);// 查询条件回传
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/advertManage/advertManage";// 跳转
	}

	/**
	 * 保存advert
	 * 
	 * @author F
	 */
	@RequestMapping("saveadvert")
	public void saveCarouselimg(HttpServletRequest request, HttpServletResponse response, String gameUrl1,
			String gameUrl2, String gameUrl3, String img1, String img2, String img3) throws IOException {

		/*String url1 = request.getSession().getServletContext().getRealPath("resources") + "\\images" + "\\topImg";//图片保存地址
*/		String url1 = imgPreFixUrl+"topImg";	
		String basePath = "topImg/";//图片地址
		Integer a = 1;
		if ("2".equals(img1)) {//判断是否修改图片
			// 上传目录
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename1 = multiRequest.getFile("a_imgUrl1");//回去图片
			File upFile = new File(url1);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName1 = filename1.getOriginalFilename();
			InputStream is = filename1.getInputStream();
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName1.lastIndexOf(".");	
			// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			/*newFilename1 = MyfileFileName1.subSequence(0, index) + Long.toString(now)
					+ MyfileFileName1.substring(index);*/
			
			String newFilename1=UtilUpName.getImgUpName(MyfileFileName1);
			System.out.println(newFilename1);
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url1 + "/" + newFilename1);
			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();
			
			try {
				Advert advert1 = new Advert();
				advert1.setGameUrl(gameUrl1);
				advert1.setId(1);
				advert1.setImgUrl(basePath+newFilename1);
				advertService.update(advert1);//update advert id=1

				a = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("error");// 成功则返回success
				logger.error("修改图片1时出错了！" + e);
			}

		}
		if ("2".equals(img2)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename2 = multiRequest.getFile("a_imgUrl2");

			String MyfileFileName2 = filename2.getOriginalFilename();
			InputStream is2 = filename2.getInputStream();
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName2.lastIndexOf(".");
			String newFilename2;// 文件名带后缀
			// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			/*newFilename2 = MyfileFileName2.subSequence(0, index) + Long.toString(now)
					+ MyfileFileName2.substring(index);*/
			newFilename2=UtilUpName.getImgUpName(MyfileFileName2);
			// 输出文件
			FileOutputStream fileOutputStream1 = new FileOutputStream(url1 + "/" + newFilename2);
			byte[] b1 = new byte[is2.available()];
			is2.read(b1);
			fileOutputStream1.write(b1);
			// 关闭流
			is2.close();
			fileOutputStream1.close();
			try {
				Advert advert2 = new Advert();
				advert2.setGameUrl(gameUrl2);
				advert2.setId(2);
				advert2.setImgUrl(basePath+newFilename2);
				advertService.update(advert2);
				a = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("error");// 成功则返回success
				logger.error("修改图片2时出错了！" + e);
			}
		}
		if ("2".equals(img3)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename3 = multiRequest.getFile("a_imgUrl3");
			String MyfileFileName3 = filename3.getOriginalFilename();
			InputStream is3 = filename3.getInputStream();
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName3.lastIndexOf(".");
			String newFilename3;// 文件名带后缀
			/*// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			newFilename3 = MyfileFileName3.subSequence(0, index) + Long.toString(now)
					+ MyfileFileName3.substring(index);*/
			newFilename3=UtilUpName.getImgUpName(MyfileFileName3);
			// 输出文件
			FileOutputStream fileOutputStream3 = new FileOutputStream(url1 + "/" + newFilename3);
			byte[] b3 = new byte[is3.available()];
			is3.read(b3);
			fileOutputStream3.write(b3);
			// 关闭流
			is3.close();
			fileOutputStream3.close();
			try {

				Advert advert3 = new Advert();
				advert3.setGameUrl(gameUrl3);
				advert3.setId(3);
				advert3.setImgUrl(basePath+newFilename3);
				advertService.update(advert3);
				a = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("error");// 成功则返回success
				logger.error("修改图片3时出错了！" + e);
			}

		}
		Advert adverts1 = new Advert();
		adverts1.setGameUrl(gameUrl1);
		adverts1.setId(1);
		Advert adverts2 = new Advert();
		adverts2.setGameUrl(gameUrl2);
		adverts2.setId(2);
		Advert adverts3 = new Advert();
		adverts3.setGameUrl(gameUrl3);
		adverts3.setId(3);

		try {
			advertService.update(adverts1);
			advertService.update(adverts2);
			advertService.update(adverts3);

			response.getWriter().write("success");// 成功则返回success
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("修改游戏链接时出错了！" + e);
		}

	}
}