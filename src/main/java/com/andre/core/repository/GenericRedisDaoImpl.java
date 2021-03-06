package com.andre.core.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.andre.core.model.DataObject;
import com.andre.core.repository.redis.GsonRedisSerializer;
import com.google.common.reflect.TypeToken;

public abstract class GenericRedisDaoImpl<T extends DataObject> implements GenericRedisDao<T>, InitializingBean {

	@SuppressWarnings("serial")
	private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
	};

	@Autowired
	private RedisTemplate<String, T> redisTemplate;

	@Override
	public void save(T data) {
		redisTemplate.opsForValue().set(data.getRedisKey(), data);
	}

	@Override
	public T find(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// Value Serializer.
		redisTemplate.setValueSerializer(new GsonRedisSerializer<T>(typeToken.getType()));
		// HashValue Serializer.
		redisTemplate.setHashValueSerializer(new GsonRedisSerializer<T>(typeToken.getType()));
	}

	@Override
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

}
