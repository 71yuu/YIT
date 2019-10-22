package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Adv;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.yuu.yit.education.util.base.Page;

import java.util.List;

public interface AdvDao {
	int save(Adv record);

	int deleteById(Long id);

	int updateById(Adv record);

	Adv getById(Long id);

	Page<Adv> listForPage(int pageCurrent, int pageSize, AdvExample example);

	/**
	 * 列出广告信息，注意：开始时间<现在时间<结束时间
	 *
	 * @param platShow
	 * @param statusId
	 * @author Yuu
	 */
	List<Adv> listByPlatShowAndStatusId(Integer platShow, Integer statusId);
}