package com.andre.core.repository;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.core.CoreConstants;
import com.andre.core.model.DataObject;

/**
 * 基本数据服务接口。
 * 
 * @author Andre
 */
public interface BaseDao {

	/**
	 * 获取Logger。
	 * 
	 * @return
	 */
	Logger getLogger();

	/**
	 * 获取Context。
	 * 
	 * @return
	 */
	ApplicationContext getApplicationContext();

	/**
	 * 获取指定数据对象的RedisDao。
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default <T extends DataObject> GenericRedisDao<T> getRedisDao(T data) {
		return (GenericRedisDao<T>) getRedisDao(data.getClass());
	}
	@SuppressWarnings("unchecked")
	default <T extends DataObject> GenericRedisDao<T> getRedisDao(Class<T> clazz) {
		try {
			String prefix = clazz.getSimpleName().toLowerCase();
			String redisDaoName = prefix + CoreConstants.REDIS_DAO_POSTFIX;
			return (GenericRedisDao<T>) getApplicationContext().getBean(redisDaoName);
		} catch (Exception e) {
			getLogger().error("Can not find matched redis dao.", e);
		}
		return null;
	}

	/**
	 * 获取指定数据对象的JpaRepository。
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default <T extends DataObject> JpaRepository<T, Long> getJPARepository(T data) {
		return (JpaRepository<T, Long>) getJPARepository(data.getClass());
	}
	@SuppressWarnings("unchecked")
	default <T extends DataObject> JpaRepository<T, Long> getJPARepository(Class<T> clazz) {
		try {
			String prefix = clazz.getSimpleName().toLowerCase();
			String jpaRepositoryName = prefix + CoreConstants.JPA_REPOSITORY_POSTFIX;
			return (JpaRepository<T, Long>) getApplicationContext().getBean(jpaRepositoryName);
		} catch (Exception e) {
			getLogger().error("Can not find matched jpa repository.", e);
		}
		return null;
	}

	/**
	 * 保存数据对象。
	 * 
	 * @param data
	 */
	<T extends DataObject> void save(T data);

	/**
	 * 查找获取数据对象。
	 * 
	 * @param clazz
	 * @param key
	 * 
	 * @return
	 */
	<T extends DataObject> T find(Class<T> clazz, String key);
	
	default <T extends DataObject> void delete(T data) {
		delete(data.getClass(), data.getRedisKey());
	}
	<T extends DataObject> void delete(Class<T> clazz, String key);
}
