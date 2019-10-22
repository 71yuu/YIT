package com.yuu.yit.education.user.service.dao;

import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Platform;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.PlatformExample;
import com.yuu.yit.education.util.base.Page;

public interface PlatformDao {
	int save(Platform record);

	int deleteById(Long id);

	int updateById(Platform record);

	Platform getById(Long id);

	Page<Platform> listForPage(int pageCurrent, int pageSize, PlatformExample example);

	Platform getByClientId(String clientId);

	Platform getByClientName(String clientName);
}