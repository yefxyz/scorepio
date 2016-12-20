package com.andre.core.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andre.core.model.DataObject;

/**
 * BaseDao Cache Aside实现。
 * 
 * @author Andre
 */
@Repository(value = "cacheAsideBaseDao")
public class CacheAsideBaseDaoImpl implements BaseDao {

	private static ApplicationContext ctx;

	@Autowired
	public void setCtx(ApplicationContext ctx) {
		CacheAsideBaseDaoImpl.ctx = ctx;
	}

	@Override
	public Logger getLogger() {
		return LogManager.getLogger(getClass());
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return ctx;
	}

	@Transactional
	@Override
	public <T extends DataObject> void save(T data) {
		// 直接存入数据库，成功后清除缓存。

		// System.out.println(redisDao.getClass().getName());
	}

	@Transactional
	@Override
	public <T extends DataObject> T find(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
