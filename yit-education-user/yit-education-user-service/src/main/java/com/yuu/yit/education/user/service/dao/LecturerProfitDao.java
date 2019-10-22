package com.yuu.yit.education.user.service.dao;

import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerProfit;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerProfitExample;
import com.yuu.yit.education.util.base.Page;

public interface LecturerProfitDao {
	int save(LecturerProfit record);

	int deleteById(Long id);

	int updateById(LecturerProfit record);

	LecturerProfit getById(Long id);

	Page<LecturerProfit> listForPage(int pageCurrent, int pageSize, LecturerProfitExample example);
}