package com.gyumaru.gameManage.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyumaru.gameManage.dao.GameDao;
import com.gyumaru.gameManage.service.GameService;
import com.gyumaru.pojo.Game;
import com.gyumaru.pojo.GameImg;



@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameDao gameDao;
	
	public void insert(Game game) throws Exception {
		gameDao.insert(game);
	}

	public void update(Game game) throws Exception {
		gameDao.update(game);
	}

	public void delete(Integer id) throws Exception {
		gameDao.delete(id);
	}

	public Game getInfoById(Integer id) throws Exception {
		return gameDao.getInfoById(id);
	}

	public List<Game> getInfoList(Map<String, Object> map) throws Exception {
		return gameDao.getInfoList(map); 
	}
	//获取总记录条数
	public int getTotal(Map<String,Object> map){
		return gameDao.getTotal(map);
	}
	
	@Override
	public List<GameImg> getImgsByGid(int gameId) {
		return gameDao.getImgsByGid(gameId);
	}

	@Override
	@Transactional
	public void insertAll(Game game, String[] detailImg, String[] smallImg) throws Exception {
		insert(game);
		
		GameImg gameImg = new GameImg();
		gameImg.setGame_id(game.getId());
		gameImg.setEnable(1);
//		gameImg.setType(type);
		
		for (int i = 0; i < detailImg.length; i++) {
			gameImg.setUrl(detailImg[i]);
			gameImg.setSurl(smallImg[i]);
			gameDao.insertGameImg(gameImg);
		}		
	}

	@Transactional
	@Override
	public void updateAll(Game game, String[] detailImg, String[] smallImg) throws Exception {
		update(game);
		
		gameDao.deleteGameImgs(game.getId());
		
		GameImg gameImg = new GameImg();
		gameImg.setGame_id(game.getId());
		gameImg.setEnable(1);
//		gameImg.setType(type);
		
		for (int i = 0; i < detailImg.length; i++) {
			gameImg.setUrl(detailImg[i]);
			gameImg.setSurl(smallImg[i]);
			gameDao.insertGameImg(gameImg);
		}		
	};
}
