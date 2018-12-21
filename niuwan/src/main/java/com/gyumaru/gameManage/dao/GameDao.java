package com.gyumaru.gameManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gyumaru.base.dao.BaseDao;
import com.gyumaru.pojo.GameImg;

@Repository
public interface GameDao extends BaseDao {
	// 获取总记录条数
	int getTotal(Map<String, Object> map);

	List<GameImg> getImgsByGid(int gameId);
	
	void insertGameImg(GameImg gameImg);

	void deleteGameImgs(int gameId);
}
