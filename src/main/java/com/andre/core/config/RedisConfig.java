package com.andre.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.andre.core.CoreConstants;
import com.andre.core.model.BaseObject;
import com.andre.core.model.User;
import com.andre.core.repository.redis.GsonRedisSerializer;
import com.andre.core.repository.redis.ObjectRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer(CoreConstants.CHARSET_UTF_8);
	}

	@Bean
	ObjectRedisSerializer objectRedisSerializer() {
		return new ObjectRedisSerializer();
	}
	
	@Bean
	<T extends BaseObject> GsonRedisSerializer<T> gsonRedisSerializer() {
		return new GsonRedisSerializer<>();
	}

	@Bean
	public RedisTemplate<String, User> redisUserTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, User> template = new RedisTemplate<String, User>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(stringRedisSerializer());
		template.setValueSerializer(objectRedisSerializer());
		return template;
	}

	@Bean
	public <T extends BaseObject> RedisTemplate<String, T> redisBaseObjectTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, T> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(stringRedisSerializer());
		template.setValueSerializer(gsonRedisSerializer());
		template.setHashKeySerializer(stringRedisSerializer());
		template.setHashValueSerializer(gsonRedisSerializer());
		return template;
	}

}
