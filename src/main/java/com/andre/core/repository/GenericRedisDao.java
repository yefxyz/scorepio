package com.andre.core.repository;

import com.andre.core.model.DataObject;

public interface GenericRedisDao<T extends DataObject> {

	void save(T data);

	T find(String key);

}
