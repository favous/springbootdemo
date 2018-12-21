package com.gyumaru.gameManage.service;

import java.util.List;
import java.util.Map;

import com.gyumaru.base.service.BaseService;
import com.gyumaru.pojo.Game;
import com.gyumaru.pojo.GameImg;

public interface GameService extends BaseService<Game>{
	//获取总记录条数
	int getTotal(Map<String,Object> map);
	
	List<GameImg> getImgsByGid(int gameId);
	
	void insertAll(Game game, String[] detailImg, String[] smallImg) throws Exception;
	
	void updateAll(Game game, String[] detailImg, String[] smallImg) throws Exception;
}
