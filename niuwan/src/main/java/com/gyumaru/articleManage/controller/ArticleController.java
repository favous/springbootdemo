package com.gyumaru.articleManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzc.common.enums.PlEnum;
import com.fzc.common.utils.EnumUtil;
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.articleManage.service.ArticleContentService;
import com.gyumaru.articleManage.service.ArticleService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Admin;
import com.gyumaru.pojo.Advert;
import com.gyumaru.pojo.Article;
import com.gyumaru.pojo.ArticleContent;
import com.gyumaru.sysManage.service.AdminService;
import com.gyumaru.util.UtilUpName;

@Controller
@RequestMapping("article")
public class ArticleController {
	// 文章控制器
	private static Logger logger = Logger.getLogger(ArticleController.class);
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleContentService articleContentService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ActionLoggerService actionLoggerService;
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;
	
	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;
	// 跳转文章管理
	@RequestMapping("articleManage")
	public String articleManage(Model model, String name, Integer state) {
		model.addAttribute("name", name);
		model.addAttribute("state", state);
		return "admin/articleManage/articleManage";
	}

	// 分页查询文章记录
	@RequestMapping("list")
	public void getList(HttpServletResponse response, Integer limit, Integer start, Integer page, String articleTitle,
			Integer status) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			map.put("state", 1);
			map.put("article_title", articleTitle);
			map.put("status", status);
			List<Article> articles = articleService.getInfoList(map);
			for (Article article : articles) {
				Admin admin = adminService.getInfoById(article.getArticleAuthor());
				article.setArticleAuthorAdmin(admin);
				article.setArticleCoverPic(outImgPreFixUrl+article.getArticleCoverPic());
			}
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(articles, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", articleService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 跳转文章详情
	@RequestMapping("articleDtl")
	public String articleDtl(Model model, Integer id) throws Exception {
		Article article = articleService.getInfoById(id);
		Admin admin = adminService.getInfoById(article.getArticleAuthor());
		article.setArticleAuthorAdmin(admin);
		Map<String, Object> map = new HashMap<>();
		map.put("articleId", id);
		List<ArticleContent> articleContents = articleContentService.getInfoList(map);
		article.setArticleContent(articleContents.get(0).getArticleContent());
		LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
		model.addAttribute("plDict", plDict);
		model.addAttribute("article", article);
		model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);
		return "admin/articleManage/articleDtl";
	}

	// 跳转文章添加
	@RequestMapping("articleAdd")
	public String articleAdd(Model model) throws Exception {
		LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
		model.addAttribute("plDict", plDict);
		return "admin/articleManage/articleAdd";
	}

	// 文章添加
	@RequestMapping("addarticle")
	public void addarticle(Model model, HttpServletResponse response, HttpServletRequest request, String articleTitle,
			String articleAbstract, String htmlData,Integer pl, HttpSession session) throws Exception {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		String url1 = imgPreFixUrl+"articleImg";	
		String basePath = "articleImg/";//图片地址
		//String url1 = request.getSession().getServletContext().getRealPath("resources") + "\\images" + "\\article";// 图片保存地址
		MultipartFile filename1 = multiRequest.getFile("articleCoverPic");// 回去图片
		File upFile = new File(url1);
		if (!upFile.isDirectory() && !upFile.exists()) {
			upFile.mkdir();
		}
		String MyfileFileName1 = filename1.getOriginalFilename();
		String newFilename =UtilUpName.getImgUpName(MyfileFileName1);
		InputStream is = filename1.getInputStream();
		// 输出文件
		FileOutputStream fileOutputStream = new FileOutputStream(url1 + "/" + newFilename);
		byte[] b = new byte[is.available()];
		is.read(b);
		fileOutputStream.write(b);
		// 关闭流
		is.close();
		fileOutputStream.close();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Admin admin = (Admin) session.getAttribute("admin");
			Article article = new Article();
			article.setArticleAbstract(articleAbstract);
			article.setArticleAuthor(admin.getId());
			article.setArticleCoverPic(basePath + newFilename);
			article.setArticleThumbsupCount(0);
			article.setArticleTitle(articleTitle);
			article.setCreateTime(formatter.format(new Date()));
			article.setModifyTime(formatter.format(new Date()));
			article.setState(1);
			article.setPl(pl);
			article.setStatus(10);
			articleService.insert(article);
			ArticleContent articleContent = new ArticleContent();
			articleContent.setArticleContent(htmlData);
			articleContent.setArticleId(article.getId());
			articleContentService.insert(articleContent);
			response.getWriter().write("success");// 成功则返回success
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("error");
		}
	}

	// 跳转文章编辑
	@RequestMapping("articleModify")
	public String articleModify(Model model, Integer id) throws Exception {
		Article article = articleService.getInfoById(id);
		Admin admin = adminService.getInfoById(article.getArticleAuthor());
		article.setArticleAuthorAdmin(admin);
		Map<String, Object> map = new HashMap<>();
		map.put("articleId", id);
		List<ArticleContent> articleContents = articleContentService.getInfoList(map);
		article.setArticleContent(articleContents.get(0).getArticleContent());
		LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
		model.addAttribute("plDict", plDict);
		model.addAttribute("article", article);
		model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);
		return "admin/articleManage/articleModify";
	}

	// 文章编辑
	@RequestMapping("modifyarticle")
	public void modifyarticle(Model model, HttpServletResponse response, HttpServletRequest request, Integer pic,
			Integer articledraftid, String articleTitle, String articleAbstract, String htmlData,Integer pl, HttpSession session)
			throws Exception {
		String url1 = imgPreFixUrl+"articleImg";	
		String basePath = "articleImg/";//图片地址
		String newFilename ="";
		if (pic == 2) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename1 = multiRequest.getFile("articleCoverPic");// 回去图片
			File upFile = new File(url1);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName1 = filename1.getOriginalFilename();
			newFilename =UtilUpName.getImgUpName(MyfileFileName1);
			InputStream is = filename1.getInputStream();
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url1 + "/" + newFilename);
			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();
			//picPath1 = "images/article/" + MyfileFileName1;// 图片地址
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Article article = articleService.getInfoById(articledraftid);
			article.setArticleAbstract(articleAbstract);
			if (pic == 2) {
				article.setArticleCoverPic(basePath + newFilename);
			}
			article.setArticleTitle(articleTitle);
			article.setModifyTime(formatter.format(new Date()));
			article.setPl(pl);
			articleService.update(article);
			Map<String, Object> map = new HashMap<>();
			map.put("articleId", articledraftid);
			List<ArticleContent> articleContents = articleContentService.getInfoList(map);
			ArticleContent articleContent = articleContents.get(0);
			articleContent.setArticleContent(htmlData);
			articleContentService.update(articleContent);
			response.getWriter().write("success");// 成功则返回success
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("error");
		}
	}

	// 文章审核
	@RequestMapping("examinearticle")
	public String examinearticle(Model model, Integer articledraftid, Integer status,HttpSession session) throws Exception {
		Article article = articleService.getInfoById(articledraftid);
		article.setStatus(status);
		Admin admin =(Admin)session.getAttribute("admin");
		if (status == 30) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			article.setPublishTime(formatter.format(new Date()));
			ActionLogger actionLogger = new ActionLogger();
			actionLogger.setAdminId(admin.getId());
				InetAddress ia=null;
	            ia=ia.getLocalHost();
	            String localname=ia.getHostName();
	            String localip=ia.getHostAddress();
	            System.out.println("本机名称是："+ localname);
	            System.out.println("本机的ip是 ："+localip);
		        actionLogger.setIp(localip);
		        actionLogger.setHandle("账号"+admin.getCard()+"通过了文章"+article.getArticleAbstract()+"文章ID"+article.getId());
		        actionLoggerService.insert(actionLogger);
		}
		articleService.update(article);
		return "redirect:../article/articleManage";
	}

	// 文章删除（假删）
	@RequestMapping("articleDel")
	public String articleDel(Model model, Integer id,HttpSession session) throws Exception {
		Article article = articleService.getInfoById(id);
		article.setState(9);
		articleService.update(article);
		Admin admin =(Admin)session.getAttribute("admin");
		ActionLogger actionLogger = new ActionLogger();
		actionLogger.setAdminId(admin.getId());
			InetAddress ia=null;
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println("本机名称是："+ localname);
            System.out.println("本机的ip是 ："+localip);
	        actionLogger.setIp(localip);
	        actionLogger.setHandle("账号"+admin.getCard()+"删除了文章"+article.getArticleAbstract()+"文章ID"+article.getId());
	        actionLoggerService.insert(actionLogger);
		return "redirect:../article/articleManage";
	}

	// 富文本框添加图片
	@RequestMapping("uploadimage")
	public void uploadimage(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		/** 上传文件处理内容 **/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
		sfu.setSizeMax(1024 * 1024); // 限制文件大小
		String imgUrl = "";
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile filename_b = multiRequest.getFile("uploadFile");
		// 上传目录
		//String url_b = request.getSession().getServletContext().getRealPath("resources") + "\\images" + "\\article";
		
		String url = imgPreFixUrl+"articleContentImg";	
		String basePath = "articleContentImg/";
		try {
			String MyfileFileName_b = filename_b.getOriginalFilename();
			String newFilename =UtilUpName.getImgUpName(MyfileFileName_b);
			File upFile = new File(url);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			InputStream is_b = filename_b.getInputStream();
			// 输出文件
			FileOutputStream fileOutputStream_b = new FileOutputStream(url + "/" + newFilename);
			byte[] bb = new byte[is_b.available()];
			is_b.read(bb);
			fileOutputStream_b.write(bb);
			// 关闭流
			is_b.close();
			fileOutputStream_b.close();
			String path = request.getContextPath();
			response.getWriter().write(outImgPreFixUrl+basePath+newFilename);
		} catch (Exception e) {
			e.printStackTrace();
			imgUrl = "error|服务器端错误";

		}

	}
}
