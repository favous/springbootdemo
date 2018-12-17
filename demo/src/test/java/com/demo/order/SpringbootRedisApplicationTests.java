package com.demo.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.order.beans.RedisUtil;
import com.demo.order.entity.Game;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Test
    public void test1() {
    	RedisUtil.set("1", 1);
    	Object v = RedisUtil.get("1");
    	System.out.println(v);
    	
    	RedisUtil.set("user", new Game());
    	Object v2 = RedisUtil.get("user");
    	System.out.println(v2);
        
    }

}
