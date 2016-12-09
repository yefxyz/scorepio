package com.andre.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.andre.core.model.DataObject;

public class RedisObjectSerializer implements RedisSerializer<DataObject> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	private final static byte[] emptyBytes = new byte[0];

	@Override
	public byte[] serialize(DataObject obj) throws SerializationException {
		if (obj == null) {
			return emptyBytes;
		}
		try {
			return serializer.convert(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
			return emptyBytes;
		}
	}

	@Override
	public DataObject deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		try {
			return (DataObject) deserializer.convert(bytes);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SerializationException("Cannot deserialize", ex);
		}
	}

}
