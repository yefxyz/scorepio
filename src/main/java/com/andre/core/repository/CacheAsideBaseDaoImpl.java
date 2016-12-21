package com.andre.core.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
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
		try {
			// 直接存入数据库，成功后清除缓存。
			JpaRepository<T, Long> jpaRepository = getJPARepository(data);
			jpaRepository.save(data);

			GenericRedisDao<T> redisDao = getRedisDao(data);
			redisDao.delete(data.getRedisKey());
		} catch (Exception e) {
			getLogger().error("Save data failed. Exception: " + e);
			throw e;
		}
	}

	@Override
	public <T extends DataObject> T find(Class<T> clazz, String key) {
		try {
			// 缓存若命中则直接返回，否则从数据库读取。
			GenericRedisDao<T> redisDao = getRedisDao(clazz);
			if (redisDao.hasKey(key)) {
				return redisDao.find(key);
			}

			JpaRepository<T, Long> jpaRepository = getJPARepository(clazz);
			T data = jpaRepository.findOne(DataObject.getIdByRedisKey(key));
			// 写入缓存。
			redisDao.save(data);
			return data;
		} catch (Exception e) {
			getLogger().error("Find data failed. Exception: " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public <T extends DataObject> void delete(Class<T> clazz, String key) {
		try {
			// 先删数据库，然后删缓存。
			JpaRepository<T, Long> jpaRepository = getJPARepository(clazz);
			jpaRepository.delete(DataObject.getIdByRedisKey(key));

			GenericRedisDao<T> redisDao = getRedisDao(clazz);
			redisDao.delete(key);
		} catch (Exception e) {
			getLogger().error("Delete data failed. Exception: " + e);
			throw e;
		}

	}

}
