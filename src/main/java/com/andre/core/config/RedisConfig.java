package com.andre.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.andre.core.CoreConstants;

@Configuration
public class RedisConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setUsePool(true);
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, ?> redisTemplate() {
		RedisTemplate<String, ?> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		// 开启@Transactional注解支持。
		template.setEnableTransactionSupport(true);
		// Key Serializer一律采用String。
		template.setKeySerializer(new StringRedisSerializer(CoreConstants.CHARSET_UTF_8));
		template.setHashKeySerializer(new StringRedisSerializer(CoreConstants.CHARSET_UTF_8));
		return template;
	}

}
