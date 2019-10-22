package com.yuu.yit.education.system.service.dao;

import com.yuu.yit.education.system.service.dao.impl.mapper.entity.Website;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.WebsiteExample;
import com.yuu.yit.education.util.base.Page;

public interface WebsiteDao {
	int save(Website record);

	int deleteById(Long id);

	int updateById(Website record);

	Website getById(Long id);

	Page<Website> listForPage(int pageCurrent, int pageSize, WebsiteExample example);

	/**
	 * 获取站点信息
	 *
	 * @author Yuu
	 */
	Website getWebsite();

	/**
	 * 根据状态信息查找站点信息
	 * 
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	Website getByStatusId(Integer statusId);
}