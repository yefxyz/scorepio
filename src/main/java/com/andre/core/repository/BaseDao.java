package com.andre.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	private static ApplicationContext ctx;

	@Autowired
	public void setCtx(ApplicationContext ctx) {
		BaseDao.ctx = ctx;
	}

	public static <T> T test(T data) {
		String prefix = data.getClass().getSimpleName().toLowerCase();
		String redisDaoName = prefix + "RedisDao";
		GenericRedisDao redisDao = (GenericRedisDao) ctx.getBean(redisDaoName);

		System.out.println(redisDao.getClass().getName());
		return null;
	}

}
