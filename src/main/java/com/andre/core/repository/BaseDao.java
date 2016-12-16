package com.andre.core.repository;

import com.andre.core.model.DataObject;

/**
 * 基本数据服务接口。
 * 
 * @author Andre
 */
public interface BaseDao {

	/**
	 * 保存数据对象。
	 * 
	 * @param data
	 */
	public <T extends DataObject> void save(T data);

	/**
	 * 查找获取数据对象。
	 * 
	 * @param key
	 * @return
	 */
	public <T extends DataObject> T find(String key);

}
