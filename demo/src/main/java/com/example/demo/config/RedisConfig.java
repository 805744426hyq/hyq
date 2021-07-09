package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 黄永琦
 * @Date 2021-04-28
 */
@Configuration
public class RedisConfig {

	@Bean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();

		template.setConnectionFactory(factory);
		//key序列化方式
		template.setKeySerializer(redisSerializer);
		//value序列化
		template.setValueSerializer(redisSerializer);
		//value hashmap序列化
		template.setHashValueSerializer(redisSerializer);
		//key haspmap序列化
		template.setHashKeySerializer(redisSerializer);

		return template;
	}
}
