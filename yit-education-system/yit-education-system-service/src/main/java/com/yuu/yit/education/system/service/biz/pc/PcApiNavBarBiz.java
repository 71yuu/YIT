package com.yuu.yit.education.system.service.biz.pc;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yuu.yit.education.system.service.common.req.*;
import com.yuu.yit.education.system.service.common.resq.NavBarPageRESQ;
import com.yuu.yit.education.system.service.common.resq.NavBarViewRESQ;
import com.yuu.yit.education.system.service.dao.NavBarDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBar;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBarExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBarExample.Criteria;
import com.yuu.yit.education.util.base.Base;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 头部导航
 *
 */
@Component
public class PcApiNavBarBiz {

	@Autowired
	private NavBarDao dao;

	/**
	 * 分页列表
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<NavBarPageRESQ>> list(NavBarPageREQ req) {
		NavBarExample example = new NavBarExample();
		Criteria c = example.createCriteria();
		// 按照导航状态查询
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		} else {
			c.andStatusIdLessThan(Base.FREEZE);
		}
		// 按照导航标题查询
		if (StringUtils.isNotEmpty(req.getNavTitle())) {
			c.andNavTitleLike(PageUtil.like(req.getNavTitle()));
		}
		// 按照导航地址查询
		if (StringUtils.isNotEmpty(req.getNavUrl())) {
			c.andNavUrlLike(PageUtil.like(req.getNavUrl()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc  ");
		Page<NavBar> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, NavBarPageRESQ.class));
	}

	/**
	 * 保存头部导航
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(NavBarSaveREQ req) {
		if (StringUtils.isEmpty(req.getNavTitle())) {
			return Result.error("导航链接不能为空");
		}
		if (StringUtils.isEmpty(req.getNavUrl())) {
			return Result.error("导航地址不能为空");
		}
		if (StringUtils.isEmpty(req.getTarget())) {
			return Result.error("跳转方式不能为空");
		}
		if (req.getSort() == null || req.getSort() == 0) {
			return Result.error("导航排序值不能为空");
		}
 		NavBar navBar = dao.getByNavUrl(req.getNavUrl());
		if (ObjectUtil.isNotNull(navBar)) {
			return Result.error("已经添加该导航");
		}
		NavBar record = BeanUtil.copyProperties(req, NavBar.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 删除
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(NavBarDeleteREQ req) {
		if (req.getId() == null) {
			return Result.error("");
		}
		NavBar record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到头部导航信息");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
	}

	public Result<Integer> update(NavBarUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("");
		}
		NavBar navBar = dao.getById(req.getId());
		if (ObjectUtil.isNull(navBar)) {
			return Result.error("找不到头部导航信息");
		}
		NavBar record = BeanUtil.copyProperties(req, NavBar.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
	}

	/**
	 * 查找头部导航信息
	 * 
	 * @param req
	 * @return
	 */
	public Result<NavBarViewRESQ> view(NavBarViewREQ req) {
		if (req.getId() == null) {
			return Result.error("");
		}
		NavBar record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到头部导航信息");
		}
		return Result.success(BeanUtil.copyProperties(record, NavBarViewRESQ.class));
	}

}
