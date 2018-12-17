package com.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.entity.Game;
import com.demo.order.service.GameService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class OrderController {
	
	@Autowired
	GameService GameService;

	@ApiOperation(value="创建游戏", notes="根据User对象创建游戏")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "游戏类型", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sort", value = "游戏排序", required = true, dataType = "Integer")
	})
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloWorld(String type, Integer sort) {
		System.out.println("ssssssssssssssssssss");
		Game game = new Game();
		game.setType(type);
		game.setSort(sort);
		GameService.insert(game);
		return "Hello World !";
	}
	
	@ApiOperation(value="查询游戏", notes="根据id查询游戏")
	@ApiImplicitParam(name = "id", value = "游戏ID", required = true, dataType = "int")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(int id) {
		System.out.println("ssssssssssssssssssss");
		
		return "Hello World !";
	}
	
	@RequestMapping("/getGame")
	@ResponseBody
	public Game getGame(){
		Game game = new Game();
		game.setType("");
		game.setSort(2);
	    return game;
	}
}