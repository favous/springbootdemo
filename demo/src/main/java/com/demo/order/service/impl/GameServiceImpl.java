package com.demo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.entity.Game;
import com.demo.order.mapper.GameMapper;
import com.demo.order.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameMapper GameMapper;
	
	@Override
	public boolean insert(Game game) {
		return GameMapper.insert(game) > 0;
	}

}
