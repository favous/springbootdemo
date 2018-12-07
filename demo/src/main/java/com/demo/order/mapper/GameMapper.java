package com.demo.order.mapper;

import org.springframework.stereotype.Repository;

import com.demo.order.entity.Game;

@Repository
public interface GameMapper {

	int insert(Game game);
}
