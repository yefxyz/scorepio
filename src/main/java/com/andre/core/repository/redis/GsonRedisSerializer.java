package com.andre.core.repository.redis;

import java.lang.reflect.Type;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.andre.core.CoreConstants;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class GsonRedisSerializer<T> implements RedisSerializer<T> {

	/**
	 * 具体泛型类型。
	 */
	@SuppressWarnings("serial")
	private final Type type = new TypeToken<T>(this.getClass()) {
	}.getType();

	private final Gson gson;

	public GsonRedisSerializer() {
		gson = new Gson();
	}

	public GsonRedisSerializer(Gson gson) {
		this.gson = gson;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t != null) {
			String json = gson.toJson(t, type);
			return json.getBytes(CoreConstants.CHARSET_UTF_8);
		}
		return null;
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes != null) {
			String json = new String(bytes, CoreConstants.CHARSET_UTF_8);
			return gson.fromJson(json, type);
		}
		return null;
	}

}
