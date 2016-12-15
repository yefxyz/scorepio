package com.andre.core.repository.redis;

import java.lang.reflect.Type;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.andre.core.CoreConstants;
import com.google.gson.Gson;

public class GsonRedisSerializer<T> implements RedisSerializer<T> {

	private final Type dataType;

	private final Gson gson;

	public GsonRedisSerializer(Type dataType) {
		this.dataType = dataType;
		this.gson = new Gson();
	}

	public GsonRedisSerializer(Type dataType, Gson gson) {
		this.dataType = dataType;
		this.gson = gson;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t != null) {
			String json = gson.toJson(t, t.getClass());
			return json.getBytes(CoreConstants.CHARSET_UTF_8);
		}
		return null;
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes != null) {
			String json = new String(bytes, CoreConstants.CHARSET_UTF_8);
			return gson.fromJson(json, dataType);
		}
		return null;
	}

}
