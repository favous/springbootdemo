package com.demo.order.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.demo.order.beans.RedisUtil;

import io.lettuce.core.RedisException;

/**
 * 
 * @author sdc
 *
 */
@Configuration
@EnableCaching // 开启缓存支持
//@ConditionalOnClass(RedisOperations.class)
//@EnableConfigurationProperties(RedisProperties.class)
//@Import({JedisConnectionConfiguration.class })
public class RedisConfiguration {

	@SuppressWarnings("unchecked")
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericFastJsonRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericFastJsonRedisSerializer());
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();

//		ProxyFactory proxyFactory = new ProxyFactory(new Class[] { ProxyFactory.class });
//		proxyFactory.setTarget(template);
//		proxyFactory.addAdvice(new MethodInterceptor() {
//			@Override
//			public Object invoke(MethodInvocation invocation) throws Throwable {
//				try {
//					return invocation.proceed();// 执行被代理对象的方法,返回方法的返回值
//				} catch (Exception e) {
//					String methodname = invocation.getMethod().getDeclaringClass().getName();
//					String args = JSON.toJSONString(invocation.getArguments());
//					throw new RedisException("redis操作(" + methodname + ")异常：args=" + args, e);
//				}
//			}
//		});
//		template = (RedisTemplate<String, Object>) proxyFactory.getProxy();

		RedisUtil.init(template);

		return template;
	}
	 
//	 
//		// 缓存管理器
//		@Bean
//		public CacheManager cacheManager() {
//			RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
//					.fromConnectionFactory(lettuceConnectionFactory);
//			@SuppressWarnings("serial")
//			Set<String> cacheNames = new HashSet<String>() {
//				{
//					add("codeNameCache");
//				}
//			};
//			builder.initialCacheNames(cacheNames);
//			return builder.build();
//		}

	@Bean
	@ConditionalOnMissingBean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		template.afterPropertiesSet();
		return template;
	}

}
