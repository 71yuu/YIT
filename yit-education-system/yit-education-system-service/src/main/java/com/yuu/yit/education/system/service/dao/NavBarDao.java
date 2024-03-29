package com.yuu.yit.education.system.service.dao;

import java.util.List;

import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBar;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBarExample;
import com.yuu.yit.education.util.base.Page;

public interface NavBarDao {
	int save(NavBar record);

	int deleteById(Long id);

	int updateById(NavBar record);

	NavBar getById(Long id);

	Page<NavBar> listForPage(int pageCurrent, int pageSize, NavBarExample example);

	/**
	 * 根据导航链接查找信息
	 * 
	 * @param navUrl
	 * @return
	 * @author Yuu
	 */
	NavBar getByNavUrl(String navUrl);

	/**
	 * 查找可用的头部导航
	 * 
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	List<NavBar> getByStatusId(Integer statusId);
}