package com.andre.core.repository;

import com.andre.core.model.DataObject;

public interface GenericRedisDao<T extends DataObject> {

	void saveData(T data);

	T findData(String key);

}
