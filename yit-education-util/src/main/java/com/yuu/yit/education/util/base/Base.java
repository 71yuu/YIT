/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.util.base;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础类
 * 
 * @author Yuu
 */
public class Base {

	/**
	 * 手机号校验正则表达式
	 */
	protected static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";

	/**
	 * 日志记录
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 线程池
	 */
	protected static final ExecutorService callbackExecutor = Executors.newFixedThreadPool(50);

	/**
	 * 冻结状态
	 */
	public final static Integer FREEZE = 3;

	/**
	 * 讲师默认分成
	 */
	public final static BigDecimal LECTURER_DEFAULT_PROPORTION = BigDecimal.valueOf(0.70);

	/**
	 * 获取 map 中 key 对应的值
	 *
	 * @param map map
	 * @param key key
	 * @return
	 */
	public static String getString(Map<String, Object> map, String key) {
		if (null != map.get(key)) {
			return map.get(key).toString();
		}
		return "";
	}

	/**
	 * 创建一个 map
	 *
	 * @return
	 */
	public static Map<String, Object> getMap() {
		return new HashMap<>();
	}

	/**
	 * 记录日志
	 *
	 * @param obj
	 */
	public void log(Object obj) {
		// 相当于 toString 方法
		logger.info(ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE));
	}
}
