package com.gyumaru.gameManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzc.common.enums.PlEnum;
import com.fzc.common.utils.EnumUtil;
import com.gyumaru.actionLoggerManage.service.ActionLoggerService;
import com.gyumaru.gameManage.enums.GameTypeEnum;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.pojo.ActionLogger;
import com.gyumaru.pojo.Admin;
import com.gyumaru.pojo.Game;
import com.gyumaru.pojo.GameImg;
import com.gyumaru.util.MyAESUtil;
import com.gyumaru.util.UseridAESUtil;

@RequestMapping("game")
@Controller
public class GameController {
	private static Logger logger = Logger.getLogger(GameController.class);
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ActionLoggerService actionLoggerService;
	
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;
	
	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;
	
	// 游戏管理
	@RequestMapping("gameManage")
	public String gameManage(Model model, String title, Integer type) {
		model.addAttribute("title", title);
		model.addAttribute("type", type);
		
		return "admin/gameManage/gameManage";// 跳转
	}

	// 去游戏修改页
	@RequestMapping("toGameUpdate")
	public String toGameUpdate(Model model, Integer id) {
		try {
			Game game = gameService.getInfoById(id);
			
			List<GameImg> imgList = gameService.getImgsByGid(id);
			
			if (imgList.size() == 0) {
				imgList.add(new GameImg());
			}
			
			LinkedHashMap<Object, Object> gameTypeDict = EnumUtil.dictMap(GameTypeEnum.class, "code", "desc");
			LinkedHashMap<Object, Object> plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
			
			model.addAttribute("gameTypeDict", gameTypeDict);
			model.addAttribute("plDict", plDict);
			model.addAttribute("game", game);
			model.addAttribute("imgList", imgList);
			model.addAttribute("outImgPreFixUrl", outImgPreFixUrl);
			model.addAttribute("opType", 2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/gameManage/gameUpdate";// 跳转
	}

	// 去游戏新增页
	@RequestMapping("gameAdd")
	public String gameAdd(Model model) {
		LinkedHashMap<Object, Object> gameTypeDict;
		try {
			gameTypeDict = EnumUtil.dictMap(GameTypeEnum.class, "code", "desc");
		} catch (Exception e) {
			gameTypeDict = new LinkedHashMap<>();
		}
		LinkedHashMap<Object, Object> plDict;
		try {
			plDict = EnumUtil.dictMap(PlEnum.class, "code", "desc");
		} catch (Exception e) {
			plDict = new LinkedHashMap<>();
		}
		List<GameImg> imgList = new ArrayList<>();
		imgList.add(new GameImg());
		
		model.addAttribute("gameTypeDict", gameTypeDict);
		model.addAttribute("plDict", plDict);
		model.addAttribute("game", new Game());
		model.addAttribute("imgList", imgList);
		model.addAttribute("opType", 1);
		
		return "admin/gameManage/gameUpdate";// 跳转
	}

	/**
	 * 游戏列表页查询
	 * @param response
	 * @param limit
	 * @param start
	 * @param page
	 * @param title
	 * @param type
	 * @throws IOException
	 */
	@RequestMapping("list")
	public void getList(HttpServletResponse response, Integer limit, Integer start, Integer page, String title,
			Integer type) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startPage", start);
			map.put("limit", limit);
			if (type == 1)
				map.put("rm", 1);
			if (type == 2)
				map.put("wy", 1);
			map.put("title", title);
			List<Game> games = gameService.getInfoList(map);
			
			for (Game game : games) {
				game.setPicture(outImgPreFixUrl + game.getPicture());
				game.setIcon(outImgPreFixUrl + game.getIcon());
			}
			
			json.put("success", "success");
			json.put("data", JSON.parse(JSON.toJSONString(games, SerializerFeature.DisableCircularReferenceDetect)));
			json.put("limit", limit);
			json.put("page", page + 1);
			// 这里应返回总数，可在载入的时候就查询出
			json.put("total", gameService.getTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 根据id隐藏游戏记录
	@RequestMapping("delGame")
	public void delGame(HttpServletResponse response, @RequestParam Integer id,HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Game game = gameService.getInfoById(id);
			game.setIsshow(0);
			gameService.update(game);
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
		        String a ="显示";
		        if (game.getIsshow()==0) {
					a="不显示";
				}
		        actionLogger.setHandle("账号"+admin.getCard()+"删除了游戏"+game.getTitle()+"游戏ID"+game.getId()+"游戏状态"+a);
		        actionLoggerService.insert(actionLogger);

			
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("隐藏游戏记录时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 保存icon图
	@RequestMapping("saveicon")
	public void saveicon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			String url = imgPreFixUrl + "/topImg";// 图片保存地址
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename = multiRequest.getFile("a_icon");// 获取图片
			File upFile = new File(url);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName = filename.getOriginalFilename();
			InputStream is = filename.getInputStream();
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName.lastIndexOf(".");
			String newFilename;// 文件名带后缀
			// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			newFilename = MyfileFileName.subSequence(0, index) + Long.toString(now) + MyfileFileName.substring(index);
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);

			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();
			String picPath = "/topImg/" + newFilename;// 图片地址
			json.put("outImgPreFixUrl", outImgPreFixUrl);
			json.put("iconPath", picPath);
			json.put("success", "success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增游戏时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 保存icon图
	@RequestMapping("savepicture")
	public void savepicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			String url = imgPreFixUrl + "/topImg";// 图片保存地址
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile filename = multiRequest.getFile("a_picture");// 获取图片
			File upFile = new File(url);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName = filename.getOriginalFilename();
			InputStream is = filename.getInputStream();
			String newFilename;// 文件名带后缀
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName.lastIndexOf(".");
			// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			newFilename = MyfileFileName.subSequence(0, index) + Long.toString(now) + MyfileFileName.substring(index);
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);
			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();

			String picPath = "/topImg/" + newFilename;// 图片地址
			json.put("outImgPreFixUrl", outImgPreFixUrl);
			json.put("picturePath", picPath);
			json.put("success", "success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增游戏时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}
	
	// 保存详情图
	@RequestMapping("saveDetailImg")
	public void saveDetailImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			String url = imgPreFixUrl + "/detailImg";// 图片保存地址
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multiRequest.getFile("a_more_");// 获取图片
			File upFile = new File(url);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String newFilename;// 文件名带后缀
			int index = 0;
			Date date = new Date();
			long now = date.getTime();
			index = MyfileFileName.lastIndexOf(".");
			// 重写名称，给文件名加上毫秒数后缀(新的文件名称)
			newFilename = MyfileFileName.subSequence(0, index) + Long.toString(now) + MyfileFileName.substring(index);
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename);
			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();
			
			String picPath = "/detailImg/" + newFilename;// 图片地址
			json.put("outImgPreFixUrl", outImgPreFixUrl);
			json.put("picturePath", picPath);
			json.put("success", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增游戏时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 新增游戏
	@RequestMapping("savegame")
	public void savegame(HttpServletRequest request, HttpServletResponse response, Game game, String[] detailImg,
			String[] smallImg) throws IOException {
		response.setCharacterEncoding("utf-8");
		try {
			gameService.insertAll(game, detailImg, smallImg);
			response.getWriter().write("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增游戏时出错了！" + e);
			response.getWriter().write("error");
		}
	}

	// 修改游戏
	@RequestMapping("updategame")
	public void updategame(HttpServletRequest request, HttpServletResponse response, Game game, String[] detailImg,
			String[] smallImg) throws IOException {
		response.setCharacterEncoding("utf-8");
		try {
			gameService.updateAll(game, detailImg, smallImg);
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改游戏时出错了！" + e);
			response.getWriter().write("error");
		}
	}

	// 删除 （物理删除）
	@RequestMapping("delgame")
	public void delgame(HttpServletRequest request, HttpServletResponse response, Integer id,HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		try {
			Game game = gameService.getInfoById(id);
			gameService.delete(id);
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
		        String a ="显示";
		        if (game.getIsshow()==0) {
					a="不显示";
				}
		        actionLogger.setHandle("账号"+admin.getCard()+"删除了游戏"+game.getTitle()+"游戏ID"+game.getId()+"游戏状态"+a);
		        actionLoggerService.insert(actionLogger);
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除游戏时出错了！" + e);
			response.getWriter().write("error");
		}
	}
}
