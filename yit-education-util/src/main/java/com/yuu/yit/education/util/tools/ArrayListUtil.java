/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.util.tools;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;




/**
 * 队列属性复制
 * 
 * @author Yuu
 * @param <T>
 */
public final class ArrayListUtil<T extends Serializable> {

	private ArrayListUtil() {
	}

	/**
	 * @param source
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <T> List<T> copy(List<?> source, Class<T> clazz)  {
		if (source == null || source.size() == 0) {
			return Collections.emptyList();
		}
		List<T> res = new ArrayList<>(source.size());
		for (Object o : source) {
			T t = null;
			try {
				t = clazz.newInstance();
				BeanUtils.copyProperties(o, t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			res.add(t);
		}
		return res;
	}

}
