package com.andre.core.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andre.core.CoreConstants;
import com.andre.core.model.DataObject;

/**
 * BaseDao Cache Aside实现。
 * 
 * @author Andre
 */
@Repository(value = "cacheAsideBaseDao")
public class CacheAsideBaseDaoImpl implements BaseDao {

	private static ApplicationContext ctx;

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	public void setCtx(ApplicationContext ctx) {
		CacheAsideBaseDaoImpl.ctx = ctx;
	}

	@SuppressWarnings("unchecked")
	public <T extends DataObject> GenericRedisDao<T> getRedisDao(T data) {
		try {
			String prefix = data.getClass().getSimpleName().toLowerCase();
			String redisDaoName = prefix + CoreConstants.REDIS_DAO_POSTFIX;
			return (GenericRedisDao<T>) ctx.getBean(redisDaoName);
		} catch (Exception e) {
			logger.error("Can not find matched redis dao.", e);
		}
		return null;
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
