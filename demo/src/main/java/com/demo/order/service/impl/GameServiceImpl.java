package com.demo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.demo.order.entity.Game;
import com.demo.order.mapper.GameMapper;
import com.demo.order.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameMapper GameMapper;
	
	@Autowired
	RedisTemplate<?, ?> redisTemplate;
	
	@Override
	public boolean insert(Game game) {
		return GameMapper.insert(game) > 0;
	}
	
	@Override
	public void test() {
		Object execute = redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = "a".getBytes();
				boolean flag1 = connection.setNX(key, "1".getBytes());
				boolean flag2 = connection.setNX(key, "2".getBytes());
				connection.get(key);
				return null;
			}
			
		});
	}

}
