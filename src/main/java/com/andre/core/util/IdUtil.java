package com.andre.core.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID工具类。
 * 
 * @author Andre
 */
public class IdUtil {

	/**
	 * 使用线程安全支持原子增长的AtomicLong，以服务器启动时的系统时间作为序列起点。
	 */
	private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis());
	
	public static long nextID() {
		return sequence.getAndIncrement();
	}
	
}
