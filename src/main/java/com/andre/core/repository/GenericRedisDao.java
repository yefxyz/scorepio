package com.andre.core.repository;

import com.andre.core.model.DataObject;

public interface GenericRedisDao<T extends DataObject> {

	void save(T data);

	T find(String key);

	boolean hasKey(String key);
	
	void delete(String key);
}
