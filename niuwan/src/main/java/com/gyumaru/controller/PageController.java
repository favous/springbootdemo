
package com.gyumaru.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fzc.common.enums.UrlTypeEnum;
import com.gyumaru.advertManage.service.AdvertService;
import com.gyumaru.advertisementManage.pojo.AdvPlan;
import com.gyumaru.advertisementManage.pojo.AdvPlanImg;
import com.gyumaru.advertisementManage.pojo.AdvPlanTime;
import com.gyumaru.advertisementManage.pojo.AdvPlanUser;
import com.gyumaru.advertisementManage.service.AdvPlanImgService;
import com.gyumaru.advertisementManage.service.AdvPlanService;
import com.gyumaru.advertisementManage.service.AdvPlanTimeService;
import com.gyumaru.advertisementManage.service.AdvPlanUserService;
import com.gyumaru.articleManage.service.ArticleContentService;
import com.gyumaru.articleManage.service.ArticleService;
import com.gyumaru.bo.GameBo;
import com.gyumaru.bo.GameBo.ImgBo;
import com.gyumaru.collectManage.service.CollectService;
import com.gyumaru.gameManage.enums.GameTypeEnum;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.loginTokenManage.service.LoginTokenService;
import com.gyumaru.pojo.Advert;
import com.gyumaru.pojo.Article;
import com.gyumaru.pojo.ArticleContent;
import com.gyumaru.pojo.Collect;
import com.gyumaru.pojo.Game;
import com.gyumaru.pojo.GameImg;
import com.gyumaru.pojo.LoginToken;
import com.gyumaru.regionManage.pojo.City;
import com.gyumaru.regionManage.pojo.County;
import com.gyumaru.regionManage.pojo.Region;
import com.gyumaru.regionManage.service.CityService;
import com.gyumaru.regionManage.service.CountyService;
import com.gyumaru.regionManage.service.RegionService;
import com.gyumaru.userManage.pojo.Recode;
import com.gyumaru.userManage.pojo.User;
import com.gyumaru.userManage.service.RecodeService;
import com.gyumaru.userManage.service.UserService;
import com.gyumaru.util.MailUtil;
import com.gyumaru.util.MyAESUtil;
import com.gyumaru.util.UseridAESUtil;
import com.gyumaru.util.UtilUpName;
import com.gyumaru.util.UuidUtil;

@RequestMapping("page")
@Controller
public class PageController {
	private static Logger logger = Logger.getLogger(PageController.class);
	@Autowired
	private AdvertService advertService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleContentService articleContentService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private CountyService countyService;
	@Autowired
	private CityService cityService;
	@Autowired
	private RecodeService recodeService;
	@Autowired
	private AdvPlanTimeService advPlanTimeService;
	@Autowired
	private AdvPlanService advPlanService;
	@Autowired
	private AdvPlanUserService advPlanUserService;
	@Autowired
	private AdvPlanImgService advPlanImgService;
	@Autowired
	private LoginTokenService loginTokenService;
	@Value("${imgPreFixUrl}")
	private String imgPreFixUrl;

	@Value("${outImgPreFixUrl}")
	private String outImgPreFixUrl;

	// 首页
	@RequestMapping("index")
	public void index(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		logger.info(">>>>>>>>>>>首页index query>>>>>>>>>>>>>>>>>>");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			List<Advert> adverts = advertService.getInfoList(null);
			for (Advert advert : adverts)
				advert.setImgUrl(outImgPreFixUrl + advert.getImgUrl());
			Map<String, Object> map = new HashMap<>();
			map.put("isshow", 1);
			List<Game> games = gameService.getInfoList(map);
			for (Game game : games)
				game.setIcon(outImgPreFixUrl + game.getIcon());
			json.put("success", "success");
			// 这里应返回总数，可在载入的时候就查询出
			json.put("games", games);
			json.put("adverts", adverts);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 根据时间获取当前应该展示的广告
	@RequestMapping("getAdver")
	public void getAdver(HttpServletResponse response, Integer type, HttpSession session, String userid) throws IOException {
		if (type == null || type <= 0 || type > 2) {
			type = 1;
		}
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			JSONArray json1 = new JSONArray();
			for (int i = 1; i < 4; i++) {
				JSONObject json2 = new JSONObject();
				Date now = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tdate", sdf1.format(now));
				map.put("thour", sdf2.format(now));
				map.put("state", 2);
				map.put("type", i);
				List<AdvPlanTime> advPlanTimes = advPlanTimeService.getInfoList(map);
				// 判断是否有计划
				if (advPlanTimes.size() > 0) {
					AdvPlanTime nowAdvPlanTime = advPlanTimes.get(0);
					AdvPlan advPlan = advPlanService.getInfoById(nowAdvPlanTime.getAdvPlanId());
					User user = getUserByUserId(userid);
					// 判断当前是否有用户
					if (user == null) {// 直接投放但是不计数
						JSONObject json10 = new JSONObject();
						json10.put("success", "nologin");
						response.getWriter().write(json10.toString());
						return;
					} else {
						map = new HashMap<String, Object>();
						map.put("advPlanId", advPlan.getId());
						List<AdvPlanUser> advPlanUsers1 = advPlanUserService.getdcNum(map);
						Long nowTotaldisNum = 0L;
						Long nowTotalclickNum = 0L;
						if (advPlanUsers1.size() > 0) {
							AdvPlanUser advPlanUserTemp = advPlanUsers1.get(0);
							if (advPlanUserTemp != null) {
								if (advPlanUserTemp.getTotaldisNum() != null)
									nowTotaldisNum = advPlanUserTemp.getTotaldisNum();
								if (advPlanUserTemp.getTotalclickNum() == null)
									nowTotalclickNum = advPlanUserTemp.getTotalclickNum();
							}
						}
						if ((advPlan.getTotaldisNum() != 0 && advPlan.getTotaldisNum() <= nowTotaldisNum) || (advPlan.getTotalNum() != 0 && advPlan.getTotalNum() <= nowTotalclickNum)) {
							json2.put("success", "no");
						} else {
							map = new HashMap<String, Object>();
							map.put("userId", user.getId());
							map.put("advPlanId", advPlan.getId());
							List<AdvPlanUser> advPlanUsers = advPlanUserService.getInfoList(map);
							// 判断是否已显示过
							if (advPlanUsers.size() > 0) {
								AdvPlanUser advPlanUser = advPlanUsers.get(0);
								// 显示阀值判断
								if ((advPlan.getDispalyNum() != 0 && advPlan.getDispalyNum() <= advPlanUser.getDisplayNum()) || (advPlan.getClickNum() != 0 && advPlan.getClickNum() <= advPlanUser.getClickNum())) {
									json2.put("success", "no");
								} else {
									map = new HashMap<String, Object>();
									if (advPlan.getType() == 1)
										map.put("type", type);
									map.put("advPlanId", advPlan.getId());
									List<AdvPlanImg> advPlanImgs = advPlanImgService.getInfoList(map);
									if (advPlanImgs.size() > 0) {
										json2.put("advPlanId", advPlan.getId());
										json2.put("advPlanType", advPlan.getType());
										json2.put("advPlanImg", outImgPreFixUrl + advPlanImgs.get(0).getUrl());
										json2.put("success", "success");
										advPlanUser.setDisplayNum(advPlanUser.getDisplayNum() + 1);
										advPlanUserService.update(advPlanUser);
									} else {
										json2.put("success", "no");
									}
								}
							} else {
								AdvPlanUser advPlanUser = new AdvPlanUser();
								advPlanUser.setAdvPlan(advPlan);
								advPlanUser.setClickNum(0);
								advPlanUser.setDisplayNum(1);
								advPlanUser.setUser(user);
								advPlanUserService.insert(advPlanUser);
								map = new HashMap<String, Object>();
								if (advPlan.getType() == 1)
									map.put("type", type);
								map.put("advPlanId", advPlan.getId());
								List<AdvPlanImg> advPlanImgs = advPlanImgService.getInfoList(map);
								if (advPlanImgs.size() > 0) {
									json2.put("advPlanId", advPlan.getId());
									json2.put("advPlanType", advPlan.getType());
									json2.put("advPlanImg", outImgPreFixUrl + advPlanImgs.get(0).getUrl());
									json2.put("success", "success");
								} else {
									json2.put("success", "no");
								}
							}
						}
					}
				} else {
					json2.put("success", "no");
				}
				json1.add(json2);
			}
			json.put("advlist", json1);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取广告计划信息时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 点击广告
	@RequestMapping("clickAdver")
	public void clickAdver(HttpServletResponse response, @RequestParam Integer id, HttpSession session, String userid) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<>();
			AdvPlan advPlan = advPlanService.getInfoById(id);
			User user = getUserByUserId(userid);
			// 判断当前是否有用户
			if (user == null) {
				json.put("success", "success");
				json.put("url", advPlan.getLink());
			} else {
				map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				map.put("advPlanId", id);
				List<AdvPlanUser> advPlanUsers = advPlanUserService.getInfoList(map);
				// 判断是否已达到展示的阀值
				if (advPlanUsers.size() > 0) {
					AdvPlanUser advPlanUser = advPlanUsers.get(0);
					advPlanUser.setClickNum(advPlanUser.getClickNum() + 1);
					advPlanUserService.update(advPlanUser);
					json.put("success", "success");
					json.put("url", advPlan.getLink());
				} else {
					AdvPlanUser advPlanUser = new AdvPlanUser();
					advPlanUser.setAdvPlan(advPlan);
					advPlanUser.setClickNum(1);
					advPlanUser.setDisplayNum(0);
					advPlanUser.setUser(user);
					advPlanUserService.insert(advPlanUser);
					json.put("success", "success");
					json.put("url", advPlan.getLink());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户点击广告时出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 跳转游戏详细
	@RequestMapping("gamedtl")
	public void gamedtl(Model model, Integer id, HttpSession session, HttpServletResponse response, String userid) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			Game game = gameService.getInfoById(id);
			if (game.getJdjs().length() > 72) {
				game.setJdjs1(game.getJdjs().substring(0, 72) + "...");
			} else {
				game.setJdjs1(game.getJdjs());
			}
			if (game.getPl() != 0 && user == null) {
				json.put("success", "nologin");// 未登录
				response.getWriter().write(json.toString());
				return;
			}
			if (user == null) {
				user = new User();
				user.setPl(0);
				user.setId(0);
			}
			if (game.getPl() > user.getPl()) {
				json.put("success", "lesspl");// 等级不足
				response.getWriter().write(json.toString());
				return;
			}
			game.setPicture(outImgPreFixUrl + game.getPicture());
			game.setIcon(outImgPreFixUrl + game.getIcon());
			Integer colourMode = 0;// 若没有查询到数据则为未点赞无颜色
			if (user.getId() != 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("type", 2);
				map.put("userId", user.getId());
				map.put("modeId", id);
				List<Collect> collects = collectService.getInfoList(map);
				if (collects.size() > 0) {
					colourMode = 1;
				}
			}
			GameBo bo = new GameBo();
			BeanUtils.copyProperties(game, bo);
			List<GameImg> imgs = gameService.getImgsByGid(game.getId());
			List<ImgBo> imgList = new ArrayList<>();

			for (GameImg img : imgs) {
				if (StringUtils.isNotBlank(img.getUrl())) {
					boolean flag = img.getUrl().matches("^.*(swf|avi|flv|mpg|rm|mov|wav|asf|3gp|mkv|rmvb)$");
					Integer type = flag ? UrlTypeEnum.VIDEO.getCode() : UrlTypeEnum.IMG.getCode();
					ImgBo imgbo = new ImgBo(type, outImgPreFixUrl + img.getUrl(), outImgPreFixUrl + img.getSurl());
					imgList.add(imgbo);
				}
			}
			bo.setImgList(imgList);

			boolean flag = StringUtils.isBlank(game.getType());
			bo.setType(flag ? null : GameTypeEnum.getDescByCode(Integer.valueOf(game.getType())));

			json.put("success", "success");
			// 这里应返回总数，可在载入的时候就查询出
			json.put("colourMode", colourMode);
			json.put("game", bo);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 跳转资讯列表
	@RequestMapping("article")
	public void article(Model model, HttpSession session, HttpServletResponse response, String userid) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("state", 1);
			map.put("status", 30);
			List<Article> articles = articleService.getInfoList(map);
			User user = getUserByUserId(userid);
			for (int i = 0; i < articles.size(); i++) {
				if (user == null) {
					articles.get(i).setCollertMode(0);// 未点赞
				} else {
					Map<String, Object> map1 = new HashMap<>();
					map1.put("type", 1);
					map1.put("userId", user.getId());
					map1.put("modeId", articles.get(i).getId());
					List<Collect> collects = collectService.getInfoList(map1);
					if (collects.size() > 0) {
						articles.get(i).setCollertMode(1);// 点赞
					} else {
						articles.get(i).setCollertMode(0);// 未点赞
					}
				}
			}
			for (Article article : articles) {
				article.setArticleCoverPic(outImgPreFixUrl + article.getArticleCoverPic());
			}

			json.put("success", "success");
			// 这里应返回总数，可在载入的时候就查询出
			json.put("articles", articles);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	/**
	 * 根据userid获取用户
	 */
	public User getUserByUserId(String userid) throws Exception {
		Map<String, Object> mapUser = new HashMap<String, Object>();
		mapUser.put("userid", userid);
		List<User> users = userService.getInfoList(mapUser);
		if (userid != null && userid.length() > 0 && users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	// 跳转资讯详细
	@RequestMapping("articledtl")
	public void articledtl(Model model, Integer id, HttpSession session, HttpServletResponse response, String userid) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			Article article = articleService.getInfoById(id);
			if (article.getArticleTitle().length()>76) {
				article.setArticleTitle(article.getArticleTitle().substring(0, 76)+"...");
			}
			if (article.getPl() != 0 && user == null) {
				json.put("success", "nologin");
				response.getWriter().write(json.toString());
				return;
			}
			if (user == null) {
				user = new User();
				user.setPl(0);
			}
			if (article.getPl() > user.getPl()) {
				json.put("success", "lesspl");// 等级不足
				response.getWriter().write(json.toString());
				return;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("articleId", id);
			List<ArticleContent> articleContents = articleContentService.getInfoList(map);
			article.setArticleContent(articleContents.get(0).getArticleContent());
			Integer colourMode = 0;// 若没有查询到数据则为未点赞无颜色
			if (user.getId() != null) {
				Map<String, Object> map1 = new HashMap<>();
				map1.put("type", 1);
				map1.put("userId", user.getId());
				map1.put("modeId", id);
				List<Collect> collects = collectService.getInfoList(map1);
				if (collects.size() > 0) {
					colourMode = 1;
				}
			}
			json.put("success", "success");
		
			json.put("colourMode", colourMode);
			article.setArticleCoverPic(outImgPreFixUrl + article.getArticleCoverPic());
			json.put("article", article);

		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 跳转个人中心
	@RequestMapping("personal")
	public void personal(Model model, HttpSession session, HttpServletResponse response, String userid) throws Exception {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			if (user != null) {
				session.setAttribute("user", user);
				user.setHeadimg(outImgPreFixUrl + user.getHeadimg());
				json.put("success", "success");
				// 这里应返回总数，可在载入的时候就查询出
				json.put("user", user);
			} else {
				// 未登录
				json.put("success", "nologin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 跳转喜欢(我的收藏)
	@RequestMapping("collect")
	public void collect(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, String userid) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			if (user != null) {
				Map<String, Object> map = new HashMap<>();
				map.put("userId", user.getId());
				List<Collect> collects = collectService.getInfoList(map);
				List<Collect> collects2 = new ArrayList<Collect>();
				for (Collect collect : collects) {
					if (collect.getType() == 1) {// 资讯
						Article article = articleService.getInfoById(collect.getModeId());
						if (article != null) {
							if (article.getState() == 1 && article.getStatus() == 30) {
								collect.setImgUrl(article.getArticleCoverPic());// 封面图片
								collect.setModeId(article.getId());// 跳转ID
								collect.setTitle(article.getArticleTitle());// 标题
								collect.setContent(article.getArticleAbstract());// 标题
								collect.setTime(article.getPublishTime());// 时间
								collect.setCollectTime(collect.getCollectTime());// 收藏时间
								collect.setType(1);
								collects2.add(collect);
							}
						}
					} else {
						Game game = gameService.getInfoById(collect.getModeId());
						if (game != null) {
							if (game.getIsshow() == 1) {
								collect.setImgUrl(game.getIcon());// 封面图片
								collect.setModeId(game.getId());// 跳转ID
								collect.setTitle(game.getTitle());// 标题
								collect.setContent(game.getJdesc());// 标题
								if (game.getDj() == 1) {// h5游戏
									collect.setType(2);
								} else {
									collect.setType(3);
								}
								collect.setCollectTime(collect.getCollectTime());// 收藏时间
								collect.setTime(game.getAdd_time());
								collects2.add(collect);
							}
						}
					}
				}
				json.put("success", "success");
				// 这里应返回总数，可在载入的时候就查询出
				json.put("collects", collects2);
			} else {
				// 未登录
				json.put("success", "nologin");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 跳转修改个人信息
	@RequestMapping("pinfo")
	public void pinfo(Model model, HttpSession session, String userid, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			if (user != null) {
				user.setHeadimg(outImgPreFixUrl + user.getHeadimg());
				json.put("success", "success");
				// 这里应返回总数，可在载入的时候就查询出
				json.put("user", JSON.parse(JSON.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect)));
				List<City> citiess = cityService.getInfoList(null);
				List<County> countiess = countyService.getInfoList(null);
				List<Region> regionss = regionService.getInfoList(null);
				JSONArray array = new JSONArray();
				JSONArray arrays = new JSONArray();
				JSONArray arrayss = new JSONArray();
				for (int i = 0; i < regionss.size(); i++) {
					JSONObject jsons = new JSONObject();
					jsons.put("code", regionss.get(i).getId());
					jsons.put("name", regionss.get(i).getName());
					for (int j = 0; j < countiess.size(); j++) {
						/* arrays = new JSONArray(); */
						JSONObject jsonss = new JSONObject();
						if (regionss.get(i).getId() == countiess.get(j).getRegion().getId()) {
							jsonss.put("code", countiess.get(j).getId());
							jsonss.put("name", countiess.get(j).getName());
							for (int k = 0; k < citiess.size(); k++) {
								if (countiess.get(j).getId() == citiess.get(k).getCounty().getId()) {
									JSONObject jsonsss = new JSONObject();
									jsonsss.put("code", citiess.get(k).getId());
									jsonsss.put("name", citiess.get(k).getName());
									arrayss.add(jsonsss);
								}
							}
							jsonss.put("childs", arrayss);
							arrayss = new JSONArray();
							arrays.add(jsonss);
						}
					}
					jsons.put("childs", arrays);
					arrays = new JSONArray();
					array.add(jsons);
				}
				json.put("array", array);
				json.put("success", "success");
			} else {
				// 未登录
				json.put("success", "nologin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());

	}

	// 修改个人信息
	@RequestMapping("modifyUser")
	public void modifyUser(HttpServletResponse response, HttpSession session, String userid, String name, Integer sex, Integer age, Integer regioinId, Integer countyId, Integer cityId, Integer profession1, Integer profession2) throws Exception {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			if (user != null) {
				if (name != null && name.length() > 0)
					user.setName(name);
				if (sex != null)
					user.setSex(sex);
				if (age != null)
					user.setAge(age);
				if (regioinId != null)
					user.setRegion(regionService.getInfoById(regioinId));
				if (countyId != null)
					user.setCounty(countyService.getInfoById(countyId));
				if (cityId != null)
					user.setCity(cityService.getInfoById(cityId));
				if (profession1 != null) {
					if (profession1 > 5)
						user.setProfession(profession1);
					else
						user.setProfession(profession2);
				}
				userService.update(user);
				session.setAttribute("user", user);
				user.setHeadimg(outImgPreFixUrl + user.getHeadimg());
				json.put("success", "success");
				// 这里应返回总数，可在载入的时候就查询出
			} else {
				// 未登录
				json.put("success", "nologin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 更换头像
	@RequestMapping("upfile")
	public void upfile(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session, String userid) throws Exception {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			String url = imgPreFixUrl + "userHeadImg";
			String basePath = "userHeadImg/";

			// String url1 =
			// request.getSession().getServletContext().getRealPath("resources")
			// +
			// "\\images" + "\\headimg";// 图片保存地址
			MultipartFile filename1 = multiRequest.getFile("upfile");// 回去图片
			File upFile = new File(url);
			if (!upFile.isDirectory() && !upFile.exists()) {
				upFile.mkdir();
			}
			String MyfileFileName1 = filename1.getOriginalFilename();
			String newFilename1 = UtilUpName.getImgUpName(MyfileFileName1);
			InputStream is = filename1.getInputStream();
			// 输出文件
			FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFilename1);
			byte[] b = new byte[is.available()];
			is.read(b);
			fileOutputStream.write(b);
			// 关闭流
			is.close();
			fileOutputStream.close();

			User user = getUserByUserId(userid);
			if (user != null) {
				user.setHeadimg(basePath + newFilename1);
				userService.update(user);
				session.setAttribute("user", user);
				json.put("outImgPreFixUrl", outImgPreFixUrl);
				json.put("success", "success");
			} else {
				// 未登录
				json.put("success", "nologin");
			}
			// 这里应返回总数，可在载入的时候就查询出
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 收藏点击事件
	@RequestMapping("chickcollect")
	public void chickcollect(HttpServletResponse response, Integer id, Integer type, HttpSession session, String userid) throws IOException {
		if (type == 3) {
			type = 2;
		}
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = getUserByUserId(userid);
			if (user != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("modeId", id);
				map.put("type", type);
				map.put("userId", user.getId());
				// 根据传入参数查询收藏表中有无数据
				List<Collect> collects = collectService.getInfoList(map);
				if (collects.size() < 1) {// 没有查询到数据为收藏 （插入数据）
					Collect collect = new Collect();
					collect.setModeId(id);
					collect.setType(type);
					collect.setUserId(user.getId());
					try {
						collectService.insert(collect);
						json.put("success", "success1");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("用户收藏出错了！" + e);
						json.put("success", "error");
					}
				} else {// 查询到了数据为取消收藏(删除表中数据)
					for (int i = 0; i < collects.size(); i++) {
						collectService.delete(collects.get(i).getId());
						json.put("success", "success");
					}
				}
			} else {
				// 未登录
				json.put("success", "nologin");
			}
			// 这里应返回总数，可在载入的时候就查询出
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 获取验证码
	@RequestMapping("getSignupCodes")
	public void getSignupCodes(HttpServletResponse response, String account) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Recode recode = new Recode();
			recode.setAccount(account);
			recode.setType(1);
			recode.setCodes(MailUtil.mail(account));
			recodeService.insert(recode);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 获取密码找回验证码
	@RequestMapping("getForgetCodes")
	public void getForgetCodes(HttpServletResponse response, String account) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Recode recode = new Recode();
			recode.setAccount(account);
			recode.setType(2);
			recode.setCodes(MailUtil.mailForgetCodes(account));
			recodeService.insert(recode);
			json.put("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			json.put("success", "error");
		}
		// 通过response传值
		response.getWriter().write(json.toString());
	}

	// 密码找回
	@RequestMapping("updatePassword")
	public void updatePassword(HttpServletResponse response, String account, String pwd, String recode, HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account);
			map.put("type", 2);
			List<Recode> recodes = recodeService.getInfoList(map);
			if (recodes.size() > 0) {
				Recode newRecode = recodes.get(0);
				if (recode.equals(newRecode.getCodes())) {
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<User> users = userService.getInfoList(map);
					if (users.size() > 0) {
						User user = users.get(0);
						String newPwd = MyAESUtil.Encrypt(pwd);
						user.setPwd(newPwd);
						userService.update(user);
						json.put("success", "success");
					} else {
						// 没有此用户
						json.put("success", "noUser");
					}
					// 删除此人验证码
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<Recode> recodes2 = recodeService.getInfoList(map);
					for (Recode recodeTemp : recodes2) {
						recodeService.delete(recodeTemp.getId());
					}
				} else {
					// 错误的验证码
					json.put("success", "errorRecodes");
				}
			} else {
				// 没有验证码或验证码过期
				json.put("success", "noRecodes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 判断该uid是否存在用户
	@RequestMapping("checkuid")
	public void checkuid(HttpServletResponse response, String uid, HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			if (uid != null && uid.length() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("uid", uid);
				List<User> users = userService.getInfoList(map);
				if (users.size() > 0) {
					session.setAttribute("user", users.get(0));
					json.put("exist", "true");
				} else {
					json.put("exist", "false");
				}
			} else {
				json.put("exist", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 注册
	@RequestMapping("signup")
	public void signup(HttpServletResponse response, String account, String uid, String idfa, String name, String pwd, String recode, HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			User user = new User();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account);
			map.put("type", 1);
			List<Recode> recodes = recodeService.getInfoList(map);
			if (recodes.size() > 0) {
				Recode newRecode = recodes.get(0);
				if (recode.equals(newRecode.getCodes())) {
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<User> users = userService.getInfoList(map);
					List<User> users2 = new ArrayList<User>();
					if (uid != null && uid.length() > 0) {
						map = new HashMap<String, Object>();
						map.put("uid", uid);
						users2 = userService.getInfoList(map);
					}
					if (users.size() > 0) {
						json.put("success", "sameAccount");
					} else if (users2.size() > 0) {
						json.put("success", "sameAccount");
					} else {
						user.setAccount(account);
						String newPwd = MyAESUtil.Encrypt(pwd);
						if (uid != null && uid.length() > 0) {
							user.setUid(uid);
						}
						if (idfa != null && idfa.length() > 0) {
							user.setIdfa(idfa);
						}
						user.setName(name);
						user.setPwd(newPwd);
						user.setState(1);
						user.setHeadimg("userHeadImg/moren.png");
						user.setPl(15);
						boolean isok = true;
						while (isok) {
							StringBuilder userid = UseridAESUtil.Decrypt();
							map = new HashMap<String, Object>();
							map.put("userid", userid.toString());
							List<User> users6 = userService.getInfoList(map);
							if (users6.size() <= 0) {
								user.setUserid(userid.toString());
								isok = false;
							}
						}
						userService.insert(user);
						json.put("success", "success");
						json.put("userid", user.getUserid());
						json.put("pl", user.getPl());
						json.put("userMode", 1);
						session.setAttribute("user", user);
					}
					map = new HashMap<String, Object>();
					map.put("account", account);
					List<Recode> recodes2 = recodeService.getInfoList(map);
					for (Recode recodeTemp : recodes2) {
						recodeService.delete(recodeTemp.getId());
					}
				} else {
					json.put("success", "errorRecodes");
				}
			} else {
				json.put("success", "noRecodes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

	// 登录
	@RequestMapping("signin")
	public void signin(HttpServletResponse response, HttpServletRequest request, String account1, String pwd, HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", account1);
			map.put("pwd", MyAESUtil.Encrypt(pwd));
			List<User> users = userService.getInfoList(map);
			if (users.size() > 0) {
				session.setAttribute("user", users.get(0));
				json.put("success", "success");
				json.put("pl", users.get(0).getPl());
				json.put("userid", users.get(0).getUserid());
				json.put("userMode", 1);
				map = new HashMap<String, Object>();
				map.put("userId", users.get(0).getId());
				List<LoginToken> loginTokens = loginTokenService.getInfoList(map);
				if (loginTokens.size() > 0) {
					for (LoginToken loginToken : loginTokens) {
						loginTokenService.delete(loginToken.getId());
					}
				}

				String token = "";
				while (true) {
					token = UuidUtil.GetUuid();
					map = new HashMap<String, Object>();
					map.put("token", token);
					List<LoginToken> loginTokens2 = loginTokenService.getInfoList(map);
					if (loginTokens2.size() == 0)
						break;
				}
				LoginToken loginToken = new LoginToken();
				loginToken.setToken(token);
				loginToken.setUserId(users.get(0).getId());
				loginTokenService.insert(loginToken);
				Cookie cookie = new Cookie("loginToken", token);
				cookie.setPath("/"); 
				cookie.setMaxAge(60*60*24*30*12);
				response.addCookie(cookie);
			} else {
				json.put("success", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录出错了！" + e);
			json.put("success", "error2");
		}
		response.getWriter().write(json.toString());
	}

	// 自动登录
	@RequestMapping("autoSignin")
	public void autoSignin(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		try {
			// 获取浏览器访问访问服务器时传递过来的cookie数组
			Cookie[] cookies = request.getCookies();
			// 如果用户是第一次访问，那么得到的cookies将是null
			if (cookies != null) {
				boolean ok = false;
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals("loginToken")) {
						ok=true;
						String userToken = cookie.getValue();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("token", userToken);
						List<LoginToken> loginTokens = loginTokenService.getInfoList(map);
						if (loginTokens.size() > 0) {
							User user = userService.getInfoById(loginTokens.get(0).getUserId());
							if (user != null) {
								json.put("userMode", 1);
								json.put("success", "success");
								json.put("userid", user.getUserid());
								json.put("pl", user.getPl());
								// 删除掉该用户及该token的
								for (LoginToken loginTokenTemp : loginTokens)
									loginTokenService.delete(loginTokenTemp.getId());
								map.put("userId", user.getId());
								List<LoginToken> loginTokens2 = loginTokenService.getInfoList(map);
								for (LoginToken loginTokenTemp : loginTokens2)
									loginTokenService.delete(loginTokenTemp.getId());
								// 生成一个新的token
								String token = "";
								while (true) {
									token = UuidUtil.GetUuid();
									map = new HashMap<String, Object>();
									map.put("token", token);
									List<LoginToken> loginTokens3 = loginTokenService.getInfoList(map);
									if (loginTokens3.size() == 0)
										break;
								}
								LoginToken loginToken = new LoginToken();
								loginToken.setToken(token);
								loginToken.setUserId(user.getId());
								loginTokenService.insert(loginToken);
								Cookie cookie2 = new Cookie("loginToken", token);
								cookie2.setPath("/"); 
								cookie2.setMaxAge(60*60*24*30*12);
								response.addCookie(cookie2);
							} else {
								json.put("success", "false");
							}

						} else {
							json.put("success", "false");
						}
					}
				}
				if(!ok) {
					json.put("success", "false");
				}
			} else {
				json.put("success", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("自动登录出错了！" + e);
			json.put("success", "error");
		}
		response.getWriter().write(json.toString());
	}

}